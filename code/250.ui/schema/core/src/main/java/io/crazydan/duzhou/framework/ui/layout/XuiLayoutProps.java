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

package io.crazydan.duzhou.framework.ui.layout;

import java.util.Map;

import io.crazydan.duzhou.framework.lang.MappableCodeSnippet;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局配置属性
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-25
 */
@DataBean
public class XuiLayoutProps implements MappableCodeSnippet {
    /** 宽度 */
    private XuiLayoutSize width = XuiLayoutSize.wrap_content();
    /** 高度 */
    private XuiLayoutSize height = XuiLayoutSize.wrap_content();

    /**
     * 对齐方式
     * <p/>
     * 非表格单元格节点的水平和垂直方向均缺省为 {@link XuiLayoutAlign.Direction#start}，
     * 而表格单元格（{@link XuiLayoutNode#getType()} 始终为 {@link XuiLayoutNode.Type#row}）
     * 的水平和垂直方向始终为 {@link XuiLayoutAlign.Direction#center}
     */
    private XuiLayoutAlign align;

    /** 布局项之间的间隔 */
    private XuiLayoutGap gap;
    /** {@link XuiLayoutNode.Type#table} 中的单元格可跨越的单元格数量 */
    private XuiLayoutSpan span;
    /**
     * 边距，即，四周的空白宽度
     * <p/>
     * 注：其仅作用于 {@link XuiLayoutNode.Type#row}、{@link XuiLayoutNode.Type#column}
     * 和 {@link XuiLayoutNode.Type#table}
     */
    private XuiLayoutSpacing padding;

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

    public XuiLayoutAlign getAlign() {
        return this.align;
    }

    public void setAlign(XuiLayoutAlign align) {
        this.align = align;
    }

    public XuiLayoutGap getGap() {
        return this.gap;
    }

    public void setGap(XuiLayoutGap gap) {
        this.gap = gap;
    }

    public void setGap(ValueWithLocation vl) {
        setGap(XuiLayoutGap.create(vl));
    }

    public XuiLayoutSpan getSpan() {
        return this.span;
    }

    public void setSpan(XuiLayoutSpan span) {
        this.span = span;
    }

    public void setSpan(ValueWithLocation vl) {
        setSpan(XuiLayoutSpan.create(vl));
    }

    public XuiLayoutSpacing getPadding() {
        return this.padding;
    }

    public void setPadding(XuiLayoutSpacing padding) {
        this.padding = padding;
    }

    public void setPadding(ValueWithLocation vl) {
        setPadding(XuiLayoutSpacing.create(vl));
    }

    @Override
    public SourceLocation getLocation() {
        return null;
    }

    @Override
    public void toMap(Map<String, Object> map) {
        ifNotNull(this.width, (v) -> map.put("width", v));
        ifNotNull(this.height, (v) -> map.put("height", v));

        ifNotNull(this.align, (v) -> map.put("align", v));
        ifNotNull(this.span, (v) -> map.put("span", v));
        ifNotNull(this.gap, (v) -> map.put("gap", v));
        ifNotNull(this.padding, (v) -> map.put("padding", v));
    }

    @Override
    public String toString() {
        // TODO 还原为标记文本
        return "()";
    }
}
