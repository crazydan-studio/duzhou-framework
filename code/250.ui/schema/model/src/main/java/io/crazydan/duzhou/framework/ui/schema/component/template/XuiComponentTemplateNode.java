package io.crazydan.duzhou.framework.ui.schema.component.template;

import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNode;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNullThenGet;

public class XuiComponentTemplateNode extends _XuiComponentTemplateNode {

    public XuiComponentTemplateNode() {
    }

    @Override
    public void validate(XuiComponent component, IValidationErrorCollector collector) {
        checkLayout(component.getStyles(), collector);
        checkStyles(component.getStyles(), collector);
        // TODO 检查组件 action

        super.validate(component, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public XuiComponentTemplateNodeNamed getChild(String name) {
        return ifNotNullThenGet(getBody(), (body) -> body.getChild(name));
    }

    @Override
    public List<XuiComponentTemplateNodeNamed> getChildren() {
        return getBody() != null ? getBody().getChildren() : List.of();
    }

    /** 始终不返回 {@code null} */
    @Override
    public XuiComponentTemplateNodeStyles getStyles() {
        return firstNonNull(super.getStyles(), XuiComponentTemplateNodeStyles.EMPTY);
    }

    /** 始终不返回 {@code null} */
    @Override
    public XuiComponentTemplateNodeLayout getLayout() {
        return firstNonNull(super.getLayout(), XuiComponentTemplateNodeLayout.EMPTY);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    protected void checkStyles(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        getStyles().validate(styleDefs, collector);
    }

    protected void checkLayout(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        getLayout().validate(styleDefs, collector);
    }
}
