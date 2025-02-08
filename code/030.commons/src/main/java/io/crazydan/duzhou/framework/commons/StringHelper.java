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

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-05-03
 */
public class StringHelper {

    /**
     * @see #snakeCase(String, boolean, boolean)
     */
    public static String snakeCase(String str) {
        return snakeCase(str, false, false);
    }

    /**
     * @param upper
     *         是否全部转为大写
     * @see #snakeCase(String, boolean, boolean)
     */
    public static String snakeCase(String str, boolean upper) {
        return snakeCase(str, upper, false);
    }

    /**
     * <pre>
     * abCd -> abcd
     * abCD -> abcd
     * ab-Cd -> ab_cd
     * ab_Cd -> ab_cd
     * ab cd -> ab_cd
     * ab%-cd -> ab_cd
     * ab_cd -> ab_cd
     * ab__cd -> ab__cd
     * ab _cd -> ab__cd
     * </pre>
     *
     * @param upper
     *         是否全部转为大写
     * @param hyphen
     *         是否使用短横线分隔
     */
    public static String snakeCase(String str, boolean upper, boolean hyphen) {
        if (str == null) {
            return null;
        }

        // a--b -> a_b
        str = str.trim().replaceAll("([^a-zA-Z0-9_]+)", "_");
        str = hyphen ? str.replaceAll("_", "-") : str;

        return upper ? str.toUpperCase() : str.toLowerCase();
    }
}
