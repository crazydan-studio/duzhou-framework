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
import { Renderer, RendererProps, isEffectiveApi, replaceText } from 'amis';

const TYPE = 'site';

export interface SiteProps extends RendererProps {
}

/**
 * <pre>
 * { type: 'site',
 *   className: 'site',
 *   schemaApi: 'get:/xxx/xx.json',
 *   schema: { ... },
 *   onReady() { ... }
 * }
 * </pre>
 */
// https://react.dev/reference/react/Component
// https://baidu.github.io/amis/zh-CN/docs/extend/custom-react
class Site extends React.Component<SiteProps, any> {
  constructor(props) {
    super(props);

    const {
      schema = {
        type: 'wrapper',
        className: 'schema-not-specified',
        body: 'Site schema is not specified'
      }
    } = props;

    this.state = { schema };
  }

  componentDidMount() {
    const { onReady } = this.props;

    this.fetchSchema(onReady);
  }

  render() {
    // https://github.com/baidu/amis/blob/master/packages/amis/src/renderers/Wrapper.tsx#L50
    const { render, className, disabled } = this.props;
    const { schema } = this.state;

    return (
      <div className={className}>
        {
          // 第一个参数为渲染容器名称，在同一个组件内具有唯一性
          // https://baidu.github.io/amis/zh-CN/docs/extend/custom-react#react-%E6%B3%A8%E5%86%8C%E8%87%AA%E5%AE%9A%E4%B9%89%E7%B1%BB%E5%9E%8B
          render('body', schema, { disabled })
        }
      </div>
    );
  }

  async fetchSchema(cb) {
    const { env, schemaApi } = this.props;
    const { fetcher } = env;

    if (!isEffectiveApi(schemaApi, {})) {
      cb();
      return;
    }

    let json;
    try {
      json = await fetcher(
        schemaApi,
        {},
        {
          method: 'get'
        }
      );
    } catch (e) {
      json = { ok: false, msg: e.message };
    }

    let schema;
    if (!json.ok) {
      schema = {
        type: 'wrapper',
        className: 'schema-loading-failed',
        body: 'Loading site schema failed'
      };
    } else {
      json.data = replaceText(
        json.data,
        env.replaceText,
        env.replaceTextIgnoreKeys
      );

      schema = json.data;
    }
    this.setState({ schema });

    cb();
  }
}

Renderer({
  type: TYPE,
  // 支持解析当前组件属性中引用的变量
  // https://baidu.github.io/amis/zh-CN/docs/extend/custom-react#%E5%B1%9E%E6%80%A7%E6%94%AF%E6%8C%81%E5%8F%98%E9%87%8F
  autoVar: true
})(Site);
