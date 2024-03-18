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

const TYPE_NODE_DEFAULT = 'dsl-node';
const TYPE_NODE_NEW = 'dsl-new-node';
const TYPE_EDGE_DEFAULT = 'dsl-edge';
const TYPE_EDGE_NEW_NODE = 'dsl-new-node-edge';
const EVENT_NODE_EDITOR_SHOW = 'node:editor:show';
const EVENT_NODE_EDITOR_CLOSE = 'node:editor:close';

const PROP_X_DEFINE = 'x:define';
const PROP_X_EXTENDS = 'x:extends';

const nodeTypes = {
  [TYPE_NODE_DEFAULT]: DslNode,
  [TYPE_NODE_NEW]: DslNewNode
};
const edgeTypes = {
  [TYPE_EDGE_DEFAULT]: DslEdge,
  [TYPE_EDGE_NEW_NODE]: DslEdge
};

const defaultEdgeOptions = {
  type: TYPE_EDGE_DEFAULT
};

/** DSL 树的布局方向 */
export type LayoutDirection = 'horizontal' | 'vertical';
/** DSL 结构定义 */
export interface DslDef {
  /** 节点定义，用于定义基础结构，方便在子树内通过 `x:extends` 进行扩展或循环定义 */
  [PROP_X_DEFINE]?: {
    [propName: string]: DslDef;
  };
  /** 派生的目标定义名称 */
  [PROP_X_EXTENDS]?: string;

  /** 节点类型，对应于 DSL 的标签名称 */
  type: string;
  /** 节点是否必要。必要节点将会自动创建，且不可删除，仅需要调整配置或增减子节点 */
  mandatory?: boolean;
  /** 节点是否可重复。针对列表节点，可不断增加兄弟节点 */
  multiple?: boolean;
  /** 节点配置属性，结构为 `属性名称: '属性默认值'` */
  props: {
    /** 节点标题 */
    title: string;
    /** 节点副标题 */
    subTitle: string;
    /** 节点图标 */
    icon: string;
    [propName: string]: any;
  };
  /**
   * 节点编辑器，其为 AMIS Form 表单的 body 部分，
   * 可以通过 group 类型对表单项分组布局
   */
  editor?: object;
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
  xdefGetter: any;

  constructor(props: EditorProps) {
    super(props);

    this.state = { data: null };
    this.xdefGetter = buildXDefGetter(props.xdef);
  }

  componentDidMount() {
    const { env, api } = this.props;

    this.fetchData(env, api.query);
  }

  componentWillUnmount(): void {
    // 通过 AMIS ScopedContext 执行绑定的 配置窗口关闭 事件的动作
    const actions =
      this.props.onEvent?.[EVENT_NODE_EDITOR_CLOSE]?.actions || [];

    this.context.doAction(actions);
  }

  render(): React.ReactNode {
    const {
      readonly,
      dispatchEvent,
      layout: { direction }
    } = this.props;

    if (!this.state.data) {
      return <ReactFlowProvider></ReactFlowProvider>;
    }

    return (
      // Note：在存在多实例和单页面应用环境中，需通过 ReactFlowProvider
      // 保证实例间的 Store 是各自独立的
      // https://reactflow.dev/examples/misc/provider
      <ReactFlowProvider>
        <ReactFlowEditor
          readonly={readonly}
          xdefGetter={this.xdefGetter}
          data={this.state.data}
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

// https://reactflow.dev/learn
function ReactFlowEditor({
  readonly,
  xdefGetter,
  data,
  direction,
  dispatchEvent
}) {
  // Note：以 use 开头的函数，都是 React 的 Hooks，只能在 函数组件 中使用
  // https://react.dev/warnings/invalid-hook-call-warning
  // https://react.dev/reference/react/useCallback

  const { getNode, getNodes, getEdges } = useReactFlow();

  const { nodes: initialNodes, edges: initialEdges } = buildNodes(
    data,
    xdefGetter,
    {
      direction,
      dispatchEvent,
      getNode,
      readonly,
      onCollapse: (node) => {
        collapseTree({
          node,
          getNode,
          getNodes,
          setNodes,
          getEdges,
          setEdges
        });
      }
    }
  );

  // Note：不能使用 useReactFlow 中的 setEdges，其无法更新新增连线
  const [nodes, setNodes, defaultOnNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges] = useEdgesState(initialEdges);

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
      const { nodes: newNodes, edges: newEdges } = node.data.create();

      const nodes = getNodes();
      const edges = getEdges();

      nodes.splice(
        nodes.findIndex((n) => n.id === node.id),
        0,
        ...newNodes.map((n) => ({ ...n, position: { ...node.position } }))
      );

      setNodes(nodes);
      setEdges(edges.concat(newEdges));
    }
  }, []);
  const onPaneClick = useCallback(() => {
    dispatchEvent(EVENT_NODE_EDITOR_CLOSE, {});
  }, []);

  const layout = useAutoLayout({ direction, setNodes, setEdges });
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
    >
      <Controls showInteractive={false} />
      <MiniMap />
      <Background variant="dots" gap={12} size={1} />
    </ReactFlow>
  );
}

function buildNodes(
  nodeData,
  nodeXDefGetter,
  opts,
  nodeParent = {}
): { nodes: object[]; edges: object[] } {
  const { direction, getNode, dispatchEvent, readonly, onCollapse } = opts;
  const nodeXDef = nodeXDefGetter ? nodeXDefGetter() : null;

  let nodes: object[] = [];
  let edges: object[] = [];
  // 节点结构不匹配
  if (
    !nodeXDef ||
    !nodeXDef.type ||
    (nodeData.type && nodeData.type !== nodeXDef.type)
  ) {
    return { nodes, edges };
  }

  const createNode = (data, xdef, nodeId, nodeParentId): object => {
    const node = {
      id: nodeId,
      type: TYPE_NODE_DEFAULT,
      parent: nodeParentId || null,
      position: { x: 0, y: 0 },
      data: {
        type: xdef.type,
        editor: isEmpty(xdef.editor) ? '' : JSON.stringify(xdef.editor),
        direction,
        onEvent: {},
        //
        props: {
          ...(xdef.props || {}),
          ...(data.props || {})
        }
      }
    };

    // 只读状态下，仅数据含子节点时，才支持展开/收起
    ((!readonly && !isEmpty(xdef.children())) ||
      (readonly && !isEmpty(data.children))) &&
      (node.data.onEvent.onCollapse = () => onCollapse(getNode(nodeId)));

    !isEmpty(xdef.editor) &&
      (node.data.onEvent.onShowEditor = () => {
        const node = getNode(nodeId);
        dispatchEvent(EVENT_NODE_EDITOR_SHOW, {
          node: {
            editor: node.data.editor,
            props: { ...node.data.props }
          }
        });
      });
    !readonly &&
      !xdef.mandatory &&
      (node.data.onEvent.onRemove = () => {
        alert('Remove');
      });

    return node;
  };

  const nodeParentId = nodeParent.id || '';
  const nodeId = `${nodeParentId}/${nodeData.id || nodeXDef.type}`;
  const node = createNode(nodeData, nodeXDef, nodeId, nodeParentId);

  nodes.push(node);
  nodeParentId &&
    edges.push({
      id: `${nodeParentId} -> ${nodeId}`,
      type: TYPE_EDGE_DEFAULT,
      source: nodeParentId,
      target: nodeId
    });

  const childNodeXDefGetters = nodeXDef.children();
  const childrenBuildResult: { nodes: object[]; edges: object[] } = {
    nodes: [],
    edges: []
  };
  (nodeData.children || []).forEach((childNodeData) => {
    const result = buildNodes(
      childNodeData,
      childNodeXDefGetters[childNodeData.type],
      opts,
      node
    );

    !isEmpty(result.nodes) &&
      (childrenBuildResult.nodes = [
        ...childrenBuildResult.nodes,
        ...result.nodes
      ]);
    !isEmpty(result.edges) &&
      (childrenBuildResult.edges = [
        ...childrenBuildResult.edges,
        ...result.edges
      ]);
  });

  const createNewNode = (xdef, nodeParentId): object => ({
    id: `${nodeParentId}/new/${xdef.type}`,
    type: TYPE_NODE_NEW,
    parent: nodeParentId || null,
    className: 'new-node',
    position: { x: 0, y: 0 },
    data: {
      type: xdef.type,
      direction,
      create: () =>
        buildNodes({ id: uuid() }, () => xdef, opts, { id: nodeParentId }),
      //
      props: { ...xdef.props } || {}
    }
  });

  Object.keys(childNodeXDefGetters).forEach((type) => {
    const childNodeXDefGetter = childNodeXDefGetters[type];
    const childNodeXDef = childNodeXDefGetter();

    let childNodeIndex = -1;
    childrenBuildResult.nodes.forEach((childNode, index) => {
      if (childNode.data.type === type) {
        childNodeIndex = index;
      }
    });

    let childNodes: object[] = [];
    let childEdges: object[] = [];
    // 添加必要子节点
    if (childNodeXDef.mandatory && childNodeIndex < 0) {
      const { nodes, edges } = buildNodes(
        { id: uuid() },
        () => childNodeXDef,
        opts,
        { id: nodeId }
      );

      childNodes = nodes;
      childEdges = edges;
    }
    // 子节点的新增占位
    else if (!readonly && childNodeXDef.multiple) {
      childNodeIndex =
        childNodeIndex < 0
          ? childrenBuildResult.nodes.length || -1
          : childNodeIndex;

      const newChildNode = createNewNode(childNodeXDef, nodeId);
      childNodes = [newChildNode];
      childEdges = [
        {
          id: `${newChildNode.parent} -> ${newChildNode.id}`,
          type:
            newChildNode.type === TYPE_NODE_NEW
              ? TYPE_EDGE_NEW_NODE
              : TYPE_EDGE_DEFAULT,
          className: newChildNode.type === TYPE_NODE_NEW ? 'new-node-edge' : '',
          source: newChildNode.parent,
          target: newChildNode.id
        }
      ];
    }

    if (!isEmpty(childNodes)) {
      childrenBuildResult.nodes.splice(childNodeIndex + 1, 0, ...childNodes);
      childrenBuildResult.edges = childrenBuildResult.edges.concat(childEdges);
    }
  });

  !isEmpty(childrenBuildResult.nodes) &&
    (nodes = [...nodes, ...childrenBuildResult.nodes]);
  !isEmpty(childrenBuildResult.edges) &&
    (edges = [...edges, ...childrenBuildResult.edges]);

  return { nodes, edges };
}

function buildXDefGetter(xdef: DslDef) {
  // 通过对函数参数进行解构，以实现克隆时对无关属性的过滤
  // https://www.codemzy.com/blog/copying-object-without-property-javascript
  const build = (xdef: DslDef, topXDefines: object = {}) => {
    const xdefines = xdef[PROP_X_DEFINE]
      ? { ...topXDefines, ...xdef[PROP_X_DEFINE] }
      : topXDefines;
    const xextends = xdef[PROP_X_EXTENDS] ? xdefines[xdef[PROP_X_EXTENDS]] : {};

    const clonedXDef = {
      ...xextends,
      // 内部定义优先于基础定义
      ...xdef,
      props: {
        ...(xextends.props || {}),
        ...(xdef.props || {})
      },
      // 子节点通过函数做延迟加载，以避免出现 x:extends 的无限递归
      children: () => {
        const map = {};

        const children = xdef.children || xextends.children || [];
        children.forEach((child) => {
          const newChild = build(child, xdefines)();
          // Note: type 可能定义在 x:extends 中
          map[newChild.type] = () => newChild;
        });

        return map;
      }
    };
    delete clonedXDef[PROP_X_DEFINE];
    delete clonedXDef[PROP_X_EXTENDS];

    return () => clonedXDef;
  };

  return build(xdef);
}
