package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeSelfProps;

import static com.google.common.base.MoreObjects.firstNonNull;

public class XuiLayoutNodeSelfProps extends _XuiLayoutNodeSelfProps {
    public static final XuiLayoutNodeSelfProps DEFAULT = new XuiLayoutNodeSelfProps() {
        @Override
        public XuiLayoutNodePropsAlign getSelfAlign() {
            return XuiLayoutNodePropsAlign.DEFAULT;
        }

        @Override
        public XuiLayoutNodePropsSize getHeight() {
            return XuiLayoutNodePropsSize.WRAP_CONTENT;
        }

        @Override
        public XuiLayoutNodePropsSize getWidth() {
            return XuiLayoutNodePropsSize.WRAP_CONTENT;
        }

        @Override
        public XuiLayoutNodePropsSpacing getPadding() {
            return XuiLayoutNodePropsSpacing.NONE;
        }
    };

    public XuiLayoutNodeSelfProps() {
    }

    @Override
    public XuiLayoutNodePropsAlign getSelfAlign() {
        return firstNonNull(super.getSelfAlign(), XuiLayoutNodePropsAlign.DEFAULT);
    }

    @Override
    public XuiLayoutNodePropsSize getHeight() {
        return firstNonNull(super.getHeight(), XuiLayoutNodePropsSize.WRAP_CONTENT);
    }

    @Override
    public XuiLayoutNodePropsSize getWidth() {
        return firstNonNull(super.getWidth(), XuiLayoutNodePropsSize.WRAP_CONTENT);
    }

    @Override
    public XuiLayoutNodePropsSpacing getPadding() {
        return firstNonNull(super.getPadding(), XuiLayoutNodePropsSpacing.NONE);
    }
}
