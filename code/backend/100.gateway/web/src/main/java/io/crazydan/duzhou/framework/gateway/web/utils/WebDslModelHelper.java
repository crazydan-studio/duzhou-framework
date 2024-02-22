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

package io.crazydan.duzhou.framework.gateway.web.utils;

import io.nop.core.lang.xml.XNode;
import io.nop.xlang.xdsl.DslModelHelper;
import io.nop.xlang.xdsl.XDslKeys;
import io.nop.xlang.xdsl.json.DslModelToXNodeTransformer;
import io.nop.xlang.xmeta.IObjMeta;
import io.nop.xlang.xmeta.IObjPropMeta;
import io.nop.xlang.xmeta.IObjSchema;
import io.nop.xlang.xmeta.SchemaLoader;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-10
 */
public class WebDslModelHelper {

    /**
     * 将 DSL 模型转换为 HTML 节点
     * <p/>
     * 与 {@link DslModelHelper#dslModelToXNode} 不同的是，
     * 其会额外做以下工作：<ul>
     * <li>去掉 `xmlns` 等与 HTML 无关的节点属性；</li>
     * <li>确保 html>body>div 内的子节点和文本与 dsl 中定义的结构一致，
     * 不能再在 div 中添加 body 标签包裹其内部节点；</li>
     * </ul>
     */
    public static XNode toHtmlNode(String xdefPath, Object model) {
        IObjMeta objMeta = SchemaLoader.loadXMeta(xdefPath);

        XNode node = new XNodeTransformer(objMeta, true).transformToXNode(model);
        node.removeAttr(XDslKeys.DEFAULT.SCHEMA);
        node.removeAttr("xmlns:x");

        return node;
    }

    private static class XNodeTransformer extends DslModelToXNodeTransformer {
        private final boolean ignoreXmlNs;

        public XNodeTransformer(IObjMeta objMeta, boolean ignoreXmlNs) {
            super(objMeta);

            this.ignoreXmlNs = ignoreXmlNs;
        }

        protected void addToNode(IObjSchema schema, XNode node, Object map, String key, Object value) {
            IObjPropMeta propMeta = schema.getProp(key);

            if (propMeta != null && "body".equals(key) //
                && value instanceof XNode && ((XNode) value).isDummyNode()) {
                // Note：子节点和文本的添加顺序不影响二者的定义顺序，在输出 xml 时，相对顺序不会发生变化
                node.appendChildren(((XNode) value).detachChildren());
                node.appendContent(((XNode) value).content());
            } else if (!this.ignoreXmlNs || !key.startsWith("xmlns:")) {
                super.addToNode(schema, node, map, key, value);
            }
        }
    }
}
