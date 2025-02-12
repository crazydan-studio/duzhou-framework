/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.ui;

/**
 * 样式属性类型
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-02-11
 */
public enum XuiStyleAttrType {
    /** null 属性，表示从节点中删除该属性 */
    Null,
    /** 字符串 */
    String,
    /** 颜色，其值可以为 #00ff00、rgb(255,0,0)、rgba(255,0,0,1)、green、red 等形式 */
    Color,
    /** 尺寸，其值可以为 1px、1em 等形式 */
    Size,
    /** 边框样式，其值为：solid、dot、dash */
    BorderStyle,
}
