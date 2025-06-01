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

import java.util.Map;

import io.crazydan.duzhou.framework.commons.UnitNumber;
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.lang.CodeSnippet;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.nop.api.core.annotations.core.Description;
import io.nop.api.core.config.IConfigReference;
import io.nop.api.core.util.SourceLocation;
import io.nop.xlang.ast.Literal;

import static com.google.common.base.MoreObjects.firstNonNull;
import static io.crazydan.duzhou.framework.commons.StringHelper.extractNumberAndUnit;
import static io.nop.api.core.config.AppConfig.varRef;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-17
 */
public abstract class XuiGenConfig {
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

    public XuiGenConfig() {
        this.fontSize = extractNumberAndUnit(CFG_FONT_SIZE.get());
        this.baseSize = extractNumberAndUnit(CFG_BASE_SIZE.get());
        this.lineSize = extractNumberAndUnit(CFG_LINE_SIZE.get());
    }

    /**
     * 将 {@link CodeSnippet} 转换为运行时 HTML 组件的属性
     * <p/>
     *
     * Svelte:
     * <pre>
     * name={props.username}
     * </pre>
     * Vue:
     * <pre>
     * :name="props.username"
     * </pre>
     *
     * @return 若 {@link #toCodeSnippet toCodeSnippet(snippet)}
     * 的结果为 <code>null</code>，则返回空字符串
     */
    public abstract String toHtmlAttr(String name, CodeSnippet snippet);

    /**
     * 将 {@link CodeSnippet} 转换为运行时 HTML 文本
     * <p/>
     * 注意，对 XSS 攻击的预防由运行时自动处理
     * <p/><br/><br/>
     *
     * Svelte:
     * <pre>
     * {'Your name is ' + props.username}
     * </pre>
     * Vue:
     * <pre>
     * {{'Your name is ' + props.username}}
     * </pre>
     *
     * @return 若 {@link #toCodeSnippet toCodeSnippet(snippet)}
     * 的结果为 <code>null</code>，则返回空模板，如 <code>{}</code> 或 <code>{{}}</code>
     */
    public String toHtmlText(CodeSnippet snippet) {
        String code = toCodeSnippet(snippet);

        return getExprPrefix() + firstNonNull(code, "") + getExprSuffix();
    }

    /**
     * 将 <code>props</code> 转换为运行时 HTML 组件属性
     * <p/>
     *
     * Svelte:
     * <pre>
     * name={props.name} address={props.address}
     * </pre>
     * Vue:
     * <pre>
     * :name="props.name" :address="props.address"
     * </pre>
     */
    public String toHtmlAttrs(Map<String, Object> props) {
        StringBuilder sb = new StringBuilder();

        props.forEach((prop, value) -> {
            if (value == null) {
                return;
            }

            if (!(value instanceof CodeSnippet)) {
                value = XuiExpression.create(value.getClass(), Literal.valueOf(null, value));
            }

            String s = toHtmlAttr(prop, (CodeSnippet) value);
            if (s.isEmpty()) {
                return;
            }

            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(s);
        });

        return sb.toString();
    }

    protected String toCodeSnippet(CodeSnippet snippet) {
        return snippet != null ? snippet.toCodeSnippet('\'') : null;
    }

    /** 将 {@link XuiSize} 转换为运行时尺寸 */
    private UnitNumber fromXuiSize(XuiSize size) {
        UnitNumber base = getBaseSize();
        switch (size.unit) {
            case percent: {
                return UnitNumber.create(size.value, "%");
            }
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
