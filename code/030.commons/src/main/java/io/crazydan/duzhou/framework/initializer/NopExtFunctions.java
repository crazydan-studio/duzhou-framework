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

package io.crazydan.duzhou.framework.initializer;

import io.nop.api.core.annotations.core.Description;
import io.nop.api.core.annotations.core.Name;
import io.nop.api.core.annotations.lang.Macro;
import io.nop.api.core.exceptions.NopEvalException;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.SourceLocation;
import io.nop.xlang.api.IXLangCompileScope;
import io.nop.xlang.ast.CallExpression;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.NamedTypeNode;
import io.nop.xlang.ast.NewExpression;
import io.nop.xlang.ast.ThrowStatement;
import io.nop.xlang.ast.TypeNameNode;

import static io.nop.xlang.XLangErrors.ARG_MIN_COUNT;
import static io.nop.xlang.XLangErrors.ERR_EXEC_TOO_FEW_ARGS;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-25
 */
public class NopExtFunctions {

    @Description("在 xpl 脚本中抛出 NopException 类型的异常，其传参与 NopException 的构造参数相同")
    @Macro
    public static Expression throwError(@Name("scope") IXLangCompileScope scope, @Name("expr") CallExpression expr) {
        if (expr.getArguments().isEmpty()) {
            throw new NopEvalException(ERR_EXEC_TOO_FEW_ARGS).param(ARG_MIN_COUNT, 1);
        }
        // 将参数的 AST 父节点置空
        expr.getArguments().forEach(arg -> arg.setASTParent(null));

        SourceLocation loc = expr.getLocation();

        // 构造 new NopException(...)
        NamedTypeNode type = TypeNameNode.fromType(loc, NopException.class);
        NewExpression value = NewExpression.valueOf(loc, type, expr.getArguments());

        // 构造 throw ...
        return ThrowStatement.valueOf(loc, value);
    }
}
