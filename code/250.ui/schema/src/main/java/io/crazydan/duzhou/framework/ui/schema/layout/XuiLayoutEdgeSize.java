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

import static io.crazydan.duzhou.framework.commons.ObjectHelper.appendJsonProp;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局项边框尺寸
 * <p/>
 * 用于内边距、外边距的配置
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-10
 */
public class XuiLayoutEdgeSize {
    public final String left;
    public final String right;
    public final String top;
    public final String bottom;

    public XuiLayoutEdgeSize(Map<String, Object> props) {
        if (props == null) {
            props = new HashMap<>();
        }

        this.left = (String) props.get("left");
        this.right = (String) props.get("right");
        this.top = (String) props.get("top");
        this.bottom = (String) props.get("bottom");
    }

    public Map<String, Object> toMap() {
        Map<String, Object> props = new HashMap<>();

        ifNotNull(this.left, (v) -> props.put("left", v));
        ifNotNull(this.right, (v) -> props.put("right", v));
        ifNotNull(this.top, (v) -> props.put("top", v));
        ifNotNull(this.bottom, (v) -> props.put("bottom", v));

        return props;
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');
        ifNotNull(this.left, (v) -> {
            appendJsonProp(sb, "left", v);
        });
        ifNotNull(this.right, (v) -> {
            appendJsonProp(sb, "right", v);
        });
        ifNotNull(this.top, (v) -> {
            appendJsonProp(sb, "top", v);
        });
        ifNotNull(this.bottom, (v) -> {
            appendJsonProp(sb, "bottom", v);
        });
        sb.append('}');

        return sb.toString();
    }
}
