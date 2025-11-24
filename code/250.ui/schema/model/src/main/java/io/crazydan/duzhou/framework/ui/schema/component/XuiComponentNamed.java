package io.crazydan.duzhou.framework.ui.schema.component;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentNamed;

public class XuiComponentNamed extends _XuiComponentNamed {

    public XuiComponentNamed() {
    }

    public String getXuiName() {
        return (String) prop_get("xui:name");
    }
}
