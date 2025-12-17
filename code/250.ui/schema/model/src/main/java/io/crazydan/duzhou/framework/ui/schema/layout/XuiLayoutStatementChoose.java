package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutStatementChoose;

public class XuiLayoutStatementChoose extends _XuiLayoutStatementChoose {

    public XuiLayoutStatementChoose() {
    }

    /** Note: init 函数将在 {@link #freeze} 之前被调用 */
    @Override
    public void init() {
        getChildren().forEach(XuiLayout::init);
    }
}
