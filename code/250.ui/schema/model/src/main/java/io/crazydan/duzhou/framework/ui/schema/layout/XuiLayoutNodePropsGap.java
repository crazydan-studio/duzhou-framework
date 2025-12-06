package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodePropsGap;

import static com.google.common.base.MoreObjects.firstNonNull;

public class XuiLayoutNodePropsGap extends _XuiLayoutNodePropsGap {
    public static final XuiLayoutNodePropsGap NONE = new XuiLayoutNodePropsGap() {
        @Override
        public XuiSize getRow() {
            return XuiSize.NONE;
        }

        @Override
        public XuiSize getColumn() {
            return XuiSize.NONE;
        }
    };

    public XuiLayoutNodePropsGap() {
    }

    @Override
    public XuiSize getRow() {
        return firstNonNull(super.getRow(), XuiSize.NONE);
    }

    @Override
    public XuiSize getColumn() {
        return firstNonNull(super.getColumn(), XuiSize.NONE);
    }
}
