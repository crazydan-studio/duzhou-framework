package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.layout.XuiLayoutSize;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodePropsSize;

import static com.google.common.base.MoreObjects.firstNonNull;

public class XuiLayoutNodePropsSize extends _XuiLayoutNodePropsSize {
    public static final XuiLayoutNodePropsSize WRAP_CONTENT = new XuiLayoutNodePropsSize() {
        @Override
        public XuiLayoutSize getType() {
            return XuiLayoutSize.wrap_content;
        }
    };

    public XuiLayoutNodePropsSize() {
    }

    @Override
    public XuiLayoutSize getType() {
        return firstNonNull(super.getType(), XuiLayoutSize.wrap_content);
    }
}
