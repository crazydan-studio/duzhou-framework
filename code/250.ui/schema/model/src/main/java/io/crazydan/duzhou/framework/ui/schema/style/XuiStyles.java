package io.crazydan.duzhou.framework.ui.schema.style;

import io.crazydan.duzhou.framework.ui.XuiErrorCollector;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyles;
import io.nop.api.core.util.INeedInit;

public class XuiStyles extends _XuiStyles implements INeedInit {

    public XuiStyles() {
    }

    /** Note: init 函数将在 {@link #freeze} 之前被调用 */
    @Override
    public void init() {
        validate();
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    protected void validate() {
        XuiErrorCollector collector = new XuiErrorCollector();

        super.validate(collector);

        collector.throwErrors();
    }
}
