package io.crazydan.duzhou.framework.ui.schema.component;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentLayoutLinear;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.schema.layout.parser.XuiLayoutLinearParser;
import io.nop.api.core.util.SourceLocation;

public class XuiComponentLayoutLinear extends _XuiComponentLayoutLinear {
    private XuiLayoutNode root;

    public enum Mode {
        column,
        row,
    }

    public XuiComponentLayoutLinear() {
    }

    @Override
    public String getType() {
        return get$type();
    }

    @Override
    public XuiLayoutNode getRoot() {
        if (this.root == null) {
            SourceLocation loc = getLocation();
            XuiLayoutLinearParser parser = new XuiLayoutLinearParser(Mode.valueOf(getMode()));

            this.root = parser.parseFromText(loc, getValue());
        }
        return this.root;
    }
}
