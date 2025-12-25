package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeStyles;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDef;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiComponentTemplateNodeStyles extends _XuiComponentTemplateNodeStyles {
    public static final XuiComponentTemplateNodeStyles EMPTY = new XuiComponentTemplateNodeStyles() {{
        freeze(true);
    }};

    public XuiComponentTemplateNodeStyles() {
    }

    /** 检查引用的样式是否已定义且其属性与定义的也一致 */
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        getChildren().forEach((styleName, styleRef) -> {
            XuiStyleDef styleDef = styleRef.checkStyleDef(styleDefs, collector);
            styleRef.checkStyleProps(null, styleDef, collector);
        });
    }
}
