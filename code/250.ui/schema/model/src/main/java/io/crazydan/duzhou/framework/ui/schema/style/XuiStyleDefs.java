package io.crazydan.duzhou.framework.ui.schema.style;

import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDefs;

public class XuiStyleDefs extends _XuiStyleDefs {

    public XuiStyleDefs() {
    }

    /** 获取指定名字的{@link XuiStyleDef 样式定义} */
    public XuiStyleDef getStyleDef(String name) {
        return getChild(name);
    }
}
