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
  MiniMap,
  Controls,
  Background,
  ConnectionLineType,
  useNodesState,
  useEdgesState,
  addEdge
} from 'reactflow';
import dagre from 'dagre';

import DslNode from './dsl/Node';

import 'reactflow/dist/style.css';
import './dsl/style.scss';

const TYPE = 'dsl-editor';
unRegisterRenderer(TYPE);

export interface EditorProps extends RendererProps {}

@Renderer({
  type: TYPE,
  autoVar: true
})
export default class DslEditor extends React.Component<EditorProps, object> {
  componentDidMount() {}

  render(): React.ReactNode {
    return <ReactFlowEditor />;
  }
}

const nodeTypes = {
  'dsl-node': DslNode
};

// https://reactflow.dev/learn
function ReactFlowEditor() {
  // Note：以 use 开头的函数，都是 React 的 Hooks，只能在 函数组件 中使用
  // https://react.dev/warnings/invalid-hook-call-warning

  const { nodes: layoutedNodes, edges: layoutedEdges } = getLayoutedElements(
    initialNodes,
    initialEdges,
    'LR'
  );

  const [nodes, setNodes, onNodesChange] = useNodesState(layoutedNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(layoutedEdges);

  const onConnect = useCallback(
    (params) =>
      setEdges((eds) =>
        addEdge({ ...params, type: ConnectionLineType.SmoothStep }, eds)
      ),
    []
  );
  const onLayout = useCallback(
    (direction) => {
      const { nodes: layoutedNodes, edges: layoutedEdges } =
        getLayoutedElements(nodes, edges, direction);

      setNodes([...layoutedNodes]);
      setEdges([...layoutedEdges]);
    },
    [nodes, edges]
  );

  return (
    <ReactFlow
      // https://reactflow.dev/api-reference/react-flow#interaction
      fitView
      panOnScroll
      elementsSelectable
      nodesDraggable={false}
      nodesConnectable={false}
      nodeTypes={nodeTypes}
      nodes={nodes}
      edges={edges}
      onNodesChange={onNodesChange}
      onEdgesChange={onEdgesChange}
      onConnect={onConnect}
      connectionLineType={ConnectionLineType.SmoothStep}
    >
      <Controls />
      <MiniMap />
      <Background variant="dots" gap={12} size={1} />
    </ReactFlow>
  );
}

// https://reactflow.dev/examples/layout/dagre
const dagreGraph = new dagre.graphlib.Graph();
dagreGraph.setDefaultEdgeLabel(() => ({}));

const nodeWidth = 16 * 14;
const nodeHeight = 36;

const getLayoutedElements = (nodes, edges, direction = 'TB') => {
  const isHorizontal = direction === 'LR';
  dagreGraph.setGraph({ rankdir: direction });

  nodes.forEach((node) => {
    dagreGraph.setNode(node.id, { width: nodeWidth, height: nodeHeight });
  });

  edges.forEach((edge) => {
    dagreGraph.setEdge(edge.source, edge.target);
  });

  dagre.layout(dagreGraph);

  nodes.forEach((node) => {
    const nodeWithPosition = dagreGraph.node(node.id);
    node.targetPosition = isHorizontal ? 'left' : 'top';
    node.sourcePosition = isHorizontal ? 'right' : 'bottom';

    // We are shifting the dagre node position (anchor=center center) to the top left
    // so it matches the React Flow node anchor point (top left).
    node.position = {
      x: nodeWithPosition.x - nodeWidth / 2,
      y: nodeWithPosition.y - nodeHeight / 2
    };

    return node;
  });

  return { nodes, edges };
};

const position = { x: 0, y: 0 };
const edgeType = 'smoothstep';

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
    data: { title: '#/auth/permission', icon: 'fa-regular fa-newspaper' },
    position
  },
  {
    id: 'resource:role',
    type: 'dsl-node',
    data: { title: '#/auth/role', icon: 'fa-regular fa-newspaper' },
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
];
const initialEdges = [
  { id: 'e-3', source: 'app', target: 'web', type: edgeType },
  { id: 'e-1', source: 'app', target: 'service:auth', type: edgeType },
  { id: 'e-2', source: 'app', target: 'service:org', type: edgeType },
  {
    id: 'e-4',
    source: 'service:auth',
    target: 'meta:permission',
    type: edgeType
  },
  { id: 'e-5', source: 'service:auth', target: 'meta:role', type: edgeType },
  { id: 'e-6', source: 'service:auth', target: 'meta:account', type: edgeType },
  { id: 'e-7', source: 'service:org', target: 'meta:user', type: edgeType },
  {
    id: 'e-8',
    source: 'service:org',
    target: 'meta:department',
    type: edgeType
  },
  { id: 'e-9', source: 'web', target: 'site:signin', type: edgeType },
  { id: 'e-10', source: 'web', target: 'site:admin', type: edgeType },
  {
    id: 'e-11',
    source: 'site:signin',
    target: 'resource:signin',
    type: edgeType
  },
  { id: 'e-12', source: 'site:admin', target: 'resource:auth', type: edgeType },
  { id: 'e-13', source: 'site:admin', target: 'resource:org', type: edgeType },
  {
    id: 'e-14',
    source: 'resource:auth',
    target: 'resource:permission',
    type: edgeType
  },
  {
    id: 'e-15',
    source: 'resource:auth',
    target: 'resource:role',
    type: edgeType
  }
];
