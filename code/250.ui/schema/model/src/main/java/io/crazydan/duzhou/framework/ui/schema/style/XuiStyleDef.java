package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.Map;

import io.crazydan.duzhou.framework.ui.domain.GenericStdDomainHandlers;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDef;
import io.nop.api.core.exceptions.NopException;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_INVALID_TAG_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiStyleDef extends _XuiStyleDef {

    public XuiStyleDef() {
    }

    public void checkTagName() {
        String tagName = get$tag();
        if (!GenericStdDomainHandlers.isValidStyleName(tagName)) {
            throw new NopException(ERR_STYLES_INVALID_TAG_NAME).source(this).param(ARG_TAG_NAME, tagName);
        }
    }

    /** @return 始终不返回 {@code null} */
    @Override
    public Map<String, String> getProps() {
        return firstNonNull(super.getProps(), Map.of());
    }
}
