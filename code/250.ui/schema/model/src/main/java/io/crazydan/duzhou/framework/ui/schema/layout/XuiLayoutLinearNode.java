package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutLinearNode;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;

public class XuiLayoutLinearNode extends _XuiLayoutLinearNode {
    public enum Type {
        /** 在行内布局节点，即，将节点挨个横向放置 */
        row,
        /** 在列内布局节点，即，将节点挨个纵向放置 */
        column,
    }

    public XuiLayoutLinearNode() {
    }

    @Override
    public XuiLayoutNodeNestedProps getProps() {
        return firstNonNull(super.getProps(), XuiLayoutNodeNestedProps.DEFAULT);
    }
}
