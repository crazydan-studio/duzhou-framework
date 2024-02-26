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

import { createRoot } from 'react-dom/client';

import Site from './Site';

// 全局的类 tailwindcss 风格的原子样式
// https://baidu.github.io/amis/zh-CN/style/index
import 'amis/lib/helper.css';
import 'amis/sdk/iconfont.css';
import 'amis/lib/themes/antd.css';

import './style.scss';

import { registerAdapter, history } from '@/sdk/nop-core';

export default async function render({ el, layout }) {
  const site = (layout && (await layout())) || {};

  const root = createRoot(el!);
  // https://github.com/baidu/amis/blob/master/examples/embed.tsx#L256
  const doRender = (props: any) => {
    root.render(
      <Site
        theme="antd"
        location={props.location}
        schema={site.schema}
        schemaApi={site.schemaApi}
        onReady={() => {
          // 在挂载节点添加根样式
          el.classList.add('site');
          // 结束加载动画
          el.parentElement.classList.add('done');
        }}
      />
    );
  };

  doRender({
    location: history.location
  });

  history.listen((state) => {
    doRender({
      location: state.location || state
    });
  });
}

registerAdapter({
  useLocale(): string {
    return 'zh-CN';
  },
  useI18n() {
    return {
      t: (msg: string) => msg
    };
  },
  useSettings() {
    return {
      apiUrl: ''
    };
  },

  useAuthToken(): string {
    return localStorage.getItem('nop-token') || '';
  },
  setAuthToken(token?: string) {
    localStorage.setItem('nop-token', token || '');
  },
  isUserInRole(role: string): boolean {
    return false;
  },

  useTenantId(): string {
    return '';
  },
  useAppId(): string {
    return 'nop-sdk-demo';
  },

  logout(): void {
    localStorage.removeItem('nop-token');
    history.push('/login');
  }
});
