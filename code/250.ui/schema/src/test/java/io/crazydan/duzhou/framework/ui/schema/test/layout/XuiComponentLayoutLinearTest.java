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

import java.util.LinkedHashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentLayoutLinear;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;
import io.nop.core.lang.json.JsonTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-27
 */
public class XuiComponentLayoutLinearTest extends NopJunitTestCase {

    @Test
    public void test_parse_from_text() {
        Map<String, String> samples = new LinkedHashMap<>() {{
            put("layout.100.json", "   \n   \n\n");
            put("layout.101.json", "[a1]\n    [b1] [b2]");
            put("layout.102.json", "[a1] <> [a2]");
            put("layout.103.json", ">[a1]<");
            put("layout.104.json", "<[a1]>");
            put("layout.105.json", "^<[]>v");
            put("layout.106.json", "^<>v");
            // - 正则表达式匹配目标组件
            put("layout.107.json", "[a1] [a2]\n[b1] [b_[\\\\d\\\\w]+]");
            // - 行注释
            put("layout.108.json", "/** 多行\n注释 */ [a1]\n// 单行注释\n/** 单行注释 */\n[b1] [c1] // 行尾注释");
            // 嵌套
            put("layout.201.json", "v>{ \n  >[a1]< \n  <[b1]> \n}<^");
            put("layout.202.json", "{\n  >[a1]<\n  <[a2]>\n} <{\n  [b1] <[b2]>\n}>");
            // 表格
            put("layout.301.json", "{\n  [a1]\n  [a2]\n}\n| [b1]> | <[b2]> | \n| [c1]> | <[c2]> |\n|       | <[d1]  |");
            put("layout.302.json", "| [a1] | [a2] |\n [b1] [b2] \n| [c1] | [c2] |\n| [d1] | [d2] |");
            put("layout.303.json", "| [a1] [a2] | [b1] |\n|           | [c1] |");
            put("layout.304.json", "| {\n    [a1]\n    [a2]\n} | [b1] |");
            put("layout.305.json", "| {\n    | [a1] | [a2] |\n    | [b1] | [b2] |\n} [c1] | [d1] [d2] |");
            // - 表头配置参数
            // put("layout.306.json", "| (width:100px) | (width:300px) |\n| [a1]> | <[b1]> |");
            // 配置参数
            // put("layout.501.json", "v>(width:100px,height:200px)<^");
            // put("layout.502.json", "v>[a1](width:100px,height:200px)<^");
            // put("layout.503.json", "v>{[a1] [a2]}(width:100px,height:200px)<^");
        }};

        samples.forEach((name, text) -> {
            this.log.info("Raw text for {}=\n{}", name, text);
            XuiLayoutNode root = XuiComponentLayoutLinear.parse(null, XuiComponentLayoutLinear.Mode.column, text);
            String json = JsonTool.serialize(JsonTool.parse(root.toJSON()), true);

            this.log.info("Layout json for {}=\n{}", name, json);
            Assertions.assertEquals(attachmentJsonText(name), json);
        });
    }
}
