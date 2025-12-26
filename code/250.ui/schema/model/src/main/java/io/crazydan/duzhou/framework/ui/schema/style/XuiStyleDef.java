package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.Map;

import io.crazydan.duzhou.framework.ui.domain.GenericStdDomainHandlers;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDef;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.xlang.xdef.XDefTypeDecl;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_INVALID_PROP_NAME;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_INVALID_TAG_NAME;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiStyleDef extends _XuiStyleDef {

    public XuiStyleDef() {
    }

    /** 检查当前样式的有效性 */
    public void validate(XuiStyleDefs styleDefs, IValidationErrorCollector collector) {
        checkTagName(collector);
        checkPropNames(collector);

        checkChildRefs(styleDefs, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 是否为原子样式 */
    public boolean isAtom() {
        return getChildren().isEmpty();
    }

    /** 是否为复合样式 */
    public boolean isComposite() {
        return !isAtom();
    }

    /** @return 始终不返回 {@code null} */
    @Override
    public Map<String, XDefTypeDecl> getProps() {
        return firstNonNull(super.getProps(), Map.of());
    }

    /** 是否存在指定名字的属性 */
    public boolean hasProp(String propName) {
        return getProps().containsKey(propName);
    }

    /** 获取指定属性的类型 */
    public XDefTypeDecl getPropType(String propName) {
        return getProps().get(propName);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** 检查当前样式的标签名是否符合规范 */
    protected void checkTagName(IValidationErrorCollector collector) {
        String tagName = get$tag();
        if (!GenericStdDomainHandlers.isValidStyleName(tagName)) {
            collector.buildError(ERR_STYLES_INVALID_TAG_NAME)
                     .loc(getLocation())
                     .param(ARG_TAG_NAME, tagName)
                     .addToCollector(collector);
        }
    }

    /** 检查当前样式的属性名是否符合规范 */
    protected void checkPropNames(IValidationErrorCollector collector) {
        getProps().forEach((name, type) -> {
            if (!GenericStdDomainHandlers.isValidStylePropName(name)) {
                collector.buildError(ERR_STYLES_INVALID_PROP_NAME)
                         .loc(getLocation())
                         .param(ARG_PROP_NAME, name)
                         .addToCollector(collector);
            }
        });
    }

    /** 检查子节点引用及其配置 */
    protected void checkChildRefs(XuiStyleDefs styleDefs, IValidationErrorCollector collector) {
        getChildren().forEach((name, ref) -> {
            ref.validate(styleDefs, this, collector);
        });
    }
}
