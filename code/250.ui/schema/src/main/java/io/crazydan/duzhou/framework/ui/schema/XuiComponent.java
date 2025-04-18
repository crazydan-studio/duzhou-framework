package io.crazydan.duzhou.framework.ui.schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.schema._gen._XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed;
import io.nop.api.core.util.INeedInit;

public class XuiComponent extends _XuiComponent implements INeedInit {

    public XuiComponent() {
    }

    @Override
    public void init() {
        initTemplate();
    }

    /** 如果模板节点对应的导入组件为原生组件，则返回其原生组件类型 */
    public String getNodeNativeType(XuiComponentNamed node) {
        XuiComponentImport imported = getNodeImport(node);

        return imported != null ? imported.getNativeType() : null;
    }

    /** 获取非原生导入组件列表 */
    public List<XuiComponentImport> getNonNativeImports() {
        return getImports().stream().filter(imp -> !imp.isNative()).collect(Collectors.toList());
    }

    /** 获取模板节点的属性列表：有效属性与对应组件声明的 `&lt;props/&gt;` 一致 */
    public Map<String, Object> getNodeAttrs(XuiComponentNamed node) {
        XuiComponent meta = getNodeMeta(node);
        if (meta == null) {
            return Map.of();
        }

        Map<String, Object> attrs = new HashMap<>();
        // TODO 根据组件结构中的 <props/> 确定在节点上配置的哪些属性可以返回
        node.prop_names().forEach(name -> {
            Object value = node.prop_get(name);
            attrs.put(name, value);
        });

        return attrs;
    }

    /** 获取模板节点的元模型对象 {@link XuiComponent}，以便于分析组件类型节点的定义结构 */
    public XuiComponent getNodeMeta(XuiComponentNamed node) {
        XuiComponentImport imported = getNodeImport(node);

        return imported != null ? imported.getModel() : null;
    }

    private void initTemplate() {
        // TODO XuiComponentNode 类型的节点的 $type 需满足组件名规范，参考 CheckStdDomainHandler.parseProp 抛出异常
    }

    private XuiComponentImport getNodeImport(XuiComponentNamed node) {
        String nodeType = node.get$type();
        return getImport(nodeType);
    }
}
