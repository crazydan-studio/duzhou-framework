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

package io.crazydan.duzhou.framework.ui.runtime.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.runtime.web.model.ImportedLib;
import io.crazydan.duzhou.framework.ui.schema.XuiComponent;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.resource.component.ResourceComponentManager;
import io.nop.xlang.xpl.xlib.XplTag;
import io.nop.xlang.xpl.xlib.XplTagLib;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-16
 */
public class XuiGenHelper {

    /** 调用 <code>GenNative_xxx</code> 标签 */
    public static String callGenNativeTag(
            SourceLocation loc, IEvalScope scope, String nativeType, Map<String, Object> args
    ) {
        String libPath = loc.getPath();
        XplTagLib lib = (XplTagLib) ResourceComponentManager.instance().loadComponentModel(libPath);
        XplTag tag = lib.getTag("GenNative_" + nativeType);

        // TODO 检查 tag 是否存在，并抛出详细的异常信息

        return tag.generateNode(scope, args).contentText();
    }

    /** 从 {@link XuiComponent} 中获取相关的库导入信息 */
    public static List<ImportedLib> getImportedLibs(List<XuiComponent> components) {
        List<ImportedLib> directives = new ArrayList<>();

        components.forEach(cmp -> {
            directives.addAll(getImportedLibs(cmp));
        });

        return directives;
    }

    /** 从 {@link XuiComponent} 中获取相关的库导入信息 */
    public static List<ImportedLib> getImportedLibs(XuiComponent component) {
        // Note: 仅转换非原生组件的导入
        return component.getNonNativeImports().stream().map(imp -> {
            String resourcePath = imp.getFrom();
            Object model = imp.getModel();

            String name = imp.getAs();
            String path = StringHelper.removeStart(resourcePath, "/");
            path = StringHelper.removeLastPart(path, '/');

            ImportedLib lib = new ImportedLib();
            lib.setName(name);
            lib.setPath(path);
            lib.setResourcePath(resourcePath);
            lib.setModel(model);

            return lib;
        }).collect(Collectors.toList());
    }

    /** 根据 {@link XuiComponent#getNonNativeImports()} 生成外部组件的导入指令 */
    public static String genLibImportDirectives(XuiComponent component, String libRootPath, String libSuffix) {
        List<ImportedLib> libs = getImportedLibs(component);

        return (libs.isEmpty() ? "" : "\n") //
               + libs.stream()
                     .map(lib -> String.format("import %s from '%s/%s/%s%s'",
                                               lib.getName(),
                                               libRootPath,
                                               lib.getPath(),
                                               lib.getName(),
                                               libSuffix))
                     .collect(Collectors.joining(";\n")) //
               + (libs.isEmpty() ? "" : ";\n");
    }
}
