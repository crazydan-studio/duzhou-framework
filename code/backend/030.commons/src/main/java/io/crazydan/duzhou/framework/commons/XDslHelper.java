/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
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

import io.nop.core.lang.xml.XNode;
import io.nop.core.resource.IResource;
import io.nop.core.resource.VirtualFileSystem;
import io.nop.xlang.xdsl.DslNodeLoader;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-29
 */
public class XDslHelper {

    /**
     * 加载指定路径的 DSL 定义，并返回其 {@link XNode} 结构
     * <p/>
     * 在 DSL 中可以在 xpl 脚本内引用传入的变量，
     * 变量名称必须以 `$` 为前缀，以指示其为全局变量，
     * 非 `$` 开头的变量无法访问
     *
     * @param vars
     *         需传入的变量键值对
     */
    public static XNode loadXNodeFromResource(String path, Object[]... vars) {
        IResource resource = VirtualFileSystem.instance().getResource(path);

        return EvalExecutor.withGlobalVars(() -> new DslNodeLoader().loadFromResource(resource).getNode(), vars);
    }

    /**
     * 将 {@link XNode} 转换为 HTML 节点
     * <p/>
     * 返回的是 {@link XNode} 的副本，其会对该副本做以下工作：<ul>
     * <li>去掉注释节点；</li>
     * <li>去掉 `xmlns` 等与 HTML 无关的节点属性；</li>
     * <li>去掉 style/script 节点内容中的注释；</li>
     * </ul>
     */
    public static XNode toHtml(XNode node) {
        XNode html = node.cloneInstance();

        html.clearComment();
        html.clearLocation();
        html.removeAttrsWithPrefix("xmlns:");
        html.findAllByTag("style").forEach(XDslHelper::removeContentComments);
        html.findAllByTag("script").forEach(XDslHelper::removeContentComments);

        return html;
    }

    /** 移除 {@link XNode} 节点内容中的注释 */
    public static void removeContentComments(XNode node) {
        if (!node.hasContent()) {
            return;
        }

        String content = node.contentText();
        content = content.replaceAll("[ \\t\\x0B\\f]*//.*$", "") //
                         .replaceAll("[ \\t\\x0B\\f]*/\\*.*\\*/[ \\t\\x0B\\f]*", "");
        node.content(content);
    }
}
