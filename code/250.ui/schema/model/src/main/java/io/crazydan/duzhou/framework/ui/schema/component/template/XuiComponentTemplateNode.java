package io.crazydan.duzhou.framework.ui.schema.component.template;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNode;
import io.nop.commons.util.StringHelper;

public class XuiComponentTemplateNode extends _XuiComponentTemplateNode {
    private List<XuiComponentTemplateNodeNamed> customOrTextChildren;
    private Map<String, XuiComponentTemplateNodeNamed> slottables;

    public XuiComponentTemplateNode() {

    }

    /** 获取该组件内嵌入的纯文本 */
    public String getInnerText() {
        StringBuilder sb = new StringBuilder();

        getCustomOrTextChildren().forEach((child) -> {
            String text;
            if (child instanceof XuiComponentTemplateNodeText) {
                text = ((XuiComponentTemplateNodeText) child).getInnerText();
            } else {
                text = ((XuiComponentTemplateNode) child).getInnerText();
            }

            if (text != null) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(text);
            }
        });
        return sb.toString();
    }

    /** 获取自定义或文本子节点 */
    public List<XuiComponentTemplateNodeNamed> getCustomOrTextChildren() {
        if (this.customOrTextChildren == null) {
            this.customOrTextChildren = //
                    getChildren().stream()
                                 .filter((child) -> child instanceof XuiComponentTemplateNodeText
                                                    || child instanceof XuiComponentTemplateNode)
                                 .collect(Collectors.toUnmodifiableList());
        }
        return this.customOrTextChildren;
    }

    /** 获取命名插槽组件 */
    public Map<String, XuiComponentTemplateNodeNamed> getSlottables() {
        if (this.slottables == null) {
            this.slottables = getCustomOrTextChildren().stream()
                                                       .filter((child) -> child instanceof XuiComponentTemplateNodeSlottable)
                                                       .map((child) -> (XuiComponentTemplateNodeSlottable) child)
                                                       .filter((child) -> StringHelper.isNotBlank(child.getXuiSlot()))
                                                       .collect(Collectors.toMap(XuiComponentTemplateNodeSlottable::getXuiSlot,
                                                                                 Function.identity()));
        }
        return this.slottables;
    }
}
