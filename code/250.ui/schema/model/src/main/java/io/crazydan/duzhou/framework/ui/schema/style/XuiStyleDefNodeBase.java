package io.crazydan.duzhou.framework.ui.schema.style;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDefNodeBase;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_ALIAS;
import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_MULTIPLE;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_ALIAS_STYLE_NOT_SUPPORT_MULTIPLE;
import static io.nop.xlang.XLangErrors.ARG_ATTR_NAME;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiStyleDefNodeBase extends _XuiStyleDefNodeBase {

    public XuiStyleDefNodeBase() {
    }

    @Override
    public void validate(XuiStyleDefs styleDefs, XuiStyleDef ownerStyleDef, IValidationErrorCollector collector) {
        // Note: 优先通过别名查找定义样式
        String styleName = getStyleAlias();
        boolean useAlias = styleName != null;
        if (!useAlias) {
            styleName = get$tag();
        }

        XuiStyleDef styleDef = checkStyleDefByName(styleDefs, styleName, collector);
        // 定义的样式未启用可重复引用
        if (styleDef != null && useAlias && !styleDef.canBeMultiple()) {
            collector.buildError(ERR_STYLES_ALIAS_STYLE_NOT_SUPPORT_MULTIPLE)
                     .loc(getLocation())
                     .param(ARG_TAG_NAME, styleDef.get$tag())
                     .param(ARG_PROP_NAME, ATTR_NAME_XUI_MULTIPLE)
                     .param(ARG_ATTR_NAME, ATTR_NAME_XUI_ALIAS)
                     .addToCollector(collector);
        }

        checkStyleProps(ownerStyleDef, styleDef, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 获取样式别名，其对应真实的定义样式 */
    public String getStyleAlias() {
        return StringHelper.toString(prop_get(ATTR_NAME_XUI_ALIAS), null);
    }
}
