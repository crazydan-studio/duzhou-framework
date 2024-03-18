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

function NewNode({ data }) {
  const { props, direction } = data;

  return (
    <div className="body">
      <div className="flex flex-col items-center">
        <i className="fa-solid fa-plus text-2xl text-center leading-none text-gray-300"></i>
        <span>
          新增<span className="font-bold">{props.title}</span>
        </span>
      </div>

      <Handle
        type="target"
        position={direction === 'vertical' ? Position.Top : Position.Left}
      />
      <Handle
        type="source"
        position={direction === 'vertical' ? Position.Bottom : Position.Right}
      />
    </div>
  );
}

export default memo(NewNode);
