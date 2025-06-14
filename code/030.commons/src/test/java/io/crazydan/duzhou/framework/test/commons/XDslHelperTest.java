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

package io.crazydan.duzhou.framework.test.commons;

import io.crazydan.duzhou.framework.commons.XDslHelper;
import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.nop.core.lang.json.JsonTool;
import io.nop.core.lang.xml.XNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-03-01
 */
public class XDslHelperTest extends NopJunitTestCase {

    @Test
    public void test_Json_to_XNode() {
        Object page = JsonTool.loadJson("/commons/test/signin.page.json");
        Assertions.assertNotNull(page);

        XNode node = XDslHelper.jsonToXNode(page);
        this.log.info("json to xml: \n{}", node.xml());

        Assertions.assertEquals(attachmentXml("signin.page.xml").xml(), node.xml());
    }
}
