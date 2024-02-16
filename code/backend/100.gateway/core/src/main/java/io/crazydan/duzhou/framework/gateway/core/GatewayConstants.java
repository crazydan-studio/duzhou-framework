/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.gateway.core;

import io.nop.http.api.server.IHttpServerFilter;

public interface GatewayConstants {
    /** API 过滤器：其需优先于 {@link #PRIORITY_WEB_SITE_FILTER Web 站点过滤器} */
    int PRIORITY_API_FILTER = IHttpServerFilter.NORMAL_PRIORITY + 100;
    /** Web 站点过滤器 */
    int PRIORITY_WEB_SITE_FILTER = IHttpServerFilter.LOW_PRIORITY - 100;
}
