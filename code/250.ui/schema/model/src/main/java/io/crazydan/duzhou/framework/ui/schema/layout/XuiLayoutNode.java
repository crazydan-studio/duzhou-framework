package io.crazydan.duzhou.framework.ui.schema.layout;

import java.util.List;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNode;

public class XuiLayoutNode extends _XuiLayoutNode {

    public XuiLayoutNode() {
    }

    /**
     * 获取布局配置
     *
     * @return 始终不为 {@code null}
     */
    public XuiLayoutNodeProps getProps() {
        for (XuiLayoutNodeNamed child : getChildren()) {
            if (child instanceof XuiLayoutNodeProps) {
                return (XuiLayoutNodeProps) child;
            }
        }
        return XuiLayoutNodeProps.DEFAULT;
    }

    /**
     * 获取布局节点（不含配置节点）
     *
     * @return 始终不为 {@code null}
     */
    public List<XuiLayoutNodeNamed> getNodes() {
        return getChildren().stream()
                            .filter((n) -> !(n instanceof XuiLayoutNodeProps))
                            .collect(Collectors.toUnmodifiableList());
    }
}
