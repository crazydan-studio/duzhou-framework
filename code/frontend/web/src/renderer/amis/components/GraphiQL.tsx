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
import { unRegisterRenderer, Renderer, RendererProps } from 'amis-core';

// https://www.npmjs.com/package/graphiql
import { GraphiQL } from 'graphiql';
import { createGraphiQLFetcher } from '@graphiql/toolkit';

import 'graphiql/graphiql.css';
import './GraphiQL.scss';

const TYPE = 'graphiql';
unRegisterRenderer(TYPE);

export interface GraphiQLIDEProps extends RendererProps {
  /** GraphQL 端点地址 */
  endpoint: string;
}

@Renderer({
  type: TYPE,
  autoVar: true
})
export default class GraphiQLIDE extends React.Component<GraphiQLIDEProps> {
  constructor(props: GraphiQLIDEProps) {
    super(props);
  }

  render(): React.ReactNode {
    const fetcher = createGraphiQLFetcher({
      url: this.props.endpoint
    });

    return <GraphiQL fetcher={fetcher} />;
  }
}
