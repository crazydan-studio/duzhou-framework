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

import { useEffect, useRef } from 'react';
import { useStore, useReactFlow, useNodesInitialized } from 'reactflow';

import { stratify } from 'd3-hierarchy';
import { flextree } from 'd3-flextree';
import { timer } from 'd3-timer';

export function collapseTree({
  node,
  getNode,
  getNodes,
  setNodes,
  getEdges,
  setEdges
}) {
  const collapsed = !!!node._collapsed;

  const links = {};
  const edges = getEdges();
  edges.forEach(({ source, target }) => {
    (links[source] ||= []).push(target);
  });

  const targets = {};
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

export function useAutoLayout({ direction, setNodes, setEdges }) {
  //https://codesandbox.io/p/sandbox/reactflow-demo-8h7hsx?file=%2Fsrc%2Fhooks%2FuseAutoLayout.js%3A97%2C36

  const layout = flextree()
    // 节点的占位空间（在水平和垂直布局方向发生切换时，需交换数组元素位置）
    .nodeSize((node) => {
      const w = node.data.width * 1.5;
      const h = node.data.height * 1.8;
      return direction === 'vertical' ? [w, node.data.height * 2] : [h, w];
    })
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
        positions[d.id] =
          direction === 'vertical' ? { x: d.x, y: d.y } : { x: d.y, y: d.x };
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
            // Note：只有放在 data 中的变更数据才会传递到 Node 的构造函数中
            data: { ...node.data, collapsed: node._collapsed },
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

  const { getNode, getNodes, getEdges, fitView } = useReactFlow();

  function Layout({ duration }) {
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

    useEffect(() => {
      if (nodeCount < 1 || !nodesInitialized) {
        return () => {};
      }

      // 检查节点是否在展开树中
      const isInCollapseTree = (node, rootId, level = 0) =>
        rootId && node && !(level > 0 && node._collapsed) && node.parent
          ? node.parent === rootId ||
            isInCollapseTree(getNode(node.parent), rootId, level + 1)
          : false;

      const edges = getEdges();
      const nodes = getNodes();
      const transitions = layoutNodes(nodes, isInCollapseTree);
      // 为待移动节点设置初始位置
      setNodes(
        transitions.map(({ node, from }) => ({
          ...node,
          position: from
        }))
      );

      const t = timer((elapsed) => {
        if (elapsed < duration) {
          const s = elapsed / duration;

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
}
