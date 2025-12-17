package io.crazydan.duzhou.framework.ui.schema.component.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.XuiConstants;
import io.crazydan.duzhou.framework.ui.domain.GenericStdDomainHandlers;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeNamed;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.INeedInit;
import io.nop.commons.util.StringHelper;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_SLOT;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_INVALID_TAG_NAME;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_SAME_NAME_SLOT_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_SAME_XUI_SLOT_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED;
import static io.nop.xlang.XLangErrors.ARG_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiComponentTemplateNodeNamed extends _XuiComponentTemplateNodeNamed implements INeedInit {

    public XuiComponentTemplateNodeNamed() {
    }

    /** Note: init 函数将在 {@link #freeze} 之前被调用 */
    @Override
    public void init() {
        checkCustomTagName();
        checkMultipleSlots();
        checkSlotInSlot();

        getChildren().forEach(XuiComponentTemplateNodeNamed::init);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 是否为 {@code <slot/>} 插槽节点 */
    public boolean isSlot() {
        return this instanceof XuiComponentTemplateNodeSlot;
    }

    /** 是否为 {@code <native/>} 节点 */
    public boolean isNative() {
        return this instanceof XuiComponentTemplateNodeNative;
    }

    /** 是否为 {@code <Text/>} 文本节点 */
    public boolean isText() {
        return this instanceof XuiComponentTemplateNodeText;
    }

    /** 是否为自定义组件节点：自定义组件需要被 {@code <import/>} 导入 */
    public boolean isCustom() {
        return this instanceof XuiComponentTemplateNodeAny;
    }

    /** 是否为可设置{@link XuiConstants#ATTR_NAME_XUI_SLOT 插槽名}的节点 */
    public boolean isSlottable() {
        return this instanceof XuiComponentTemplateNodeSlottable || isText();
    }

    /** 是否内嵌 {@code <slot/>} 标签（不限层级） */
    public boolean hasSlotInDepth() {
        for (XuiComponentTemplateNodeNamed child : getChildren()) {
            if (child.isSlot() || child.hasSlotInDepth()) {
                return true;
            }
        }
        return false;
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 获取当前节点的标签名 */
    public String getTagName() {
        return get$tag();
    }

    /** 获取插槽节点的插槽名，仅针对 {@link #isSlot()} 为 {@code true} 的节点 */
    public String getSlotName() {
        return isSlot() ? ((XuiComponentTemplateNodeSlot) this).getName() : null;
    }

    /**
     * 获取{@link XuiConstants#ATTR_NAME_XUI_SLOT 插槽名}，
     * 仅针对 {@link #isSlottable()} 为 {@code true} 的节点
     */
    public String getXuiSlot() {
        return isSlottable() ? (String) prop_get(ATTR_NAME_XUI_SLOT) : null;
    }

    /**
     * 获取当前节点内各个层级中嵌入的 {@code <Text/>} 的文本内容，并以一个换行符分隔
     *
     * @return 若无有效文本，则返回 {@code null}
     */
    public String getInnerText() {
        StringBuilder sb = new StringBuilder();

        getChildren().forEach((child) -> {
            String text = child.getInnerText();
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
     * 获取 {@link #isSlottable()} 为 {@code true} 且设置了{@link #getXuiSlot() 插槽名}的节点
     * <p/>
     * 仅从直接子节点中查找，不做深度遍历
     *
     * @return 始终不返回 {@code null}
     */
    public Map<String, XuiComponentTemplateNodeNamed> getSlottables() {
        return getChildren().stream()
                            .filter(XuiComponentTemplateNodeNamed::isSlottable)
                            .filter((child) -> StringHelper.isNotBlank(child.getXuiSlot()))
                            .collect(Collectors.toMap(XuiComponentTemplateNodeNamed::getXuiSlot, Function.identity()));
    }

    /** 获取指定名字的直接子节点 */
    public XuiComponentTemplateNodeNamed getChild(String name) {
        return null;
    }

    /** 获取当前节点的全部直接子节点，始终不返回 {@code null} */
    public List<XuiComponentTemplateNodeNamed> getChildren() {
        return List.of();
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 检查 {@link #isCustom()} 为 {@code true} 的节点的标签名是否符合
     * {@link GenericStdDomainHandlers#isValidComponentName} 规范
     */
    protected void checkCustomTagName() {
        if (isCustom()) {
            String tagName = getTagName();

            if (!GenericStdDomainHandlers.isValidComponentName(tagName)) {
                throw new NopException(ERR_COMPONENT_INVALID_TAG_NAME).source(this).param(ARG_TAG_NAME, tagName);
            }
        }
    }

    /** 在 {@code <slot/>} 标签内不能嵌套任意层级的 {@code <slot/>} */
    protected void checkSlotInSlot() {
        if (isSlot() && hasSlotInDepth()) {
            throw new NopException(ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED).source(this);
        }
    }

    /**
     * 在当前节点中不能定义多个同名的 {@code <slot/>} 或 {@code xui:slot}
     * <p/>
     * 仅检查直接子节点，不做深度遍历
     */
    protected void checkMultipleSlots() {
        List<String> slots = new ArrayList<>(getChildren().size());
        List<String> xuiSlots = new ArrayList<>(getChildren().size());

        for (XuiComponentTemplateNodeNamed child : getChildren()) {
            String xuiSlot = child.getXuiSlot();
            String slotName = child.getSlotName();

            if (slotName != null) {
                if (slots.contains(slotName)) {
                    throw new NopException(ERR_COMPONENT_MULTIPLE_SAME_NAME_SLOT_NOT_ALLOWED).source(this)
                                                                                             .param(ARG_TAG_NAME,
                                                                                                    getTagName())
                                                                                             .param(ARG_NAME, slotName);
                } else {
                    slots.add(slotName);
                }
            } //
            else if (xuiSlot != null) {
                if (xuiSlots.contains(xuiSlot)) {
                    throw new NopException(ERR_COMPONENT_MULTIPLE_SAME_XUI_SLOT_NOT_ALLOWED).source(this)
                                                                                            .param(ARG_TAG_NAME,
                                                                                                   getTagName())
                                                                                            .param(ARG_NAME, xuiSlot);
                } else {
                    xuiSlots.add(xuiSlot);
                }
            }
        }
    }
}
