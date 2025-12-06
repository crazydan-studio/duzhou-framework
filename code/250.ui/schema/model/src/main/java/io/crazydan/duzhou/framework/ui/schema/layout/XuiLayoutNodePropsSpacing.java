package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodePropsSpacing;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;

public class XuiLayoutNodePropsSpacing extends _XuiLayoutNodePropsSpacing {
    public static final XuiLayoutNodePropsSpacing NONE = new XuiLayoutNodePropsSpacing() {
        @Override
        public XuiSize getLeft() {
            return XuiSize.NONE;
        }

        @Override
        public XuiSize getRight() {
            return XuiSize.NONE;
        }

        @Override
        public XuiSize getTop() {
            return XuiSize.NONE;
        }

        @Override
        public XuiSize getBottom() {
            return XuiSize.NONE;
        }
    };

    public XuiLayoutNodePropsSpacing() {
    }

    @Override
    public XuiSize getLeft() {
        return firstNonNull(super.getLeft(), super.getRow(), super.getAll(), XuiSize.NONE);
    }

    @Override
    public XuiSize getRight() {
        return firstNonNull(super.getRight(), super.getRow(), super.getAll(), XuiSize.NONE);
    }

    @Override
    public XuiSize getTop() {
        return firstNonNull(super.getTop(), super.getColumn(), super.getAll(), XuiSize.NONE);
    }

    @Override
    public XuiSize getBottom() {
        return firstNonNull(super.getBottom(), super.getColumn(), super.getAll(), XuiSize.NONE);
    }
}
