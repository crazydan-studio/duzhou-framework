/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2026 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.junit;

import io.nop.commons.util.StringHelper;
import io.nop.config.ConfigConstants;
import io.nop.config.source.IConfigSource;
import io.nop.config.source.ResourceConfigSourceLoader;
import io.nop.core.resource.IResource;
import io.nop.core.resource.ResourceHelper;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static io.nop.core.unittest.BaseTestCase.getTestConfigs;
import static io.nop.core.unittest.BaseTestCase.setTestConfig;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-01-01
 */
public class NopJunitExtension implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String additional = getTestConfigs().getOrDefault(ConfigConstants.CFG_CONFIG_ADDITIONAL_LOCATION, "")
                                            .toString();

        if (!StringHelper.isEmpty(additional)) {
            IResource resource = ResourceHelper.buildConfigResource(additional);

            try (IConfigSource source = new ResourceConfigSourceLoader(resource).loadConfigSource(null)) {
                source.getConfigValues().forEach((name, vl) -> {
                    setTestConfig(name, vl.getValue());
                });
            }
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
    }
}
