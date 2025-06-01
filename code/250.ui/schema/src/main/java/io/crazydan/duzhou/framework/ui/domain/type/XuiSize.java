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

import java.util.Arrays;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.commons.UnitNumber;
import io.crazydan.duzhou.framework.lang.CodeSnippet;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

import static io.crazydan.duzhou.framework.commons.StringHelper.extractNumberAndUnit;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_DOMAIN_TYPE_UNKNOWN_SIZE;
import static io.nop.xlang.XLangErrors.ARG_NAMES;
import static io.nop.xlang.XLangErrors.ARG_VALUE;

/**
 * 尺寸
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
@DataBean
public class XuiSize implements IJsonSerializable, CodeSnippet {
    /** {@link XuiSize} 的单位 */
    public enum Unit {
        /**
         * 统一的基准尺寸单位
         * <p/>
         * 在不同平台，可根据其最佳规范将该单位的尺寸进行转换，如：
         * - 移动端（Native）：1u = 8dp（Android）/ 8pt（iOS）；
         * - Web端（HTML/CSS）：1u = 0.5rem（默认 1rem = 16px，即 1u = 8px）；
         */
        base("u"),

        /** 百分比 */
        percent("%"),

        /** 线条单位：代表最细的线条宽度 */
        a_line("i"),
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

    /**
     * @param vl
     *         其 {@link ValueWithLocation#getValue()} 只能为 {@link String} 类型，
     *         且其可以为 <code>${a.b.c}</code> 形式的动态表达式，也可以为
     *         <code>1u</code>、<code>50%</code> 等形式的尺寸常量
     * @return 在 {@link #parse} 对 {@link ValueWithLocation#getValue()}
     * 的解析结果为 <code>null</code> 时，返回 <code>null</code>
     */
    public static XuiExpression<XuiSize> expr(ValueWithLocation vl) {
        return XuiExpression.create(XuiSize.class, vl, XuiSize::parse);
    }

    public static XuiSize parse(SourceLocation loc, String s) {
        UnitNumber nut = extractNumberAndUnit(s);
        if (nut == null) {
            return null;
        }

        if (nut.number != null && nut.unit != null) {
            for (Unit unit : Unit.values()) {
                if (!unit.label.equals(nut.unit)) {
                    continue;
                }

                float value = nut.number.floatValue();
                return create(value, unit);
            }
        }

        throw new NopException(ERR_DOMAIN_TYPE_UNKNOWN_SIZE).loc(loc)
                                                            .param(ARG_VALUE, s)
                                                            .param(ARG_NAMES,
                                                                   Arrays.stream(Unit.values())
                                                                         .map(u -> u.label)
                                                                         .collect(Collectors.joining(", ")));
    }

    @Override
    public String toCodeSnippet(char strQuote) {
        return strQuote + toString() + strQuote;
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.stringValue(null, toString());
    }

    @Override
    public String toString() {
        return this.value + this.unit.label;
    }
}
