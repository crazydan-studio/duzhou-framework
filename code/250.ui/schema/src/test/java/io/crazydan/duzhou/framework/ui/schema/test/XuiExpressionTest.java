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

package io.crazydan.duzhou.framework.ui.schema.test;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.xlang.api.IXLangCompileScope;
import io.nop.xlang.api.XLang;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.Literal;
import io.nop.xlang.xpl.IXplCompiler;
import io.nop.xlang.xpl.utils.XplParseHelper;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-15
 */
public class XuiExpressionTest extends NopJunitTestCase {

    @Test
    public void test_parse_nop_xpl_expression() {
        String[] samples = new String[] {
                "1x", "${props.size}", "Size is ${props.size}",
                };

        for (String sample : samples) {
            Expression expr = create(sample);
            this.log.info("Expression: {}", expr);
        }
    }

    private Expression create(String s) {
        ValueWithLocation value = ValueWithLocation.of(null, s);
        IXplCompiler cp = XLang.newXplCompiler();
        IXLangCompileScope scope = cp.newCompileScope();

        Expression expr;
        if (XplParseHelper.hasExpr(value.asString())) {
            expr = XplParseHelper.parseTemplateExpr(value, cp, scope);
        } else {
            expr = Literal.valueOf(value.getLocation(), value.getValue());
        }
        return expr;
    }
}
