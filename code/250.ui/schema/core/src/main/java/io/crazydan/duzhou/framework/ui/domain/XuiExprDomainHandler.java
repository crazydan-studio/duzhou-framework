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

import io.crazydan.duzhou.framework.ui.XuiConstants;
import io.crazydan.duzhou.framework.ui.domain.type.XuiExpr;
import io.nop.api.core.util.SourceLocation;
import io.nop.api.core.validate.IValidationErrorCollector;
import io.nop.core.type.IGenericType;
import io.nop.core.type.utils.JavaGenericTypeBuilder;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.xdef.IStdDomainHandler;

import static io.crazydan.duzhou.framework.ui.XuiConstants.STD_DOMAIN_XUI_EXPR;

/**
 * 数据域 {@link XuiConstants#STD_DOMAIN_XUI_EXPR xui-expr}，
 * 用于限定属性值只能为字面量或 <code>${xxx}</code> 形式的变量引用
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

    @Override
    public IGenericType getGenericType(boolean mandatory, String options) {
        return JavaGenericTypeBuilder.buildGenericType(XuiExpr.class);
    }

    @Override
    public Object parseProp(String options, SourceLocation loc, String propName, Object text, XLangCompileTool cp) {
        if (text instanceof XuiExpr) {
            return text;
        }
        return XuiExpr.create(loc, text != null ? text.toString() : null);
    }

    @Override
    public void validate(SourceLocation loc, String propName, Object value, IValidationErrorCollector collector) {
        // Note: 该校验仅在 SimpleSchemaValidator 中被调用，解析 XDSL 模型时不会被调用
    }
}
