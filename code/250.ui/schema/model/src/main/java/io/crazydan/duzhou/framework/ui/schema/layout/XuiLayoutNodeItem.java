package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutNodeItem;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.commons.text.regex.IRegex;
import io.nop.commons.text.regex.RegexHelper;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_LAYOUT_NO_ITEM_PATTERN_SPECIFIED;

public class XuiLayoutNodeItem extends _XuiLayoutNodeItem {
    private IRegex regex;

    public XuiLayoutNodeItem() {
    }

    @Override
    public void validate(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        if (StringHelper.isBlank(getPattern())) {
            collector.buildError(ERR_LAYOUT_NO_ITEM_PATTERN_SPECIFIED) //
                     .loc(getLocation()).addToCollector(collector);
        }

        super.validate(styleDefs, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 是否匹配该布局节点 */
    public boolean matched(String key) {
        return getRegex().test(key);
    }

    protected IRegex getRegex() {
        if (this.regex == null) {
            // Note: 可能涉及的量比较大，故而，不对正则表达式做全局缓存
            this.regex = RegexHelper.compileRegex(getPattern());
        }
        return this.regex;
    }
}
