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

package io.crazydan.duzhou.framework.store;

import java.io.File;

import io.nop.core.resource.IResource;
import io.nop.core.resource.IResourceNamespaceHandler;
import io.nop.core.resource.IResourceStore;
import io.nop.core.resource.ResourceHelper;
import io.nop.core.resource.impl.FileResource;

/**
 * 带名字空间的 vfs 路径必须为绝对路径，不带名字空间的绝对路径对应的是
 * {@code /_vfs/} 中的文件。在 vfs 文件中可以通过相对路径引用资源，
 * 其最终对应是相对于其所在文件的名字空间中的文件
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-01-08
 */
public class CustomNamespaceHandler implements IResourceNamespaceHandler {
    private final String ns;
    private final String rootDir;

    public CustomNamespaceHandler(String ns, String rootDir) {
        this.ns = ns;
        this.rootDir = rootDir;
    }

    @Override
    public String getNamespace() {
        return this.ns;
    }

    @Override
    public IResource getResource(String vPath, IResourceStore locator) {
        String path = ResourceHelper.removeNamespace(vPath, getNamespace());
        ResourceHelper.checkNormalVirtualPath(path);

        return new FileResource(vPath, new File(this.rootDir, path));
    }
}
