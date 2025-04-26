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

package io.crazydan.duzhou.framework.ui.schema.layout;

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed;

/**
 * 布局（树）节点
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-25
 */
public class XuiLayoutNode {
    /** 组件的匹配模式，能够按此模式匹配的组件为对应的布局项 */
    private final String pattern;

    /** 对齐方式 */
    private XuiLayoutAlign align = new XuiLayoutAlign(XuiLayoutAlign.Direction.start, XuiLayoutAlign.Direction.start);
    /** 宽度 */
    private XuiLayoutSize width = new XuiLayoutSize(XuiLayoutSize.Type.wrap_content);
    /** 高度 */
    private XuiLayoutSize height = new XuiLayoutSize(XuiLayoutSize.Type.wrap_content);

    /** 嵌套子树 */
    private final List<XuiLayoutNode> children = new ArrayList<>();

    public XuiLayoutNode(String pattern) {
        this.pattern = pattern;
    }

    /** 是否为匹配的组件 */
    public boolean matched(XuiComponentNamed component) {
        return false;
    }

    public XuiLayoutAlign getAlign() {
        return this.align;
    }

    public void setAlign(XuiLayoutAlign align) {
        this.align = align;
    }

    public XuiLayoutSize getWidth() {
        return this.width;
    }

    public void setWidth(XuiLayoutSize width) {
        this.width = width;
    }

    public XuiLayoutSize getHeight() {
        return this.height;
    }

    public void setHeight(XuiLayoutSize height) {
        this.height = height;
    }
}
