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

package io.crazydan.duzhou.framework.test.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.lang.CodeSnippetPrinter;
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
 * @date 2025-06-01
 */
public class CodeSnippetPrinterTest {

    @Test
    public void test_print_Expression() {
        Map<Object, String> samples = new HashMap<>() {{
            put(true, "true");
            put(100, "100");
            put(100.5f, "100.5");
            put("1u", "\"1u\"");
            put("${props.size}", "props.size");
            put("${props.size + props.gap}", "props.size + props.gap");
            put("${props.name.substring(0, 10)}", "props.name.substring(0,10)");
            put("${props.size > 10}", "props.size > 10");
            put("${props.name != null}", "props.name != null");
            put("${!props.disabled}", "!props.disabled");
            put("${!props.disabled && !state.started}", "!props.disabled && !state.started");
            put("Size's ${props.size}", "\"Size\\'s \" + (props.size)");
            put("a = 3", "\"a = 3\"");
            put("a = ${null}", "\"a = \" + null");
            put("a = ${'a'}", "\"a = \" + \"a\"");
            put("a = ${'a' + 'b'}", "\"a = \" + (\"a\" + \"b\")");
            put("a = ${a + 'b'}", "\"a = \" + (a + \"b\")");
            put("a = ${3}", "\"a = \" + 3");
            put("a = ${true}", "\"a = \" + true");
            put("a = ${3 + 2}", "\"a = \" + (3 + 2)");
            put("a + b = ${a + b}", "\"a + b = \" + (a + b)");
            put("a + b = ${a + b}, a = ${a} and b = ${b}",
                "\"a + b = \" + (a + b) + \", a = \" + (a) + \" and b = \" + (b)");
            //
            put(new LinkedHashMap<>() {{
                put("a", 5);
                put("b", 5.5f);
                put("c", "abc");
                put("d", expr("a = ${5}"));
                put("e", expr("${a+5}"));
            }}, "{a:5,b:5.5,c:\"abc\",d:\"a = \" + 5,e:a + 5}");
            put(new ArrayList<>() {{
                add(12);
                add(12.5f);
                add("abc");
                add(expr("a = ${5}"));
                add(expr("${a+5}"));
            }}, "[12,12.5,\"abc\",\"a = \" + 5,a + 5]");
        }};

        CodeSnippetPrinter printer = CodeSnippetPrinter.create('"');
        samples.forEach((sample, expected) -> {
            Expression expr = expr(sample);
            String actual = printer.print(expr);

            Assertions.assertEquals(expected, actual);
        });
    }

    private Expression expr(Object value) {
        IXplCompiler cp = XLang.newXplCompiler();
        IXLangCompileScope scope = cp.newCompileScope();

        Expression expr;
        ValueWithLocation vl = ValueWithLocation.of(null, value);
        if (value instanceof String && XplParseHelper.hasExpr(value.toString())) {
            expr = XplParseHelper.parseTemplateExpr(vl, cp, scope);
        } else {
            expr = Literal.valueOf(vl.getLocation(), vl.getValue());
        }
        return expr;
    }
}
