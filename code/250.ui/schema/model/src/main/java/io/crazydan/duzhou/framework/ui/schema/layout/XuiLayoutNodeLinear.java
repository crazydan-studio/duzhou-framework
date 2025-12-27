package io.crazydan.duzhou.framework.ui.schema.layout;

import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeLinear;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNullThenGet;

public class XuiLayoutNodeLinear extends _XuiLayoutNodeLinear {

    public XuiLayoutNodeLinear() {
    }

    @Override
    public XuiLayoutNodeNamed getChild(String name) {
        return ifNotNullThenGet(getBody(), (body) -> body.getChild(name));
    }

    @Override
    public List<XuiLayoutNodeNamed> getChildren() {
        return getBody() != null ? getBody().getChildren() : List.of();
    }
}
