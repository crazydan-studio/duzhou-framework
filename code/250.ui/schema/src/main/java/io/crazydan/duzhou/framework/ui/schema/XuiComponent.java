package io.crazydan.duzhou.framework.ui.schema;

import java.util.List;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.schema._gen._XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed;
import io.nop.api.core.util.INeedInit;

public class XuiComponent extends _XuiComponent implements INeedInit {
    public static final String NATIVE_IMPORT_PREFIX = "native:";

    public XuiComponent() {
    }

    @Override
    public void init() {
        initTemplate();
    }

    /** 如果导入的节点为原生组件，则返回其原生组件类型 */
    public String getNodeNativeType(XuiComponentNamed node) {
        String nodeType = node.get$type();
        XuiComponentImport imported = getImport(nodeType);

        return imported != null && imported.getFrom().startsWith(NATIVE_IMPORT_PREFIX)
               ? imported.getFrom()
                         .substring(NATIVE_IMPORT_PREFIX.length())
               : null;
    }

    /** 获取非原生导入组件列表 */
    public List<XuiComponentImport> getNonNativeImports() {
        return getImports().stream()
                           .filter(imp -> !imp.getFrom().startsWith(NATIVE_IMPORT_PREFIX))
                           .collect(Collectors.toList());
    }

    private void initTemplate() {
        // TODO XuiComponentNode 类型的节点的 $type 需满足组件名规范，参考 CheckStdDomainHandler.parseProp 抛出异常
    }
}
