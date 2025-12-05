package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeSlottable;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_SLOT;

public class XuiComponentTemplateNodeSlottable extends _XuiComponentTemplateNodeSlottable {

    public XuiComponentTemplateNodeSlottable() {

    }

    public String getXuiSlot() {
        return (String) prop_get(ATTR_NAME_XUI_SLOT);
    }
}
