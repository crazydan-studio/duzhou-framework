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

import { Graph, Cell, Node, Dom } from '@antv/x6';
import { Selection } from '@antv/x6-plugin-selection';
import { Scroller } from '@antv/x6-plugin-scroller';
import { MiniMap } from '@antv/x6-plugin-minimap';
import { Snapline } from '@antv/x6-plugin-snapline';

export { Graph } from '@antv/x6';

interface Options {
  containers: {
    graph: HTMLElement;
    minimap: HTMLElement;
  };
}

export function createGraph(options: Options) {
  const graph = new Graph({
    container: options.containers.graph!,
    autoResize: true,
    grid: {
      visible: true,
      type: 'doubleMesh',
      args: [
        // 主网格线
        {
          color: '#eee',
          thickness: 1
        },
        // 次网格线
        {
          color: '#ddd',
          thickness: 1,
          factor: 4
        }
      ]
    }
  });

  installPlugins(graph, {
    minimapContainer: options.containers.minimap
  });

  return graph;
}

function installPlugins(
  graph: Graph,
  options: { minimapContainer: HTMLElement }
) {
  const plugins = [
    // https://x6.antv.antgroup.com/tutorial/plugins/selection
    new Selection({
      enabled: true,
      multiple: true,
      rubberband: true,
      movable: true,
      showNodeSelectionBox: true,
      showEdgeSelectionBox: true
    }),
    // https://x6.antv.antgroup.com/tutorial/plugins/scroller
    new Scroller({
      enabled: true,
      autoResize: true,
      autoResizeOptions: {
        minHeight: 1000,
        border: 200
      }
    }),
    // https://x6.antv.antgroup.com/tutorial/plugins/minimap
    new MiniMap({
      container: options.minimapContainer,
      width: 200,
      height: 160,
      padding: 10,
      graphOptions: {}
    }),
    // https://x6.antv.antgroup.com/tutorial/plugins/snapline
    new Snapline({
      enabled: true
    })
  ];

  plugins.forEach((p) => graph.use(p));
}
