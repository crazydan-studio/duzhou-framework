package io.crazydan.duzhou.framework.ui.schema.component.template;

import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNode;
import io.nop.api.core.util.INeedInit;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNullThenGet;

public class XuiComponentTemplateNode extends _XuiComponentTemplateNode implements INeedInit {

    public XuiComponentTemplateNode() {
    }

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
        if (super.getStyles() == null) {
            return XuiComponentTemplateNodeStyles.EMPTY;
        }
        return super.getStyles();
    }
}
