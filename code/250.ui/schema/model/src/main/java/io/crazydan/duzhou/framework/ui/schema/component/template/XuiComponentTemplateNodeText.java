package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeText;

import static io.crazydan.duzhou.framework.commons.StringHelper.trimAllLinesByFirstNonBlankLine;

public class XuiComponentTemplateNodeText extends _XuiComponentTemplateNodeText {

    public XuiComponentTemplateNodeText() {

    }

    public String getInnerText() {
        return trimAllLinesByFirstNonBlankLine(getValue());
    }
}
