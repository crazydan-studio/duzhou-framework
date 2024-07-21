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

import { memo } from 'react';
import { Handle, Position } from 'reactflow';

function Node({ data }) {
  // Note：只有放在 data 中的数据才能够自由变更
  const { props, onEvent, collapsed, direction } = data;
  const hasFooter = onEvent.onShowEditor || onEvent.onDelete;

  return (
    <>
      <div className="body flex flex-row justify-between">
        <div className="left flex">
          <div className="rounded-full flex justify-center items-center">
            {props.icon?.includes('/') ? (
              <img src={props.icon} className="w-12 h-12" />
            ) : (
              <i className={props.icon + ' text-3xl'}></i>
            )}
          </div>
          <div className="ml-2">
            <div className="text-lg font-bold">{props.title}</div>
            <div className="text-gray-500 max-w-xs">{props.subTitle}</div>
          </div>
        </div>
        {onEvent.onCollapse && (
          <div className="right flex items-center ml-4">
            <div className="btn">
              <i
                className={
                  collapsed
                    ? 'fa-solid fa-circle-chevron-right'
                    : 'fa-solid fa-circle-chevron-left'
                }
                title={collapsed ? '展开子节点' : '收起子节点'}
                onClick={onEvent.onCollapse}
              ></i>
            </div>
          </div>
        )}
      </div>
      <Handle
        type="target"
        position={direction === 'vertical' ? Position.Top : Position.Left}
      />
      <Handle
        type="source"
        position={direction === 'vertical' ? Position.Bottom : Position.Right}
      />
      {hasFooter && (
        <div className="footer">
          <div className="toolbar">
            <div
              className={'item btn' + (onEvent.onDelete ? '' : ' disabled')}
              title="移除"
              onClick={onEvent.onDelete}
            >
              <i className="fa-solid fa-trash-can"></i>
            </div>
            <div className="divider"></div>
            <div
              className={'item btn' + (onEvent.onShowEditor ? '' : ' disabled')}
              title="配置"
              onClick={onEvent.onShowEditor}
            >
              <i className="fa-solid fa-sliders"></i>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default memo(Node);
