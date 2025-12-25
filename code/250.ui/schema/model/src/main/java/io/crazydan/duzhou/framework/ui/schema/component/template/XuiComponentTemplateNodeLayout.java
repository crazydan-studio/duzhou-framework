package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeLayout;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiComponentTemplateNodeLayout extends _XuiComponentTemplateNodeLayout {
    public static final XuiComponentTemplateNodeLayout EMPTY = new XuiComponentTemplateNodeLayout() {{
        freeze(true);
    }};

    public XuiComponentTemplateNodeLayout() {
    }

    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        // TODO 检查组件节点布局有效性
    }
}
