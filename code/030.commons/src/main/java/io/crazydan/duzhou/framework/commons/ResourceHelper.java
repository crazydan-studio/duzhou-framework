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

package io.crazydan.duzhou.framework.commons;

import io.nop.commons.bytes.ByteString;
import io.nop.core.resource.IResource;
import io.nop.core.resource.ResourceConstants;
import io.nop.core.resource.impl.ClassPathResource;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-08
 */
public class ResourceHelper extends io.nop.core.resource.ResourceHelper {

    /**
     * 获取指定路径的图片资源的 data url
     *
     * @param path
     *         只能为 classpath 下的资源文件路径
     */
    public static String getImageDataUrl(String path) {
        IResource resource = new ClassPathResource(ResourceConstants.RESOURCE_NS_CLASSPATH + ":" + path);
        if (!isFile(resource)) {
            return null;
        }

        byte[] bytes = readBytes(resource);
        ByteString bs = ByteString.from(bytes, null);

        String mimeType = getImageMimeType(path);
        return bs.toDataUrl(mimeType);
    }

    /** 根据图片文件路径确定其 MIME 类型 */
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

    /** 判断指定的资源是否为文件 */
    public static boolean isFile(IResource resource) {
        return resource != null && resource.exists() && !resource.isDirectory();
    }
}
