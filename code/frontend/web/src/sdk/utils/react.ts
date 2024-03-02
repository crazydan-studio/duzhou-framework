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

import { isArray } from 'lodash-es';

export function travelElementMutation<T extends JSX.Element>(
  element: T,
  mutate: (el: T) => T
): T {
  if (
    !element || //
    (element.props && //
      element.props.__mutated__ === 'true')
  ) {
    return element;
  }

  let updated = mutate(element);
  if (!updated) {
    return element;
  }

  // Note：节点为只读的，必须解构对象
  if (updated !== element && updated.props) {
    updated = updateElementProps(updated, {
      __mutated__: 'true'
    });
  }

  let children = updated.props && updated.props.children;
  if (!children) {
    return updated;
  }

  if (!isArray(children)) {
    children = [children];
  }

  let hasUpdated = false;
  const updatedChildren: any[] = [];
  for (let child of children) {
    const updatedChild = travelElementMutation(child, mutate);

    if (!updatedChild || updatedChild === child) {
      updatedChildren.push(child);
    } else {
      hasUpdated = true;
      updatedChildren.push(updatedChild);
    }
  }

  if (!hasUpdated) {
    return updated;
  }
  return updateElementProps(updated, {
    __mutated__: 'true',
    children: updatedChildren
  });
}

export function updateElementProps<T extends JSX.Element>(
  element: T,
  props: object
): T {
  return {
    ...element,
    props: {
      ...element.props,
      ...props
    }
  };
}
