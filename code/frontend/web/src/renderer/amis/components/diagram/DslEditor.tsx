/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

import React, { useCallback } from 'react';
import {
  RendererProps,
  Renderer,
  unRegisterRenderer,
  autobind,
  replaceText,
  ScopedContext
} from 'amis-core';
import ReactFlow, {
  ReactFlowProvider,
  MiniMap,
  Controls,
  Background,
  useNodesState,
  useEdgesState,
  useReactFlow
} from 'reactflow';

import { v4 as uuid } from 'uuid';
import { isEmpty } from 'lodash-es';

import { useAutoLayout, collapseTree } from './dsl/useAutoLayout';
import DslNode from './dsl/Node';
import DslNewNode from './dsl/NewNode';
import DslEdge from './dsl/Edge';

import 'reactflow/dist/style.css';
import './dsl/style.scss';

const TYPE = 'dsl-editor';
unRegisterRenderer(TYPE);

const EVENT_NODE_PREFERENCE_SHOW = 'node:preference:show';
const EVENT_NODE_PREFERENCE_CLOSE = 'node:preference:close';

/** DSL 树的布局方向 */
export type LayoutDirection = 'horizontal' | 'vertical';
/** DSL 结构定义 */
export interface DslDef {
  /** 节点定义，用于定义基础结构，方便在子树内通过 `x:extends` 进行扩展或循环定义 */
  'x:define'?: {
    [propName: string]: DslDef;
  };
  /** 派生的目标定义名称 */
  'x:extends'?: string;

  /** 节点类型，对应于 DSL 的标签名称 */
  type: string;
  /** 节点标题 */
  title: string;
  /** 节点副标题 */
  subTitle?: string;
  /** 节点图标 */
  icon: string;
  /** 节点是否必要。必要节点将会自动创建，且不可删除，仅需要调整配置或增减子节点 */
  mandatory?: boolean;
  /** 节点是否可重复。针对列表节点，可不断增加兄弟节点 */
  multiple?: boolean;
  /** 节点配置属性，结构为 `属性名称: '属性类型名称'`，其中，属性类型对应于其编辑器 */
  props: {
    [propName: string]: string;
  };
  /** 子节点的 DSL 结构 */
  children?: DslDef[];
}

export interface EditorProps extends RendererProps {
  /** 是否只读 */
  readonly?: boolean;
  api: {
    /** DSL 数据更新接口 */
    mutation?: string;
    /** DSL 数据获取接口 */
    query: string;
  };
  layout: {
    /** DSL 树的布局方向 */
    direction: LayoutDirection;
  };
  /** DSL 结构定义 */
  xdef: DslDef;
}

@Renderer({
  type: TYPE,
  autoVar: true
})
export default class DslEditor extends React.Component<EditorProps, object> {
  static contextType = ScopedContext;

  constructor(props: EditorProps) {
    super(props);

    this.state = { data: {} };
  }

  componentDidMount() {
    const { env, api } = this.props;

    this.fetchData(env, api.query);
  }

  componentWillUnmount(): void {
    const actions =
      this.props.onEvent?.[EVENT_NODE_PREFERENCE_CLOSE]?.actions || [];

    this.context.doAction(actions);
  }

  render(): React.ReactNode {
    const {
      dispatchEvent,
      layout: { direction },
      xdef
    } = this.props;

    if (isEmpty(this.state.data)) {
      return <ReactFlowProvider></ReactFlowProvider>;
    }

    return (
      // Note：在存在多实例和单页面应用环境中，需通过 ReactFlowProvider
      // 保证实例间的 Store 是各自独立的
      // https://reactflow.dev/examples/misc/provider
      <ReactFlowProvider>
        <ReactFlowEditor
          xdef={xdef}
          data={[this.state.data]}
          direction={direction}
          dispatchEvent={dispatchEvent}
        />
      </ReactFlowProvider>
    );
  }

  async fetchData(env, api: string) {
    let data: any;

    try {
      const json = await env.fetcher({ url: api, method: 'get' });

      data = json || { status: -1, msg: 'No data' };
    } catch (e) {
      data = { status: -1, msg: e.message };
    }

    if (data.status != 0) {
      data = {
        id: 'error',
        icon: 'fa-solid fa-triangle-exclamation',
        title: '拉取数据出现异常',
        subTitle: data.msg
      };
    } else {
      data = replaceText(data.data, env.replaceText, env.replaceTextIgnoreKeys);
    }

    this.setState({ data });
  }
}

const nodeTypes = {
  'dsl-node': DslNode,
  'dsl-new-node': DslNewNode
};
const edgeTypes = {
  'dsl-edge': DslEdge,
  'dsl-new-node-edge': DslEdge
};

const defaultEdgeOptions = {
  type: 'dsl-edge'
};

// https://reactflow.dev/learn
function ReactFlowEditor({ xdef, data, direction, dispatchEvent }) {
  // Note：以 use 开头的函数，都是 React 的 Hooks，只能在 函数组件 中使用
  // https://react.dev/warnings/invalid-hook-call-warning
  // https://react.dev/reference/react/useCallback

  const { getNode, getNodes, setNodes, setEdges, getEdges } = useReactFlow();

  const buildNodes = (
    data,
    initialNodes,
    initialEdges,
    parentNode = {},
    topTypePath: string[] = []
  ) => {
    const source = parentNode.id || '';

    data.forEach((d) => {
      const nodeTypePath = [...topTypePath, d.type];
      const nodeXDef = getXDefByTypePath(nodeTypePath, [xdef]);

      const node = {
        id: `${source}/${d.id}`,
        type: 'dsl-node',
        parent: source || null,
        position: { x: 0, y: 0 },
        data: {
          direction,
          type: d.type,
          title: d.props?.title || nodeXDef.title,
          subTitle: d.props?.subTitle || nodeXDef.subTitle,
          icon: d.props?.icon || nodeXDef.icon,
          mandatory: nodeXDef.mandatory,
          onEvent: {},
          props: d.props || {},
          editor: JSON.stringify({
            type: 'form',
            title: '',
            mode: 'horizontal',
            body: [
              {
                type: 'input-text',
                name: 'var1',
                label: '输入框',
                value: '${node.data.title}'
              },
              {
                type: 'input-color',
                name: 'var2',
                label: '颜色选择',
                value: '#F0F'
              },
              {
                type: 'switch',
                name: 'switch',
                label: '开关',
                option: '开关说明',
                value: true
              }
            ],
            actions: []
          })
        }
      };

      initialNodes.push(node);
      buildNodes(
        d.children || [],
        initialNodes,
        initialEdges,
        node,
        nodeTypePath
      );

      const target = node.id;
      !isEmpty(node.data.props) &&
        (node.data.onEvent.onShowPreference = () => {
          const node = getNode(target);
          dispatchEvent(EVENT_NODE_PREFERENCE_SHOW, {
            node
          });
        });
      !node.data.mandatory &&
        (node.data.onEvent.onRemove = () => {
          alert('Remove');
        });

      if (source) {
        initialEdges.push({
          id: `${source} -> ${target}`,
          type: 'dsl-edge',
          source,
          target
        });
      }
    });
  };

  const initialNodes = [];
  const initialEdges = [];
  buildNodes(data, initialNodes, initialEdges);

  const [nodes, , defaultOnNodesChange] = useNodesState(initialNodes);
  const [edges, ,] = useEdgesState(initialEdges);

  const onNodesChange = useCallback((changes) => {
    const targets = {};
    changes.forEach(({ id, type, selected }) => {
      if (type === 'select') {
        targets[id] = selected;
      }
    });

    if (Object.keys(targets).length > 0) {
      setEdges((edges) =>
        edges.map((e) => ({
          ...e,
          animated: e.type !== 'dsl-new-node-edge' && targets[e.target]
        }))
      );
    }

    defaultOnNodesChange(changes);
  }, []);
  const onNodeClick = useCallback((event, node) => {
    if (node.type === 'dsl-new-node') {
      const { parent, create } = node.data;
      const newNode = create();
      newNode.id = uuid();
      newNode.parent = parent;
      newNode.data.direction = direction;
      newNode.position = { ...node.position };

      const newEdge = {
        id: `e:${parent}->${newNode.id}`,
        source: parent,
        target: newNode.id
      };

      const nodes = getNodes();
      const edges = getEdges();

      const newNodes = [...nodes];
      newNodes.splice(
        nodes.findIndex((n) => n.id === node.id),
        0,
        newNode
      );
      const newEdges = [...edges];
      newEdges.splice(
        edges.findIndex((e) => e.source === parent && e.target === node.id),
        0,
        newEdge
      );

      setNodes(newNodes);
      setEdges(newEdges);
    }
  }, []);
  const onNodeDoubleClick = useCallback(
    (event, node) => {
      if (node.type === 'dsl-node') {
        collapseTree({
          node,
          getNode,
          getNodes,
          setNodes,
          getEdges,
          setEdges
        });
      }
    },
    [setNodes]
  );
  const onPaneClick = useCallback(() => {
    dispatchEvent(EVENT_NODE_PREFERENCE_CLOSE, {});
  }, []);

  const layout = useAutoLayout({ direction });
  layout({ duration: 300 });

  return (
    <ReactFlow
      // https://reactflow.dev/api-reference/react-flow#interaction
      fitView
      panOnScroll
      elementsSelectable
      nodesDraggable={false}
      nodesConnectable={false}
      zoomOnDoubleClick={false}
      nodeTypes={nodeTypes}
      edgeTypes={edgeTypes}
      defaultEdgeOptions={defaultEdgeOptions}
      className="dsl-editor"
      nodes={nodes}
      edges={edges}
      onNodesChange={onNodesChange}
      onPaneClick={onPaneClick}
      onNodeClick={onNodeClick}
      onNodeDoubleClick={onNodeDoubleClick}
    >
      <Controls showInteractive={false} />
      <MiniMap />
      <Background variant="dots" gap={12} size={1} />
    </ReactFlow>
  );
}

function getXDefByTypePath(
  typePath: string[],
  xdefs: DslDef[],
  xdefines: object = {}
): DslDef | null {
  if (isEmpty(typePath) || isEmpty(xdefs)) {
    return null;
  }

  const [type, ...typePathLeft] = typePath;
  for (let xdef of xdefs) {
    const copiedXDef = {
      ...xdef,
      ...(xdefines[xdef['x:extends'] || ''] || {})
    };
    const children = copiedXDef.children || [];
    delete copiedXDef.children;
    delete copiedXDef['x:define'];
    delete copiedXDef['x:extends'];

    if (copiedXDef.type !== type) {
      continue;
    }

    if (isEmpty(typePathLeft)) {
      return copiedXDef;
    } else if (isEmpty(children)) {
      return null;
    }
    return getXDefByTypePath(typePathLeft, children, {
      ...xdefines,
      ...(xdef['x:define'] || {})
    });
  }
  return null;
}
