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
import { theme as registerTheme } from 'amis-core';

import { history } from '@/sdk/nop-core';
import '@/renderer/amis/components';

import Renderer from './Renderer';

// 引入 FA 字体图标
import '@fortawesome/fontawesome-free/css/all.css';
import '@fortawesome/fontawesome-free/css/v4-shims.css';
import 'amis/sdk/iconfont.css';
// 全局的类 tailwindcss 风格的原子样式
// https://baidu.github.io/amis/zh-CN/style/index
import 'amis-ui/scss/helper.scss';

// 在该 scss 文件中定制 AMIS 自带的主题
import '@/renderer/amis/themes/antd.scss';

import './style.scss';

export default async function render({ container, ...site }) {
  if (typeof container === 'string') {
    container = document.querySelector(container);
  }

  if (!container) {
    return console.error('Amis.invalidRoot');
  } else if (container.tagName === 'BODY') {
    container = document.createElement('div');
    document.body.appendChild(container);
  }
  container.classList.add('amis-scope', 'site');

  const theme = 'antd';
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
        props={{ ...props, theme: theme }}
        onReady={() => {
          // 结束加载动画
          container.parentElement.classList.add('done');
        }}
      />
    );
  };

  // Note：重载 document.title 之后，只能通过 <title/> 标签修改其值
  const $title = document.head.getElementsByTagName('title')[0];
  Object.defineProperty(document, 'title', {
    get() {
      return $title?.innerText.trim();
    },
    set(newValue) {
      // Note: AMIS 的 App 组件默认在 AppStore#setActivePage
      // 中直接对 document.title 赋值以修改浏览器标签名称，
      // 当前只能重定义该属性的 setter 接口，以使其修改无效
      // $title && ($title.innerText = newValue + ' | 渡舟平台');
    }
  });

  // see amis-core/src/theme.tsx
  // 所有主题的 class 名称都设置相同前缀，以便于统一对主题进行定制
  registerTheme(theme, {
    classPrefix: 'amis-'
  });

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
