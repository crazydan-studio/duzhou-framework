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

import java.util.Arrays;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.schema.XuiConstants;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.SourceLocation;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.commons.type.StdDataType;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.core.type.IGenericType;
import io.nop.core.type.utils.JavaGenericTypeBuilder;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.xdef.IStdDomainHandler;

import static io.crazydan.duzhou.framework.ui.schema.XuiConstants.STD_DOMAIN_XUI_EXPR;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_DOMAIN_TYPE_INVALID_FORMAT;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_DOMAIN_TYPE_INVALID_OPTIONS;
import static io.nop.api.core.util.ApiStringHelper.isBlank;
import static io.nop.xlang.XLangErrors.ARG_ALLOWED_VALUES;
import static io.nop.xlang.XLangErrors.ARG_NAME;
import static io.nop.xlang.XLangErrors.ARG_OPTIONS;
import static io.nop.xlang.XLangErrors.ARG_VALUE;
import static io.nop.xlang.xdef.XDefConstants.XDEF_TYPE_PREFIX_MANDATORY;
import static io.nop.xlang.xdef.XDefConstants.XDEF_TYPE_PREFIX_OPTIONS;

/**
 * 数据域 {@link XuiConstants#STD_DOMAIN_XUI_EXPR xui-expr}，
 * 用于限定属性值只能为字面量或 <code>${xxx}</code> 形式的变量引用
 * <p/>
 * 使用案例：
 * <pre>
 * xui:when="!xui-expr:boolean"
 * </pre>
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-29
 */
public class XuiExprDomainHandler implements IStdDomainHandler {
    public static final XuiExprDomainHandler INSTANCE = new XuiExprDomainHandler();

    @Override
    public String getName() {
        return STD_DOMAIN_XUI_EXPR;
    }

    @Override
    public boolean isFixedType() {
        return true;
    }

    /** 确定对应的模型属性类型，如 <code>XuiExpression&lt;String&gt;</code> */
    @Override
    public IGenericType getGenericType(boolean mandatory, String options) {
        if (isBlank(options)) {
            String value = (mandatory ? XDEF_TYPE_PREFIX_MANDATORY : "") + STD_DOMAIN_XUI_EXPR;

            throw new NopException(ERR_DOMAIN_TYPE_INVALID_FORMAT).param(ARG_VALUE, value)
                                                                  .param(ARG_ALLOWED_VALUES,
                                                                         value
                                                                         + XDEF_TYPE_PREFIX_OPTIONS
                                                                         + StdDataType.STRING.getName());
        }

        if ("html-text".equals(options)) {
            options = "string";
        }

        StdDataType type = StdDataType.fromStdName(options);
        if (type == null) {
            throw new NopException(ERR_DOMAIN_TYPE_INVALID_OPTIONS).param(ARG_NAME, STD_DOMAIN_XUI_EXPR)
                                                                   .param(ARG_VALUE, options)
                                                                   .param(ARG_OPTIONS,
                                                                          Arrays.stream(StdDataType.values())
                                                                                .map(StdDataType::getName)
                                                                                .collect(Collectors.joining(", ")));
        }

        return JavaGenericTypeBuilder.buildParameterizedType(XuiExpression.class, type.getJavaClass());
    }

    @Override
    public Object parseProp(String options, SourceLocation loc, String propName, Object text, XLangCompileTool cp) {
        if ("html-text".equals(options)) {
            options = "string";
            if (text != null) {
                text = text.toString().trim()
                           // 将 HTML 多行文本转为单行
                           .replaceAll("(?m)^\\s+", "")
                           // 还原空格
                           .replaceAll("&nbsp;", " ");
            }
        }

        // TODO 对于 boolean、int、float 等原始类型，待解析的值必须为具体值或者纯表达式，而不能为模板表达式

        StdDataType type = StdDataType.fromStdName(options);
        ValueWithLocation value = ValueWithLocation.of(loc, text);

        // Note: 对表达式所引用变量类型的检查只能在组件模型中进行，这里无法得到引用变量的信息
        // TODO 向 cp 的 scope 注入当前组件对象？

        return XuiExpression.create(type, value);
    }

    @Override
    public void validate(SourceLocation loc, String propName, Object value, IValidationErrorCollector collector) {
        // Note: 该校验仅在 SimpleSchemaValidator 中被调用，解析 XDSL 模型时不会被调用
    }
}
