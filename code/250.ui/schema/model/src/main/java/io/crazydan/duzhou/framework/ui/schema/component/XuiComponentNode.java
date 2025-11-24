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

package io.crazydan.duzhou.framework.ui.schema.component;

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentNode;
import io.crazydan.duzhou.framework.ui.layout.XuiLayoutNode;

public class XuiComponentNode extends _XuiComponentNode {

    public XuiComponentNode() {
    }

    /** 查找待布局节点 */
    public List<XuiComponentNamed> findLayoutChild(XuiLayoutNode layout) {
        List<XuiComponentNamed> nodes = new ArrayList<>();

        getChildren().forEach((child) -> {
            // TODO 处理条件指令、动画、校验等情况

            if (layout.matched(child.getXuiName())) {
                nodes.add(child);
            }
        });

        return nodes;
    }
}
