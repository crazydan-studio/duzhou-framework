package io.crazydan.duzhou.framework.ui.schema.style;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDefNode;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_ALIAS;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_NOT_ALLOWED_PROP;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_PATCH_NODE_NOT_ALLOWED;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG1;
import static io.nop.xlang.XLangErrors.ARG_TAG2;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiStyleDefNode extends _XuiStyleDefNode {

    public XuiStyleDefNode() {
    }

    @Override
    public void validate(XuiStyleDefs styleDefs, XuiStyleDef ownerStyleDef, IValidationErrorCollector collector) {
        if (!(styleDefs instanceof XuiComponentStyles)) {
            // 在非组件样式定义集中，样式只能包含一层结构
            if (hasChildren()) {
                collector.buildError(ERR_STYLES_PATCH_NODE_NOT_ALLOWED)
                         .loc(getLocation())
                         .param(ARG_TAG1, ownerStyleDef.get$tag())
                         .param(ARG_TAG2, get$tag())
                         .addToCollector(collector);
            } else {
                super.validate(styleDefs, ownerStyleDef, collector);
            }
        } else {
            if (prop_has(ATTR_NAME_XUI_ALIAS)) {
                collector.buildError(ERR_STYLES_NOT_ALLOWED_PROP)
                         .loc(getLocation())
                         .param(ARG_TAG_NAME, get$tag())
                         .param(ARG_PROP_NAME, ATTR_NAME_XUI_ALIAS)
                         .addToCollector(collector);
            }

            // 在组件样式定义集中，不检查第一层结构的样式定义，仅检查其属性引用
            checkStyleProps(ownerStyleDef, null, collector);

            getChildren().forEach((name, child) -> {
                child.validate(styleDefs, ownerStyleDef, collector);
            });
        }
    }
}
