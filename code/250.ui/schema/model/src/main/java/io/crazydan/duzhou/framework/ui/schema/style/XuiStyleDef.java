package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.Map;

import io.crazydan.duzhou.framework.ui.domain.GenericStdDomainHandlers;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDef;
import io.nop.api.core.exceptions.NopException;
import io.nop.xlang.xdef.XDefTypeDecl;

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
    public Map<String, XDefTypeDecl> getPropTypes() {
        return firstNonNull(get$props(), Map.of());
    }

    /** 是否存在指定名字的属性 */
    public boolean hasProp(String propName) {
        return getPropTypes().containsKey(propName);
    }

    /** 获取指定属性的类型 */
    public XDefTypeDecl getPropType(String propName) {
        return getPropTypes().get(propName);
    }
}
