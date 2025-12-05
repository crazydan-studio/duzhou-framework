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

package io.crazydan.duzhou.framework.ui.vendor;

import java.util.Map;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.crazydan.duzhou.framework.ui.schema.page.XuiPage;
import io.crazydan.duzhou.framework.ui.util.XuiHelper;
import io.crazydan.duzhou.framework.ui.vendor.core.XuiComponentTreeNode;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.xlang.api.XLang;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-27
 */
public class TestXuiComponentTreeNode extends NopJunitTestCase {

    @Test
    public void test_build_tree() {
        XuiPage page = XuiHelper.loadPage("/duzhou/ui/test-tree-node.page.xui");

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("data", Map.of());

        XuiComponentTreeNode tree = XuiComponentTreeNode.build(page, scope);
        String json = toJson(tree);
        assertEquals(attachmentJsonText("tree-0.json"), json);

        //
        scope.setLocalValue("data", Map.of("me", "Lily"));
        tree = XuiComponentTreeNode.build(page, scope);
        json = toJson(tree);
        assertEquals(attachmentJsonText("tree-1.json"), json);
    }
}
