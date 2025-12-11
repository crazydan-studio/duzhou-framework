package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeStatementChoose;
import io.nop.api.core.util.INeedInit;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

public class XuiComponentTemplateNodeStatementChoose extends _XuiComponentTemplateNodeStatementChoose
        implements INeedInit {

    public XuiComponentTemplateNodeStatementChoose() {
    }

    @Override
    public void init() {
        getWhens().forEach(XuiComponentTemplateNodeBody::init);
        ifNotNull(getOtherwise(), XuiComponentTemplateNodeBody::init);
    }

    public boolean hasSlotInDepth() {
        for (XuiComponentTemplateNodeStatementChooseWhen when : getWhens()) {
            if (when.hasSlotInDepth()) {
                return true;
            }
        }

        return getOtherwise() != null && getOtherwise().hasSlotInDepth();
    }
}
