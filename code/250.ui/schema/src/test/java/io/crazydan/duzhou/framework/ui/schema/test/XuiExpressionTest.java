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
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutGap;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSize;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSpacing;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSpan;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.xlang.api.IXLangCompileScope;
import io.nop.xlang.api.XLang;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.Literal;
import io.nop.xlang.ast.UnaryExpression;
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
            // TODO 表达式符号丢失
            put("Disabled ${!props.disabled}", "Disabled ${props.disabled}");
            put("Result is ${props.size > 10}", "Result is ${props.size > 10}");
        }};
        samples.forEach((sample, expected) -> {
            Expression expr = create(sample);
            String actual = expr.toExprString();

            if (expr instanceof UnaryExpression) {
                UnaryExpression e = (UnaryExpression) expr;
                actual = e.getOperator() + e.getArgument().toExprString();
            }

            Assertions.assertEquals(expected, actual);
        });
    }

    @Test
    public void test_convert_to_xml_attr_expr() {
        Map<Object, String> samples = new HashMap<>() {{
            put("5u", "'5.0u'");
            put("${props.size}", "props.size");
        }};
        samples.forEach((sample, expected) -> {
            XuiExpression<XuiSize> expr = XuiSize.expr(ValueWithLocation.of(null, sample));
            String actual = XuiSize.toXmlAttrExpr(expr, XuiSize::toString);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutSize.fill_remains(), "{'fill_remains'}");
            put(XuiLayoutSize.match_parent(), "{'match_parent'}");
            put(XuiLayoutSize.wrap_content(), "{'wrap_content'}");
            put(XuiLayoutSize.with_specified(ValueWithLocation.of(null, "1u")), "{'1.0u'}");
            put(XuiLayoutSize.with_specified(ValueWithLocation.of(null, "${props.size}")), "{props.size}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutSize) sample).toXmlAttrExpr("{", "}", XuiSize::toString);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutAlign.create(null, null), null);
            put(XuiLayoutAlign.create(XuiLayoutAlign.Direction.start, null), "{{row:'start',}}");
            put(XuiLayoutAlign.create(null, XuiLayoutAlign.Direction.center), "{{col:'center',}}");
            put(XuiLayoutAlign.create(XuiLayoutAlign.Direction.center, XuiLayoutAlign.Direction.center),
                "{{row:'center',col:'center',}}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutAlign) sample).toXmlAttrExpr("{", "}");

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, null)), null);
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, "2")), "{{row:2,col:2,}}");
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, "${props.span}")),
                "{{row:props.span,col:props.span,}}");
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, new HashMap<>() {{
                put("row", ValueWithLocation.of(null, "2"));
                put("col", ValueWithLocation.of(null, "${props.span}"));
            }})), "{{row:2,col:props.span,}}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutSpan) sample).toXmlAttrExpr("{", "}");

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutGap.create(ValueWithLocation.of(null, null)), null);
            put(XuiLayoutGap.create(ValueWithLocation.of(null, "2u")), "{{row:'2.0u',col:'2.0u',}}");
            put(XuiLayoutGap.create(ValueWithLocation.of(null, "${props.gap}")), "{{row:props.gap,col:props.gap,}}");
            put(XuiLayoutGap.create(ValueWithLocation.of(null, new HashMap<>() {{
                put("row", ValueWithLocation.of(null, "2.0u"));
                put("col", ValueWithLocation.of(null, "${props.gap}"));
            }})), "{{row:'2.0u',col:props.gap,}}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutGap) sample).toXmlAttrExpr("{", "}", XuiSize::toString);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, null)), null);
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, "2u")),
                "{{left:'2.0u',right:'2.0u',top:'2.0u',bottom:'2.0u',}}");
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, "${props.space}")),
                "{{left:props.space,right:props.space,top:props.space,bottom:props.space,}}");
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, new HashMap<>() {{
                put("left", ValueWithLocation.of(null, "2.0u"));
                put("top", ValueWithLocation.of(null, "${props.space}"));
            }})), "{{left:'2.0u',top:props.space,}}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutSpacing) sample).toXmlAttrExpr("{", "}", XuiSize::toString);

            Assertions.assertEquals(expected, actual);
        });
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
