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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed;

/**
 * 布局（树）节点
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-25
 */
public class XuiLayoutNode {

    /** {@link XuiLayoutNode} 类型 */
    public enum Type {
        /** 布局项，其对应{@link #matched 匹配}的组件 */
        item,
        /** 空白占位 */
        space,
        /** 表格：{@link XuiLayoutNode#children} 中的元素均需为 {@link #row} 类型 */
        table,

        /**
         * 行：{@link XuiLayoutNode#children} 中的元素均在同一行，
         * 即，行内布局，若其上层节点为 {@link #table}，则行内节点均为表格单元格
         */
        row,
        /**
         * 列内布局：{@link XuiLayoutNode#children} 中的元素均单独占用一行，
         * 即，列内布局
         */
        column,
    }

    /** 布局节点类型 */
    private final Type type;
    /** 组件的匹配模式，能够按此模式匹配的组件为对应的布局节点 */
    private final Pattern pattern;

    /**
     * 对齐方式
     * <p/>
     * 非表格单元格节点的水平和垂直方向均缺省为 {@link XuiLayoutAlign.Direction#start}，
     * 而表格单元格（{@link #type} 始终为 {@link Type#row}）的水平和垂直方向始终为
     * {@link XuiLayoutAlign.Direction#center}
     */
    private XuiLayoutAlign align;
    /** 宽度 */
    private XuiLayoutSize width = XuiLayoutSize.wrap_content();
    /** 高度 */
    private XuiLayoutSize height = XuiLayoutSize.wrap_content();

    /** 嵌套子树 */
    private final List<XuiLayoutNode> children = new ArrayList<>();

    XuiLayoutNode(Type type, String pattern) {
        this.type = type;
        this.pattern = pattern != null ? Pattern.compile("^" + pattern + "$") : null;
    }

    XuiLayoutNode(Type type) {
        this(type, null);
    }

    public static XuiLayoutNode item(String pattern) {
        return new XuiLayoutNode(Type.item, pattern);
    }

    public static XuiLayoutNode space() {
        return new XuiLayoutNode(Type.space);
    }

    public static XuiLayoutNode table() {
        return new XuiLayoutNode(Type.table);
    }

    public static XuiLayoutNode row() {
        return new XuiLayoutNode(Type.row);
    }

    public static XuiLayoutNode row(List<XuiLayoutNode> nodes) {
        XuiLayoutNode node = row();
        node.addChildren(nodes);

        return node;
    }

    public static XuiLayoutNode column() {
        return new XuiLayoutNode(Type.column);
    }

    public static XuiLayoutNode column(List<XuiLayoutNode> nodes) {
        XuiLayoutNode node = column();
        node.addChildren(nodes);

        return node;
    }

    /** 是否为匹配的组件 */
    public boolean matched(XuiComponentNamed component) {
        return this.pattern != null && this.pattern.matcher(component.getXuiName()).matches();
    }

    /** 递归获取当前节点所包含的{@link Type 布局节点类型} */
    public Set<Type> getTypes() {
        Set<Type> types = new HashSet<>();

        types.add(getType());
        getChildren().forEach((child) -> {
            types.addAll(child.getTypes());
        });

        return types;
    }

    public Type getType() {
        return this.type;
    }

    public String getPattern() {
        return this.pattern != null ? this.pattern.pattern() : null;
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

    public List<XuiLayoutNode> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    public boolean hasChild() {
        return !this.children.isEmpty();
    }

    public void addChild(XuiLayoutNode node) {
        if (node != null) {
            this.children.add(node);
        }
    }

    public void addChildren(List<XuiLayoutNode> nodes) {
        if (nodes != null) {
            this.children.addAll(nodes);
        }
    }

    @Override
    public String toString() {
        // TODO 还原为标记文本
        return "";
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append('{').append('\n');
        sb.append("  \"type\": \"").append(this.type).append("\"\n");
        if (this.pattern != null) {
            sb.append("  , \"pattern\": \"").append(this.pattern).append("\"\n");
        }
        if (this.align != null) {
            sb.append("  , \"align\": ").append(this.align.toJSON()).append('\n');
        }
        if (this.width != null) {
            sb.append("  , \"width\": ").append(this.width.toJSON()).append('\n');
        }
        if (this.height != null) {
            sb.append("  , \"height\": ").append(this.height.toJSON()).append('\n');
        }
        if (!this.children.isEmpty()) {
            sb.append("  , \"children\": [");
            for (int i = 0; i < this.children.size(); i++) {
                XuiLayoutNode child = this.children.get(i);
                String json = (i > 0 ? ", " : "") + child.toJSON();

                sb.append(json.replaceAll("(?m)^", "    ").replaceAll("^\\s+", ""));
            }
            sb.append(']').append('\n');
        }
        sb.append('}');

        return sb.toString();
    }
}
