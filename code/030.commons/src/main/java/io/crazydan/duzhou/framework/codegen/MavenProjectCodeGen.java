/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2026 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.codegen;

import java.io.File;

import io.nop.api.core.config.AppConfig;
import io.nop.codegen.XCodeGenerator;
import io.nop.commons.util.MavenDirHelper;
import io.nop.core.CoreConfigs;
import io.nop.core.CoreConstants;
import io.nop.core.initialize.CoreInitialization;

/**
 * 便于通过 IDE 运行 {@code main} 函数以执行或调试 Maven 工程目录中
 * postcompile/precompile/precompile2 内的 {@code *.xgen} 代码
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-01-15
 */
public class MavenProjectCodeGen {

    public static void run(Class<?> codeGenMainClass) {
        File projectDir = MavenDirHelper.projectDir(codeGenMainClass);
        run(projectDir);
    }

    public static void run(File projectDir) {
        AppConfig.getConfigProvider()
                 .updateConfigValue(CoreConfigs.CFG_CORE_MAX_INITIALIZE_LEVEL,
                                    CoreConstants.INITIALIZER_PRIORITY_ANALYZE);

        CoreInitialization.initialize();
        try {
            XCodeGenerator.runPrecompile(projectDir, "/", false);
            XCodeGenerator.runPrecompile2(projectDir, "/", false);
            XCodeGenerator.runPostcompile(projectDir, "/", false);
        } finally {
            CoreInitialization.destroy();
        }
    }
}
