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

package io.crazydan.duzhou.framework.commons;

import java.io.File;

import io.nop.api.core.exceptions.ErrorCode;
import io.nop.api.core.exceptions.NopException;
import io.nop.commons.env.PlatformEnv;
import io.nop.commons.util.ArrayHelper;
import io.nop.shell.DefaultShellOutputCollector;
import io.nop.shell.ShellCommand;
import io.nop.shell.ShellRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.nop.api.core.exceptions.ErrorCode.define;
import static io.nop.xlang.XLangErrors.ARG_VALUE;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-01-14
 */
public class ShellHelper {
    private static final ErrorCode ERR_COMMAND_RUNNING_FAILED = //
            define("duzhou.err.shell.command-running-failed", //
                   "执行命令 [{" + ARG_VALUE + "}] 出现异常", ARG_VALUE);

    /** 运行可执行文件 */
    public static int runExecutable(String execPath, String[] args, File workDir, boolean throwError) {
        String[] cmds;
        if (PlatformEnv.isWindows()) {
            cmds = new String[] { "cmd", "/c", '\'' + execPath + '\'' };
        } else {
            // 采用占位符以避免注入攻击
            cmds = new String[] { "sh", "-c", '\'' + execPath + "' $@", "_" };
        }

        String[] execCmds = (String[]) ArrayHelper.concat(cmds, args);

        return runCommand(execCmds, workDir, throwError);
    }

    /**
     * 对于执行带有 Shebang（如 {@code #!/usr/bin/env node}）的脚本，
     * 需以以下方式执行：<pre>
     * sh -c '/usr/bin/npm $1' _ install
     * </pre>
     * 其效果等同于 {@code /usr/bin/npm install}
     */
    public static int runCommand(String[] cmds, File workDir, boolean throwError) {
        ShellCommand cmd = new ShellCommand(cmds);

        cmd.redirectErrorStream(true);
        cmd.workDir(workDir.getAbsolutePath());

        ShellRunner runner = new ShellRunner();
        LogShellOutputCollector collector = new LogShellOutputCollector();

        int exitCode = runner.run(cmd, collector);
        if (exitCode != 0 && throwError) {
            String error = collector.getError();
            String msg = StringHelper.isBlank(error) ? collector.getOutput() : error;
            Exception e = new IllegalStateException(msg);

            throw new NopException(ERR_COMMAND_RUNNING_FAILED, e).param(ARG_VALUE, cmd.getCommandString());
        }

        return exitCode;
    }

    private static class LogShellOutputCollector extends DefaultShellOutputCollector {
        private static final Logger log = LoggerFactory.getLogger(LogShellOutputCollector.class);

        @Override
        public void onOutput(String line) {
            log.info(line);
            super.onOutput(line);
        }

        @Override
        public void onError(String line) {
            log.error(line);
            super.onError(line);
        }
    }
}
