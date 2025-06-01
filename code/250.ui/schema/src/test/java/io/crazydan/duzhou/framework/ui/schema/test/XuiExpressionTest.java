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

import java.util.HashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.xlang.api.IXLangCompileScope;
import io.nop.xlang.api.XLang;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.Literal;
import io.nop.xlang.xpl.IXplCompiler;
import io.nop.xlang.xpl.utils.XplParseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-15
 */
public class XuiExpressionTest extends NopJunitTestCase {

    @Test
    public void test_parse_nop_xpl_expression() {
        Map<String, String> samples = new HashMap<>() {{
            put("1u", "\"1u\"");
            put("${props.size}", "props.size");
            put("${props.size + props.gap}", "props.size + props.gap");
            put("${props.name.substring(0, 10)}", "props.name.substring(0,10)");
            put("${props.size > 10}", "props.size > 10");
            put("${props.name != null}", "props.name != null");
            put("${!props.disabled}", "!props.disabled");
            put("Size is ${props.size}", "Size is ${props.size}");
            put("Disabled ${!props.disabled && !state.started}", "Disabled ${!props.disabled && !state.started}");
            put("Result is ${props.size > 10}", "Result is ${props.size > 10}");
        }};
        samples.forEach((sample, expected) -> {
            Expression expr = expr(sample);
            String actual = expr.toExprString();

            Assertions.assertEquals(expected, actual);
        });
    }

    private Expression expr(String value) {
        IXplCompiler cp = XLang.newXplCompiler();
        IXLangCompileScope scope = cp.newCompileScope();

        Expression expr;
        ValueWithLocation vl = ValueWithLocation.of(null, value);
        if (XplParseHelper.hasExpr(value)) {
            expr = XplParseHelper.parseTemplateExpr(vl, cp, scope);
        } else {
            expr = Literal.valueOf(vl.getLocation(), vl.getValue());
        }
        return expr;
    }
}
