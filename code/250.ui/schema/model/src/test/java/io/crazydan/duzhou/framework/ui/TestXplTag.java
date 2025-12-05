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

package io.crazydan.duzhou.framework.ui;

import io.nop.core.lang.eval.IEvalAction;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.xml.XNode;
import io.nop.xlang.api.XLang;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.ast.XLangOutputMode;
import io.nop.xlang.xdsl.DslModelHelper;
import io.nop.xlang.xmeta.IObjMeta;
import io.nop.xlang.xpl.tags.ChooseTagCompiler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-17
 */
public class TestXplTag extends XuiJunitTestCase {

    @Test
    public void test_render_model_in_choose() {
        String version_1 = "1.2.1";
        String version_2 = "0.1.2";
        XNode node = loadNode("/duzhou/ui/test-xpl-choose-render-model.xml");

        XLangCompileTool compileTool = XLang.newCompileTool();
        compileTool.getScope().addTagCompiler("cc:choose", ChooseTagCompiler.INSTANCE);

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("v", 1);

        IEvalAction action = compileTool.allowUnregisteredScopeVar(true).compileTagBody(node, XLangOutputMode.node);
        node = (XNode) action.invoke(scope);
        assertNotNull(node);

        IObjMeta objMeta = toModel(node);
        assertEquals(version_1, objMeta.getVersion());

        //
        scope.setLocalValue("v", 2);

        node = (XNode) action.invoke(scope);
        assertNotNull(node);

        objMeta = toModel(node);
        assertEquals(version_2, objMeta.getVersion());
    }

    private <T> T toModel(XNode node) {
        return (T) DslModelHelper.parseDslNode("/nop/schema/xmeta.xdef", node);
    }
}
