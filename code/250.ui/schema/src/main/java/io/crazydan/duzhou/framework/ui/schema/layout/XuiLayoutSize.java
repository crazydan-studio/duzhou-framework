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
    private static final XuiLayoutSize match_parent = new XuiLayoutSize(Type.match_parent);
    private static final XuiLayoutSize fill_remains = new XuiLayoutSize(Type.fill_remains);
    private static final XuiLayoutSize wrap_content = new XuiLayoutSize(Type.wrap_content);

    /** {@link XuiLayoutSize} 类型 */
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
    public final Type type;
    /** {@link Type#user_specified} 对应的值 */
    public final Object value;

    XuiLayoutSize(Type type) {
        this.type = type;
        this.value = null;
    }

    XuiLayoutSize(Object value) {
        this.type = Type.user_specified;
        this.value = value;
    }

    public static XuiLayoutSize match_parent() {
        return match_parent;
    }

    public static XuiLayoutSize fill_remains() {
        return fill_remains;
    }

    public static XuiLayoutSize wrap_content() {
        return wrap_content;
    }

    public static XuiLayoutSize user_specified(Object value) {
        return new XuiLayoutSize(value);
    }
}
