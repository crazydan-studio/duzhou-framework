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

import { match } from 'path-to-regexp';
// https://github.com/remix-run/history/blob/dev/docs/getting-started.md
import h from 'history/hash';

export const history = h;

/** 创建路由链接 */
export function createRouteLink(link: string) {
  return `#${link}`;
}

/** 清除路由链接 */
export function purgeRouteLink(link: string) {
  return link.replaceAll(/^#/g, '');
}

export function jumpTo(to: string, action: any) {
  if (to === 'goForward') {
    return history.forward();
  } else if (to === 'goBack') {
    return history.back();
  }

  to = normalizeLink(to);
  if (isCurrentUrl(to)) {
    return;
  }

  if (action && action.actionType === 'url') {
    action.blank === false
      ? (window.location.href = to)
      : window.open(to, '_blank');
    return;
  } else if (action && action.blank) {
    window.open(to, '_blank');
    return;
  }

  if (/^https?:\/\//.test(to)) {
    window.location.href = to;
  } else if (
    (!/^https?\:\/\//.test(to) &&
      to === history.location.pathname + history.location.search) ||
    to === window.location.href
  ) {
    // do nothing
  } else {
    history.push(to);
  }
}

export function openWindow(
  url: string,
  opt?: { target?: string; noopener?: boolean; noreferrer?: boolean }
) {
  const { target = '__blank', noopener = true, noreferrer = true } = opt || {};
  const feature: string[] = [];

  noopener && feature.push('noopener=yes');
  noreferrer && feature.push('noreferrer=yes');

  window.open(url, target, feature.join(','));
}

export function updateLocation(to: any, replace: boolean) {
  to = normalizeLink(to);

  if (to === 'goBack') {
    return history.back();
  }
  // 目标地址和当前地址一样，不处理，免得重复刷新
  else if (
    (!/^https?\:\/\//.test(to) &&
      to === history.location.pathname + history.location.search) ||
    to === window.location.href
  ) {
    return;
  } else if (/^https?\:\/\//.test(to) || !history) {
    return (window.location.href = to);
  }

  history[replace ? 'replace' : 'push'](to);
}

export function isCurrentUrl(to: string, ctx?: any) {
  const location = history.location;
  const pathname = location.pathname;
  const link = normalizeLink(to, {
    ...location,
    hash: ''
  });

  if (!~link.indexOf('http') && ~link.indexOf(':')) {
    return match(link, {
      decode: decodeURIComponent,
      strict: ctx?.strict ?? true
    })(pathname);
  }

  return decodeURI(pathname) === link;
}

function normalizeLink(link: string, location = history.location) {
  if (!link) {
    return '';
  }

  if (link[0] === '#') {
    link = location.pathname + location.search + link;
  } else if (link[0] === '?') {
    link = location.pathname + link;
  } else if (link[0] === '@') {
    const actionIndex = link.indexOf(':');
    const pathname = link.substring(actionIndex + 1);
    const action = link.substring(1, actionIndex);

    switch (action) {
      case 'redirect':
        if (pathname[0] === '/') {
          return window.location.origin + pathname;
        }
        return window.location.origin + window.location.pathname + pathname;
    }
  }

  const searchIndex = link.indexOf('?');
  const hashIndex = link.indexOf('#');
  let pathname = ~searchIndex
    ? link.substring(0, searchIndex)
    : ~hashIndex
    ? link.substring(0, hashIndex)
    : link;
  const search = ~searchIndex
    ? link.substring(searchIndex, ~hashIndex ? hashIndex : undefined)
    : '';
  const hash = ~hashIndex ? link.substring(hashIndex) : location.hash;

  if (!pathname) {
    pathname = location.pathname;
  } else if (pathname[0] != '/' && !/^https?\:\/\//.test(pathname)) {
    const relativeBase = location.pathname;
    const paths = relativeBase.split('/');
    paths.pop();

    let m;
    while ((m = /^\.\.?\//.exec(pathname))) {
      if (m[0] === '../') {
        paths.pop();
      }
      pathname = pathname.substring(m[0].length);
    }
    pathname = paths.concat(pathname).join('/');
  }

  return pathname + search + hash;
}
