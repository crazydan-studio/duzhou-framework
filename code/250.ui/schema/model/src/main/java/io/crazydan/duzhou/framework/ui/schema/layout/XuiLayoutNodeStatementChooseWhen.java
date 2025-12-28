package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeStatementChooseWhen;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_CHOOSE_WHEN_NO_TEST_SPECIFIED;

public class XuiLayoutNodeStatementChooseWhen extends _XuiLayoutNodeStatementChooseWhen {

    public XuiLayoutNodeStatementChooseWhen() {
    }

    @Override
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        if (getTest() == null) {
            collector.buildError(ERR_CHOOSE_WHEN_NO_TEST_SPECIFIED) //
                     .loc(getLocation()).addToCollector(collector);
        }

        super.validate(styleDefs, collector);
    }
}
