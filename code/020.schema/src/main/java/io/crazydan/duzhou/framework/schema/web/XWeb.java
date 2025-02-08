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

package io.crazydan.duzhou.framework.schema.web;

import io.crazydan.duzhou.framework.schema.web._gen._XWeb;
import io.nop.xlang.xdsl.DslModelParser;

public class XWeb extends _XWeb {

    public XWeb() {

    }

    public static XWeb parseFromVirtualPath(String path) {
        // Note：在 DSL 中已经指定 x:schema，无需在 DslModelParser 构造参数中再指定
        return (XWeb) new DslModelParser().parseFromVirtualPath(path);
    }

    public XWebSite getSiteByUrl(String url) {
        for (XWebSite site : getSites()) {
            if (site.getUrl().equals(url)) {
                return site;
            }
        }
        return null;
    }
}
