package io.crazydan.duzhou.framework.ui.schema.style;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleRefs;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiStyleRefs extends _XuiStyleRefs {

    public XuiStyleRefs() {
    }

    /** 检查当前组件引用样式的有效性 */
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        getChildren().forEach((name, ref) -> {
            // Note: 在样式引用集中不检查样式属性值的引用
            ref.validate(styleDefs, null, collector);
        });
    }
}
