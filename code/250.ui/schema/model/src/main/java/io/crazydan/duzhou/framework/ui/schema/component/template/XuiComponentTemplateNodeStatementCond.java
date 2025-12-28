package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeStatementCond;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiComponentTemplateNodeStatementCond extends _XuiComponentTemplateNodeStatementCond {

    public XuiComponentTemplateNodeStatementCond() {
    }

    @Override
    public void validate(XuiComponent component, IValidationErrorCollector collector) {
        super.validate(getLocation(), collector);

        super.validate(component, collector);
    }
}
