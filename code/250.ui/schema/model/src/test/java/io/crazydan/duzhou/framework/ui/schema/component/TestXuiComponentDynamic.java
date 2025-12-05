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

package io.crazydan.duzhou.framework.ui.schema.component;

import java.util.List;
import java.util.Map;

import io.crazydan.duzhou.framework.ui.XuiJunitTestCase;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate;
import io.crazydan.duzhou.framework.ui.util.XuiHelper;
import io.nop.api.core.exceptions.NopException;
import io.nop.core.lang.eval.IEvalAction;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.xml.XNode;
import io.nop.xlang.api.XLang;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.ast.XLangOutputMode;
import io.nop.xlang.xdsl.DslModelHelper;
import org.junit.jupiter.api.Test;

import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_TEMPLATE;
import static io.crazydan.duzhou.framework.ui.XuiConstants.XDSL_SCHEMA_COMPONENT_TEMPLATE;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_DISPATCHES_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-22
 */
public class TestXuiComponentDynamic extends XuiJunitTestCase {

    @Test
    public void test_vars() {
        XuiComponent component = XuiHelper.loadComponent("/duzhou/ui/test-dynamic-component-tree-vars.xui");

        Map<?, ?> props = Map.of("padding", "1u", "msg", "Welcome!");
        XuiComponentTemplate template = evalTemplate(component, props);

        String json = toJson(template);
        assertEquals(attachmentJsonText("vars.json"), json);

        XNode node = toXNode(template);
        String xml = cleanXml(toXml(node));
        assertEquals(cleanXml(attachmentXmlText("vars.xml")), xml);
    }

    @Test
    public void test_statement_if() {
        XuiComponent component = XuiHelper.loadComponent("/duzhou/ui/test-dynamic-component-tree-statement-if.xui");

        Map<?, ?>[] samples = new Map[] {
                Map.of("var", 1, "msg1", "Hello IF#1"), //
                Map.of("var", 2, "msg2", "Hello IF#2"), //
        };

        for (int i = 0; i < samples.length; i++) {
            Map<?, ?> props = samples[i];
            XuiComponentTemplate template = evalTemplate(component, props);

            String json = toJson(template);
            assertEquals(attachmentJsonText("statement-if-" + i + ".json"), json);

            XNode node = toXNode(template);
            String xml = cleanXml(toXml(node));
            assertEquals(cleanXml(attachmentXmlText("statement-if-" + i + ".xml")), xml);
        }
    }

    @Test
    public void test_statement_choose() {
        XuiComponent component = XuiHelper.loadComponent("/duzhou/ui/test-dynamic-component-tree-statement-choose.xui");

        Map<?, ?>[] samples = new Map[] {
                Map.of("var", 1, "msg1", "Hello WHEN#1"), //
                Map.of("var", 2, "msg2", "Hello WHEN#2"), //
                Map.of("var", 3, "msg3", "Hello WHEN#3"), //
                Map.of("var", 4, "msg", "Hello OTHERWISE"), //
        };

        for (int i = 0; i < samples.length; i++) {
            Map<?, ?> props = samples[i];
            XuiComponentTemplate template = evalTemplate(component, props);

            String json = toJson(template);
            assertEquals(attachmentJsonText("statement-choose-" + i + ".json"), json);

            XNode node = toXNode(template);
            String xml = cleanXml(toXml(node));
            assertEquals(cleanXml(attachmentXmlText("statement-choose-" + i + ".xml")), xml);
        }
    }

    @Test
    public void test_statement_for() {
        XuiComponent component = XuiHelper.loadComponent("/duzhou/ui/test-dynamic-component-tree-statement-for.xui");

        Map<?, ?>[] samples = new Map[] {
                Map.of("var", 1, "items", List.of("a", "b", "c")), //
                Map.of("var", 2, "items", List.of(12, 15, 21, 34)), //
        };

        for (int i = 0; i < samples.length; i++) {
            Map<?, ?> props = samples[i];
            XuiComponentTemplate template = evalTemplate(component, props);

            String json = toJson(template);
            assertEquals(attachmentJsonText("statement-for-" + i + ".json"), json);

            XNode node = toXNode(template);
            String xml = cleanXml(toXml(node));
            assertEquals(cleanXml(attachmentXmlText("statement-for-" + i + ".xml")), xml);
        }
    }

    @Test
    public void test_invalid_tree() {
        try {
            XuiComponent component = //
                    XuiHelper.loadComponent("/duzhou/ui/test-invalid-dynamic-component-multi-layout-in-statement-if.xui");
            evalTemplate(component, Map.of("var", 2));

            fail("multiple-layout");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiComponent component = //
                    XuiHelper.loadComponent(
                            "/duzhou/ui/test-invalid-dynamic-component-multi-layout-in-statement-for.xui");
            evalTemplate(component, Map.of());

            fail("multiple-layout");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }

        try {
            XuiComponent component = //
                    XuiHelper.loadComponent(
                            "/duzhou/ui/test-invalid-dynamic-component-multi-dispatch-in-statement-if.xui");
            evalTemplate(component, Map.of("var", 2));

            fail("multiple-dispatch");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_MULTIPLE_DISPATCHES_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiComponent component = //
                    XuiHelper.loadComponent(
                            "/duzhou/ui/test-invalid-dynamic-component-multi-dispatch-in-statement-for.xui");
            evalTemplate(component, Map.of());

            fail("multiple-dispatch");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_MULTIPLE_DISPATCHES_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }
    }

    private XuiComponentTemplate evalTemplate(XuiComponent component, Map<?, ?> props) {
        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("props", props);

        XNode dummy = XNode.makeDummyNode();
        component.getDslNode().childByTag(TAG_NAME_TEMPLATE).cloneInstance().insertParent(dummy);

        XLangCompileTool compileTool = XuiComponent.newCompileTool();
        IEvalAction action = compileTool.compileTagBody(dummy, XLangOutputMode.node);
        XNode node = (XNode) action.invoke(scope);

        return (XuiComponentTemplate) DslModelHelper.parseDslNode(XDSL_SCHEMA_COMPONENT_TEMPLATE, node);
    }
}
