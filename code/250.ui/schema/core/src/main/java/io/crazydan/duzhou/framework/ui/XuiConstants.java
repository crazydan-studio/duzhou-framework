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

package io.crazydan.duzhou.framework.ui;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-29
 */
public interface XuiConstants {
    String STD_DOMAIN_XUI_STYLE = "xui-style";
    String STD_DOMAIN_XUI_EXPR = "xui-expr";

    String XDSL_SCHEMA_APP = "/duzhou/ui/schema/app.xdef";
    String XDSL_SCHEMA_PAGE = "/duzhou/ui/schema/page.xdef";
    String XDSL_SCHEMA_COMPONENT = "/duzhou/ui/schema/component.xdef";
    String XDSL_SCHEMA_COMPONENT_TEMPLATE = "/duzhou/ui/schema/component/template.xdef";
    String XDSL_SCHEMA_COMPONENT_IMPORT = "/duzhou/ui/schema/component/import.xdef";
    String XDSL_SCHEMA_COMPONENT_MESSAGE = "/duzhou/ui/schema/component/message.xdef";

    String TAG_NAME_TEMPLATE = "template";
    String TAG_NAME_IF = "if";
    String TAG_NAME_FOR = "for";
    String TAG_NAME_CHOOSE = "choose";
    String TAG_NAME_WHEN = "when";
    String TAG_NAME_OTHERWISE = "otherwise";

    String ATTR_NAME_XUI_SLOT = "xui:slot";
    String ATTR_NAME_XUI_NAME = "xui:name";
    /** 用于记录在 Xpl &lt;for/> 标签中组件的原始 {@link #ATTR_NAME_XUI_NAME 唯一标识} */
    String ATTR_NAME_XUI_NAME_RAW = "raw-" + ATTR_NAME_XUI_NAME;
}
