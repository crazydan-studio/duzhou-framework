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

import static io.crazydan.duzhou.framework.commons.StringHelper.trimToNull;

/**
 * 尺寸
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
public class XuiSize {
    /** {@link XuiSize} 的单位 */
    public enum Unit {
        /**
         * 统一的基准尺寸单位
         * <p/>
         * 在不同平台，可根据其最佳规范将该单位的尺寸进行转换，如：
         * - 移动端（Native）：1x = 8dp（Android）/ 8pt（iOS）；
         * - Web端（HTML/CSS）：1x = 0.5rem（默认 1rem = 16px，即 1x = 8px）；
         */
        base("x"),

        /** 百分比 */
        percent("%"),
        ;

        public final String label;

        Unit(String label) {this.label = label;}
    }

    /** 值 */
    public final float value;
    /** 单位 */
    public final Unit unit;

    XuiSize(float value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static XuiSize create(float value, Unit unit) {
        return new XuiSize(value, unit);
    }

    public static XuiSize parse(String s) {
        s = trimToNull(s);
        if (s == null) {
            return null;
        }

        for (Unit unit : Unit.values()) {
            if (!s.endsWith(unit.label)) {
                continue;
            }

            s = s.substring(0, s.length() - unit.label.length());

            float value = Float.parseFloat(s);
            return create(value, unit);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value + this.unit.label;
    }
}
