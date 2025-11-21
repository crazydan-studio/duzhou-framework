package io.crazydan.duzhou.framework.ui.schema.component;

import io.crazydan.duzhou.framework.ui.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.layout.parser.XuiLayoutLinearParser;
import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentLayoutLinear;
import io.nop.api.core.util.SourceLocation;

public class XuiComponentLayoutLinear extends _XuiComponentLayoutLinear {
    private XuiLayoutNode root;

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
            XuiLayoutLinearParser parser = new XuiLayoutLinearParser(getMode());

            this.root = parser.parseFromText(loc, getValue());
        }
        return this.root;
    }
}
