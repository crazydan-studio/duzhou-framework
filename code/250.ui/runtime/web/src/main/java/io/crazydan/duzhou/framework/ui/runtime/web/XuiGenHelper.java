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
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.runtime.web.model.ImportedLib;
import io.crazydan.duzhou.framework.ui.schema.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentLayout;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNode;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutProps;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.resource.component.ResourceComponentManager;
import io.nop.xlang.xpl.xlib.XplTag;
import io.nop.xlang.xpl.xlib.XplTagLib;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-16
 */
public class XuiGenHelper {
    public static final String GEN_NATIVE_TAG_PREFIX = "GenNative_";
    public static final String LAYOUT_IMPORT_NAME_PREFIX = "Layout_";

    /** 调用 <code>GenNative_xxx</code> 标签 */
    public static String callGenNativeTag(
            SourceLocation loc, IEvalScope scope, String nativeType, Map<String, Object> args
    ) {
        String libPath = loc.getPath();
        XplTagLib lib = (XplTagLib) ResourceComponentManager.instance().loadComponentModel(libPath);
        XplTag tag = lib.getTag(GEN_NATIVE_TAG_PREFIX + nativeType);

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

        return toImportDirectives(libs, "", libRootPath, libSuffix);
    }

    /** 生成组件布局的导入指令 */
    public static String genLayoutImportDirectives(XuiComponent component, String libRootPath, String libSuffix) {
        Map<String, ImportedLib> libs = new LinkedHashMap<>();

        getUsedLayouts(component.getTemplate()).forEach((layout) -> {
            XuiLayoutNode layoutRoot = layout.getType().getRoot();
            String layoutType = layout.getType().getType();

            String layoutLibName = StringHelper.camelCase(layoutType, '-', true);
            if (!libs.containsKey(layoutLibName)) {
                ImportedLib layoutLib = new ImportedLib();
                layoutLib.setName(layoutLibName);
                layoutLib.setPath("layout");

                libs.put(layoutLibName, layoutLib);
            }

            layoutRoot.getTypes().forEach((layoutNodeType) -> {
                String layoutNodeLibName = StringHelper.camelCase(layoutNodeType.name(), true);
                if (libs.containsKey(layoutNodeLibName)) {
                    return;
                }

                ImportedLib layoutNodeLib = new ImportedLib();
                layoutNodeLib.setName(layoutNodeLibName);
                layoutNodeLib.setNamePrefix(layoutLibName + "_");
                layoutNodeLib.setPath("layout/" + layoutType);

                libs.put(layoutNodeLib.getNamePrefix() + layoutNodeLibName, layoutNodeLib);
            });
        });

        return toImportDirectives(libs.values(), LAYOUT_IMPORT_NAME_PREFIX, libRootPath, libSuffix);
    }

    /**
     * 生成布局节点的属性列表
     * <p/>
     * 生成结果如下：
     * <pre>
     * { gap: "{ '16px' }", padding: "{ {left: '16px', right: '8px'} }" }
     * </pre>
     * <pre>
     * { gap: "{ props.gap }", padding: "{ {left: props.padding, right: '16px'} }" }
     * </pre>
     */
    public static Map<String, String> genLayoutNodeAttrs(XuiLayoutNode node, XuiGenConfig genConfig) {
        Map<String, String> attrs = new HashMap<>();

        XuiLayoutProps props = node.getProps();

        ifNotNull(genConfig.toXmlAttrExpr(props.getWidth()), (v) -> attrs.put("width", v));
        ifNotNull(genConfig.toXmlAttrExpr(props.getHeight()), (v) -> attrs.put("height", v));

        ifNotNull(genConfig.toXmlAttrExpr(props.getAlign()), (v) -> attrs.put("align", v));
        ifNotNull(genConfig.toXmlAttrExpr(props.getSpan()), (v) -> attrs.put("span", v));
        ifNotNull(genConfig.toXmlAttrExpr(props.getGap()), (v) -> attrs.put("gap", v));
        ifNotNull(genConfig.toXmlAttrExpr(props.getPadding()), (v) -> attrs.put("padding", v));

        return attrs;
    }

    private static String toImportDirectives(
            Collection<ImportedLib> libs, String importNamePrefix, String libRootPath, String libSuffix
    ) {
        return (libs.isEmpty() ? "" : "\n") //
               + libs.stream()
                     .map(lib -> String.format("import %s%s%s from '%s/%s/%s%s'",
                                               importNamePrefix,
                                               lib.getNamePrefix() != null ? lib.getNamePrefix() : "",
                                               lib.getName(),
                                               libRootPath,
                                               lib.getPath(),
                                               lib.getName(),
                                               libSuffix))
                     .collect(Collectors.joining(";\n")) //
               + (libs.isEmpty() ? "" : ";\n");
    }

    private static List<XuiComponentLayout> getUsedLayouts(XuiComponentNode node) {
        List<XuiComponentLayout> layouts = new ArrayList<>();

        node.getChildren().forEach((child) -> {
            if (child instanceof XuiComponentLayout && ((XuiComponentLayout) child).getType() != null) {
                layouts.add((XuiComponentLayout) child);
            } else if (child instanceof XuiComponentNode) {
                layouts.addAll(getUsedLayouts((XuiComponentNode) child));
            }
        });

        return layouts;
    }
}
