package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeStyles;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefs;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_LAYOUT_STYLE_PREFIX;

public class XuiLayoutNodeStyles extends _XuiLayoutNodeStyles {
    public static final XuiLayoutNodeStyles EMPTY = new XuiLayoutNodeStyles() {{
        freeze(true);
    }};

    public XuiLayoutNodeStyles() {
    }

    /** 检查当前布局引用样式的有效性 */
    @Override
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        getChildren().forEach((name, ref) -> {
            String layoutStylePrefix = TAG_NAME_LAYOUT_STYLE_PREFIX;
            XuiStyleDefs layoutStyleDefs = styleDefs;

            // 优先检查带有前缀的样式
            if (styleDefs.getStyleDef(layoutStylePrefix + name) == null) {
                // 再检查基础布局样式库中定义的样式
                if (styleDefs.getLayoutStyles().getStyleDef(name) != null) {
                    layoutStylePrefix = null;
                    layoutStyleDefs = styleDefs.getLayoutStyles();
                }
            }

            // Note: 在样式引用集中不检查样式属性值的引用
            ref.validate(layoutStyleDefs, null, layoutStylePrefix, collector);
        });
    }
}
