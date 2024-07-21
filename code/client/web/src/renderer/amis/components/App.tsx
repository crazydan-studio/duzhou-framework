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

// import { addMiddleware } from 'mobx-state-tree';
import React from 'react';
import { unRegisterRenderer, Renderer, AppStore, autobind } from 'amis';
import AmisApp, { AppProps } from 'amis/lib/renderers/App';

import { createRouteLink, purgeRouteLink } from '@/sdk/nop-core';
import { travelElementMutation, updateElementProps } from '@/sdk/utils/react';

const TYPE = 'app';
unRegisterRenderer(TYPE);

@Renderer({
  type: TYPE,
  storeType: AppStore.name
})
export default class App extends AmisApp {
  constructor(props: AppProps) {
    super(props);

    // const { store, docTitle } = this.props;
    // https://mobx-state-tree.js.org/concepts/middleware
    // addMiddleware(store, (call, next, abort) => {
    //   console.log(call.type, call.name, call.args);

    //   next(call);
    //   document.title = docTitle;
    // });
  }

  @autobind
  handleNavClick(e: React.MouseEvent) {
    e.preventDefault();

    const env = this.props.env;
    let link = e.currentTarget.getAttribute('href')!;
    link = purgeRouteLink(link);

    env.jumpTo(link, undefined, this.props.data);
  }

  renderHeader() {
    const { classnames: cx } = this.props;

    let header = super.renderHeader();
    if (!header) {
      return header;
    }

    header = travelElementMutation(header, (el) => {
      if (!el.props || el.props.className !== cx('Layout-brand')) {
        return el;
      }

      return updateElementProps(el, {
        children: (
          <a href={createRouteLink('/')} onClick={this.handleNavClick}>
            {el.props.children}
          </a>
        )
      });
    });

    return header;
  }

  renderAside() {
    let aside = super.renderAside();

    aside = travelElementMutation(aside, (el) => {
      const oldRenderLink = el.props && el.props.renderLink;
      if (!oldRenderLink) {
        return el;
      }

      const newRenderLink = (...args: any[]) => {
        let links = oldRenderLink(...args);

        links = travelElementMutation(links, (lnk) => {
          let href = !!lnk.props && !lnk.props.target && lnk.props.href;
          if (!href) {
            return lnk;
          }

          return updateElementProps(lnk, {
            href: createRouteLink(href)
          });
        });

        return links;
      };

      return updateElementProps(el, {
        renderLink: newRenderLink
      });
    });

    return aside;
  }

  render() {
    const { classnames: cx } = this.props;
    let root = super.render();

    root = travelElementMutation(root, (el) => {
      if (!el.props || el.props.className !== cx('AppBcn-item')) {
        return el;
      }

      return travelElementMutation(el, (e) => {
        return e.type !== 'a'
          ? e
          : updateElementProps(e, {
              href: createRouteLink(e.props.href)
            });
      });
    });

    return root;
  }
}
