package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeStyles;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiComponentTemplateNodeStyles extends _XuiComponentTemplateNodeStyles {
    public static final XuiComponentTemplateNodeStyles EMPTY = new XuiComponentTemplateNodeStyles() {{
        freeze(true);
    }};

    public XuiComponentTemplateNodeStyles() {
    }

    /** 检查当前组件引用样式的有效性 */
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        validate(styleDefs, null, collector);
    }
}
