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

package io.crazydan.duzhou.framework.gateway.core;

import io.nop.api.core.annotations.core.Description;
import io.nop.api.core.annotations.core.Locale;
import io.nop.api.core.config.IConfigReference;
import io.nop.api.core.util.SourceLocation;

import static io.nop.api.core.config.AppConfig.varRef;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-15
 */
@Locale("zh-CN")
public interface GatewayConfigs {
    SourceLocation s_loc = SourceLocation.fromClass(GatewayConfigs.class);

    @Description("Web 静态资源的根路径，可以为系统文件的绝对路径，也可以为 classpath 下的资源路径，"
                 + "前者需加前缀 `file:`，后者需加前缀 `classpath:`。"
                 + "默认为 `classpath:META-INF/resources`")
    IConfigReference<String> WEB_STATIC_RESOURCES_PATH = varRef(s_loc,
                                                                "duzhou.gateway.web.static-resources.path",
                                                                String.class,
                                                                "classpath:META-INF/resources");
}
