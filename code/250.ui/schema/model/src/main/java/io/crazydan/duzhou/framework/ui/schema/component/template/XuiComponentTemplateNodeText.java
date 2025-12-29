package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeText;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.commons.StringHelper.trimAllLinesByFirstNonBlankLine;

public class XuiComponentTemplateNodeText extends _XuiComponentTemplateNodeText {

    public XuiComponentTemplateNodeText() {
    }

    @Override
    public void validate(XuiComponent component, IValidationErrorCollector collector) {
        getStyles().validate(component.getStyles(), collector);

        super.validate(component, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public String getInnerText() {
        return trimAllLinesByFirstNonBlankLine(getValue());
    }

    /** 始终不返回 {@code null} */
    @Override
    public XuiComponentTemplateNodeStyles getStyles() {
        return firstNonNull(super.getStyles(), XuiComponentTemplateNodeStyles.EMPTY);
    }
}
