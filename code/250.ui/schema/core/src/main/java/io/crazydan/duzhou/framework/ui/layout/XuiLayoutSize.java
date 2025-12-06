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

package io.crazydan.duzhou.framework.ui.layout;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.nop.api.core.annotations.core.Option;
import io.nop.api.core.annotations.core.StaticFactoryMethod;

/**
 * 尺寸类型
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-12-06
 */
public enum XuiLayoutSize {
    /** 与父容器相同 */
    @Option("match-parent") match_parent,
    /** 占满父容器的剩余空间 */
    @Option("fill-remains") fill_remains,
    /** 自适应内容 */
    @Option("wrap-content") wrap_content,

    /** 设定值 */
    @Option("value-specified") value_specified,
    ;

    @StaticFactoryMethod
    public static XuiLayoutSize fromText(String text) {
        return StringHelper.isBlank(text) //
               ? null : valueOf(text.replace('-', '_'));
    }
}
