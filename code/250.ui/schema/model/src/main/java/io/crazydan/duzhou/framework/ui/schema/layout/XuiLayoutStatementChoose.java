package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutStatementChoose;

public class XuiLayoutStatementChoose extends _XuiLayoutStatementChoose {

    public XuiLayoutStatementChoose() {
    }

    @Override
    public void init() {
        getChildren().forEach(XuiLayout::init);
    }
}
