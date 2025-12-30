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

package io.crazydan.duzhou.framework.ui.domain.type;

import io.nop.api.core.annotations.core.Option;
import io.nop.api.core.annotations.core.StaticFactoryMethod;

/**
 * {@link XuiSize} 的类型
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-12-06
 */
public enum XuiSizeType {
    /** 与视口尺寸相同 */
    @Option("match-viewport") match_viewport("match-viewport"),

    /** 与父容器尺寸相同 */
    @Option("match-parent") match_parent("match-parent"),
    /** 占满父容器的剩余空间 */
    @Option("fill-remaining") fill_remaining("fill-remaining"),

    /** 自适应内容尺寸 */
    @Option("fit-content") fit_content("fit-content"),

    /** 设定值 */
    @Option("value-specified") value_specified("value-specified"),
    ;

    public final String code;

    XuiSizeType(String code) {
        this.code = code;
    }

    @StaticFactoryMethod
    public static XuiSizeType fromText(String text) {
        if (text != null) {
            for (XuiSizeType v : values()) {
                if (v.code.equals(text) || v.name().equals(text)) {
                    return v;
                }
            }
        }
        return null;
    }
}
