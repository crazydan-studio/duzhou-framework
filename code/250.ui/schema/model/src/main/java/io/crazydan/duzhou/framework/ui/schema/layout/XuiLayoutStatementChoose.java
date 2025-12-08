package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutStatementChoose;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

public class XuiLayoutStatementChoose extends _XuiLayoutStatementChoose {

    public XuiLayoutStatementChoose() {
    }

    @Override
    public void init() {
        getWhens().forEach(XuiLayout::init);
        ifNotNull(getOtherwise(), XuiLayout::init);
    }
}
