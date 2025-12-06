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

import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.nop.api.core.util.SourceLocation;
import io.nop.core.type.IGenericType;
import io.nop.core.type.utils.JavaGenericTypeBuilder;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.xdef.domain.SimpleStdDomainHandler;

import static io.crazydan.duzhou.framework.ui.XuiConstants.STD_DOMAIN_XUI_SIZE;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-12-06
 */
public class XuiSizeDomainHandler extends SimpleStdDomainHandler {
    public static final XuiSizeDomainHandler INSTANCE = new XuiSizeDomainHandler();

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
        return XuiSize.parse(loc, (String) text);
    }
}
