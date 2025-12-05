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

package io.crazydan.duzhou.framework.ui.vendor.web;

import java.io.File;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.nop.codegen.XCodeGenerator;
import io.nop.commons.util.FileHelper;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.resource.component.ResourceComponentManager;
import io.nop.xlang.api.XLang;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-02
 */
public class TestSvelteCodeGen extends NopJunitTestCase {
    private static final String APP_VENDOR = "svelte";
    private static final String GEN_TPL_ROOT_PATH = "/duzhou/ui/vendor/web/templates/@app";

    @Test
    public void test_gen_login_app() {
        File targetDir = getTargetFile("code-gen/login-page");
        String appDslPath = "/duzhou/ui/test/login.app.xui";

        genApp(appDslPath, targetDir);
    }

    @Test
    public void test_gen_ui_designer_app() {
        File targetDir = getTargetFile("code-gen/ui-designer");
        String appDslPath = "/duzhou/ui/ui-designer/main.app.xui";

        genApp(appDslPath, targetDir);
    }

    private void genApp(String appDslPath, File targetDir) {
        // 确保目标目录已创建
        FileHelper.assureParent(new File(targetDir, "/any"));

        Object appDslModel = ResourceComponentManager.instance().loadComponentModel(appDslPath);

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("vendor", APP_VENDOR);
        scope.setLocalValue("app", appDslModel);

        XCodeGenerator gen = new XCodeGenerator(GEN_TPL_ROOT_PATH, targetDir.getAbsolutePath());
        gen.forceOverride(true);
        // 禁用对 xml 文本输出的格式化，因为，输出的 *.svelte 虽然为 xml 但其没有根节点，格式化时会抛出异常
        gen.autoFormat(false);

        // Note: 第一个参数用于指定在根模板中所要执行的 *.xrun 文件路径或者其所在的目录，用于生成部分文件
        gen.execute("/", scope);
    }
}
