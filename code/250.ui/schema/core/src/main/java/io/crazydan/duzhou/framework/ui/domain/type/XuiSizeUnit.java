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

/** {@link XuiSize} 的单位 */
public enum XuiSizeUnit {
    /**
     * 统一的基准尺寸单位
     * <p/>
     * 在不同平台，可根据其最佳规范将该单位的尺寸进行转换，如：
     * - 移动端（Native）：1u = 8dp（Android）/ 8pt（iOS）；
     * - Web端（HTML/CSS）：1u = 0.5rem（默认 1rem = 16px，即 1u = 8px）；
     */
    @Option("u") base("u"),

    /** 百分比 */
    @Option("%") percent("%"),

    /** 线条单位：代表最细的线条宽度 */
    @Option("i") a_line("i"),
    ;

    public final String code;

    XuiSizeUnit(String code) {
        this.code = code;
    }

    @StaticFactoryMethod
    public static XuiSizeUnit fromText(String text) {
        if (text != null) {
            for (XuiSizeUnit v : values()) {
                if (v.code.equals(text)) {
                    return v;
                }
            }
        }
        return null;
    }
}
