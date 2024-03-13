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
  autobind
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
  xdef: DslDef;
}

@Renderer({
  type: TYPE,
  autoVar: true
})
export default class DslEditor extends React.Component<EditorProps, object> {
  componentDidMount() {}

  render(): React.ReactNode {
    return (
      // Note：在存在多实例和单页面应用环境中，需通过 ReactFlowProvider
      // 保证实例间的 Store 是各自独立的
      // https://reactflow.dev/examples/misc/provider
      <ReactFlowProvider>
        <ReactFlowEditor
          initialNodes={initialNodes}
          initialEdges={initialEdges}
        />
      </ReactFlowProvider>
    );
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
function ReactFlowEditor({ initialNodes, initialEdges }) {
  // Note：以 use 开头的函数，都是 React 的 Hooks，只能在 函数组件 中使用
  // https://react.dev/warnings/invalid-hook-call-warning
  // https://react.dev/reference/react/useCallback

  const [nodes, , defaultOnNodesChange] = useNodesState(initialNodes);
  const [edges, , onEdgesChange] = useEdgesState(initialEdges);

  const { getNode, getNodes, setNodes, setEdges, getEdges } = useReactFlow();

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
  const onNodeDoubleClick = useCallback((event, node) => {
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
  }, []);

  const layout = useAutoLayout();
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
      onNodeClick={onNodeClick}
      onNodeDoubleClick={onNodeDoubleClick}
      onNodesChange={onNodesChange}
      onEdgesChange={onEdgesChange}
    >
      <Controls showInteractive={false} />
      <MiniMap />
      <Background variant="dots" gap={12} size={1} />
    </ReactFlow>
  );
}

const position = { x: 0, y: 0 };
const newNodeProps = {
  type: 'dsl-new-node',
  position
};
const newNodeEdgeProps = {
  type: 'dsl-new-node-edge',
  className: 'new-node-edge'
};

const initialNodes = [
  {
    id: 'app',
    type: 'dsl-node',
    data: { title: '部门管理系统', icon: 'fa-solid fa-globe' },
    position
  },
  {
    id: 'web',
    type: 'dsl-node',
    data: { title: 'Web 前端', icon: 'fa-solid fa-computer' },
    position
  },
  {
    id: 'site:signin',
    type: 'dsl-node',
    data: { title: '/signin/', icon: 'fa-solid fa-table-columns' },
    position
  },
  {
    id: 'resource:signin',
    type: 'dsl-node',
    data: { title: '#/signin', icon: 'fa-regular fa-newspaper' },
    position
  },
  {
    id: 'site:admin',
    type: 'dsl-node',
    data: { title: '/admin/', icon: 'fa-solid fa-table-columns' },
    position
  },
  {
    id: 'resource:auth',
    type: 'dsl-node',
    data: { title: '#/auth', icon: 'fa-regular fa-newspaper' },
    position
  },
  {
    id: 'resource:permission',
    type: 'dsl-node',
    data: { title: '#/permission', icon: 'fa-regular fa-newspaper' },
    position
  },
  {
    id: 'resource:role',
    type: 'dsl-node',
    data: { title: '#/role', icon: 'fa-regular fa-newspaper' },
    position
  },
  {
    id: 'resource:org',
    type: 'dsl-node',
    data: { title: '#/org', icon: 'fa-regular fa-newspaper' },
    position
  },
  {
    id: 'service:auth',
    type: 'dsl-node',
    data: { title: '用户认证', icon: 'fa-brands fa-docker' },
    position
  },
  {
    id: 'service:org',
    type: 'dsl-node',
    data: { title: '组织机构', icon: 'fa-brands fa-docker' },
    position
  },
  {
    id: 'meta:permission',
    type: 'dsl-node',
    data: { title: 'Permission', icon: 'fa-solid fa-cube' },
    position
  },
  {
    id: 'meta:role',
    type: 'dsl-node',
    data: { title: 'Role', icon: 'fa-solid fa-cube' },
    position
  },
  {
    id: 'meta:account',
    type: 'dsl-node',
    data: { title: 'Account', icon: 'fa-solid fa-cube' },
    position
  },
  {
    id: 'meta:user',
    type: 'dsl-node',
    data: { title: 'User', icon: 'fa-solid fa-cube' },
    position
  },
  {
    id: 'meta:department',
    type: 'dsl-node',
    data: { title: 'Department', icon: 'fa-solid fa-cube' },
    position
  }
].concat([
  {
    id: 'service:new',
    ...newNodeProps,
    data: {
      parent: 'app',
      create: () => ({
        type: 'dsl-node',
        data: { title: '未命名', icon: 'fa-brands fa-docker' },
        position
      })
    }
  },
  {
    id: 'service:auth:meta:new',
    ...newNodeProps,
    data: {
      parent: 'service:auth',
      create: () => ({
        type: 'dsl-node',
        data: { title: '未命名', icon: 'fa-solid fa-cube' },
        position
      })
    }
  },
  {
    id: 'service:org:meta:new',
    ...newNodeProps,
    data: {
      parent: 'service:org',
      create: () => ({
        type: 'dsl-node',
        data: { title: '未命名', icon: 'fa-solid fa-cube' },
        position
      })
    }
  }
]);
const initialEdges = [
  { id: 'e-3', source: 'app', target: 'web' },
  { id: 'e-1', source: 'app', target: 'service:auth' },
  { id: 'e-2', source: 'app', target: 'service:org' },
  {
    id: 'e-4',
    source: 'service:auth',
    target: 'meta:permission'
  },
  { id: 'e-5', source: 'service:auth', target: 'meta:role' },
  { id: 'e-6', source: 'service:auth', target: 'meta:account' },
  { id: 'e-7', source: 'service:org', target: 'meta:user' },
  {
    id: 'e-8',
    source: 'service:org',
    target: 'meta:department'
  },
  { id: 'e-9', source: 'web', target: 'site:signin' },
  { id: 'e-10', source: 'web', target: 'site:admin' },
  {
    id: 'e-11',
    source: 'site:signin',
    target: 'resource:signin'
  },
  { id: 'e-12', source: 'site:admin', target: 'resource:auth' },
  { id: 'e-13', source: 'site:admin', target: 'resource:org' },
  {
    id: 'e-14',
    source: 'resource:auth',
    target: 'resource:permission'
  },
  {
    id: 'e-15',
    source: 'resource:auth',
    target: 'resource:role'
  }
].concat([
  {
    id: 'e-service:new',
    source: 'app',
    target: 'service:new',
    ...newNodeEdgeProps
  },
  {
    id: 'e-service:auth:meta:new',
    source: 'service:auth',
    target: 'service:auth:meta:new',
    ...newNodeEdgeProps
  },
  {
    id: 'e-service:org:meta:new',
    source: 'service:org',
    target: 'service:org:meta:new',
    ...newNodeEdgeProps
  }
]);

initialNodes.forEach((node) => {
  const edge = initialEdges.find((edge) => edge.target === node.id);
  edge && (node.parent = edge.source);
});
