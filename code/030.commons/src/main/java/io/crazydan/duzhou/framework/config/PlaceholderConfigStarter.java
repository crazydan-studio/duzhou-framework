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

package io.crazydan.duzhou.framework.config;

import java.lang.reflect.Field;
import java.util.Map;

import io.nop.api.core.config.AppConfig;
import io.nop.api.core.config.DefaultConfigReference;
import io.nop.api.core.config.IConfigProvider;
import io.nop.api.core.config.IConfigReference;
import io.nop.api.core.util.SourceLocation;
import io.nop.api.core.util.StaticValue;
import io.nop.config.enhancer.IConfigValueEnhancer;
import io.nop.config.impl.DefaultConfigProvider;
import io.nop.config.source.IConfigSource;
import io.nop.config.starter.ConfigStarter;

/**
 * 通过 {@link ConfigStarter#registerInstance(ConfigStarter)}
 * 修改默认的 {@link ConfigStarter}。该替换必须在
 * {@code ConfigInitializer} 初始化之前完成
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-02-07
 */
public class PlaceholderConfigStarter extends ConfigStarter {

    @Override
    protected void initConfigProvider(IConfigSource configSource) {
        IConfigValueEnhancer valueEnhancer = newValueEnhancer(configSource);
        IConfigProvider defaultProvider = AppConfig.getConfigProvider();

        DefaultConfigProvider configProvider = //
                new ConfigProvider(configSource,
                                   valueEnhancer,
                                   defaultProvider.getConfigReferences(),
                                   defaultProvider.getStaticConfigValues());

        try {
            Field field = ConfigStarter.class.getDeclaredField("configProvider");
            field.setAccessible(true);
            field.set(this, configProvider);

            AppConfig.registerConfigProvider(configProvider);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static class ConfigProvider extends DefaultConfigProvider {

        public ConfigProvider(
                IConfigSource configSource, IConfigValueEnhancer configValueEnhancer,
                Map<String, DefaultConfigReference<?>> refs, Map<String, StaticValue<?>> staticValues
        ) {
            super(configSource, configValueEnhancer, refs, staticValues);
        }

        @Override
        public <T> IConfigReference<T> getConfigReference(
                String varName, Class<T> clazz, T defaultValue,
                SourceLocation loc
        ) {
            IConfigReference<T> ref = super.getConfigReference(varName, clazz, defaultValue, loc);

            return AppConfig.withPlaceholder(ref);
        }

        @Override
        protected DefaultConfigReference getConfigRef(String varName) {
            DefaultConfigReference ref = super.getConfigRef(varName);

            if (ref != null) {
                ref = DefaultConfigReference.makeDefault(AppConfig.withPlaceholder(ref), ref.getDefaultValue());
            }
            return ref;
        }
    }
}
