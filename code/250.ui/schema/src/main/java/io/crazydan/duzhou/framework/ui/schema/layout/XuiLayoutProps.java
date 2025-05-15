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

import java.util.Map;

import io.nop.api.core.annotations.data.DataBean;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

/**
 * 布局配置属性
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-25
 */
@DataBean
public class XuiLayoutProps implements IJsonSerializable {
    /** 布局项之间的间隔 */
    public final XuiLayoutGap gap;
    /** {@link XuiLayoutNode.Type#table} 中的单元格可跨越的单元格数量 */
    public final XuiLayoutSpan span;
    /**
     * 边距，即，四周的空白宽度
     * <p/>
     * 注：其仅作用于 {@link XuiLayoutNode.Type#row}、{@link XuiLayoutNode.Type#column}
     * 和 {@link XuiLayoutNode.Type#table}
     */
    public final XuiLayoutSpacing padding;

    XuiLayoutProps(XuiLayoutGap gap, XuiLayoutSpan span, XuiLayoutSpacing padding) {
        this.gap = gap;
        this.span = span;
        this.padding = padding;
    }

    public static XuiLayoutProps create(Map<String, Object> props) {
        XuiLayoutGap gap = XuiLayoutGap.create(props.get("gap"));
        XuiLayoutSpan span = XuiLayoutSpan.create(props.get("span"));
        XuiLayoutSpacing padding = XuiLayoutSpacing.create(props.get("padding"));

        return new XuiLayoutProps(gap, span, padding);
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.beginObject(null);

        out.putNotNull("gap", this.gap);
        out.putNotNull("span", this.span);
        out.putNotNull("padding", this.padding);

        out.endObject();
    }

    @Override
    public String toString() {
        // TODO 还原为标记文本
        return "()";
    }
}
