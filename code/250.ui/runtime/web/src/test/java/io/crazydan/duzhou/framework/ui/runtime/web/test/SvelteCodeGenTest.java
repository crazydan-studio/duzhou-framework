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

package io.crazydan.duzhou.framework.ui.runtime.web.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.nop.codegen.XCodeGenerator;
import io.nop.commons.util.FileHelper;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.xlang.api.XLang;
import io.nop.xlang.xdsl.DslModelHelper;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-02
 */
public class SvelteCodeGenTest extends NopJunitTestCase {
    private static final String APP_RUNTIME = "svelte";
    private static final String GEN_TPL_ROOT_PATH = "/duzhou/ui/runtime/web/templates/@app";

    @Test
    public void test_gen_login_form() {
        File targetDir = getTargetFile("code-gen/" + APP_RUNTIME);

        String pageDslPath = "/duzhou/ui/test/login-form.xui";
        Object pageDslModel = DslModelHelper.loadDslModelFromPath(pageDslPath);

        Map<String, Object> app = createAppData();
        app.put("code", "user-login-form");
        app.put("displayName", "User Login Form");
        app.put("mainPage", pageDslModel);

        // 确保目标目录已创建
        FileHelper.assureParent(new File(targetDir, "/any"));

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("app", app);

        XCodeGenerator gen = new XCodeGenerator(GEN_TPL_ROOT_PATH, targetDir.getAbsolutePath());
        gen.forceOverride(true);
        // Note: 第一个参数用于指定在根模板中所要执行的 *.xrun 文件路径或者其所在的目录，用于生成部分文件
        gen.execute("/", scope);
    }

    private Map<String, Object> createAppData() {
        return new HashMap<>() {{
            put("runtime", APP_RUNTIME);
            put("version", "0.1.0");
        }};
    }
}
