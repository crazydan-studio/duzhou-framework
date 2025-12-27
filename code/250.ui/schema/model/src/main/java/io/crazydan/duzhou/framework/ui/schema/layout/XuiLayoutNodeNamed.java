package io.crazydan.duzhou.framework.ui.schema.layout;

import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeNamed;
import io.nop.api.core.util.INeedInit;

public class XuiLayoutNodeNamed extends _XuiLayoutNodeNamed implements INeedInit {

    public XuiLayoutNodeNamed() {
    }

    /** Note: init 函数将在 {@link #freeze} 之前被调用 */
    @Override
    public void init() {
        getChildren().forEach(XuiLayoutNodeNamed::init);
    }

    public XuiLayoutNodeNamed getChild(String name) {
        return null;
    }

    public List<XuiLayoutNodeNamed> getChildren() {
        return List.of();
    }
}
