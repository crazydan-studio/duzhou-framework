package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeAny;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiComponentTemplateNodeAny extends _XuiComponentTemplateNodeAny {

    public XuiComponentTemplateNodeAny() {
    }

    @Override
    public void validate(XuiComponent component, IValidationErrorCollector collector) {
        // TODO 在组件上设置的属性必须在组件定义中定义

        super.validate(component, collector);
    }
}
