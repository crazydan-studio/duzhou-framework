package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeStyles;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_LAYOUT_PREFIX;

public class XuiLayoutNodeStyles extends _XuiLayoutNodeStyles {
    public static final XuiLayoutNodeStyles EMPTY = new XuiLayoutNodeStyles() {{
        freeze(true);
    }};

    public XuiLayoutNodeStyles() {
    }

    /** 检查当前布局引用样式的有效性 */
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        validate(styleDefs, TAG_NAME_LAYOUT_PREFIX, collector);
    }
}
