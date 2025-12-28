package io.crazydan.duzhou.framework.ui.schema.component.style;

import io.crazydan.duzhou.framework.ui.schema.component.style._gen._XuiComponentStyleBase;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefs;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_MULTIPLE;
import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_SOURCE;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_NOT_ALLOWED_PROP;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiComponentStyleBase extends _XuiComponentStyleBase {

    public XuiComponentStyleBase() {
    }

    @Override
    public void validate(XuiStyleDefs styleDefs, IValidationErrorCollector collector) {
        for (String prop : new String[] { ATTR_NAME_XUI_MULTIPLE, ATTR_NAME_XUI_SOURCE }) {
            if (prop_has(prop)) {
                collector.buildError(ERR_STYLES_NOT_ALLOWED_PROP)
                         .loc(getLocation())
                         .param(ARG_TAG_NAME, get$tag())
                         .param(ARG_PROP_NAME, prop)
                         .addToCollector(collector);
            }
        }

        super.validate(styleDefs, collector);
    }
}
