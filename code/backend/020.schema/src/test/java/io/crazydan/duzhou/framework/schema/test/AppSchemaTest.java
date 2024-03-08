/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.schema.test;

import java.util.HashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.schema.SchemaBaseTest;
import io.nop.core.lang.xml.XNode;
import io.nop.core.model.tree.ITreeVisitor;
import io.nop.core.model.tree.TreeVisitResult;
import io.nop.core.resource.IResource;
import io.nop.core.resource.VirtualFileSystem;
import io.nop.xlang.feature.XModelInclude;
import io.nop.xlang.xdsl.DslNodeLoader;
import io.nop.xlang.xdsl.XDslExtendPhase;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-03-08
 */
public class AppSchemaTest extends SchemaBaseTest {
    private static final String APP_XDSL = "/duzhou/app/assets.app.xml";

    @Test
    public void test_Parse_XNode_with_I18n() {
        IResource resource = VirtualFileSystem.instance().getResource(APP_XDSL);

        XNode node = XModelInclude.instance().keepComment(true).loadActiveNodeFromResource(resource);

        String i18nNs = "i18n:";
        String i18nPath = (String) node.removeAttr(i18nNs + "use").getValue();
        resource = VirtualFileSystem.instance().getResource(i18nPath);
        XNode i18nNode = DslNodeLoader.INSTANCE.loadFromResource(resource).getNode();

        node.visit(new ITreeVisitor<>() {
            @Override
            public TreeVisitResult beginNode(XNode node) {
                if (node.hasAttr()) {
                    Map<String, Object> attrs = new HashMap<>();
                    node.getAttrs().forEach((attr, value) -> {
                        if (attr.startsWith(i18nNs)) {
                            XNode i18n = i18nNode.childByAttr("key", value);

                            attrs.put(attr.substring(i18nNs.length()),
                                      i18n.childValue(i18nNode.attrText("defaultLang")).toString().trim());
                        }
                    });

                    node.removeAttrsWithPrefix(i18nNs);
                    node.setAttrs(attrs);
                }
                return TreeVisitResult.CONTINUE;
            }
        });

        node = DslNodeLoader.INSTANCE.loadFromNode(node, null, XDslExtendPhase.validate).getNode();
        this.log.info("app.node.xml={}", node.xml());
    }
}
