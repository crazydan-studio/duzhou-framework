package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.domain.GenericStdDomainHandlers;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeAny;
import io.nop.api.core.exceptions.NopException;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_INVALID_TAG_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiComponentTemplateNodeAny extends _XuiComponentTemplateNodeAny {

    public XuiComponentTemplateNodeAny() {
    }

    @Override
    public void init() {
        checkTagName();

        super.init();
    }

    protected void checkTagName() {
        String tagName = getTagName();

        if (!GenericStdDomainHandlers.isValidComponentName(tagName)) {
            throw new NopException(ERR_COMPONENT_INVALID_TAG_NAME).source(this).param(ARG_TAG_NAME, tagName);
        }
    }
}
