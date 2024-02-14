/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.crazydan.duzhou.framework.gateway.starter;

import java.util.Optional;

import io.crazydan.duzhou.framework.gateway.starter.ioc.NopQuarkusBeanContainer;
import io.micrometer.core.instrument.MeterRegistry;
import io.nop.api.core.ApiConfigs;
import io.nop.api.core.config.AppConfig;
import io.nop.api.core.ioc.BeanContainer;
import io.nop.commons.metrics.GlobalMeterRegistry;
import io.nop.commons.util.StringHelper;
import io.quarkus.runtime.configuration.ProfileManager;
import org.eclipse.microprofile.config.ConfigProvider;

import static io.crazydan.duzhou.framework.gateway.starter.QuarkusConstants.CONFIG_KEY_PROFILE_PARENT;

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

        BeanContainer.registerInstance(new NopQuarkusBeanContainer());
        if (BeanContainer.instance().containsBeanType(MeterRegistry.class)) {
            MeterRegistry meterRegistry = BeanContainer.instance().getBeanByType(MeterRegistry.class);
            GlobalMeterRegistry.registerInstance(meterRegistry);
        }
    }
}
