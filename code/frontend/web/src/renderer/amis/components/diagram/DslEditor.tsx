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
import { RendererProps, Renderer, autobind } from 'amis-core';
import ReactFlow, {
  MiniMap,
  Controls,
  Background,
  useNodesState,
  useEdgesState,
  addEdge
} from 'reactflow';

import 'reactflow/dist/style.css';
import './dsl/style.scss';

const TYPE = 'dsl-editor';

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

// https://reactflow.dev/learn
function ReactFlowEditor() {
  // Note：以 use 开头的函数，都是 React 的 Hooks，只能在 函数组件 中使用
  // https://react.dev/warnings/invalid-hook-call-warning
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

  const onConnect = useCallback(
    (params) => setEdges((eds) => addEdge(params, eds)),
    [setEdges]
  );

  return (
    <ReactFlow
      nodes={nodes}
      edges={edges}
      onNodesChange={onNodesChange}
      onEdgesChange={onEdgesChange}
      onConnect={onConnect}
    >
      <Controls />
      <MiniMap />
      <Background variant="dots" gap={12} size={1} />
    </ReactFlow>
  );
}

const initialNodes = [
  { id: '1', position: { x: 0, y: 0 }, data: { label: '1' } },
  { id: '2', position: { x: 0, y: 100 }, data: { label: '2' } }
];
const initialEdges = [{ id: 'e1-2', source: '1', target: '2' }];
