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

    public String toJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');
        if (this.gap != null) {
            if (sb.length() > 1) {
                sb.append("\n  , ");
            }
            sb.append("\"gap\": \"").append(this.gap).append('"');
        }
        if (this.colspan != null) {
            if (sb.length() > 1) {
                sb.append("\n  , ");
            }
            sb.append("\"colspan\": ").append(this.colspan);
        }
        if (this.rowspan != null) {
            if (sb.length() > 1) {
                sb.append("\n  , ");
            }
            sb.append("\"rowspan\": ").append(this.rowspan);
        }
        if (this.padding != null) {
            String json = this.padding.toJSON();
            if (json.length() > 2) {
                json = json.replaceAll("(?m)^", "    ").trim();

                if (sb.length() > 1) {
                    sb.append("\n  , ");
                }
                sb.append("\"padding\": ").append(json);
            }
        }
        if (this.margin != null) {
            String json = this.margin.toJSON();
            if (json.length() > 2) {
                json = json.replaceAll("(?m)^", "    ").trim();

                if (sb.length() > 1) {
                    sb.append("\n  , ");
                }
                sb.append("\"margin\": ").append(json);
            }
        }
        sb.append('}');

        return sb.toString();
    }
}
