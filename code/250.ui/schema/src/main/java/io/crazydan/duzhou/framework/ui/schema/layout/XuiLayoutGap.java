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

import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.appendJsonProp;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局间隔
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
public class XuiLayoutGap {
    /** 水平方向的间隔 */
    public final XuiSize h;
    /** 垂直方向的间隔 */
    public final XuiSize v;

    XuiLayoutGap(XuiSize h, XuiSize v) {
        this.h = h;
        this.v = v;
    }

    /**
     * @param value
     *         只能为 <code>null</code>、<code>String</code> 或 <code>Map</code>
     */
    public static XuiLayoutGap create(Object value) {
        if (value instanceof String || value == null) {
            XuiSize size = XuiSize.parse((String) value);

            return new XuiLayoutGap(size, size);
        }

        assert value instanceof Map;
        Map<String, Object> props = (Map<String, Object>) value;

        XuiSize h = XuiSize.parse((String) props.get("h"));
        XuiSize v = XuiSize.parse((String) props.get("v"));

        return new XuiLayoutGap(h, v);
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');
        ifNotNull(this.h, (v) -> {
            appendJsonProp(sb, "h", v);
        });
        ifNotNull(this.v, (v) -> {
            appendJsonProp(sb, "v", v);
        });
        sb.append('}');

        return sb.toString();
    }
}
