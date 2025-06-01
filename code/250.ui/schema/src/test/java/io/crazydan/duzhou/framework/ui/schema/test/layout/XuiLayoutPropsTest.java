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

package io.crazydan.duzhou.framework.ui.schema.test.layout;

import java.util.HashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutGap;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSize;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSpacing;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSpan;
import io.nop.commons.util.objects.ValueWithLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-31
 */
public class XuiLayoutPropsTest {

    @Test
    public void test_to_code_snippet() {
        char strQuote = '\'';

        Map<Object, String> samples = new HashMap<>() {{
            put("5u", "'5.0u'");
            put("${props.size}", "props.size");
        }};
        samples.forEach((sample, expected) -> {
            XuiExpression<XuiSize> expr = XuiSize.expr(ValueWithLocation.of(null, sample));
            String actual = expr.toCodeSnippet(strQuote);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutSize.fill_remains(), "'fill_remains'");
            put(XuiLayoutSize.match_parent(), "'match_parent'");
            put(XuiLayoutSize.wrap_content(), "'wrap_content'");
            put(XuiLayoutSize.with_specified(ValueWithLocation.of(null, "1u")), "'1.0u'");
            put(XuiLayoutSize.with_specified(ValueWithLocation.of(null, "${props.size}")), "props.size");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutSize) sample).toCodeSnippet(strQuote);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutAlign.create(null, null), "{}");
            put(XuiLayoutAlign.create(XuiLayoutAlign.Direction.start, null), "{row:'start'}");
            put(XuiLayoutAlign.create(null, XuiLayoutAlign.Direction.center), "{col:'center'}");
            put(XuiLayoutAlign.create(XuiLayoutAlign.Direction.center, XuiLayoutAlign.Direction.center),
                "{row:'center',col:'center'}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutAlign) sample).toCodeSnippet(strQuote);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, null)), "{}");
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, "2")), "{row:2,col:2}");
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, "${props.span}")), "{row:props.span,col:props.span}");
            put(XuiLayoutSpan.create(ValueWithLocation.of(null, new HashMap<>() {{
                put("row", ValueWithLocation.of(null, "2"));
                put("col", ValueWithLocation.of(null, "${props.span}"));
            }})), "{row:2,col:props.span}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutSpan) sample).toCodeSnippet(strQuote);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutGap.create(ValueWithLocation.of(null, null)), "{}");
            put(XuiLayoutGap.create(ValueWithLocation.of(null, "2u")), "{row:'2.0u',col:'2.0u'}");
            put(XuiLayoutGap.create(ValueWithLocation.of(null, "${props.gap}")), "{row:props.gap,col:props.gap}");
            put(XuiLayoutGap.create(ValueWithLocation.of(null, new HashMap<>() {{
                put("row", ValueWithLocation.of(null, "2.0u"));
                put("col", ValueWithLocation.of(null, "${props.gap}"));
            }})), "{row:'2.0u',col:props.gap}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutGap) sample).toCodeSnippet(strQuote);

            Assertions.assertEquals(expected, actual);
        });

        samples = new HashMap<>() {{
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, null)), "{}");
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, "2u")),
                "{left:'2.0u',right:'2.0u',top:'2.0u',bottom:'2.0u'}");
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, "${props.space}")),
                "{left:props.space,right:props.space,top:props.space,bottom:props.space}");
            put(XuiLayoutSpacing.create(ValueWithLocation.of(null, new HashMap<>() {{
                put("left", ValueWithLocation.of(null, "2.0u"));
                put("top", ValueWithLocation.of(null, "${props.space}"));
            }})), "{left:'2.0u',top:props.space}");
        }};
        samples.forEach((sample, expected) -> {
            String actual = ((XuiLayoutSpacing) sample).toCodeSnippet(strQuote);

            Assertions.assertEquals(expected, actual);
        });
    }
}
