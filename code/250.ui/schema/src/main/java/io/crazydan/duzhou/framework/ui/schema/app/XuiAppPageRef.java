package io.crazydan.duzhou.framework.ui.schema.app;

import io.crazydan.duzhou.framework.ui.schema.XuiPage;
import io.crazydan.duzhou.framework.ui.schema.app._gen._XuiAppPageRef;
import io.nop.xlang.xdsl.DslModelHelper;

public class XuiAppPageRef extends _XuiAppPageRef {
    private XuiPage page;

    public XuiAppPageRef() {

    }

    public XuiPage getPage() {
        if (this.page == null) {
            this.page = (XuiPage) DslModelHelper.loadDslModelFromPath(getPath());
        }
        return this.page;
    }
}
