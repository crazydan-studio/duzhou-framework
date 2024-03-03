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

package io.crazydan.duzhou.framework.gateway.web;

import io.crazydan.duzhou.framework.schema.web.XWeb;
import io.crazydan.duzhou.framework.schema.web.XWebSite;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.xml.XNode;
import io.nop.core.resource.component.ResourceComponentManager;

/**
 * 其实例在 /duzhou/web/beans/default.beans.xml 中注册
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-07
 */
public class WebSiteProvider {

    public String getSiteHtmlByRequestPath(String path) {
        XWebSite site = getSiteByPath(path);
        return getSiteHtml(site);
    }

    public String getDefaultSiteLogo() {
        XWebSite site = getDefaultSite();
        return site != null ? site.getLogo() : null;
    }

    public String getDefaultSiteHtml() {
        XWebSite site = getDefaultSite();
        return getSiteHtml(site);
    }

    private XWebSite getDefaultSite() {
        // TODO 内置默认页面
        return getSiteByPath("*");
    }

    private XWebSite getSiteByPath(String path) {
        XWeb web = (XWeb) ResourceComponentManager.instance() //
                                                  .loadComponentModel(WebSiteConstants.VFS_XDSL_XWEB);

        return web.getSiteByUrl(path);
    }

    private String getSiteHtml(XWebSite site) {
        XNode htmlNode = site != null ? site.getLayoutHtmlNode() : null;

        return htmlNode != null ? toHtml(htmlNode) : null;
    }

    private String toHtml(XNode node) {
        String html = node.html().replaceAll("\n\\s*", "");
        html = StringHelper.unescapeXml(html);

        return "<!DOCTYPE html>" + html;
    }
}
