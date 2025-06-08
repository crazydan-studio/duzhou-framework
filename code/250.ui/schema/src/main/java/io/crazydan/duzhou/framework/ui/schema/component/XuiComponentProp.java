package io.crazydan.duzhou.framework.ui.schema.component;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentProp;
import io.nop.api.core.util.INeedInit;

public class XuiComponentProp extends _XuiComponentProp implements INeedInit {

    public XuiComponentProp() {
    }

    @Override
    public void init() {
        // TODO 有子结构的，不可配置 `type` 和 `obj-meta`
        // TODO 没有子结构的，`type` 和 `obj-meta` 必须且只能配置一个
    }
}
