package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeStyled;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiLayoutNodeStyled extends _XuiLayoutNodeStyled {

    public XuiLayoutNodeStyled() {
    }

    @Override
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        getStyles().validate(styleDefs, collector);

        super.validate(styleDefs, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 始终不返回 {@code null} */
    @Override
    public XuiLayoutNodeStyles getStyles() {
        if (super.getStyles() == null) {
            return XuiLayoutNodeStyles.EMPTY;
        }
        return super.getStyles();
    }
}
