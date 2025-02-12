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

package io.crazydan.duzhou.framework.ui.schema;

import io.nop.core.lang.xml.XNode;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-02-11
 */
public class XuiStylesHelper {

    /**
     * 对样式节点的结构有效性进行校验
     * - 其结构节点所引用的样式节点必须在根节点中已定义，且属性列表和属性类型与样式的定义是一致的
     * - 其结构节点的属性值只能引用该样式节点的属性，且二者的类型必须相同，不能引用未定义的变量
     */
    public static void validate(XNode root) {
        root.getBody();
    }

    /**
     * 展开样式节点，最终使其仅由原子样式组成
     */
    public static void expand(XNode root) {
        root.getBody();
    }
}
