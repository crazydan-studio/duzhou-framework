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
import io.crazydan.duzhou.framework.ui.schema.XuiStyleDefs;
import io.nop.core.lang.xml.XNode;
import io.nop.core.resource.IResource;
import io.nop.core.resource.VirtualFileSystem;
import io.nop.xlang.xdsl.DslModelHelper;
import io.nop.xlang.xdsl.DslNodeLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-02-11
 */
public class XuiStylesTest extends NopJunitTestCase {
    private static final String BASE_STYLES_DSL = "/duzhou/ui/base.styles.xui";
    private static final String BUTTON_STYLES_DSL = "/duzhou/ui/button.styles.xui";
    private static final String BUTTON_EXTENDS_STYLES_DSL = "/duzhou/ui/button-extends.styles.xui";

    @Test
    public void test_parse_base_dsl() {
        XuiStyleDefs styles = parseModel(BASE_STYLES_DSL);

        XNode node = parseXNode(BASE_STYLES_DSL);
        this.log.info("base.styles.xui: xml={}", node.xml());
        Assertions.assertEquals(attachmentXml("base.styles.xui.xml").clearComment().xml(), node.xml());
    }

    @Test
    public void test_parse_button_dsl() {
        XuiStyleDefs styles = parseModel(BUTTON_STYLES_DSL);

        XNode node = parseXNode(BUTTON_STYLES_DSL);
        this.log.info("button.styles.xui: xml={}", node.xml());
        Assertions.assertEquals(attachmentXml("button.styles.xui.xml").clearComment().xml(), node.xml());
    }

    @Test
    public void test_parse_button_extends_dsl() {
        XuiStyleDefs styles = parseModel(BUTTON_EXTENDS_STYLES_DSL);

        XNode node = parseXNode(BUTTON_EXTENDS_STYLES_DSL);
        this.log.info("button-extends.styles.xui: xml={}", node.xml());
        Assertions.assertEquals(attachmentXml("button-extends.styles.xui.xml").clearComment().xml(), node.xml());
    }

    private XuiStyleDefs parseModel(String path) {
        return (XuiStyleDefs) DslModelHelper.loadDslModelFromPath(path);
    }

    private XNode parseXNode(String path) {
        IResource resource = VirtualFileSystem.instance().getResource(path);
        XNode node = DslNodeLoader.INSTANCE.loadFromResource(resource).getNode();
        node.clearComment();
        node.removeAttrsWithPrefix("xmlns:");

        return node;
    }
}
