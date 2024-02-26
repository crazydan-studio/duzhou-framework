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
import {
  render as renderAmis,
  setDefaultLocale,
  isEffectiveApi,
  replaceText,
  ToastComponent,
  AlertComponent
} from 'amis';
import { RootRenderProps } from 'amis-core/lib/Root';

import { useAdapter, FetcherResult } from '@/sdk/nop-core';

import { createEnv } from './env';

export interface SiteProps {
  theme: string;
  location?: any;
  schema?: object;
  // get:/xxx/xx.json
  schemaApi?: string;
  // 就绪后的响应函数
  onReady: () => any;
}

const env = createEnv();

export default class Site extends React.Component<SiteProps, any> {
  constructor(props: SiteProps) {
    super(props);

    const { schema } = props;

    this.state = { schema };
  }

  componentDidMount() {
    const { onReady } = this.props;

    this.fetchSchema(onReady);
  }

  render() {
    const { schema } = this.state;
    const { theme } = this.props;

    if (schema) {
      return (
        <>
          <ToastComponent
            position={(env && env.toastPosition) || 'top-center'}
            closeButton={false}
            timeout={5000}
            locale={env?.locale}
            theme={theme}
          />
          <AlertComponent
            locale={env?.locale}
            theme={theme}
            container={() => env?.getModalContainer?.() || 'body'}
          />

          {this.doRender()}
        </>
      );
    }
    return <></>;
  }

  doRender() {
    let amisScoped: any;

    const { schema } = this.state;
    const { theme, location } = this.props;

    const locale = useAdapter().useLocale();
    setDefaultLocale(locale);

    let props: RootRenderProps = {
      // Note: Amis 内部会自动替换 zh_CN 为 zh-CN
      locale,
      theme,
      location,
      data: {},
      context: {},
      scopeRef: (scoped: any) => {
        amisScoped = scoped;
      }
    };

    return renderAmis(schema, props, env);
  }

  async fetchSchema(cb: () => any) {
    if (this.state.schema) {
      return cb();
    }

    let schema: any;
    const { schemaApi } = this.props;

    if (!isEffectiveApi(schemaApi, {})) {
      schema = {
        type: 'wrapper',
        className: 'schema-loading-failed',
        body: `${schemaApi} is invalid.`
      };
    } else {
      let json: FetcherResult;
      let data: any;
      try {
        json = await env.fetcher({ url: schemaApi, method: 'get' });

        data = json.data || { status: -1, msg: 'No data' };
      } catch (e) {
        data = { status: -1, msg: e.message };
      }

      if (data.status != 0) {
        schema = {
          type: 'wrapper',
          className: 'schema-loading-failed',
          body: 'Loading site schema failed: ' + data.msg
        };
      } else {
        schema = replaceText(
          data.data,
          env.replaceText,
          env.replaceTextIgnoreKeys
        );
      }
    }

    this.setState({ schema });
    cb();
  }
}
