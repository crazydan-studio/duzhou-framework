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

package io.crazydan.duzhou.framework.ui;

import java.util.function.BiFunction;

import io.crazydan.duzhou.framework.lang.CodeSnippet;
import io.crazydan.duzhou.framework.lang.CodeSnippetPrinter;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.type.StdDataType;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;
import io.nop.xlang.api.IXLangCompileScope;
import io.nop.xlang.api.XLang;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.Literal;
import io.nop.xlang.xpl.IXplCompiler;
import io.nop.xlang.xpl.utils.XplParseHelper;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-15
 */
@DataBean
public class XuiExpression<T> implements ISourceLocationGetter, IJsonSerializable, CodeSnippet {
    private static final IXplCompiler cp = XLang.newXplCompiler();
    private static final IXLangCompileScope scope = cp.newCompileScope();

    public final SourceLocation loc;

    public final Class<T> type;
    public final Expression expr;

    XuiExpression(SourceLocation loc, Class<T> type, Expression expr) {
        this.loc = loc;

        this.type = type;
        this.expr = expr;
    }

    /**
     * @return 在 {@link ValueWithLocation#asString() vl.asString()} 满足 {@link XplParseHelper#hasExpr} 时，
     * 如，<code>${a.b.c}</code>，则根据该值构造模板表达式，否则，若 <code>literal</code> 对 <code>vl.asString()</code>
     * 的结果不为 <code>null</code>，则根据该结果构造 {@link Literal} 表达式，否则，直接返回 <code>null</code>
     */
    public static <T> XuiExpression<T> create(
            Class<T> type, ValueWithLocation vl, BiFunction<SourceLocation, String, T> literal
    ) {
        if (vl == null) {
            return null;
        }

        SourceLocation loc = vl.getLocation();
        String value = vl.asString();

        Expression expr;
        if (XplParseHelper.hasExpr(value)) {
            expr = XplParseHelper.parseTemplateExpr(vl, cp, scope);
        } else {
            T val = literal.apply(loc, value);
            expr = val != null ? Literal.valueOf(loc, val) : null;
        }

        return expr != null ? new XuiExpression<>(loc, type, expr) : null;
    }

    /** @see #create(Class, ValueWithLocation, BiFunction) */
    public static XuiExpression<?> create(StdDataType type, ValueWithLocation vl) {
        return create((Class<Object>) type.getJavaClass(), vl, (l, v) -> type.convert(vl.getValue()));
    }

    public static <T> XuiExpression<T> create(Class<T> type, Expression expr) {
        return new XuiExpression<>(expr.getLocation(), type, expr);
    }

    public void validate() {
        // TODO 字面量数据类型检查
        // TODO 表达式引用目标类型检查
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }

    @Override
    public String toCodeSnippet(char strQuote) {
        return CodeSnippetPrinter.create(strQuote).print(this.expr);
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        Object value;
        if (this.expr instanceof Literal) {
            value = ((Literal) this.expr).getValue();
        } else {
            String s = this.expr.toExprString();
            value = XplParseHelper.hasExpr(s) ? s : "${" + s + '}';
        }

        if (value instanceof IJsonSerializable) {
            ((IJsonSerializable) value).serializeToJson(out);
        } else {
            out.rawValue(null, value);
        }
    }

    @Override
    public String toString() {
        if (this.expr instanceof Literal) {
            return ((Literal) this.expr).getStringValue();
        }

        return this.expr.toExprString();
    }
}
