package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.Map;

import io.crazydan.duzhou.framework.ui.domain.GenericStdDomainHandlers;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDef;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.xlang.xdef.XDefTypeDecl;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_INVALID_TAG_NAME;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_PATCH_NODE_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_STYLE;
import static io.nop.xlang.XLangErrors.ARG_DEF_LOC;
import static io.nop.xlang.XLangErrors.ARG_TAG1;
import static io.nop.xlang.XLangErrors.ARG_TAG2;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiStyleDef extends _XuiStyleDef {

    public XuiStyleDef() {
    }

    /** 检查当前样式的标签名规范 */
    public void checkTagName(IValidationErrorCollector collector) {
        String tagName = get$tag();
        if (!GenericStdDomainHandlers.isValidStyleName(tagName)) {
            NopException e = //
                    new NopException(ERR_STYLES_INVALID_TAG_NAME).source(this).param(ARG_TAG_NAME, tagName);
            collector.addException(e);
        }

        // TODO 属性名必须为驼峰形式
    }

    /** 检查子节点引用及其配置 */
    public void checkChildRefs(XuiStyles styles, IValidationErrorCollector collector) {
        String tagName = get$tag();

        getChildren().forEach((refTagName, ref) -> {
            // 在独立的样式定义集中，样式只能包含一层结构
            if (ref.hasChildren()) {
                NopException e = //
                        new NopException(ERR_STYLES_PATCH_NODE_NOT_ALLOWED).source(this)
                                                                           .param(ARG_TAG1, tagName)
                                                                           .param(ARG_TAG2, refTagName);
                collector.addException(e);
                return;
            }

            XuiStyleDef refDef = styles.getStyleDef(refTagName);
            if (refDef == null) {
                NopException e = //
                        new NopException(ERR_STYLES_UNDEFINED_STYLE).source(ref)
                                                                    .param(ARG_DEF_LOC, styles.getLocation())
                                                                    .param(ARG_TAG_NAME, refTagName);
                collector.addException(e);
                return;
            }

            ref.checkStyleProps(this, refDef, collector);
        });
    }

    /** @return 始终不返回 {@code null} */
    public Map<String, XDefTypeDecl> getPropTypes() {
        return firstNonNull(get$props(), Map.of());
    }

    /** 是否存在指定名字的属性 */
    public boolean hasProp(String propName) {
        return getPropTypes().containsKey(propName);
    }

    /** 获取指定属性的类型 */
    public XDefTypeDecl getPropType(String propName) {
        return getPropTypes().get(propName);
    }
}
