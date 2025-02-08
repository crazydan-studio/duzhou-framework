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

package io.crazydan.duzhou.framework.codegen;

import java.io.File;

import io.nop.api.core.config.AppConfig;
import io.nop.codegen.XCodeGenerator;
import io.nop.commons.util.MavenDirHelper;
import io.nop.core.CoreConfigs;
import io.nop.core.CoreConstants;
import io.nop.core.initialize.CoreInitialization;

/**
 * 便于通过 IDE 执行 {@link #main} 函数以生成 ORM 模型等代码，
 * 或者用于调试从 *.xgen 生成代码过程中的问题
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-05
 */
public class NopCodeGen {

    public static void main(String[] args) {
        AppConfig.getConfigProvider()
                 .updateConfigValue(CoreConfigs.CFG_CORE_MAX_INITIALIZE_LEVEL,
                                    CoreConstants.INITIALIZER_PRIORITY_ANALYZE);

        CoreInitialization.initialize();
        try {
            run();
        } finally {
            CoreInitialization.destroy();
        }
    }

    public static void run() {
        File projectDir = MavenDirHelper.projectDir(NopCodeGen.class);

        XCodeGenerator.runPrecompile(projectDir, "/", false);
        XCodeGenerator.runPrecompile2(projectDir, "/", false);
        XCodeGenerator.runPostcompile(projectDir, "/", false);
    }
}
