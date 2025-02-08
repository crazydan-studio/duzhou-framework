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

package io.crazydan.duzhou.framework.gateway.core.utils;

import java.io.File;

import io.crazydan.duzhou.framework.gateway.core.GatewayConfigs;
import io.nop.commons.bytes.ByteString;
import io.nop.commons.util.StringHelper;
import io.nop.core.resource.IResource;
import io.nop.core.resource.ResourceConstants;
import io.nop.core.resource.ResourceHelper;
import io.nop.core.resource.impl.ClassPathResource;
import io.nop.core.resource.impl.FileResource;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-15
 */
public class WebStaticResourcesHelper {
    public static final String GZIP_SUFFIX = ".gz";

    /**
     * 获取指定路径的资源对象
     *
     * @param path
     *         只能为 Web 资源根路径下的文件路径，其以 `/` 开头，并且不能包含 `.` 或 `..`
     */
    public static IResource getResource(String path) {
        if (!StringHelper.isAbsolutePath(path)) {
            return null;
        }

        String rootPath = GatewayConfigs.WEB_STATIC_RESOURCES_PATH.get();
        String fullPath = StringHelper.appendPath(rootPath, path);

        fullPath = StringHelper.normalizePath(fullPath);

        IResource resource = null;
        String ns = ResourceHelper.getPathNamespace(fullPath);
        if (ResourceConstants.FILE_NS.equals(ns)) {
            String filePath = ResourceHelper.removeNamespace(fullPath, ns);

            resource = new FileResource(new File(filePath));
        } else if (ResourceConstants.CLASSPATH_NS.equals(ns)) {
            resource = new ClassPathResource(fullPath);
        }

        return resource;
    }

    /**
     * 获取指定路径的图片资源的 data url
     *
     * @param path
     *         只能为 Web 资源根路径下的文件路径，其以 `/` 开头，并且不能包含 `.` 或 `..`
     */
    public static String getImageDataUrl(String path) {
        IResource resource = getResource(path);
        if (!isFile(resource)) {
            return null;
        }

        byte[] bytes = ResourceHelper.readBytes(resource);
        ByteString bs = ByteString.from(bytes, null);

        String mimeType = getImageMimeType(path);
        return bs.toDataUrl(mimeType);
    }

    public static String getImageMimeType(String path) {
        String type = StringHelper.fileExt(path);

        // 参考: io.nop.excel.model.ExcelImage#getMimeType
        if (type.equals("svg")) {
            type = "svg+xml";
        } else if (type.equals("jpg")) {
            type = "jpeg";
        }
        return "image/" + type;
    }

    /** 判断指定路径的资源是否为文件 */
    public static boolean isFile(String path) {
        IResource resource = getResource(path);

        return isFile(resource);
    }

    /** 判断指定的资源是否为文件 */
    public static boolean isFile(IResource resource) {
        return resource != null && resource.exists() && !resource.isDirectory();
    }
}
