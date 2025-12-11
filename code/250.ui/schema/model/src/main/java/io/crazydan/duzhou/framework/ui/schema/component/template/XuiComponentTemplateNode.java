package io.crazydan.duzhou.framework.ui.schema.component.template;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNode;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.INeedInit;
import io.nop.commons.util.StringHelper;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNullThenGet;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED;

public class XuiComponentTemplateNode extends _XuiComponentTemplateNode implements INeedInit {
    private Map<String, XuiComponentTemplateNodeNamed> slottables;

    public XuiComponentTemplateNode() {
    }

    @Override
    public void init() {
        checkSlotInSlot();
        // TODO xui:slot 不能存在重名

        ifNotNull(getBody(), XuiComponentTemplateNodeBody::init);
    }

    /** @return 始终不返回 {@code null} */
    public List<XuiComponentTemplateNodeNamed> getChildren() {
        return getBody() != null ? getBody().getChildren() : List.of();
    }

    public XuiComponentTemplateNodeNamed getChild(String name) {
        return ifNotNullThenGet(getBody(), (body) -> body.getChild(name));
    }

    /**
     * 获取该组件内嵌入的纯文本
     *
     * @return 若无有效文本，则返回 {@code null}
     */
    public String getInnerText() {
        StringBuilder sb = new StringBuilder();

        getChildren().forEach((child) -> {
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
        return sb.length() != 0 ? sb.toString() : null;
    }

    /**
     * 获取命名插槽组件
     *
     * @return 始终不返回 {@code null}
     */
    public Map<String, XuiComponentTemplateNodeNamed> getSlottables() {
        if (this.slottables == null) {
            this.slottables = getChildren().stream()
                                           .filter(XuiComponentTemplateNodeNamed::isSlottable)
                                           .filter((child) -> StringHelper.isNotBlank(child.getXuiSlot()))
                                           .collect(Collectors.toMap(XuiComponentTemplateNodeNamed::getXuiSlot,
                                                                     Function.identity()));
        }
        return this.slottables;
    }

    public boolean hasSlotInDepth() {
        return getBody() != null && getBody().hasSlotInDepth();
    }

    protected void checkSlotInSlot() {
        if (isSlot() && hasSlotInDepth()) {
            throw new NopException(ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED).source(this);
        }
    }
}
