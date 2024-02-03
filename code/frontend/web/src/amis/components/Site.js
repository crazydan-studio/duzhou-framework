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

import { amisLib, React } from '@/amis/sdk';

/**
 * <pre>
 * { type: 'site',
 *   className: 'site',
 *   schemaApi: 'get:/xxx/xx.json',
 *   schema: { ... }
 * }
 * </pre>
 */
// https://react.dev/reference/react/Component
class Site extends React.Component {
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
    this.fetchSchema(() => document.body.classList.add('done'));
  }

  render() {
    // https://github.com/baidu/amis/blob/master/packages/amis/src/renderers/Wrapper.tsx#L50
    const { render, className, disabled } = this.props;
    const { schema } = this.state;

    return React.createElement(
      'div',
      {
        className
      },
      render('body', schema, { disabled })
    );
  }

  async fetchSchema(cb) {
    const { env, schemaApi } = this.props;
    const { fetcher } = env;

    if (!amisLib.isEffectiveApi(schemaApi, {})) {
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
      json.data = amisLib.replaceText(
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

amisLib.Renderer({
  test: /(^|\/)site/
})(Site);
