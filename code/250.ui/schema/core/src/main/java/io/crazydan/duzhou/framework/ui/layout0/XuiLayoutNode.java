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

package io.crazydan.duzhou.framework.ui.layout0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.api.core.util.SourceLocation;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局（树）节点
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-25
 */
@DataBean
public class XuiLayoutNode implements ISourceLocationGetter, IJsonSerializable {

    /** {@link XuiLayoutNode} 类型 */
    public enum Type {
        /** 布局项，其对应{@link #matched 匹配}的组件 */
        item,
        /** 空白占位 */
        space,
        /** 表格：{@link XuiLayoutNode#children} 中的元素均需为 {@link #row} 类型 */
        table,

        /**
         * 横向布局，即 {@link XuiLayoutNode#children} 中的元素均在同一行中，
         * 若其上层节点为 {@link #table}，则行内节点均为表格单元格
         */
        row,
        /** 纵向布局，即 {@link XuiLayoutNode#children} 中的元素均在同一列中 */
        column,
    }

    private final SourceLocation loc;

    /** 布局节点类型 */
    private final Type type;
    /** 组件的匹配模式，能够按此模式匹配的组件为对应的布局节点 */
    private final Pattern pattern;

    /** 布局配置 */
    private final XuiLayoutProps props = new XuiLayoutProps();

    /** 嵌套子树 */
    private final List<XuiLayoutNode> children = new ArrayList<>();

    /** Note: 在 CodeGen 时需要无参构造函数 */
    public XuiLayoutNode() {
        this(null, null);
    }

    XuiLayoutNode(SourceLocation loc, Type type, String pattern) {
        this(loc, type, pattern != null ? Pattern.compile("^" + pattern + "$") : null);
    }

    XuiLayoutNode(SourceLocation loc, Type type, Pattern pattern) {
        this.loc = loc;
        this.type = type;
        this.pattern = pattern;
    }

    XuiLayoutNode(SourceLocation loc, Type type) {
        this(loc, type, (Pattern) null);
    }

    public static XuiLayoutNode item(SourceLocation loc, String pattern) {
        return new XuiLayoutNode(loc, Type.item, pattern);
    }

    public static XuiLayoutNode space(SourceLocation loc) {
        return new XuiLayoutNode(loc, Type.space);
    }

    public static XuiLayoutNode table(SourceLocation loc) {
        return new XuiLayoutNode(loc, Type.table);
    }

    public static XuiLayoutNode row(SourceLocation loc) {
        return new XuiLayoutNode(loc, Type.row);
    }

    public static XuiLayoutNode row(SourceLocation loc, List<XuiLayoutNode> nodes) {
        XuiLayoutNode node = row(loc);
        node.addChildren(nodes);

        return node;
    }

    public static XuiLayoutNode column(SourceLocation loc) {
        return new XuiLayoutNode(loc, Type.column);
    }

    public static XuiLayoutNode column(SourceLocation loc, List<XuiLayoutNode> nodes) {
        XuiLayoutNode node = column(loc);
        node.addChildren(nodes);

        return node;
    }

    /** 是否匹配该布局节点 */
    public boolean matched(String key) {
        return this.pattern != null && this.pattern.matcher(key).matches();
    }

    /** 递归获取当前节点所包含的所有{@link Type 布局节点类型} */
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

    public XuiLayoutProps getProps() {
        return this.props;
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

    /** 替换指定的节点 */
    public void replaceChild(XuiLayoutNode oldNode, XuiLayoutNode newNode) {
        int oldNodeIndex = this.children.indexOf(oldNode);
        if (oldNodeIndex < 0) {
            return;
        }

        this.children.set(oldNodeIndex, newNode);
    }

    public XuiLayoutNode cloneInstance() {
        XuiLayoutNode node = new XuiLayoutNode(this.loc, this.type, this.pattern);

        this.props.copyTo(node.props);
        for (XuiLayoutNode child : this.children) {
            node.addChild(child.cloneInstance());
        }

        return node;
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.beginObject(null);

        out.putNotNull("loc", this.loc);
        out.put("type", this.type);
        ifNotNull(this.pattern, (v) -> out.putNotNull("pattern", v.pattern()));

        out.put("props", this.props);

        if (!this.children.isEmpty()) {
            out.put("children", this.children);
        }

        out.endObject();
    }

    @Override
    public String toString() {
        // TODO 还原为标记文本
        return "";
    }
}
