package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeStatementFor;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiComponentTemplateNodeStatementFor extends _XuiComponentTemplateNodeStatementFor {

    public XuiComponentTemplateNodeStatementFor() {
    }

    @Override
    public void validate(XuiComponent component, IValidationErrorCollector collector) {
        super.validate(getLocation(), collector);

        super.validate(component, collector);
    }
}
