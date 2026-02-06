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
import java.util.Collection;

import static io.crazydan.duzhou.framework.CommonConfigs.CFG_CLI_PNPM_PATH;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-02-06
 */
public class PnpmRunner {
    public static final String DEFAULT_DIST_DIR = "dist";

    private final File srcDir;
    private final File distDir;

    private boolean disableInstall;

    public PnpmRunner(File srcDir) {
        this(srcDir, new File(srcDir, DEFAULT_DIST_DIR));
    }

    public PnpmRunner(File srcDir, File distDir) {
        this.srcDir = srcDir;
        this.distDir = distDir;
    }

    /** 运行 {@code package.json} 中定义的脚本，不遍历执行 workspace 中的脚本 */
    public PnpmRunner runScript(String scriptName) {
        return runScript(scriptName, false);
    }

    /** 运行 {@code package.json} 中定义的脚本 */
    public PnpmRunner runScript(String scriptName, boolean recursive) {
        if (!this.disableInstall) {
            execPnpm("install");
        }

        execPnpm("run", scriptName);
        if (recursive) {
            // Note: -r 仅构建子工作空间，--include-workspace-root 才包含根工作空间，
            // 但其默认是并发构建的，在涉及清空产物的情况下，容易导致根工作空间最后构建而丢失子工作空间的构建产物
            execPnpm("run", "-r", scriptName);
        }

        return this;
    }

    /** 复制构建产物到指定目录，复制前清空目标目录 */
    public PnpmRunner copyDistTo(File targetDir) {
        return copyDistTo(targetDir, true);
    }

    /** 复制构建产物到指定目录 */
    public PnpmRunner copyDistTo(File targetDir, boolean cleanTargetDir) {
        if (cleanTargetDir) {
            FileHelper.deleteDir(targetDir);
        }

        FileHelper.copyWithFilter(this.distDir, targetDir, null);

        return this;
    }

    /** 卸载依赖包，并保留 {@code node_modules} */
    public PnpmRunner uninstallPackages(Collection<String> pkgs) {
        return uninstallPackages(pkgs, false);
    }

    /** 卸载依赖包 */
    public PnpmRunner uninstallPackages(Collection<String> pkgs, boolean deleteNodeModulesDir) {
        if (!pkgs.isEmpty()) {
            execPnpm("uninstall", StringHelper.join(pkgs, " "));
        }

        if (deleteNodeModulesDir) {
            File nodeModulesDir = new File(this.srcDir, "node_modules");
            FileHelper.deleteDir(nodeModulesDir);
        }

        return this;
    }

    private void execPnpm(String... args) {
        String pnpm = StringHelper.normalizePath(CFG_CLI_PNPM_PATH.get());

        ShellHelper.runExecutable(pnpm, args, this.srcDir, true);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public PnpmRunner disableInstall(boolean disabled) {
        this.disableInstall = disabled;
        return this;
    }
}
