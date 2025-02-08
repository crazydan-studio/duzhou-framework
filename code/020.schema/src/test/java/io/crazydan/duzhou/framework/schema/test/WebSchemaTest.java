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

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.schema.SchemaBaseTest;
import io.crazydan.duzhou.framework.schema.web.XWeb;
import io.crazydan.duzhou.framework.schema.web.XWebSite;
import io.nop.core.lang.json.JsonTool;
import io.nop.core.lang.xml.XNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-05
 */
public class WebSchemaTest extends SchemaBaseTest {
    private static final String WEB_XDSL = "/duzhou/web/app.web.xml";

    @Test
    public void test_Parse_Dsl() {
        XWeb web = XWeb.parseFromVirtualPath(WEB_XDSL);

        String json = JsonTool.serialize(web, true).replaceAll("\\s+\"(location|config|xmlns:x)\".+", "");
        this.log.info(json);
        Assertions.assertEquals(attachmentJsonText("app.web.json"), json);

        XWebSite defaultSite = web.getDefaultSite();
        Assertions.assertNotNull(defaultSite);

        List<XWebSite> sites = new ArrayList<>(web.getSites());
        sites.add(web.getDefaultSite());
        for (XWebSite site : sites) {
            Object result = site.getLayoutConfig();
            json = JsonTool.serialize(result, true);
            this.log.info(json);

            XNode node = site.getLayoutHtmlNode();
            String html = node.html().replaceAll("(?m)^\\s+", "");
            this.log.info(html);

            String expected = attachmentXml("app.web-site." + site.getId() + ".html").html()
                                                                                     .replaceAll("(?m)^\\s+", "");
            Assertions.assertEquals(expected, html);
        }
    }
}
