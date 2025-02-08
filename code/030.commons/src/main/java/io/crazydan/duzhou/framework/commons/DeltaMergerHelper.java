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

package io.crazydan.duzhou.framework.commons;

import io.nop.core.lang.xml.XNode;
import io.nop.xlang.xdef.XDefOverride;
import io.nop.xlang.xdsl.XDslKeys;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-13
 */
public class DeltaMergerHelper {

    /** 清除虚拟节点、扩展删除节点、抽象节点 */
    public static void cleanNode(XNode node) {
        XDslKeys keys = XDslKeys.of(node);

        cleanNode(node, keys);
    }

    /** 清除虚拟节点、扩展删除节点、抽象节点 */
    public static void cleanNode(XNode node, XDslKeys keys) {
        node.removeAttr(keys.OVERRIDE);

        // Note：代码改造自 io.nop.xlang.xdsl.XDslValidator#clean
        for (int i = 0, n = node.getChildCount(); i < n; i++) {
            XNode child = node.child(i);

            String overrideAttr = child.removeAttr(keys.OVERRIDE).asString();
            boolean shouldRemoved = child.attrBoolean(keys.ABSTRACT)
                                    || XDefOverride.REMOVE.getText()
                                                          .equals(overrideAttr)
                                    || child.attrBoolean(keys.VIRTUAL);

            if (shouldRemoved) {
                node.removeChildByIndex(i);
                i--;
                n--;
            } else {
                cleanNode(child, keys);
            }
        }
    }
}
