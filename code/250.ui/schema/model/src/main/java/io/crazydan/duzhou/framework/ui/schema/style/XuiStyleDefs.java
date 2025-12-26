package io.crazydan.duzhou.framework.ui.schema.style;

import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDefs;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiStyleDefs extends _XuiStyleDefs {

    public XuiStyleDefs() {
    }

    /** 检查当前样式定义集的有效性 */
    public void validate(IValidationErrorCollector collector) {
        getChildren().forEach((name, def) -> {
            def.validate(this, collector);
        });
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 获取指定名字的{@link XuiStyleDef 样式定义} */
    public XuiStyleDef getStyleDef(String name) {
        return getChild(name);
    }
}
