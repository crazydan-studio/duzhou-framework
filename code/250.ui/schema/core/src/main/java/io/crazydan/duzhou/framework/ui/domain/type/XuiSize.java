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

package io.crazydan.duzhou.framework.ui.domain.type;

import java.util.function.Function;

import io.crazydan.duzhou.framework.commons.UnitNumber;
import io.crazydan.duzhou.framework.lang.CodeSnippet;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.convert.ConvertHelper;
import io.nop.api.core.convert.ITypeConverter;
import io.nop.api.core.exceptions.ErrorCode;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.api.core.util.SourceLocation;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

import static io.crazydan.duzhou.framework.commons.StringHelper.extractNumberAndUnit;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_DOMAIN_TYPE_UNKNOWN_SIZE;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_DOMAIN_TYPE_XUI_SIZE_VALUE_SPECIFIED_NOT_ALLOWED;

/**
 * 尺寸
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
@DataBean
public class XuiSize implements ISourceLocationGetter, IJsonSerializable, CodeSnippet {
    public static final ITypeConverter TYPE_CONVERTER = //
            (value, errorFactory) -> parse(null, value, errorFactory);

    private final SourceLocation loc;

    /** 类型 */
    public final XuiSizeType type;
    /** 值：{@code [float, unit]} */
    public final Object[] value;

    public static XuiSize parse(SourceLocation loc, Object source) {
        return parse(loc, source, (errorCode) -> new NopException(errorCode).loc(loc));
    }

    public static XuiSize parse(SourceLocation loc, Object source, Function<ErrorCode, NopException> errorFactory) {
        if (source == null) {
            return null;
        } else if (source instanceof XuiSize) {
            return (XuiSize) source;
        }

        String s = source.toString();
        XuiSizeType type = XuiSizeType.fromText(s);
        if (type == XuiSizeType.value_specified) {
            return ConvertHelper.handleError(ERR_DOMAIN_TYPE_XUI_SIZE_VALUE_SPECIFIED_NOT_ALLOWED,
                                             null,
                                             XuiSize.class,
                                             source,
                                             errorFactory);
        }

        if (type == null) {
            UnitNumber nut = extractNumberAndUnit(s);
            if (nut != null && nut.number != null && nut.unit != null) {
                XuiSizeUnit unit = XuiSizeUnit.fromText(nut.unit);
                float value = nut.number.floatValue();

                return new XuiSize(loc, XuiSizeType.value_specified, value, unit);
            }

            return ConvertHelper.handleError(ERR_DOMAIN_TYPE_UNKNOWN_SIZE, null, XuiSize.class, source, errorFactory);
        } else {
            return new XuiSize(loc, type, 0, null);
        }
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    XuiSize(SourceLocation loc, XuiSizeType type, float value, XuiSizeUnit unit) {
        this.loc = loc;
        this.type = type;
        this.value = type == XuiSizeType.value_specified //
                     ? new Object[] { value, unit } //
                     : new Object[2];
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }

    @Override
    public String toCodeSnippet(char strQuote) {
        return strQuote + toString() + strQuote;
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.stringValue(null, toString());
    }

    @Override
    public String toString() {
        if (this.type == XuiSizeType.value_specified) {
            return this.value[0] + ((XuiSizeUnit) this.value[1]).code;
        }
        return this.type.code;
    }
}
