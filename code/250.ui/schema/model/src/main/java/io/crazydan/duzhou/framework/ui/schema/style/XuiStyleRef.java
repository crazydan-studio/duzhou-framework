package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.crazydan.duzhou.framework.ui.domain.type.XuiExpr;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleRef;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.xlang.xdef.XDefTypeDecl;

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

    /**
     * 检查引用样式的定义是否存在，并检查定义的属性与其配置的属性是否一致
     *
     * @param ownerStyleDef
     *         当前引用样式所属的定义样式。若该值不为 {@code null}，
     *         则检查当前引用样式的属性值中的 {@code ${var}} 表达式所引用的变量 {@code var}
     *         是否在 {@code ownerStyleDef} 上定义
     */
    public void validate(XuiStyleDefs styleDefs, XuiStyleDef ownerStyleDef, IValidationErrorCollector collector) {
        validate(styleDefs, ownerStyleDef, null, collector);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /** @return 始终不返回 {@code null} */
    @Override
    public Map<String, XuiExpr> getProps() {
        return firstNonNull(super.getProps(), Map.of());
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    protected void validate(
            XuiStyleDefs styleDefs, XuiStyleDef ownerStyleDef, String styleNamePrefix,
            IValidationErrorCollector collector
    ) {
        XuiStyleDef styleDef = checkStyleDef(styleDefs, styleNamePrefix, collector);
        checkStyleProps(ownerStyleDef, styleDef, collector);
    }

    /** 检查引用样式的定义是否存在 */
    protected XuiStyleDef checkStyleDef(
            XuiStyleDefs styleDefs, String styleNamePrefix, IValidationErrorCollector collector) {
        String styleName = (styleNamePrefix != null ? styleNamePrefix : "") + get$tag();

        XuiStyleDef styleDef = styleDefs.getStyleDef(styleName);
        if (styleDef == null) {
            collector.buildError(ERR_STYLES_UNDEFINED_STYLE)
                     .loc(getLocation())
                     .param(ARG_DEF_LOC, styleDefs.getLocation())
                     .param(ARG_TAG_NAME, styleName)
                     .addToCollector(collector);
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
    protected void checkStyleProps(
            XuiStyleDef ownerStyleDef, XuiStyleDef myStyleDef, IValidationErrorCollector collector) {
        if (ownerStyleDef == null && myStyleDef == null) {
            return;
        }

        Set<String> nullProps = new HashSet<>();
        getProps().forEach((propName, prop) -> {
            if (prop.isLiteral() && prop.getLiteralValue() == null) {
                nullProps.add(propName);
                return;
            }

            XDefTypeDecl propType = checkStylePropType(propName, prop, myStyleDef, collector);

            // 检查对其所属的定义样式的属性的引用
            if (ownerStyleDef != null && prop.isIdentifier()) {
                String propRefName = prop.getIdentifierName();

                XDefTypeDecl propRefType = ownerStyleDef.getPropType(propRefName);
                if (propRefType == null) {
                    collector.buildError(ERR_STYLES_UNDEFINED_REF_VAR)
                             .loc(prop.getLocation())
                             .param(ARG_DEF_LOC, ownerStyleDef)
                             .param(ARG_PROP_NAME, propRefName)
                             .param(ARG_REF_NAME, propRefName)
                             .addToCollector(collector);
                } //
                else if (propType != null && !propRefType.getStdDomain().equals(propType.getStdDomain())) {
                    collector.buildError(ERR_STYLES_REF_VAR_NOT_MATCH_DEF_PROP)
                             .loc(prop.getLocation())
                             .param(ARG_PROP_NAME, propName)
                             .param(ARG_VAR_DECL1, propType.getStdDomain())
                             .param(ARG_VAR_NAME, propRefName)
                             .param(ARG_VAR_DECL2, propRefType.getStdDomain())
                             .addToCollector(collector);
                }
            }
        });

        // 移除 null 值属性
        nullProps.forEach(getProps()::remove);
    }

    /** 检查配置的属性是否存在，以及其值是否与定义类型一致 */
    protected XDefTypeDecl checkStylePropType(
            String propName, XuiExpr prop, XuiStyleDef styleDef,
            IValidationErrorCollector collector
    ) {
        if (styleDef == null) {
            return null;
        }

        XDefTypeDecl propType = styleDef.getPropType(propName);
        if (propType == null) {
            collector.buildError(ERR_STYLES_UNDEFINED_STYLE_PROP)
                     .loc(prop.getLocation())
                     .param(ARG_DEF_LOC, styleDef)
                     .param(ARG_PROP_NAME, propName)
                     .addToCollector(collector);
        }
        // Note: 非字面量表达式的实际值类型需在运行时确定，故而，仅对字面量做检查
        else if (!prop.isLiteral()) {
            String propValue = prop.getLiteralValue();
            if (propValue.isEmpty()) {
                if (propType.isMandatory()) {
                    collector.buildError(ERR_STYLES_MANDATORY_STYLE_PROP)
                             .loc(prop.getLocation())
                             .param(ARG_PROP_NAME, propName)
                             .addToCollector(collector);
                }
            } else {
                try {
                    prop.parseValue(propType, propName, propValue);
                } catch (Exception e) {
                    collector.addException(e);
                }
            }
        }

        return propType;
    }
}
