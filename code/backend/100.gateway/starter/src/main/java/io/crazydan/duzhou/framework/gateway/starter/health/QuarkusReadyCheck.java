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
package io.crazydan.duzhou.framework.gateway.starter.health;

import io.nop.core.initialize.CoreInitialization;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/** 是否已经启动并可以对外提供服务 */
@Readiness
public class QuarkusReadyCheck implements HealthCheck {
    static final String APP_NAME = "NopPlatform";

    @Override
    public HealthCheckResponse call() {
        boolean inited = CoreInitialization.isInitialized();

        return inited ? HealthCheckResponse.up(APP_NAME) : HealthCheckResponse.down(APP_NAME);
    }
}
