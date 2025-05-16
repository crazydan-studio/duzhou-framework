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

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-05-03
 */
public class StringHelper extends io.nop.commons.util.StringHelper {

    public static String trimToNull(String str) {
        return trimToNull(str, null);
    }

    public static String trimToNull(String str, String defaultValue) {
        str = strip(str);
        str = emptyAsNull(str);

        return firstNonNull(str, defaultValue);
    }

    public static Integer trimAndParseInt(String str, int radix) {
        return trimAndParseInt(str, radix, null);
    }

    public static Integer trimAndParseInt(String str, int radix, Integer defaultValue) {
        str = trimToNull(str);
        Integer n = parseInt(str, radix);

        return firstNonNull(n, defaultValue);
    }

    public static NumberAndUnit extractNumberAndUnit(String str) {
        str = trimToNull(str);
        if (str == null) {
            return null;
        }

        int pos = str.length();
        for (; pos > 0; pos -= 1) {
            char ch = str.charAt(pos - 1);
            if (isDigit(ch)) {
                break;
            }
        }

        String number = pos > 0 ? str.substring(0, pos) : null;
        String unit = pos < str.length() ? str.substring(pos) : null;

        return new NumberAndUnit(tryParseNumber(number), unit);
    }

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

    public static class NumberAndUnit {
        public final Number number;
        public final String unit;

        NumberAndUnit(Number number, String unit) {
            this.number = number;
            this.unit = unit;
        }
    }
}
