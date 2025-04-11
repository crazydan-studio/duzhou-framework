package io.crazydan.duzhou.framework.ui.schema;

import io.crazydan.duzhou.framework.ui.schema._gen._XuiComponent;
import io.nop.api.core.util.INeedInit;

public class XuiComponent extends _XuiComponent implements INeedInit {

    public XuiComponent() {

    }

    @Override
    public void init() {
        initTemplate();
    }

    private void initTemplate() {
        getTemplate().getChildren();

        // TODO XuiComponentNode 类型的节点的 $type 需满足组件名规范，参考 CheckStdDomainHandler.parseProp 抛出异常
    }
}
