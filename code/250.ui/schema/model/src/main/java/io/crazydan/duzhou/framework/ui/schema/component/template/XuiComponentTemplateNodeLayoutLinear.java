package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.layout0.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.layout0.parser.XuiLayoutLinearParser;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeLayoutLinear;
import io.nop.api.core.util.SourceLocation;
import io.nop.core.lang.json.IJsonHandler;

public class XuiComponentTemplateNodeLayoutLinear extends _XuiComponentTemplateNodeLayoutLinear {
    private XuiLayoutNode root;

    public XuiComponentTemplateNodeLayoutLinear() {

    }

    @Override
    public String getType() {
        return get$tag();
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

    @Override
    protected void outputJson(IJsonHandler out) {
        super.outputJson(out);

        out.putNotNull("root", getRoot());
    }
}
