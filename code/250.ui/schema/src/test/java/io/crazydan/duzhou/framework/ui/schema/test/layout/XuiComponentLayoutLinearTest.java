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

package io.crazydan.duzhou.framework.ui.schema.test.layout;

import java.util.HashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentLayoutLinear;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-27
 */
public class XuiComponentLayoutLinearTest extends NopJunitTestCase {

    @Test
    public void test_parse_from_text() {
        Map<String, String> samples = new HashMap<>() {{
            put("layout.empty.json", "   \n   \n\n");
            put("layout.simple.json", "[a1]\n    [b1] [b2]");
        }};

        samples.forEach((name, text) -> {
            XuiLayoutNode root = XuiComponentLayoutLinear.parse(null, "column", text);
            String json = root.toJSON();

            this.log.info("{}={}", name, json);
            // Assertions.assertEquals(attachmentJsonText(name), json);
        });
    }
}
