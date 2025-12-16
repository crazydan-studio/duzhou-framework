package io.crazydan.duzhou.framework.ui.schema.layout;

import io.crazydan.duzhou.framework.ui.schema.layout._gen._XuiLayoutLinearItem;
import io.nop.commons.text.regex.IRegex;
import io.nop.commons.text.regex.RegexHelper;

public class XuiLayoutLinearItem extends _XuiLayoutLinearItem {
    private IRegex regex;

    public XuiLayoutLinearItem() {
    }

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
