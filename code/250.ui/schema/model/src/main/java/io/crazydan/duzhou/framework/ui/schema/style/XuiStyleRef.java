package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.Map;

import io.crazydan.duzhou.framework.ui.domain.type.XuiExpr;
import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleRef;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.xlang.ast.Identifier;
import io.nop.xlang.xdef.XDefTypeDecl;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_REF_VAR_NOT_MATCH_DEF_PROP;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_REF_VAR;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_STYLES_UNDEFINED_STYLE_PROP;
import static io.nop.xlang.XLangErrors.ARG_DEF_LOC;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;
import static io.nop.xlang.XLangErrors.ARG_REF_NAME;
import static io.nop.xlang.XLangErrors.ARG_VAR_DECL1;
import static io.nop.xlang.XLangErrors.ARG_VAR_DECL2;
import static io.nop.xlang.XLangErrors.ARG_VAR_NAME;

public class XuiStyleRef extends _XuiStyleRef {

    public XuiStyleRef() {
    }

    /** 检查样式引用上的属性有效性 */
    public void checkProps(XuiStyleDef topDef, XuiStyleDef myDef, IValidationErrorCollector collector) {
        getProps().forEach((propName, propValue) -> {
            XDefTypeDecl propType = myDef.getPropType(propName);
            if (propType == null) {
                collector.buildError(ERR_STYLES_UNDEFINED_STYLE_PROP)
                         .loc(getLocation())
                         .param(ARG_DEF_LOC, myDef)
                         .param(ARG_PROP_NAME, propName);
                return;
            }

            // 在属性中引用变量只能来自于上层样式定义，且该变量的类型必须与其自身定义的一致
            if (XuiExpr.isExpr(propValue)) {
                XuiExpr expr = XuiExpr.create(getLocation(), propType, propValue);

                if (expr.expr instanceof Identifier) {
                    Identifier id = (Identifier) expr.expr;
                    String propRefName = id.getName();

                    XDefTypeDecl propRefType = topDef.getPropType(propRefName);
                    if (propRefType == null) {
                        collector.buildError(ERR_STYLES_UNDEFINED_REF_VAR)
                                 .loc(getLocation())
                                 .param(ARG_DEF_LOC, topDef)
                                 .param(ARG_PROP_NAME, propRefName)
                                 .param(ARG_REF_NAME, propRefName);
                        return;
                    } else if (!propRefType.getStdDomain().equals(propType.getStdDomain())) {
                        collector.buildError(ERR_STYLES_REF_VAR_NOT_MATCH_DEF_PROP)
                                 .loc(getLocation())
                                 .param(ARG_PROP_NAME, propName)
                                 .param(ARG_VAR_DECL1, propType.getStdDomain())
                                 .param(ARG_VAR_NAME, propRefName)
                                 .param(ARG_VAR_DECL2, propRefType.getStdDomain());
                        return;
                    }
                }
            } else {
                // TODO 通过样式定义类型转换属性值
            }
            // TODO 绑定转换后的属性值
        });
    }

    /** @return 始终不返回 {@code null} */
    public Map<String, String> getProps() {
        return firstNonNull(get$props(), Map.of());
    }
}
