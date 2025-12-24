package io.crazydan.duzhou.framework.ui.schema.style;

import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleRefs;
import io.nop.api.core.exceptions.NopException;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_STYLE;
import static io.nop.xlang.XLangErrors.ARG_DEF_LOC;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiStyleRefs extends _XuiStyleRefs {

    public XuiStyleRefs() {
    }

    /** 根据{@link XuiStyleDefs 样式定义集}校验并初始化当前的 XuiStyleRefs */
    public void init(XuiStyleDefs styleDefs, String stylePrefix) {
        // Note: 在样式引用集中，引用的样式必须已定义，且只能包含定义的属性，但属性值可通过表达式 ${xxx} 引用组件变量
        getChildren().forEach((styleName, styleRef) -> {
            String newStyleName = stylePrefix != null ? stylePrefix + styleName : styleName;
            XuiStyleDef styleDef = styleDefs.getStyleDef(newStyleName);
            if (styleDef == null) {
                throw new NopException(ERR_STYLES_UNDEFINED_STYLE).source(styleRef)
                                                                  .param(ARG_DEF_LOC, styleDefs)
                                                                  .param(ARG_TAG_NAME, newStyleName);
            }

            styleRef.init(styleDef);

            // TODO 根据组件定义检查组件部件样式的引用
        });
    }
}
