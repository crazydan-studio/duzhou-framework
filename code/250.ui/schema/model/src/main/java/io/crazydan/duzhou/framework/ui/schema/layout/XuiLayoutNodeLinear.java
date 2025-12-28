package io.crazydan.duzhou.framework.ui.schema.layout;

import java.util.List;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeLinear;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNullThenGet;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_LAYOUT_NO_LINEAR_TYPE_SPECIFIED;

public class XuiLayoutNodeLinear extends _XuiLayoutNodeLinear {

    public XuiLayoutNodeLinear() {
    }

    @Override
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        if (StringHelper.isBlank(getType())) {
            collector.buildError(ERR_LAYOUT_NO_LINEAR_TYPE_SPECIFIED) //
                     .loc(getLocation()).addToCollector(collector);
        }

        super.validate(styleDefs, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public XuiLayoutNodeNamed getChild(String name) {
        return ifNotNullThenGet(getBody(), (body) -> body.getChild(name));
    }

    @Override
    public List<XuiLayoutNodeNamed> getChildren() {
        return getBody() != null ? getBody().getChildren() : List.of();
    }
}
