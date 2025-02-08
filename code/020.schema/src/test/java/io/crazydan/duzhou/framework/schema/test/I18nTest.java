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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-03-08
 */
public class I18nTest extends SchemaBaseTest {
    private static final String WITH_I18N_DELTA_XDSL = "/duzhou/i18n/with-delta.i18n.test.xml";
    private static final String WITH_I18N_NS_XDSL = "/duzhou/i18n/with-ns.i18n.test.xml";

    @Test
    public void test_Parse_XNode_with_I18n_Delta() {
        for (String lang : new String[] { "zh_CN", "en_US" }) {
            for (String path : new String[] { "/locales/" + lang + WITH_I18N_DELTA_XDSL, WITH_I18N_DELTA_XDSL }) {
                IResource resource = VirtualFileSystem.instance().getResource(path);
                if (!resource.exists()) {
                    continue;
                }

                XNode node = DslNodeLoader.INSTANCE.loadFromResource(resource).getNode();
                node.clearComment();
                node.removeAttrsWithPrefix("xmlns:");

                this.log.info("i18n.delta.node:lang={},xml={}", lang, node.xml());
                Assertions.assertEquals(attachmentXml("i18n.node." + lang + ".xml").xml(), node.xml());

                break;
            }
        }
    }

    @Test
    public void test_Parse_XNode_with_I18n_NS() {
        for (String lang : new String[] { "zh_CN", "en_US" }) {
            IResource resource = VirtualFileSystem.instance().getResource(WITH_I18N_NS_XDSL);
            XNode node = XModelInclude.instance().keepComment(false).loadActiveNodeFromResource(resource);

            String i18nNs = "i18n:";
            String i18nPath = (String) node.removeAttr(i18nNs + "use").getValue();
            IResource i18nResource = VirtualFileSystem.instance().getResource(i18nPath);
            XNode i18nNode = DslNodeLoader.INSTANCE.loadFromResource(i18nResource).getNode();

            node.visit(new ITreeVisitor<>() {
                @Override
                public TreeVisitResult beginNode(XNode node) {
                    if (node.hasAttr()) {
                        Map<String, Object> attrs = new HashMap<>();
                        node.getAttrs().forEach((attr, value) -> {
                            if (attr.startsWith(i18nNs)) {
                                XNode i18n = i18nNode.childByAttr("key", value);

                                attrs.put(attr.substring(i18nNs.length()), i18n.childValue(lang).toString().trim());
                            }
                        });

                        node.removeAttrsWithPrefix(i18nNs);
                        node.setAttrs(attrs);
                    }
                    return TreeVisitResult.CONTINUE;
                }
            });

            node = DslNodeLoader.INSTANCE.loadFromNode(node, null, XDslExtendPhase.validate).getNode();
            node.removeAttrsWithPrefix("xmlns:");

            this.log.info("i18n.ns.node:lang={},xml={}", lang, node.xml());
            Assertions.assertEquals(attachmentXml("i18n.node." + lang + ".xml").xml(), node.xml());
        }
    }
}
