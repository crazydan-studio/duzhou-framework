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

package io.crazydan.duzhou.framework.commons;

import java.util.HashMap;

import io.nop.commons.text.CDataText;
import io.nop.core.lang.json.xml.DefaultJsonToXNodeAdapter;
import io.nop.core.lang.json.xml.IJsonToXNodeAdapter;
import io.nop.core.lang.json.xml.JsonToXNodeTransformer;
import io.nop.core.lang.json.xml.JsonXNodeType;
import io.nop.core.lang.json.xml.NodeData;
import io.nop.core.lang.xml.XNode;
import io.nop.core.lang.xml.handler.CollectXNodeHandler;
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
     * <p/>
     * 需在单线程内完成同名变量的使用，避免在多线程内引用同名变量
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
    public static XNode toHtmlNode(XNode node) {
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

    /** 将 JSON 对象状换为 {@link XNode} */
    public static XNode jsonToXNode(Object json) {
        IJsonToXNodeAdapter adapter = new JsonToXNodeAdapter();
        JsonToXNodeTransformer transformer = new JsonToXNodeTransformer(adapter);

        CollectXNodeHandler out = new CollectXNodeHandler();
        transformer.transform(json, out);

        return out.root();
    }

    private static class JsonToXNodeAdapter extends DefaultJsonToXNodeAdapter {

        @Override
        public NodeData getNodeData(String key, Object obj) {
            if (obj instanceof NodeData) {
                return (NodeData) obj;
            }

            NodeData node = super.getNodeData(key, obj);
            if (node.getNodeType() == null) {
                node.setNodeType(JsonXNodeType.node);

                Object type = node.getAttrs().remove("type");
                if (type != null) {
                    node.setTagName(type.toString());
                }
            }

            if (node.getAttrs() != null) {
                (new HashMap<>(node.getAttrs())).forEach((attr, value) -> {
                    // 对文本内容，采用 CDATA 节点
                    if (attr.equals("body") && value instanceof String) {
                        node.getAttrs().remove(attr);

                        NodeData child = new NodeData();
                        child.setNodeType(JsonXNodeType.node);
                        child.setTagName(attr);
                        child.addChild(new CDataText(value.toString()));

                        node.addChild(child);
                    }
                    // 值为 boolean 时，需为属性值添加 `@:` 前缀
                    else if (value instanceof Boolean) {
                        node.getAttrs().put(attr, "@:" + value);
                    }
                });
            }

            // 对 list 类型，设置节点属性 `j:list="true"`
            if (node.getNodeType() == JsonXNodeType.list) {
                node.addAttr("j:list", true);
            }

            return node;
        }
    }
}
