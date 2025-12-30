/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

package io.crazydan.duzhou.framework.ui.domain;

import io.crazydan.duzhou.framework.ui.domain.type.XuiExpr;
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.nop.api.core.util.SourceLocation;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.commons.util.StringHelper;
import io.nop.core.type.IGenericType;
import io.nop.core.type.utils.JavaGenericTypeBuilder;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.xdef.IStdDomainHandler;
import io.nop.xlang.xdef.domain.CheckStdDomainHandler;
import io.nop.xlang.xdef.domain.SimpleStdDomainHandler;

import static io.crazydan.duzhou.framework.ui.XuiConstants.STD_DOMAIN_COMPONENT_NAME;
import static io.crazydan.duzhou.framework.ui.XuiConstants.STD_DOMAIN_XUI_EXPR;
import static io.crazydan.duzhou.framework.ui.XuiConstants.STD_DOMAIN_XUI_SIZE;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-11
 */
public class GenericStdDomainHandlers {
    public static final ComponentNameDomainHandler HANDLER_COMPONENT_NAME = new ComponentNameDomainHandler();
    public static final XuiSizeDomainHandler HANDLER_XUI_SIZE = new XuiSizeDomainHandler();
    public static final XuiExprDomainHandler HANDLER_XUI_EXPR = new XuiExprDomainHandler();

    /**
     * 是否为有效的组件名，由字母、数字、下划线组成的驼峰形式，且必须首字母需大写，
     * 如 {@code Button}、{@code Button_Ext}`
     */
    public static boolean isValidComponentName(String text) {
        return text.charAt(0) >= 'A' && text.charAt(0) <= 'Z' //
               && StringHelper.isValidJavaVarName(text) //
               && !text.contains("$") //
                ;
    }

    /** 是否为有效的样式名，其由小写字母、数字、下划线、短横线组成，开头必须为字母，且短横线不能连续出现 */
    public static boolean isValidStyleName(String text) {
        return text.charAt(0) >= 'a' && text.charAt(0) <= 'z' //
               && StringHelper.isValidXmlNamespaceName(text) //
               && !StringHelper.containsUpperCase(text) //
                ;
    }

    /** 是否为有效的样式属性名，其由字母、数字组成的驼峰形式，且开头必须为小写字母，如 {@code textColor} */
    public static boolean isValidStylePropName(String text) {
        return text.charAt(0) >= 'a' && text.charAt(0) <= 'z' //
               && StringHelper.isValidPropName(text) //
               && !text.contains("_") //
                ;
    }

    /** 组件名类型：{@link #isValidComponentName} */
    public static class ComponentNameDomainHandler extends CheckStdDomainHandler {

        @Override
        public String getName() {
            return STD_DOMAIN_COMPONENT_NAME;
        }

        @Override
        protected boolean isValid(String text) {
            return isValidComponentName(text);
        }
    }

    /** 尺寸类型 */
    public static class XuiSizeDomainHandler extends SimpleStdDomainHandler {

        @Override
        public String getName() {
            return STD_DOMAIN_XUI_SIZE;
        }

        @Override
        public boolean isFixedType() {
            return true;
        }

        /** 确定对应的模型属性类型 */
        @Override
        public IGenericType getGenericType(boolean mandatory, String options) {
            return JavaGenericTypeBuilder.buildRawType(XuiSize.class);
        }

        @Override
        public Object parseProp(String options, SourceLocation loc, String propName, Object text, XLangCompileTool cp) {
            return XuiSize.parse(loc, text);
        }
    }

    /** {@code ${xxx}} 表达式 */
    public static class XuiExprDomainHandler implements IStdDomainHandler {

        @Override
        public String getName() {
            return STD_DOMAIN_XUI_EXPR;
        }

        @Override
        public boolean isFixedType() {
            return true;
        }

        @Override
        public IGenericType getGenericType(boolean mandatory, String options) {
            return JavaGenericTypeBuilder.buildGenericType(XuiExpr.class);
        }

        @Override
        public Object parseProp(String options, SourceLocation loc, String propName, Object text, XLangCompileTool cp) {
            return XuiExpr.parse(loc, text);
        }

        @Override
        public void validate(SourceLocation loc, String propName, Object value, IValidationErrorCollector collector) {
            // Note: 该校验仅在 SimpleSchemaValidator 中被调用，解析 XDSL 模型时不会被调用
        }
    }
}
