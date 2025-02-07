package io.crazydan.duzhou.framework.schema;

import java.io.File;

import io.nop.api.core.config.AppConfig;
import io.nop.codegen.XCodeGenerator;
import io.nop.commons.util.MavenDirHelper;
import io.nop.core.CoreConfigs;
import io.nop.core.CoreConstants;
import io.nop.core.initialize.CoreInitialization;

/** 用于调试从 *.xgen 生成代码过程中的问题 */
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
