package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeStatementChoose;
import io.nop.api.core.util.INeedInit;

public class XuiComponentTemplateNodeStatementChoose extends _XuiComponentTemplateNodeStatementChoose
        implements INeedInit {

    public XuiComponentTemplateNodeStatementChoose() {

    }

    @Override
    public void init() {
        getWhens().forEach(XuiComponentTemplateNodeNested::init);
        if (getOtherwise() != null) {
            getOtherwise().init();
        }
    }
}
