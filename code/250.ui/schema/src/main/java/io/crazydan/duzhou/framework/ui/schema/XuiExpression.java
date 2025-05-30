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

package io.crazydan.duzhou.framework.ui.schema;

import java.util.function.BiFunction;

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
import io.nop.xlang.ast.TemplateExpression;
import io.nop.xlang.xpl.IXplCompiler;
import io.nop.xlang.xpl.utils.XplParseHelper;

import static io.nop.api.core.util.ApiStringHelper.escape;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-15
 */
@DataBean
public class XuiExpression<T> implements ISourceLocationGetter, IJsonSerializable {
    private static final IXplCompiler cp = XLang.newXplCompiler();
    private static final IXLangCompileScope scope = cp.newCompileScope();

    private final SourceLocation loc;

    private final Class<T> type;
    private final Expression expr;

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

    public void validate() {
        // TODO 字面量数据类型检查
        // TODO 表达式引用目标类型检查
    }

    /** @return 获取常量值，或者表达式 */
    public Object getVariable() {
        if (this.expr instanceof Literal) {
            return ((Literal) this.expr).getValue();
        } else {
            return this.expr.toExprString();
        }
    }

    /**
     * 转换为表达式，其中，字符串字面量使用 <code>strQuote</code> 包裹
     * <pre>
     * Hello, ${username} ==> 'Hello, ' + (username)
     * </pre>
     * <pre>
     * a + b = ${a + b} ==> 'a + b = ' + (a + b)
     * </pre>
     */
    public String toExprString(char strQuote) {
        if (this.expr instanceof Literal) {
            return literalToExprString((Literal) this.expr, strQuote);
        }

        if (!(this.expr instanceof TemplateExpression)) {
            return this.expr.toExprString();
        }

        StringBuilder sb = new StringBuilder();
        ((TemplateExpression) this.expr).getExpressions().forEach((expr) -> {
            if (sb.length() > 0) {
                sb.append(" + ");
            }

            String val;
            if (expr instanceof Literal) {
                val = literalToExprString((Literal) expr, strQuote);
            } else {
                val = "(" + expr.toExprString() + ')';
            }
            sb.append(val);
        });

        return sb.toString();
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
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

    private static String literalToExprString(Literal expr, char strQuote) {
        Object val = expr.getValue();

        if (val instanceof Number || val instanceof Boolean) {
            return val.toString();
        } else if (val instanceof String) {
            return strQuote
                   + escape(val.toString(), new char[] { strQuote }, new String[] { "\\" + strQuote })
                   + strQuote;
        } else if (val != null) {
            throw new IllegalStateException("Unsupported literal " + val.getClass().getName() + "[" + val + "]");
        }
        return null;
    }
}
