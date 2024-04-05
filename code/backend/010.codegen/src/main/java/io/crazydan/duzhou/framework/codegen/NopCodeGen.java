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
