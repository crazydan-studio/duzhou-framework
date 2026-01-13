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

package io.crazydan.duzhou.framework.initializer;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.commons.ResourceHelper;
import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.store.CustomNamespaceHandler;
import io.nop.commons.lang.impl.Cancellable;
import io.nop.core.CoreConstants;
import io.nop.core.initialize.ICoreInitializer;
import io.nop.core.lang.eval.global.EvalGlobalRegistry;
import io.nop.core.lang.eval.global.StaticClassGlobalVariableDefinition;
import io.nop.core.resource.IResourceNamespaceHandler;
import io.nop.core.resource.IVirtualFileSystem;
import io.nop.core.resource.VirtualFileSystem;

import static io.crazydan.duzhou.framework.CommonConfigs.CFG_NOP_VFS_CUSTOM_NS_MAPPINGS;
import static io.nop.core.CoreConstants.INITIALIZER_PRIORITY_REGISTER_COMPONENT;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-25
 */
public class NopExtInitializer implements ICoreInitializer {
    private final Cancellable cleanup = new Cancellable();

    @Override
    public int order() {
        return INITIALIZER_PRIORITY_REGISTER_COMPONENT;
    }

    @Override
    public void initialize() {
        registerVfs();
        registerFunctions();
    }

    @Override
    public void destroy() {
        this.cleanup.cancel();
    }

    private void registerVfs() {
        Set<String> mappings = CFG_NOP_VFS_CUSTOM_NS_MAPPINGS.get();
        if (mappings == null) {
            return;
        }

        IVirtualFileSystem vfs = VirtualFileSystem.instance();
        List<IResourceNamespaceHandler> handlers = mappings.stream().map((mapping) -> {
            String ns = StringHelper.getNamespace(mapping);
            if (ns == null) {
                return null;
            }

            String rootDir = ResourceHelper.removeNamespace(mapping, ns);
            if (!StringHelper.isValidFilePath(rootDir)) {
                return null;
            }

            rootDir = StringHelper.normalizePath(rootDir);
            return new CustomNamespaceHandler(ns, rootDir);
        }).filter(Objects::nonNull).collect(Collectors.toList());

        handlers.forEach(vfs::registerNamespaceHandler);
        this.cleanup.appendOnCancelTask(() -> {
            handlers.forEach(vfs::unregisterNamespaceHandler);
        });
    }

    private void registerFunctions() {
        EvalGlobalRegistry registry = EvalGlobalRegistry.instance();
        registry.registerVariable(CoreConstants.GLOBAL_VAR_STRING,
                                  new StaticClassGlobalVariableDefinition(StringHelper.class));

        this.cleanup.append(registry.registerStaticFunctions(NopExtFunctions.class));
    }
}
