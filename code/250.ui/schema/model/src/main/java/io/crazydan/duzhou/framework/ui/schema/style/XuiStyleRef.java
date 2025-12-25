package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.crazydan.duzhou.framework.ui.domain.type.XuiExpr;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleRef;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.xlang.api.XLang;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.xdef.IStdDomainHandler;
import io.nop.xlang.xdef.XDefTypeDecl;
import io.nop.xlang.xdef.domain.StdDomainRegistry;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_MANDATORY_STYLE_PROP;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_REF_VAR_NOT_MATCH_DEF_PROP;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_REF_VAR;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_STYLE;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_STYLE_PROP;
import static io.nop.xlang.XLangErrors.ARG_DEF_LOC;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;
import static io.nop.xlang.XLangErrors.ARG_REF_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;
import static io.nop.xlang.XLangErrors.ARG_VAR_DECL1;
import static io.nop.xlang.XLangErrors.ARG_VAR_DECL2;
import static io.nop.xlang.XLangErrors.ARG_VAR_NAME;

public class XuiStyleRef extends _XuiStyleRef {

    public XuiStyleRef() {
    }

    /** @return 始终不返回 {@code null} */
    public Map<String, String> getProps() {
        return firstNonNull(get$props(), Map.of());
    }

    /** 检查引用样式的定义是否存在 */
    public XuiStyleDef checkStyleDef(XuiComponentStyles styleDefs, IValidationErrorCollector collector) {
        String styleName = get$tag();

        XuiStyleDef styleDef = styleDefs.getStyleDef(styleName);
        if (styleDef == null) {
            NopException e = //
                    new NopException(ERR_STYLES_UNDEFINED_STYLE).source(this)
                                                                .param(ARG_DEF_LOC, styleDefs.getLocation())
                                                                .param(ARG_TAG_NAME, styleName);
            collector.addException(e);
        }
        return styleDef;
    }

    /**
     * 检查引用样式上的属性的有效性
     *
     * @param ownerStyleDef
     *         当前引用样式所属的定义样式。若该值不为 {@code null}，
     *         则检查当前引用样式的属性值中的 {@code ${var}} 表达式所引用的变量 {@code var}
     *         是否在 {@code ownerStyleDef} 上定义
     * @param myStyleDef
     *         当前引用样式所对应的样式定义，通过 {@link #checkStyleDef} 得到。
     *         若该值不为 {@code null}，则检查当前引用样式的属性及其值类型是否与 {@code myStyleDef} 定义的一致
     */
    public void checkStyleProps(
            XuiStyleDef ownerStyleDef, XuiStyleDef myStyleDef, IValidationErrorCollector collector) {
        if (ownerStyleDef == null && myStyleDef == null) {
            return;
        }

        Set<String> nullProps = new HashSet<>();
        getProps().forEach((propName, propValue) -> {
            XuiExpr expr = XuiExpr.create(getLocation(), propValue);
            if (expr.isLiteral()) {
                propValue = expr.getLiteralValue();
            }

            if (propValue == null) {
                nullProps.add(propName);
                return;
            }

            XDefTypeDecl propType = checkStylePropType(propName, propValue, !expr.isLiteral(), myStyleDef, collector);

            // 检查对其所属的定义样式的属性的引用
            if (ownerStyleDef != null && expr.isIdentifier()) {
                String propRefName = expr.getIdentifierName();

                XDefTypeDecl propRefType = ownerStyleDef.getPropType(propRefName);
                if (propRefType == null) {
                    NopException e = //
                            new NopException(ERR_STYLES_UNDEFINED_REF_VAR).source(this)
                                                                          .param(ARG_DEF_LOC, ownerStyleDef)
                                                                          .param(ARG_PROP_NAME, propRefName)
                                                                          .param(ARG_REF_NAME, propRefName);
                    collector.addException(e);
                } //
                else if (propType != null && !propRefType.getStdDomain().equals(propType.getStdDomain())) {
                    NopException e = //
                            new NopException(ERR_STYLES_REF_VAR_NOT_MATCH_DEF_PROP).source(this)
                                                                                   .param(ARG_PROP_NAME, propName)
                                                                                   .param(ARG_VAR_DECL1,
                                                                                          propType.getStdDomain())
                                                                                   .param(ARG_VAR_NAME, propRefName)
                                                                                   .param(ARG_VAR_DECL2,
                                                                                          propRefType.getStdDomain());
                    collector.addException(e);
                }
            }
        });

        // 移除 null 值属性
        nullProps.forEach(getProps()::remove);
    }

    protected XDefTypeDecl checkStylePropType(
            String propName, String propValue, boolean isPropExpr,
            XuiStyleDef styleDef, IValidationErrorCollector collector
    ) {
        if (styleDef == null) {
            return null;
        }

        XDefTypeDecl propType = styleDef.getPropType(propName);
        if (propType == null) {
            NopException e = //
                    new NopException(ERR_STYLES_UNDEFINED_STYLE_PROP).source(this)
                                                                     .param(ARG_DEF_LOC, styleDef)
                                                                     .param(ARG_PROP_NAME, propName);
            collector.addException(e);
        }
        // Note: 表达式的实际值类型需在运行时确定，故而，仅对字面量做检查
        else if (!isPropExpr) {
            if (propValue.isEmpty()) {
                if (propType.isMandatory()) {
                    NopException e = //
                            new NopException(ERR_STYLES_MANDATORY_STYLE_PROP).source(this)
                                                                             .param(ARG_PROP_NAME, propName);
                    collector.addException(e);
                }
            } else {
                IStdDomainHandler handler = StdDomainRegistry.instance().getStdDomainHandler(propType.getStdDomain());

                XLangCompileTool cp = XLang.newCompileTool();
                try {
                    handler.parseProp(propType.getOptions(), getLocation(), propName, propValue, cp);
                } catch (Exception e) {
                    collector.addException(e);
                }
            }
        }

        return propType;
    }
}
