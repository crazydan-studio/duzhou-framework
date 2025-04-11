package io.crazydan.duzhou.framework.ui.schema.app;

import io.crazydan.duzhou.framework.ui.schema.XuiPage;
import io.crazydan.duzhou.framework.ui.schema.app._gen._XuiAppPageRef;
import io.nop.core.resource.component.ResourceComponentManager;

public class XuiAppPageRef extends _XuiAppPageRef {
    private XuiPage page;

    public XuiAppPageRef() {

    }

    public XuiPage getPage() {
        if (this.page == null) {
            this.page = (XuiPage) ResourceComponentManager.instance().loadComponentModel(getPath());
        }
        return this.page;
    }
}
