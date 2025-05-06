package io.crazydan.duzhou.framework.ui.schema.component;

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentNode;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;

public class XuiComponentNode extends _XuiComponentNode {

    public XuiComponentNode() {
    }

    /** 查找待布局节点 */
    public List<XuiComponentNamed> findLayoutChild(XuiLayoutNode layout) {
        List<XuiComponentNamed> nodes = new ArrayList<>();

        getChildren().forEach((child) -> {
            // TODO 处理条件指令、动画、校验等情况

            if (layout.matched(child)) {
                nodes.add(child);
            }
        });

        return nodes;
    }
}
