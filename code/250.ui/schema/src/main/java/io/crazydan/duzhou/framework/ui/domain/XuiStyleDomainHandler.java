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
import io.nop.xlang.xdef.domain.DictStdDomainHandler;

import static io.crazydan.duzhou.framework.ui.XuiConstants.STD_DOMAIN_XUI_STYLE;

/**
 * 数据域 {@link XuiConstants#STD_DOMAIN_XUI_STYLE xui-style}，
 * 用于限定属性值只能为确定的样式类型
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-03-03
 */
public class XuiStyleDomainHandler extends DictStdDomainHandler {
    public static final XuiStyleDomainHandler INSTANCE = new XuiStyleDomainHandler();

    @Override
    public String getName() {
        return STD_DOMAIN_XUI_STYLE;
    }

    // TODO 参考 io.nop.xlang.xdef.domain.SimpleStdDomainHandlers.DefTypeType 实现，用于定义类型的类型

    //
    // /**
    //  * 解析属性类型定义结构 <code>(!~#)?{stdDomain}(:{options})?(={defaultValue})?</code>
    //  * 中的 <code>{options}</code> 部分，如 <code>xui:color</code> 中的 <code>color</code>
    //  */
    // @Override
    // public IStdDomainOptions parseOptions(SourceLocation loc, String options) {
    //     // TODO 根据 options 名字确定样式的数据类型
    //     return null;
    // }
    //
    // /**
    //  * 根据 options 确定属性类型对应的运行环境中的数据类型（含自定义 class），
    //  * 如 String、Date 等，可直接引用定义在 {@link PredefinedGenericTypes}
    //  * 上的内置类型，或者通过 {@link ReflectionManager} 构建
    //  */
    // @Override
    // public IGenericType getGenericType(boolean mandatory, IStdDomainOptions options) {
    //     return PredefinedGenericTypes.STRING_TYPE;
    // }
    //
    // /**
    //  * 根据 options 解析标签属性（对应参数 propName）的值（对应参数 text）。
    //  * 对标签的文本子节点的解析，则也调用该方法
    //  */
    // @Override
    // public Object parseProp(
    //         IStdDomainOptions options, SourceLocation loc, String propName, Object text, XLangCompileTool cp
    // ) {
    //     // TODO 根据 options 对应的数据类型，转换 text，并校验其有效性
    //     return text;
    // }
}
