package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeNative;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_NAME;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_TAG_ATTR_REQUIRED;
import static io.nop.xlang.XLangErrors.ARG_ATTR_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiComponentTemplateNodeNative extends _XuiComponentTemplateNodeNative {

    public XuiComponentTemplateNodeNative() {
    }

    @Override
    public void validate(XuiComponent component, IValidationErrorCollector collector) {
        if (StringHelper.isBlank(getName())) {
            collector.buildError(ERR_TAG_ATTR_REQUIRED)
                     .loc(getLocation())
                     .param(ARG_TAG_NAME, get$tag())
                     .param(ARG_ATTR_NAME, ATTR_NAME_NAME)
                     .addToCollector(collector);
        }

        super.validate(component, collector);
    }
}
