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

function Node({ data, selected }) {
  return (
    <div
      className={
        'px-4 py-2 shadow-md rounded-md bg-white border-solid border-2' +
        (selected ? ' border-pink-600' : ' border-gray-300')
      }
    >
      <div className="flex">
        <div className="rounded-full flex justify-center items-center">
          <i
            className={
              (data.icon ?? 'fa-solid fa-circle-question') + ' text-3xl'
            }
          ></i>
        </div>
        <div className="ml-2">
          <div className="text-lg font-bold">{data.title}</div>
          <div className="text-gray-500">{data.subTitle}</div>
        </div>
      </div>

      <Handle type="target" position={Position.Left} />
      <Handle type="source" position={Position.Right} />
    </div>
  );
}

export default memo(Node);
