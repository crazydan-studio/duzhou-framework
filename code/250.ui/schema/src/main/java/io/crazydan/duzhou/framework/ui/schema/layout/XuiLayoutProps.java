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

import io.crazydan.duzhou.framework.commons.StringHelper;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.appendJsonProp;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.appendJsonToJsonProp;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局配置属性
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-25
 */
public class XuiLayoutProps {
    /**
     * 布局项之间的间隔
     * <p/>
     * 若父节点类型为 {@link XuiLayoutNode.Type#table}
     * 或 {@link XuiLayoutNode.Type#column}，
     * 则用于设置垂直方向上的节点间隔，若父节点类型为
     * {@link XuiLayoutNode.Type#row}，则用于设置水平方向上的节点间隔
     * <p/>
     * 可通过插入空白项的方式控制项间的间隔？（需避免插入空白项导致布局项的对齐方式等受到影响）
     */
    public final String gap;

    /** {@link XuiLayoutNode.Type#table} 中的单元格可横跨的列数量 */
    public final Integer colspan;
    /** {@link XuiLayoutNode.Type#table} 中的单元格可纵跨的行数量 */
    public final Integer rowspan;

    /**
     * 内边距
     * <p/>
     * 可直接作用在配置节点上
     */
    public final XuiLayoutEdgeSize padding;
    /**
     * 外边距
     * <p/>
     * 需在其外部附加一层节点，以确保对齐方式不受影响？
     */
    public final XuiLayoutEdgeSize margin;

    public XuiLayoutProps() {
        this(null);
    }

    public XuiLayoutProps(Map<String, Object> props) {
        if (props == null) {
            props = new HashMap<>();
        }

        this.gap = StringHelper.trimToNull((String) props.get("gap"));

        this.colspan = StringHelper.trimAndParseInt((String) props.get("colspan"), 10);
        this.rowspan = StringHelper.trimAndParseInt((String) props.get("rowspan"), 10);

        this.padding = new XuiLayoutEdgeSize((Map<String, Object>) props.get("padding"));
        this.margin = new XuiLayoutEdgeSize((Map<String, Object>) props.get("margin"));
    }

    public Map<String, Object> toMap() {
        Map<String, Object> props = new HashMap<>();

        ifNotNull(this.gap, (v) -> props.put("gap", v));
        ifNotNull(this.colspan, (v) -> props.put("colspan", v));
        ifNotNull(this.rowspan, (v) -> props.put("rowspan", v));
        ifNotNull(this.padding, (v) -> props.put("padding", v.toMap()));
        ifNotNull(this.margin, (v) -> props.put("margin", v.toMap()));

        return props;
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');
        ifNotNull(this.gap, (v) -> {
            appendJsonProp(sb, "gap", v);
        });
        ifNotNull(this.colspan, (v) -> {
            appendJsonProp(sb, "colspan", v);
        });
        ifNotNull(this.rowspan, (v) -> {
            appendJsonProp(sb, "rowspan", v);
        });
        ifNotNull(this.padding, (v) -> {
            String json = v.toJSON();
            appendJsonToJsonProp(sb, "padding", json);
        });
        ifNotNull(this.margin, (v) -> {
            String json = v.toJSON();
            appendJsonToJsonProp(sb, "margin", json);
        });
        sb.append('}');

        return sb.toString();
    }
}
