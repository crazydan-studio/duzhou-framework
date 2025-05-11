package io.crazydan.duzhou.framework.ui.schema.component;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentText;

public class XuiComponentText extends _XuiComponentText {

    public XuiComponentText() {
    }

    @Override
    public String getValue() {
        String value = super.getValue();

        if (value != null) {
            value = value.trim().replaceAll("(?m)^\\s+", "");
            // 转义 HTML 以避免 XSS 攻击
            value = StringHelper.escapeHtml(value);
            // 恢复空格等转义符号
            value = value.replaceAll("&amp;nbsp;", "&nbsp;");
        }
        return value;
    }
}
