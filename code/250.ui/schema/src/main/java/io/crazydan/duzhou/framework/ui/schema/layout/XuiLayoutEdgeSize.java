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

    public String toJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');
        if (this.left != null) {
            if (sb.length() > 1) {
                sb.append("\n  , ");
            }
            sb.append("\"left\": \"").append(this.left).append('"');
        }
        if (this.right != null) {
            if (sb.length() > 1) {
                sb.append("\n  , ");
            }
            sb.append("\"right\": \"").append(this.right).append('"');
        }
        if (this.top != null) {
            if (sb.length() > 1) {
                sb.append("\n  , ");
            }
            sb.append("\"top\": \"").append(this.top).append('"');
        }
        if (this.bottom != null) {
            if (sb.length() > 1) {
                sb.append("\n  , ");
            }
            sb.append("\"bottom\": \"").append(this.bottom).append('"');
        }
        sb.append('}');

        return sb.toString();
    }
}
