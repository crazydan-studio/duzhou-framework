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
import { history } from '@/sdk/nop-core';
import '@/renderer/amis/components';

import Renderer from './Renderer';

// 全局的类 tailwindcss 风格的原子样式
// https://baidu.github.io/amis/zh-CN/style/index
import 'amis/lib/helper.css';
import 'amis/sdk/iconfont.css';
import 'amis/lib/themes/antd.css';

import './style.scss';

export default async function render({ container, ...site }) {
  if (typeof container === 'string') {
    container = document.querySelector(container);
  }

  if (!container) {
    return console.error('Embed.invalidRoot');
  } else if (container.tagName === 'BODY') {
    container = document.createElement('div');
    document.body.appendChild(container);
  }
  container.classList.add('amis-scope', 'site');

  const reactRoot = createRoot(container!);

  // https://github.com/baidu/amis/blob/master/examples/embed.tsx#L256
  const doRender = (props: any) => {
    reactRoot.render(
      <Renderer
        schema={site.schema || site.schemaApi}
        env={{
          toastPosition: 'top-center',
          getModalContainer: () => container
        }}
        props={{ ...props, theme: 'antd' }}
        onReady={() => {
          // 结束加载动画
          container.parentElement.classList.add('done');
        }}
      />
    );
  };

  doRender({
    location: history.location
  });

  // 地址栏更新时，需按地址重新渲染页面
  history.listen((state) => {
    doRender({
      location: state.location || state
    });
  });
}
