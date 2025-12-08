package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeNestedProps;

import static com.google.common.base.MoreObjects.firstNonNull;

public class XuiLayoutNodeNestedProps extends _XuiLayoutNodeNestedProps {
    public static final XuiLayoutNodeNestedProps DEFAULT = new XuiLayoutNodeNestedProps() {
        @Override
        public XuiLayoutNodePropsAlign getNodeAlign() {
            return XuiLayoutNodePropsAlign.DEFAULT;
        }

        @Override
        public XuiLayoutNodePropsGap getNodeGap() {
            return XuiLayoutNodePropsGap.NONE;
        }
    };

    public XuiLayoutNodeNestedProps() {
    }

    @Override
    public XuiLayoutNodePropsAlign getNodeAlign() {
        return firstNonNull(super.getNodeAlign(), XuiLayoutNodePropsAlign.DEFAULT);
    }

    @Override
    public XuiLayoutNodePropsGap getNodeGap() {
        return firstNonNull(super.getNodeGap(), XuiLayoutNodePropsGap.NONE);
    }
}
