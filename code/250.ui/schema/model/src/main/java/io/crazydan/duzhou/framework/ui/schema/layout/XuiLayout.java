package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayout;
import io.nop.api.core.util.INeedInit;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

public class XuiLayout extends _XuiLayout implements INeedInit {

    public XuiLayout() {
    }

    /** Note: init 函数将在 {@link #freeze} 之前被调用 */
    @Override
    public void init() {
        XuiLayoutRoot root = getRoot();
        ifNotNull(root, XuiLayoutRoot::init);
    }
}
