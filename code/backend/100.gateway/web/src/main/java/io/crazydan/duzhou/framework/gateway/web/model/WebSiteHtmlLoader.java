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

package io.crazydan.duzhou.framework.gateway.web.model;

import io.crazydan.duzhou.framework.gateway.web.WebDslModelHelper;
import io.nop.api.core.util.IComponentModel;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.xml.XNode;
import io.nop.core.lang.xml.parse.XNodeParser;
import io.nop.core.resource.IResourceObjectLoader;
import io.nop.xlang.xdsl.DslModelParser;

/**
 * `*.site-html.xml` 的加载器
 * <p/>
 * 该加载器注册在 `_vfs/nop/core/registry/xweb.register-model.xml` 中
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-19
 */
public class WebSiteHtmlLoader implements IResourceObjectLoader<WebSiteHtmlLoader.WebSiteHtml> {

    @Override
    public WebSiteHtml loadObjectFromPath(String path) {
        XNode node = XNodeParser.instance().parseFromVirtualPath(path);
        String xdefPath = node.attrText("x:schema");

        Object model = new DslModelParser(xdefPath).parseFromNode(node);
        XNode htmlNode = parseToHtmlNode(xdefPath, model);

        String html = htmlNode.html().replaceAll("\n\\s*", "");
        html = StringHelper.unescapeXml(html);

        return new WebSiteHtml(path, html);
    }

    protected XNode parseToHtmlNode(String xdefPath, Object model) {
        return WebDslModelHelper.toHtmlNode(xdefPath, model);
    }

    public static class WebSiteHtml implements IComponentModel {
        private final String content;
        private final SourceLocation location;

        public WebSiteHtml(String path, String content) {
            this.content = content;
            this.location = SourceLocation.fromPath(path);
        }

        @Override
        public SourceLocation getLocation() {
            return this.location;
        }

        public String getContent() {
            return "<!DOCTYPE html>" + this.content;
        }
    }
}
