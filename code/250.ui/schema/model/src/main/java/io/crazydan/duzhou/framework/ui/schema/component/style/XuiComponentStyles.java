package io.crazydan.duzhou.framework.ui.schema.component.style;

import io.crazydan.duzhou.framework.ui.schema.component.style._gen._XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDef;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyles;
import io.crazydan.duzhou.framework.ui.util.XuiHelper;

public class XuiComponentStyles extends _XuiComponentStyles {
    public static final XuiComponentStyles EMPTY = new XuiComponentStyles() {{
        freeze(true);
    }};

    private XuiStyles viewStyles;
    private XuiStyles layoutStyles;

    public XuiComponentStyles() {
    }

    /** 重载接口，以支持从基础样式库中查找样式定义 */
    @Override
    public XuiStyleDef getStyleDef(String name) {
        // 先从当前定义集中查找
        XuiStyleDef def = super.getStyleDef(name);
        // 再从基础视觉样式库中查找
        if (def == null) {
            def = getViewStyles().getStyleDef(name);
        }
        // 最后从基础布局样式库中查找
        if (def == null) {
            def = getLayoutStyles().getStyleDef(name);
        }

        return def;
    }

    public XuiStyles getViewStyles() {
        if (this.viewStyles == null) {
            this.viewStyles = XuiHelper.loadStyles(getView());
        }
        return this.viewStyles;
    }

    public XuiStyles getLayoutStyles() {
        if (this.layoutStyles == null) {
            this.layoutStyles = XuiHelper.loadStyles(getLayout());
        }
        return this.layoutStyles;
    }
}
