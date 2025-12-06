package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeItem;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;

public class XuiLayoutNodeItem extends _XuiLayoutNodeItem {

    public XuiLayoutNodeItem() {
    }

    @Override
    public XuiLayoutNodeProps getProps() {
        return firstNonNull(super.getProps(), XuiLayoutNodeProps.DEFAULT);
    }
}
