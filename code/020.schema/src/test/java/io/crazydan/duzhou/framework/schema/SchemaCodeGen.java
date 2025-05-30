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

package io.crazydan.duzhou.framework.schema;

import java.io.File;

import io.nop.api.core.config.AppConfig;
import io.nop.codegen.XCodeGenerator;
import io.nop.commons.util.MavenDirHelper;
import io.nop.core.CoreConfigs;
import io.nop.core.CoreConstants;
import io.nop.core.initialize.CoreInitialization;

/**
 * 用于调试从 *.xgen 生成代码过程中的问题
 * <p/>
 * 为了避免生成的 class 存在问题而导致代码无法运行，
 * 一般需通过 Maven 构建来生成 class：<code>
 * mvn compile -o
 * </code>
 */
public class SchemaCodeGen {

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
        File projectDir = MavenDirHelper.projectDir(SchemaCodeGen.class);

        XCodeGenerator.runPrecompile(projectDir, "/", false);
        XCodeGenerator.runPrecompile2(projectDir, "/", false);
        XCodeGenerator.runPostcompile(projectDir, "/", false);
    }
}
