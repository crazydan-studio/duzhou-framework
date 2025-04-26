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

/**
 * 布局尺寸
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-26
 */
public class XuiLayoutSize {

    /** 布局尺寸类型 */
    public enum Type {
        /** 与父容器相同 */
        match_parent,
        /** 占满剩余空间 */
        fill_remains,
        /** 自适应内容 */
        wrap_content,
        /** 用户设定值 */
        user_specified,
    }

    /** 类型 */
    private final Type type;

    /** {@link Type#user_specified} 对应的值 */
    private final Object value;

    public XuiLayoutSize(Type type) {
        this.type = type;
        this.value = null;
    }

    public XuiLayoutSize(Object value) {
        this.type = Type.user_specified;
        this.value = value;
    }

    public Type getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }
}
