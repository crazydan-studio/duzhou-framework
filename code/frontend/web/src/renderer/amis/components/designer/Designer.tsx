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

import React from 'react';
import { RendererProps, Renderer, autobind } from 'amis-core';

import { Graph, createGraph } from './graph';

import './style.scss';

const TYPE = 'designer';

export interface DesignerProps extends RendererProps {}

@Renderer({
  type: TYPE,
  autoVar: true
})
export default class Designer extends React.Component<DesignerProps, object> {
  private graph: Graph;

  private graphDom: HTMLDivElement;
  graphDomRef = (dom: HTMLDivElement) => {
    this.graphDom = dom;
  };
  private minimapDom: HTMLDivElement;
  minimapDomRef = (dom: HTMLDivElement) => {
    this.minimapDom = dom;
  };

  componentDidMount() {
    if (this.graph) {
      this.graph.dispose();
    }

    this.graph = createGraph({
      containers: {
        graph: this.graphDom,
        minimap: this.minimapDom
      }
    });

    this.graph.fromJSON({ nodes: Object.keys(data).map((key) => data[key]) });
    this.graph.centerContent();
  }

  render(): React.ReactNode {
    return (
      <div className="designer">
        <div className="toolbar"></div>
        <div className="graph">
          <div className="content">
            <div ref={this.graphDomRef}></div>
          </div>
          <div className="minimap" ref={this.minimapDomRef}></div>
        </div>
      </div>
    );
  }
}

const data = {
  hello: {
    id: 'hello',
    x: 32,
    y: 32,
    width: 100,
    height: 40,
    label: 'Hello',
    attrs: {
      body: {
        stroke: '#8f8f8f',
        strokeWidth: 1,
        fill: '#fff',
        rx: 6,
        ry: 6
      }
    }
  },
  world: {
    id: 'world',
    shape: 'circle',
    x: 160,
    y: 180,
    width: 60,
    height: 60,
    label: 'World',
    attrs: {
      body: {
        stroke: '#8f8f8f',
        strokeWidth: 1,
        fill: '#fff'
      }
    }
  },
  rect: {
    id: 'rect',
    x: -60,
    y: 100,
    width: 100,
    height: 40,
    label: 'Rect',
    attrs: {
      body: {
        stroke: '#8f8f8f',
        strokeWidth: 1,
        fill: '#fff',
        rx: 6,
        ry: 6
      }
    }
  }
};
