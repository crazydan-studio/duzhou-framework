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

import java.util.HashMap;

import io.nop.core.lang.json.xml.DefaultJsonToXNodeAdapter;
import io.nop.core.lang.json.xml.IJsonToXNodeAdapter;
import io.nop.core.lang.json.xml.JsonToXNodeTransformer;
import io.nop.core.lang.json.xml.JsonXNodeType;
import io.nop.core.lang.json.xml.NodeData;
import io.nop.core.lang.xml.XNode;
import io.nop.core.lang.xml.handler.CollectXNodeHandler;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-22
 */
public class WebPageProviderHelper {

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
                node.getAttrs().remove("_");

                Object type = node.getAttrs().remove("type");
                if (type != null) {
                    node.setTagName(type.toString());
                }
            }

            if (node.getAttrs() != null) {
                (new HashMap<>(node.getAttrs())).forEach((attr, value) -> {
                    if (attr.equals("body") && value instanceof String) {
                        node.getAttrs().remove(attr);

                        NodeData child = new NodeData();
                        child.setNodeType(JsonXNodeType.node);
                        child.setTagName(attr);
                        child.addChild(value);

                        node.addChild(child);
                    } else if (value instanceof Boolean) {
                        node.getAttrs().put(attr, "@:" + value);
                    }
                });
            }

            if (node.getNodeType() == JsonXNodeType.list) {
                node.addAttr("j:list", true);
            }

            return node;
        }
    }
}
