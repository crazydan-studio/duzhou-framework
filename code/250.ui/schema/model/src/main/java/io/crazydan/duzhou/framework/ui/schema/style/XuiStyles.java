package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyles;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.INeedInit;
import io.nop.api.core.validate.ListValidationErrorCollector;
import io.nop.xlang.xpl.utils.XplParseHelper;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_PATCH_NODE_NOT_ALLOWED;
import static io.nop.xlang.XLangErrors.ARG_TAG1;
import static io.nop.xlang.XLangErrors.ARG_TAG2;

public class XuiStyles extends _XuiStyles implements INeedInit {

    public XuiStyles() {
    }

    /** Note: init 函数将在 {@link #freeze} 之前被调用 */
    @Override
    public void init() {
        ListValidationErrorCollector errorCollector = new ListValidationErrorCollector();

        getChildren().forEach((defName, def) -> {
            def.checkTagName(errorCollector);

            def.checkChildRefs(this, errorCollector);
        });
    }

    /** 检查样式定义的有效性 */
    protected void checkDefNodes() {
        for (XuiStyleDef def : getChildren().values()) {

            for (XuiStyleDefNode defNode : def.getChildren().values()) {
                String defNodeName = defNode.get$tag();
                XuiStyleDef defNodeDef = getStyleDef(defNodeName);

                if (defNode.hasChildren()) {
                    throw new NopException(ERR_STYLES_PATCH_NODE_NOT_ALLOWED).source(defNode)
                                                                             .param(ARG_TAG1, def.get$tag())
                                                                             .param(ARG_TAG2, defNodeName);
                } else {
                    // TODO check reference and parameters
                }
            }
        }
    }

    protected void checkRefNodeProps(XuiStyleRef styleRef, XuiStyleDef styleDef) {
        List<String> undefinedProps = new ArrayList<>();

        styleRef.getProps().forEach((prop, value) -> {
            if (styleDef != null && !styleDef.hasProp(prop)) {
                undefinedProps.add(prop);
            }

            // TODO set style prop type

            if (XplParseHelper.isExpr(value.toString())) {
                //
            }
        });
    }
}
