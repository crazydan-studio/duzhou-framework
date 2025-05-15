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
import io.nop.api.core.annotations.data.DataBean;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

/**
 * 布局间隔
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
@DataBean
public class XuiLayoutGap implements IJsonSerializable {
    /** 水平方向上的间隔 */
    public final XuiSize row;
    /** 垂直方向上的间隔 */
    public final XuiSize col;

    XuiLayoutGap(XuiSize row, XuiSize col) {
        this.row = row;
        this.col = col;
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

        XuiSize row = XuiSize.parse((String) props.get("row"));
        XuiSize col = XuiSize.parse((String) props.get("col"));

        return new XuiLayoutGap(row, col);
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.beginObject(null);

        out.putNotNull("row", this.row);
        out.putNotNull("col", this.col);

        out.endObject();
    }
}
