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
package io.crazydan.duzhou.framework.starter;

import java.util.Optional;

import io.crazydan.duzhou.framework.starter.ioc.NopQuarkusBeanContainer;
import io.micrometer.core.instrument.MeterRegistry;
import io.nop.api.core.ApiConfigs;
import io.nop.api.core.config.AppConfig;
import io.nop.api.core.ioc.BeanContainer;
import io.nop.commons.metrics.GlobalMeterRegistry;
import io.nop.commons.util.StringHelper;
import io.quarkus.runtime.configuration.ProfileManager;
import org.eclipse.microprofile.config.ConfigProvider;

import static io.crazydan.duzhou.framework.starter.QuarkusConstants.CONFIG_KEY_PROFILE_PARENT;

public class QuarkusIntegration {

    public static void start() {
        String profile = ProfileManager.getActiveProfile();
        Optional<String> parentProfile = ConfigProvider.getConfig()
                                                       .getOptionalValue(CONFIG_KEY_PROFILE_PARENT, String.class);

        if (!StringHelper.isEmpty(profile)) {
            System.setProperty(ApiConfigs.CFG_PROFILE.getName(), profile);
            AppConfig.getConfigProvider().updateConfigValue(ApiConfigs.CFG_PROFILE, profile);

            if (profile.equals("dev")) {
                AppConfig.getConfigProvider().updateConfigValue(ApiConfigs.CFG_DEBUG, true);
            }
        }

        if (parentProfile.isPresent()) {
            System.setProperty(ApiConfigs.CFG_PROFILE_PARENT.getName(), parentProfile.get());
            AppConfig.getConfigProvider().updateConfigValue(ApiConfigs.CFG_PROFILE_PARENT, parentProfile.get());
        }

        // 注册 beans 容器
        BeanContainer.registerInstance(new NopQuarkusBeanContainer());

        if (BeanContainer.instance().containsBeanType(MeterRegistry.class)) {
            MeterRegistry meterRegistry = BeanContainer.instance().getBeanByType(MeterRegistry.class);
            GlobalMeterRegistry.registerInstance(meterRegistry);
        }
    }
}
