package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeProps;

import static com.google.common.base.MoreObjects.firstNonNull;

public class XuiLayoutNodeProps extends _XuiLayoutNodeProps {
    public static final XuiLayoutNodeProps DEFAULT = new XuiLayoutNodeProps() {
        @Override
        public XuiLayoutNodePropsAlign getAlign() {
            return XuiLayoutNodePropsAlign.DEFAULT;
        }

        @Override
        public XuiLayoutNodePropsGap getGap() {
            return XuiLayoutNodePropsGap.NONE;
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

    public XuiLayoutNodeProps() {
    }

    @Override
    public XuiLayoutNodePropsAlign getAlign() {
        return firstNonNull(super.getAlign(), XuiLayoutNodePropsAlign.DEFAULT);
    }

    @Override
    public XuiLayoutNodePropsGap getGap() {
        return firstNonNull(super.getGap(), XuiLayoutNodePropsGap.NONE);
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
