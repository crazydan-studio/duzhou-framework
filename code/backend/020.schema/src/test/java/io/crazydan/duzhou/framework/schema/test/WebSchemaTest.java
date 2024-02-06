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

import io.crazydan.duzhou.framework.schema.RunInEnv;
import io.crazydan.duzhou.framework.schema.SchemaBaseTest;
import io.crazydan.duzhou.framework.schema.web.XWeb;
import io.crazydan.duzhou.framework.schema.web.XWebSite;
import io.nop.core.lang.json.JsonTool;
import io.nop.xlang.xdsl.DslModelParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-05
 */
public class WebSchemaTest extends SchemaBaseTest {
    private static final String WEB_XDEF = "/duzhou/web/web.xdef";
    private static final String WEB_XDSL = "/duzhou/web/app.web.xml";

    @Test
    public void test_Parse_DSL() {
        XWeb web = (XWeb) new DslModelParser(WEB_XDEF).parseFromVirtualPath(WEB_XDSL);

        String json = JsonTool.serialize(web, true);
        log.info(json);

        for (XWebSite site : web.getSites()) {
            Assertions.assertEquals(RunInEnv.development, site.getRunInEnv());
            Assertions.assertNotNull(site.getTitle());
            Assertions.assertNotNull(site.getSubTitle());
            Assertions.assertNotNull(site.getImage().getLogo());
            Assertions.assertNotNull(site.getImage().getLoading());
            Assertions.assertTrue(site.getLayout().hasScripts());
            Assertions.assertTrue(site.getLayout().hasStyles());

            Object result = site.renderLayout();
            json = JsonTool.serialize(result, true);
            log.info(json);
        }

        XWebSite signinSite = web.getSite("signin");
        Assertions.assertNotNull(signinSite);
        Assertions.assertEquals(signinSite.getResource("signin").getUrl(), signinSite.renderLayout().get("schemaApi"));

        XWebSite defaultSite = web.getSiteByUrl("*");
        Assertions.assertNotNull(defaultSite);
    }
}
