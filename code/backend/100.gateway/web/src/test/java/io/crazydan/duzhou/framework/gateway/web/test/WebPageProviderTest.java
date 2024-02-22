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

package io.crazydan.duzhou.framework.gateway.web.test;

import io.crazydan.duzhou.framework.gateway.web.GatewayWebBaseTest;
import io.crazydan.duzhou.framework.gateway.web.utils.WebPageProviderHelper;
import io.nop.core.lang.json.JsonTool;
import io.nop.core.lang.xml.XNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-22
 */
public class WebPageProviderTest extends GatewayWebBaseTest {

    @Test
    public void test_Json_to_Xml() {
        Object page = JsonTool.loadJson("/duzhou/web/signin.page.json");
        Assertions.assertNotNull(page);

        XNode node = WebPageProviderHelper.jsonToXNode(page);
        this.log.info("json to xml: \n{}", node.xml());

        Assertions.assertEquals(attachmentXml("signin.page.xml").xml(), node.xml());
    }
}
