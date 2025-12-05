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

import io.crazydan.duzhou.framework.ui.XuiJunitTestCase;
import io.crazydan.duzhou.framework.ui.util.XuiHelper;
import io.nop.api.core.exceptions.NopException;
import io.nop.core.lang.xml.XNode;
import org.junit.jupiter.api.Test;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_INVALID_TAG_NAME;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_DISPATCHES_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-20
 */
public class TestXuiComponentTree extends XuiJunitTestCase {

    @Test
    public void test_valid_tree() {
        String dslPath = "/duzhou/ui/test-valid-component-tree.xui";
        XuiComponent component = XuiHelper.loadComponent(dslPath);

        String json = toJson(component);
        assertEquals(attachmentJsonText("valid-component-tree.json"), json);

        XNode node = component.getDslNode();
        String xml = cleanXml(toXml(node));
        assertEquals(cleanXml(attachmentXmlText("valid-component-tree-from-dsl.xml")), xml);

        node = toXNode(component);
        xml = cleanXml(toXml(node));
        assertEquals(cleanXml(attachmentXmlText("valid-component-tree.xml")), xml);

        node = toXNode(component.getTemplate());
        xml = cleanXml(toXml(node));
        assertEquals(cleanXml(attachmentXmlText("valid-component-tree-root.xml")), xml);
    }

    @Test
    public void test_invalid_tree() {
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-tag-name.xui");
            fail("invalid-tag-name");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_INVALID_TAG_NAME.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-tag-name-in-depth.xui");
            fail("invalid-tag-name");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_INVALID_TAG_NAME.getErrorCode(), e.getErrorCode());
        }

        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-multi-layout.xui");
            fail("multiple-layout");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-multi-layout-depth.xui");
            fail("multiple-layout");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }

        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-multi-dispatch.xui");
            fail("multiple-dispatch");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_MULTIPLE_DISPATCHES_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }

        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-slot-in-slot.xui");
            fail("slot-in-depth");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-slot-in-slot-depth.xui");
            fail("slot-in-depth");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED.getErrorCode(), e.getErrorCode());
        }

        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-not-imported.xui");
            fail("not-imported");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-not-imported-in-depth.xui");
            fail("not-imported");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-not-imported-in-statement-for.xui");
            fail("not-imported");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-not-imported-in-statement-choose.xui");
            fail("not-imported");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED.getErrorCode(), e.getErrorCode());
        }
        try {
            XuiHelper.loadComponent("/duzhou/ui/test-invalid-component-not-imported-in-statement-choose-1.xui");
            fail("not-imported");
        } catch (NopException e) {
            assertEquals(ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED.getErrorCode(), e.getErrorCode());
        }
    }
}
