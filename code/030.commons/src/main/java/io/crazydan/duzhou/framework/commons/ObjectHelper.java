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

package io.crazydan.duzhou.framework.commons;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-12
 */
public class ObjectHelper {

    /** 在值不为 {@code null} 时，执行指定逻辑 */
    public static <T> void ifNotNull(T obj, Consumer<T> consumer) {
        if (obj != null) {
            consumer.accept(obj);
        }
    }

    /** 在值不为 {@code null} 时，返回运算结果，否则，返回 {@code null} */
    public static <T, R> R ifNotNullThenGet(T obj, Function<T, R> getter) {
        if (obj != null) {
            return getter.apply(obj);
        }
        return null;
    }

    /** 取第一个非 {@code null} 值 */
    @SafeVarargs
    public static <T> T firstNonNull(T... values) {
        for (T value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
