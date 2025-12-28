package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeStatementChooseWhen;
import io.nop.api.core.validate.IValidationErrorCollector;

public class XuiLayoutNodeStatementChooseWhen extends _XuiLayoutNodeStatementChooseWhen {

    public XuiLayoutNodeStatementChooseWhen() {
    }

    @Override
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        super.validate(getLocation(), collector);

        super.validate(styleDefs, collector);
    }
}
