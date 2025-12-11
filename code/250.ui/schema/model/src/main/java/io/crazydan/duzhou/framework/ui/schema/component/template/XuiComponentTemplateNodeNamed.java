package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeNamed;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_SLOT;

public class XuiComponentTemplateNodeNamed extends _XuiComponentTemplateNodeNamed {

    public XuiComponentTemplateNodeNamed() {
    }

    public String getTagName() {
        return get$tag();
    }

    public boolean isSlot() {
        return this instanceof XuiComponentTemplateNodeSlot;
    }

    public boolean isNative() {
        return this instanceof XuiComponentTemplateNodeNative;
    }

    public boolean isText() {
        return this instanceof XuiComponentTemplateNodeText;
    }

    public boolean isCustom() {
        return this instanceof XuiComponentTemplateNodeAny;
    }

    public boolean isSlottable() {
        return this instanceof XuiComponentTemplateNodeSlottable || isText();
    }

    public String getXuiSlot() {
        return isSlottable() ? (String) prop_get(ATTR_NAME_XUI_SLOT) : null;
    }
}
