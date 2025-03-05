package io.crazydan.duzhou.framework.ui.schema;

import io.crazydan.duzhou.framework.ui.schema._gen._XuiStyleDef;
import io.nop.api.core.util.INeedInit;

public class XuiStyleDef extends _XuiStyleDef implements INeedInit {

    public XuiStyleDef() {

    }

    @Override
    public void init() {
        // TODO 校验属性类型等：当前 Nop 未支持对 xdef:unknown-attr 属性的校验

        // Note:
        // - 被标记为 xui:null 的属性将在 x:post-extends 阶段被移除

        getStyles().forEach((name, style) -> style.init());
    }
}
