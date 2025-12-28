package io.crazydan.duzhou.framework.ui.schema.layout;

import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeNamed;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiLayoutNodeNamed extends _XuiLayoutNodeNamed {

    public XuiLayoutNodeNamed() {
    }

    /** 检查组件布局的有效性 */
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        getChildren().forEach((child) -> {
            child.validate(styleDefs, collector);
        });
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public XuiLayoutNodeNamed getChild(String name) {
        return null;
    }

    public List<XuiLayoutNodeNamed> getChildren() {
        return List.of();
    }
}
