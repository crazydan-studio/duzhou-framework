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

import React, { useCallback, useEffect, useRef } from 'react';
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
  useStore,
  useReactFlow,
  useNodesInitialized
} from 'reactflow';

import { v4 as uuid } from 'uuid';
import { stratify } from 'd3-hierarchy';
import { flextree } from 'd3-flextree';
import { timer } from 'd3-timer';

import DslNode from './dsl/Node';
import DslNewNode from './dsl/NewNode';
import DslEdge from './dsl/Edge';

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
    return (
      // Note：在存在多实例和单页面应用环境中，需通过 ReactFlowProvider
      // 保证实例间的 Store 是各自独立的
      // https://reactflow.dev/examples/misc/provider
      <ReactFlowProvider>
        <ReactFlowEditor />
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
function ReactFlowEditor() {
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
      const targets = {};
      const collapsed = !!!node._collapsed;

      const links = {};
      const edges = getEdges();
      edges.forEach(({ source, target }) => {
        (links[source] ||= []).push(target);
      });

      const travel = (ids) => {
        (ids || []).forEach((id) => {
          const n = getNode(id);
          targets[id] = {
            ...n,
            // 已隐藏的，先保持隐藏状态，
            // 以避免动画移动出现闪跳（移动的初始位置需要在重新布局后才能确定）
            // hidden: n.hidden,
            _hidden: collapsed
          };

          const subIds = links[id];
          delete links[id];

          // 忽略已收缩的树，确保其收缩状态保持不变
          if (!n._collapsed) {
            travel(subIds);
          }
        });
      };
      travel(links[node.id]);

      setEdges(
        edges.map((e) =>
          targets[e.target]
            ? {
                ...e,
                // 保持展开树中的连线隐藏状态，仅在开始发生位置变化时才显示，
                // 以避免目标节点位置未确定而出现连线闪跳
                _hidden: collapsed,
                _collapsed: true
              }
            : e
        )
      );

      const nodes = getNodes();
      setNodes(
        nodes.map(
          (n) =>
            targets[n.id] ||
            (n.id === node.id ? { ...n, _collapsed: collapsed } : n)
        )
      );
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

const useAutoLayout = () => {
  //https://codesandbox.io/p/sandbox/reactflow-demo-8h7hsx?file=%2Fsrc%2Fhooks%2FuseAutoLayout.js%3A97%2C36

  const layout = flextree()
    // 节点的占位空间（在水平和垂直布局方向发生切换时，需交换数组元素位置）
    .nodeSize((node) => [node.data.height * 1.5, node.data.width * 1.5])
    .spacing(() => 1);

  function layoutNodes(nodes, isInCollapseTree) {
    // stratify() 用于将扁平数据转换为树形结构
    const tree = stratify()
      .id((d) => d.id)
      .parentId((d) => d.parent)(
      // 仅对不需要隐藏的节点进行布局
      nodes.filter((n) => !n._hidden)
    );

    const positions = {};
    layout(tree)
      .descendants()
      .forEach((d) => {
        // 交换 tree 布局的 x/y 坐标，以将垂直方向布局转换为水平方向布局
        positions[d.id] = { x: d.y, y: d.x };
      });

    const collapseRoot = nodes.find((n) => n.selected);
    const collapseRootPos = positions[collapseRoot?.id];
    let isCollapseNode = false;

    return nodes.map(
      (node) => (
        (isCollapseNode = isInCollapseTree(node, collapseRoot?.id)),
        {
          id: node.id,
          node: {
            ...node,
            // 若不在展开树中，则保持隐藏状态，否则，始终不隐藏
            hidden: node.hidden && !isCollapseNode
          },
          // 若节点需显示且其在展开书中，则从展开树的根节点的位置移动节点
          from:
            !node._hidden && isCollapseNode
              ? collapseRoot.position
              : node.position,
          // 需显示的节点采用布局后的坐标，待隐藏的节点采用待收缩树的根节点坐标，余下节点的坐标不变
          to:
            positions[node.id] ||
            (node._hidden ? collapseRootPos : node.position)
        }
      )
    );
  }

  function Layout(options) {
    const initial = useRef(false);

    const nodesInitialized = useNodesInitialized();
    // 在节点待隐藏、节点增删时，均触发重新布局
    const nodeCount = useStore(({ nodeInternals }) => {
      let count = 0;
      nodeInternals.forEach((node) => {
        if (!node._hidden) {
          count += 1;
        }
      });

      return count;
    });

    const { getNodes, getNode, setNodes, getEdges, setEdges, fitView } =
      useReactFlow();

    useEffect(() => {
      if (nodeCount < 1 || !nodesInitialized) {
        return () => {};
      }

      const nodes = getNodes();
      const edges = getEdges();

      // 检查节点是否在展开树中
      const isInCollapseTree = (node, rootId, level = 0) =>
        rootId && node && !(level > 0 && node._collapsed) && node.parent
          ? node.parent === rootId ||
            isInCollapseTree(getNode(node.parent), rootId, level + 1)
          : false;

      const transitions = layoutNodes(nodes, isInCollapseTree);
      // 为待移动节点设置初始位置
      setNodes(
        transitions.map(({ node, from }) => ({
          ...node,
          position: from
        }))
      );

      const t = timer((elapsed) => {
        if (elapsed < options.duration) {
          const s = elapsed / options.duration;

          setNodes(
            transitions.map(({ node, from, to }) => ({
              ...node,
              position: {
                x: from.x + (to.x - from.x) * s,
                y: from.y + (to.y - from.y) * s
              }
            }))
          );

          setEdges(
            edges.map((e) => (e._collapsed ? { ...e, hidden: false } : e))
          );
        } else {
          t.stop();

          setNodes(
            transitions.map(({ node, to }) => ({
              ...node,
              // 在动画结束后隐藏节点
              hidden: node._hidden,
              position: { ...to }
            }))
          );

          setEdges(
            edges.map((e) => ({
              ...e,
              hidden: e._hidden,
              _collapsed: false
            }))
          );

          if (!initial.current) {
            initial.current = true;
            fitView({ duration: 200, padding: 0.2 });
          }
        }
      });

      return () => t.stop();
    }, [nodeCount, nodesInitialized]);
  }
  return Layout;
};

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
