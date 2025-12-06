package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodePropsAlign;

import static com.google.common.base.MoreObjects.firstNonNull;

public class XuiLayoutNodePropsAlign extends _XuiLayoutNodePropsAlign {
    public static final XuiLayoutNodePropsAlign DEFAULT = new XuiLayoutNodePropsAlign() {
        @Override
        public XuiLayoutAlign getRow() {
            return XuiLayoutAlign.start;
        }

        @Override
        public XuiLayoutAlign getColumn() {
            return XuiLayoutAlign.start;
        }
    };

    public XuiLayoutNodePropsAlign() {
    }

    @Override
    public XuiLayoutAlign getRow() {
        return firstNonNull(super.getRow(), XuiLayoutAlign.start);
    }

    @Override
    public XuiLayoutAlign getColumn() {
        return firstNonNull(super.getColumn(), XuiLayoutAlign.start);
    }
}
