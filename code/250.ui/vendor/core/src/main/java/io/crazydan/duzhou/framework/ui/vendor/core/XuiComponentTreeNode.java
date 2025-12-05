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

package io.crazydan.duzhou.framework.ui.vendor.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.crazydan.duzhou.framework.ui.XuiLayout;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNode;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeAny;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNative;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeText;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;
import io.nop.xlang.api.XLang;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_NAME_RAW;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-26
 */
public class XuiComponentTreeNode implements IJsonSerializable {
    public static final String VAR_NAME_PROPS = "props";
    public static final String VAR_PROP_NAME_CHILDREN = "$children";
    public static final String VAR_PROP_NAME_SLOT = "$slot";
    public static final String VAR_PROP_NAME_INNER_TEXT = "$innerText";

    public final String key;
    public final XuiLayout layout;
    public final List<XuiComponentTreeNode> children;

    public final String nativeName;
    public final Map<String, Object> nativeProps;

    public static XuiComponentTreeNode build(XuiComponent component, IEvalScope scope) {
        return build(null, component, scope);
    }

    public static XuiComponentTreeNode build(String key, XuiComponent component, IEvalScope scope) {
        // Note: 生成的组建模版树中已不再包含 <if/>、<for/> 等控制节点
        XuiComponentTemplate template = component.evalTemplate(scope);

        return buildNode(key, template, component, null, null);
    }

    XuiComponentTreeNode(
            String key, //
            XuiLayout layout, List<XuiComponentTreeNode> children, //
            String nativeName, Map<String, Object> nativeProps
    ) {
        this.key = key;
        this.layout = layout;
        this.children = Collections.unmodifiableList(children);

        this.nativeName = nativeName;
        this.nativeProps = nativeProps != null
                           ? Collections.unmodifiableMap(nativeProps)
                           : nativeName != null ? Map.of() : null;
    }

    @Override
    public void serializeToJson(IJsonHandler out) {
        out.beginObject(null);

        out.putNotNull("key", this.key);
        out.putNotNull("layout", this.layout);
        if (!this.children.isEmpty()) {
            out.put("children", this.children);
        }

        out.putNotNull("native", this.nativeName);
        out.putNotNull("props", this.nativeProps);

        out.endObject();
    }

    protected static XuiComponentTreeNode buildNode(
            String key, //
            XuiComponentTemplateNode templateNode, XuiComponent component, //
            String nativeName, Map<String, Object> nativeProps
    ) {
        XuiLayout layout = null;
        List<XuiComponentTreeNode> treeNodes = new ArrayList<>();

        for (XuiComponentTemplateNodeNamed templateNodeChild : templateNode.getChildren()) {
            if (templateNodeChild instanceof XuiComponentTemplateNodeLayout) {
                layout = ((XuiComponentTemplateNodeLayout) templateNodeChild).getType();
            } //
            else if (templateNodeChild instanceof XuiComponentTemplateNodeDispatch) {
                XuiComponentTemplateNodeDispatch dispatch = (XuiComponentTemplateNodeDispatch) templateNodeChild;
            }

            XuiComponentTreeNode treeNode = null;
            if (templateNodeChild instanceof XuiComponentTemplateNodeText //
                || templateNodeChild instanceof XuiComponentTemplateNodeAny //
            ) {
                treeNode = buildCustomNode(templateNodeChild, component);
            } //
            else if (templateNodeChild instanceof XuiComponentTemplateNodeNative) {
                treeNode = buildNativeNode((XuiComponentTemplateNodeNative) templateNodeChild, component);
            }

            if (treeNode != null) {
                treeNodes.add(treeNode);
            }
        }

        return new XuiComponentTreeNode(key, layout, treeNodes, nativeName, nativeProps);
    }

    protected static XuiComponentTreeNode buildCustomNode(
            XuiComponentTemplateNodeNamed templateNode, XuiComponent component) {
        XuiComponent tagComponent = component.loadTagComponent(templateNode);

        String key = getKey(templateNode);
        Props props = new Props(templateNode);

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue(VAR_NAME_PROPS, props);

        return build(key, tagComponent, scope);
    }

    protected static XuiComponentTreeNode buildNativeNode(
            XuiComponentTemplateNodeNative templateNode, XuiComponent component) {
        String key = getKey(templateNode);
        String nativeName = templateNode.getName();

        Map<String, Object> nativeProps = getUnknownAttrs(templateNode);

        return buildNode(key, templateNode, component, nativeName, nativeProps);
    }

    protected static String getKey(XuiComponentTemplateNodeNamed node) {
        Map<String, Object> attrs = getUnknownAttrs(node);

        // Note: 对于 <for/> 节点中的组件，会将真实 xui:name 放在 attrs 中
        if (attrs != null && attrs.containsKey(ATTR_NAME_XUI_NAME_RAW)) {
            return (String) attrs.get(ATTR_NAME_XUI_NAME_RAW);
        }
        return node.getXuiName();
    }

    protected static Map<String, Object> getUnknownAttrs(XuiComponentTemplateNodeNamed node) {
        if (node instanceof XuiComponentTemplateNodeNative) {
            return ((XuiComponentTemplateNodeNative) node).getAttrs();
        } //
        else if (node instanceof XuiComponentTemplateNodeText) {
            return ((XuiComponentTemplateNodeText) node).getAttrs();
        } //
        else if (node instanceof XuiComponentTemplateNodeAny) {
            return ((XuiComponentTemplateNodeAny) node).getAttrs();
        }
        return null;
    }

    static class Props implements Map<String, Object> {
        private final XuiComponentTemplateNodeNamed node;

        Props(XuiComponentTemplateNodeNamed node) {
            this.node = node;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return VAR_PROP_NAME_INNER_TEXT.equals(key) //
                   || VAR_PROP_NAME_CHILDREN.equals(key) //
                   || VAR_PROP_NAME_SLOT.equals(key) //
                   || this.node.prop_has((String) key) //
                    ;
        }

        @Override
        public Object get(Object key) {
            boolean isText = this.node instanceof XuiComponentTemplateNodeText;
            if (VAR_PROP_NAME_INNER_TEXT.equals(key)) {
                if (isText) {
                    return ((XuiComponentTemplateNodeText) this.node).getInnerText();
                }
                return ((XuiComponentTemplateNode) this.node).getInnerText();
            } //
            else if (VAR_PROP_NAME_CHILDREN.equals(key)) {
                if (isText) {
                    return List.of();
                }
                return ((XuiComponentTemplateNode) this.node).getCustomOrTextChildren();
            } //
            else if (VAR_PROP_NAME_SLOT.equals(key)) {
                if (isText) {
                    return Map.of();
                }
                return ((XuiComponentTemplateNode) this.node).getSlottables();
            }

            Map<String, Object> attrs = getUnknownAttrs(this.node);
            if (attrs != null && attrs.containsKey((String) key)) {
                return attrs.get(key);
            }
            return null;
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsValue(Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object put(String key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Map<? extends String, ?> m) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<String> keySet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<Object> values() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<Entry<String, Object>> entrySet() {
            throw new UnsupportedOperationException();
        }
    }
}
