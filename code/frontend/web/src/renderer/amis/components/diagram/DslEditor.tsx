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
  /** 作为节点唯一标识的属性名称。在直接兄弟节点之间，该属性值需唯一 */
  'x:unique-attr': string | 'type';

  /** 节点类型 */
  type: string;
  /** 是否必须 */
  mandatory?: boolean;
  /** 是否可重复 */
  multiple?: boolean;
  /** 是否为分组 */
  group?: boolean;
  /** 子节点 */
  children?: DslDef[];
  /** 节点配置属性，用于设置属性的默认值，并最终按节点路径与真实节点做合并 */
  props: {
    title?: string;
    desc?: string;
    icon?: string;
    [propName: string]: any;
  };
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
  xdef?: DslDef;
}

@Renderer({
  type: TYPE,
  autoVar: true
})
export default class DslEditor extends React.Component<EditorProps, object> {
  static contextType = ScopedContext;

  constructor(props: EditorProps) {
    super(props);

    this.state = { data: [] };
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
      layout: { direction }
    } = this.props;

    if (this.state.data.length === 0) {
      return <ReactFlowProvider></ReactFlowProvider>;
    }

    return (
      // Note：在存在多实例和单页面应用环境中，需通过 ReactFlowProvider
      // 保证实例间的 Store 是各自独立的
      // https://reactflow.dev/examples/misc/provider
      <ReactFlowProvider>
        <ReactFlowEditor
          direction={direction}
          data={this.state.data}
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
      data = [
        {
          id: 'error',
          icon: 'fa-solid fa-triangle-exclamation',
          title: '拉取数据出现异常',
          subTitle: data.msg
        }
      ];
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
function ReactFlowEditor({ direction, data, dispatchEvent }) {
  // Note：以 use 开头的函数，都是 React 的 Hooks，只能在 函数组件 中使用
  // https://react.dev/warnings/invalid-hook-call-warning
  // https://react.dev/reference/react/useCallback

  const { getNode, getNodes, setNodes, setEdges, getEdges } = useReactFlow();

  const buildNodes = (data, initialNodes, initialEdges, parentNode = {}) => {
    const source = parentNode.id || '';

    data.forEach((d) => {
      const node = {
        id: `${source}/${d.id}`,
        parent: source || null,
        type: 'dsl-node',
        position: { x: 0, y: 0 },
        data: {
          direction,
          type: d.type,
          title: d.title,
          subTitle: d.subTitle,
          icon: d.icon || 'fa-solid fa-circle-question',
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
      buildNodes(d.children || [], initialNodes, initialEdges, node);

      const target = node.id;
      node.data.onShowPreference = () => {
        const node = getNode(target);
        dispatchEvent(EVENT_NODE_PREFERENCE_SHOW, {
          node
        });
      };
      // node.data.onRemove = () => {
      //   alert('Remove');
      // };

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
