package io.crazydan.duzhou.framework.ui.schema.component.template;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeBody;
import io.nop.api.core.util.INeedInit;

public class XuiComponentTemplateNodeBody extends _XuiComponentTemplateNodeBody implements INeedInit {

    public XuiComponentTemplateNodeBody() {
    }

    @Override
    public void init() {
        getChildren().forEach((child) -> {
            if (child instanceof INeedInit) {
                ((INeedInit) child).init();
            }
        });
    }

    public boolean hasSlotInDepth() {
        for (XuiComponentTemplateNodeNamed child : getChildren()) {
            // 检查 choose 语句
            if (child instanceof XuiComponentTemplateNodeStatementChoose) {
                if (((XuiComponentTemplateNodeStatementChoose) child).hasSlotInDepth()) {
                    return true;
                }
            }
            // 检查 if/for 语句
            else if (child instanceof XuiComponentTemplateNodeBody) {
                if (((XuiComponentTemplateNodeBody) child).hasSlotInDepth()) {
                    return true;
                }
            }
            // 检查子组件自身
            else if (child.isSlot()) {
                return true;
            }
            // 检查子组件内部
            else if (child instanceof XuiComponentTemplateNode) {
                if (((XuiComponentTemplateNode) child).hasSlotInDepth()) {
                    return true;
                }
            }
        }
        return false;
    }
}
