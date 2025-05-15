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
 * 布局空白
 * <p/>
 * 用于内边距、外边距的配置
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-10
 */
@DataBean
public class XuiLayoutSpacing implements IJsonSerializable {
    public final XuiSize left;
    public final XuiSize right;
    public final XuiSize top;
    public final XuiSize bottom;

    XuiLayoutSpacing(XuiSize left, XuiSize right, XuiSize top, XuiSize bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * @param value
     *         只能为 <code>null</code>、<code>String</code> 或 <code>Map</code>
     */
    public static XuiLayoutSpacing create(Object value) {
        if (value instanceof String || value == null) {
            XuiSize size = XuiSize.parse((String) value);

            return new XuiLayoutSpacing(size, size, size, size);
        }

        assert value instanceof Map;
        Map<String, Object> props = (Map<String, Object>) value;

        XuiSize left = XuiSize.parse((String) props.get("left"));
        XuiSize right = XuiSize.parse((String) props.get("right"));
        XuiSize top = XuiSize.parse((String) props.get("top"));
        XuiSize bottom = XuiSize.parse((String) props.get("bottom"));

        return new XuiLayoutSpacing(left, right, top, bottom);
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.beginObject(null);

        out.putNotNull("left", this.left);
        out.putNotNull("right", this.right);
        out.putNotNull("top", this.top);
        out.putNotNull("bottom", this.bottom);

        out.endObject();
    }
}
