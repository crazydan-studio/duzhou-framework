package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.LinkedHashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.ui.domain.type.XuiExpr;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleRef;
import io.nop.api.core.exceptions.NopException;
import io.nop.xlang.xdef.XDefTypeDecl;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_STYLE_PROP;
import static io.nop.xlang.XLangErrors.ARG_DEF_LOC;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;

public class XuiStyleRef extends _XuiStyleRef {
    private Map<String, Object> typedProps;

    public XuiStyleRef() {
    }

    /** @return 始终不返回 {@code null} */
    public Map<String, String> getProps() {
        return firstNonNull(get$props(), Map.of());
    }

    /** 检查样式引用上的属性有效性，并对其进行初始化 */
    public void init(XuiStyleDef styleDef) {
        Map<String, Object> typedProps = new LinkedHashMap<>();

        getProps().forEach((propName, propValue) -> {
            XDefTypeDecl propType = styleDef.getPropType(propName);
            if (propType == null) {
                throw new NopException(ERR_STYLES_UNDEFINED_STYLE_PROP).source(this)
                                                                       .param(ARG_DEF_LOC, styleDef)
                                                                       .param(ARG_PROP_NAME, propName);
            }

            Object typedPropValue;
            if (XuiExpr.isExpr(propValue)) {
                typedPropValue = XuiExpr.create(getLocation(), propType, propValue);
            } else {
                // TODO 按类型转换值
                typedPropValue = null;
            }
            typedProps.put(propName, typedPropValue);
        });

        this.typedProps = typedProps;
    }
}
