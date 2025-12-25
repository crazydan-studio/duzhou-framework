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

import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.api.core.util.SourceLocation;
import io.nop.xlang.api.IXLangCompileScope;
import io.nop.xlang.api.XLang;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.Identifier;
import io.nop.xlang.ast.Literal;
import io.nop.xlang.expr.ExprPhase;
import io.nop.xlang.xpl.IXplCompiler;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-12-22
 */
public class XuiExpr implements ISourceLocationGetter {
    private static final IXplCompiler cp = XLang.newXplCompiler();
    private static final IXLangCompileScope scope = cp.newCompileScope();

    private final SourceLocation loc;

    public final Expression expr;

    public static boolean isExpr(String s) {
        return s != null && s.endsWith("}") && s.startsWith("${");
    }

    /** @return 若 {@code source} 不是表达式，则将其构造为字面量表达式 */
    public static XuiExpr create(SourceLocation loc, String source) {
        Expression expr;
        if (!isExpr(source)) {
            expr = Literal.valueOf(loc, source);
        } else {
            expr = cp.parseTemplateExpr(loc, source, true, ExprPhase.eval, scope);
        }

        return new XuiExpr(loc, expr);
    }

    XuiExpr(SourceLocation loc, Expression expr) {
        this.loc = loc;
        this.expr = expr;
    }

    /** 是否为变量名 */
    public boolean isIdentifier() {
        return this.expr instanceof Identifier;
    }

    /** 获取变量的名字 */
    public String getIdentifierName() {
        return isIdentifier() ? ((Identifier) this.expr).getName() : null;
    }

    /** 是否为字面量 */
    public boolean isLiteral() {
        return this.expr instanceof Literal;
    }

    /** 获取字面量的值（字符串） */
    public String getLiteralValue() {
        return isLiteral() ? ((Literal) this.expr).getStringValue() : null;
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }
}
