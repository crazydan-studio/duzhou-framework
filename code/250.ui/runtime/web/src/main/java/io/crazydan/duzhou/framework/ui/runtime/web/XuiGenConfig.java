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

package io.crazydan.duzhou.framework.ui.runtime.web;

import io.crazydan.duzhou.framework.commons.UnitNumber;
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutGap;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSize;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSpacing;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSpan;
import io.nop.api.core.annotations.core.Description;
import io.nop.api.core.config.IConfigReference;
import io.nop.api.core.util.SourceLocation;

import static io.crazydan.duzhou.framework.commons.StringHelper.extractNumberAndUnit;
import static io.nop.api.core.config.AppConfig.varRef;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-17
 */
public class XuiGenConfig {
    private static final SourceLocation s_loc = SourceLocation.fromClass(XuiGenConfig.class);

    @Description("字体大小，即 1rem 对应的尺寸")
    public static final IConfigReference<String> CFG_FONT_SIZE = varRef(s_loc,
                                                                        "duzhou.ui.runtime.web.font-size",
                                                                        String.class,
                                                                        "16px");
    @Description("1u 对应的实际尺寸")
    public static final IConfigReference<String> CFG_BASE_SIZE = varRef(s_loc,
                                                                        "duzhou.ui.runtime.web.base-size",
                                                                        String.class,
                                                                        "0.5rem");
    @Description("1i 对应的实际尺寸")
    public static final IConfigReference<String> CFG_LINE_SIZE = varRef(s_loc,
                                                                        "duzhou.ui.runtime.web.line-size",
                                                                        String.class,
                                                                        "1px");

    /** 表达式模板前缀 */
    private String exprPrefix;
    /** 表达式模板后缀 */
    private String exprSuffix;

    /** 字体尺寸 */
    private UnitNumber fontSize;
    /** 一个 {@link XuiSize.Unit#base} 单位对应的尺寸 */
    private UnitNumber baseSize;
    /** 一个 {@link XuiSize.Unit#a_line} 单位对应的尺寸 */
    private UnitNumber lineSize;

    XuiGenConfig() {
    }

    public static XuiGenConfig create() {
        XuiGenConfig config = new XuiGenConfig();

        config.fontSize = extractNumberAndUnit(CFG_FONT_SIZE.get());
        config.baseSize = extractNumberAndUnit(CFG_BASE_SIZE.get());
        config.lineSize = extractNumberAndUnit(CFG_LINE_SIZE.get());

        return config;
    }

    /** @return 返回结果如：<code>{ 2 }</code>、<code>{ 'abc' + props.size }</code> 或 <code>{ props.size }</code> */
    public String toXmlAttrExpr(XuiExpression<?> expr) {
        Object var = expr != null ? expr.toExprString('\'') : null;

        return var != null ? this.exprPrefix + var + this.exprSuffix : null;
    }

    public String toXmlAttrExpr(XuiLayoutSize value) {
        return value != null ? value.toXmlAttrExpr(this.exprPrefix, this.exprSuffix, this::fromXuiSize) : null;
    }

    public String toXmlAttrExpr(XuiLayoutGap value) {
        return value != null ? value.toXmlAttrExpr(this.exprPrefix, this.exprSuffix, this::fromXuiSize) : null;
    }

    public String toXmlAttrExpr(XuiLayoutSpacing value) {
        return value != null ? value.toXmlAttrExpr(this.exprPrefix, this.exprSuffix, this::fromXuiSize) : null;
    }

    public String toXmlAttrExpr(XuiLayoutAlign value) {
        return value != null ? value.toXmlAttrExpr(this.exprPrefix, this.exprSuffix) : null;
    }

    public String toXmlAttrExpr(XuiLayoutSpan value) {
        return value != null ? value.toXmlAttrExpr(this.exprPrefix, this.exprSuffix) : null;
    }

    /** 将 {@link XuiSize} 转换为运行时尺寸 */
    private UnitNumber fromXuiSize(XuiSize size) {
        if (size.unit == XuiSize.Unit.percent) {
            return UnitNumber.create(size.value, "%");
        }

        UnitNumber base = getBaseSize();
        switch (size.unit) {
            case a_line: {
                base = getLineSize();
                break;
            }
        }

        float value = size.value * base.number.floatValue();

        return UnitNumber.create(value, base.unit);
    }

    public String getExprPrefix() {
        return this.exprPrefix;
    }

    public void setExprPrefix(String exprPrefix) {
        this.exprPrefix = exprPrefix;
    }

    public String getExprSuffix() {
        return this.exprSuffix;
    }

    public void setExprSuffix(String exprSuffix) {
        this.exprSuffix = exprSuffix;
    }

    public UnitNumber getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(UnitNumber fontSize) {
        this.fontSize = fontSize;
    }

    public UnitNumber getBaseSize() {
        return this.baseSize;
    }

    public void setBaseSize(UnitNumber baseSize) {
        this.baseSize = baseSize;
    }

    public UnitNumber getLineSize() {
        return this.lineSize;
    }

    public void setLineSize(UnitNumber lineSize) {
        this.lineSize = lineSize;
    }
}
