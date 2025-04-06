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
import io.crazydan.duzhou.framework.ui.schema.XuiComponent;
import io.nop.core.lang.json.JsonTool;
import io.nop.core.lang.xml.XNode;
import io.nop.core.resource.IResource;
import io.nop.core.resource.VirtualFileSystem;
import io.nop.xlang.xdsl.DslModelHelper;
import io.nop.xlang.xdsl.DslNodeLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-05
 */
public class XuiComponentTest extends NopJunitTestCase {
    private static final String LOGIN_FORM_DSL = "/duzhou/ui/component/login-form.xui";
    private static final String EXTENDS_LOGIN_FORM_DSL = "/duzhou/ui/component/login-form-extends.xui";

    @Test
    public void test_parse_login_form() {
        XuiComponent component = parseModel(LOGIN_FORM_DSL);
        String json = JsonTool.serialize(component, true);
        this.log.info("login-form.xui: json={}", json);

        XNode node = parseXNode(LOGIN_FORM_DSL);
        this.log.info("login-form.xui: xml={}", node.xml());
        Assertions.assertEquals(attachmentXml("login-form.xui.xml").clearComment().xml(), node.xml());
    }

    @Test
    public void test_parse_login_form_extends() {
        XuiComponent component = parseModel(EXTENDS_LOGIN_FORM_DSL);
        String json = JsonTool.serialize(component, true);
        this.log.info("login-form-extends.xui: json={}", json);

        XNode node = parseXNode(EXTENDS_LOGIN_FORM_DSL);
        this.log.info("login-form-extends.xui: xml={}", node.xml());
        Assertions.assertEquals(attachmentXml("login-form-extends.xui.xml").clearComment().xml(), node.xml());
    }

    private XuiComponent parseModel(String path) {
        return (XuiComponent) DslModelHelper.loadDslModelFromPath(path);
    }

    private XNode parseXNode(String path) {
        IResource resource = VirtualFileSystem.instance().getResource(path);
        XNode node = DslNodeLoader.INSTANCE.loadFromResource(resource).getNode();
        node.clearComment();
        node.removeAttrsWithPrefix("xmlns:");

        return node;
    }
}
