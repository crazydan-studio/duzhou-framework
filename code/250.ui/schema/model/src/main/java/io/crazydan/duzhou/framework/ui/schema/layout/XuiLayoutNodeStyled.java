package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeStyled;

public class XuiLayoutNodeStyled extends _XuiLayoutNodeStyled {

    public XuiLayoutNodeStyled() {
    }

    /** 始终不返回 {@code null} */
    @Override
    public XuiLayoutNodeStyles getStyles() {
        if (super.getStyles() == null) {
            return XuiLayoutNodeStyles.EMPTY;
        }
        return super.getStyles();
    }
}
