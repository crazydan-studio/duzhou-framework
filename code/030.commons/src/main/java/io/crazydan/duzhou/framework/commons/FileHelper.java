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
import java.io.IOException;
import java.nio.file.Files;

import io.nop.codegen.XCodeGenerator;
import io.nop.core.lang.json.JsonTool;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-01-13
 */
public class FileHelper extends io.nop.commons.util.FileHelper {

    public static void assureDirExists(File file) {
        assureParent(new File(file + "/any"));
    }

    public static void assureDirExists(String path) {
        assureDirExists(new File(path));
    }

    /** 递归删除目录，对于目录的软链接仅直接删除其本身，不做遍历 */
    public static boolean deleteDir(File dir) {
        // 首先尝试直接删除文件、软链接、空目录
        if (dir.delete()) {
            return true;
        }
        // 再尝试删除其中的文件
        File[] subs = dir.listFiles();
        if (subs != null) {
            for (File sub : subs) {
                deleteDir(sub);
            }
        }

        return dir.delete();
    }

    /** 将源目录中的文件移至目标目录下，并删除源目录 */
    public static void moveDirFilesTo(File source, File target) {
        copyWithFilter(source, target, null);

        deleteDir(source);
    }

    /** 创建符号链接 */
    public static void createSymbolLink(File link, File target) {
        try {
            Files.createSymbolicLink(link.toPath(), getAbsoluteFile(target).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**  */
    public static File getAbsoluteFile(File dir, String path) {
        File file = new File(dir, path);
        return getAbsoluteFile(file);
    }

    /** 获取 {@link XCodeGenerator} 所处的 Maven 项目根目录 */
    public static File getMavenProjectRoot(XCodeGenerator codeGenerator) {
        // Note: codeGenerator.getTargetRootPath() 得到的是带 file: 前缀的 url 路径，需要移除该前缀
        return resolveFile(codeGenerator.getTargetRootPath());
    }

    /** 读取 Json 文件 */
    public static <T> T readJson(File file, String encoding) {
        String json = readText(file, encoding);

        return (T) JsonTool.parseNonStrict(json);
    }

    /** 写入 Json */
    public static void writeJson(File file, Object obj, String encoding) {
        String json = JsonTool.stringify(obj);

        writeText(file, json, encoding);
    }

    public static void copyFiles(File targetDir, File... sources) {
        for (File src : sources) {
            File target = new File(targetDir, src.getName());

            copyFile(src, target);
        }
    }
}
