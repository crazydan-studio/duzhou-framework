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

import java.util.HashMap;
import java.util.Map;

import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;
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
public class XuiExpression<T> {
    private final SourceLocation loc;
    private final Class<T> type;
    private final Expression expr;

    XuiExpression(SourceLocation loc, Class<T> type, Expression expr) {
        this.loc = loc;
        this.type = type;
        this.expr = expr;
    }

    public static <T> XuiExpression<T> create(Class<T> type, SourceLocation loc, Object value) {
        Expression expr;

        if (XplParseHelper.hasExpr(value.toString())) {
            ValueWithLocation vl = ValueWithLocation.of(loc, value);

            IXplCompiler cp = XLang.newXplCompiler();
            IXLangCompileScope scope = cp.newCompileScope();

            expr = XplParseHelper.parseTemplateExpr(vl, cp, scope);
        } else {
            expr = Literal.valueOf(loc, value);
        }

        return new XuiExpression<>(loc, type, expr);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        // [a1](${props.layout}) 可以拆解为：
        // - gap: ${props.layout.gap}
        // - span: ${props.layout.span}
        // - padding: ${props.layout.padding}

        return map;
    }

    public void validate() {
        // TODO 字面量数据类型检查
        // TODO 表达式引用目标类型检查
    }
}
