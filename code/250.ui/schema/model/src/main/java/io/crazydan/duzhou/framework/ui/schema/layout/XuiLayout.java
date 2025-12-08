package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayout;
import io.nop.api.core.util.INeedInit;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

public class XuiLayout extends _XuiLayout implements INeedInit {

    public XuiLayout() {
    }

    @Override
    public void init() {
        XuiLayoutRoot root = getRoot();
        ifNotNull(root, XuiLayoutRoot::init);
    }
}
