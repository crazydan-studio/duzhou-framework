package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.Map;

import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleRef;
import io.nop.api.core.exceptions.NopException;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_STYLE_PROP;
import static io.nop.xlang.XLangErrors.ARG_DEF_LOC;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;

public class XuiStyleRef extends _XuiStyleRef {

    public XuiStyleRef() {
    }

    /** 检查样式引用上的属性有效性，并对其进行初始化 */
    public void init(XuiStyleDef styleDef, Map<String, Object> varDefs) {
        getProps().forEach((propName, propValue) -> {
            Object propDef = styleDef.getProps().get(propName);
            if (propDef == null) {
                throw new NopException(ERR_STYLES_UNDEFINED_STYLE_PROP).source(this)
                                                                       .param(ARG_DEF_LOC, styleDef)
                                                                       .param(ARG_PROP_NAME, propName);
            }

            // TODO 检查 ${xxx} 引用中的变量类型与定义的属性类型是否一致
        });
    }
}
