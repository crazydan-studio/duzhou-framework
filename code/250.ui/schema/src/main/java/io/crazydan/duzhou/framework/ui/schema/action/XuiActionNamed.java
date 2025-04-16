package io.crazydan.duzhou.framework.ui.schema.action;

import io.crazydan.duzhou.framework.ui.schema.action._gen._XuiActionNamed;

public class XuiActionNamed extends _XuiActionNamed {

    public XuiActionNamed() {
    }

    public String getXuiName() {
        return (String) prop_get("xui:name");
    }
}
