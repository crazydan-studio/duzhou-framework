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
import io.nop.xlang.xdef.XDefTypeDecl;
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

    public final XDefTypeDecl type;
    public final Expression expr;

    XuiExpr(SourceLocation loc, XDefTypeDecl type, Expression expr) {
        this.loc = loc;
        this.type = type;
        this.expr = expr;
    }

    public static XuiExpr create(SourceLocation loc, XDefTypeDecl type, String source) {
        Expression expr = cp.parseSimpleExpr(loc, source, scope);

        return new XuiExpr(loc, type, expr);
    }

    public static boolean isExpr(String s) {
        return s != null && s.endsWith("}") && s.startsWith("${");
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }
}
