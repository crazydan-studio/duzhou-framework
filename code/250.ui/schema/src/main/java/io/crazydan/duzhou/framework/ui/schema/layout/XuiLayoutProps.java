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

import java.util.HashMap;
import java.util.Map;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.appendJsonToJsonProp;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局配置属性
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-25
 */
public class XuiLayoutProps {
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

    public XuiLayoutProps() {
        this(null);
    }

    public XuiLayoutProps(Map<String, Object> props) {
        if (props == null) {
            props = new HashMap<>();
        }

        Object gap = props.get("gap");
        this.gap = XuiLayoutGap.create(gap);

        Object span = props.get("span");
        this.span = XuiLayoutSpan.create(span);

        Object padding = props.get("padding");
        this.padding = XuiLayoutSpacing.create(padding);
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');
        ifNotNull(this.gap, (v) -> {
            String json = v.toJSON();
            appendJsonToJsonProp(sb, "gap", json);
        });
        ifNotNull(this.span, (v) -> {
            String json = v.toJSON();
            appendJsonToJsonProp(sb, "span", json);
        });
        ifNotNull(this.padding, (v) -> {
            String json = v.toJSON();
            appendJsonToJsonProp(sb, "padding", json);
        });
        sb.append('}');

        return sb.toString();
    }
}
