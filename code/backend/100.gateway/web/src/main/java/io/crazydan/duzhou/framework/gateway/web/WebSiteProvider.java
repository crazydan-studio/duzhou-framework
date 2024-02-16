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
import io.nop.commons.lang.impl.Cancellable;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.eval.global.EvalGlobalRegistry;
import io.nop.core.lang.xml.XNode;
import io.nop.core.lang.xml.parse.XNodeParser;
import io.nop.core.resource.component.ComponentModelConfig;
import io.nop.core.resource.component.ResourceComponentManager;
import io.nop.xlang.xdsl.DslModelParser;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * 其实例在 /duzhou/web/beans/default.beans.xml 中注册
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-07
 */
public class WebSiteProvider {
    private final Cancellable cleanup = new Cancellable();

    @PostConstruct
    public void init() {
        registerWebLoader();
        registerGlobalVars();
    }

    @PreDestroy
    public void destroy() {
        this.cleanup.cancel();
    }

    public String getSiteHtmlByRequestPath(String path) {
        XWeb web = (XWeb) ResourceComponentManager.instance().loadComponentModel(WebSiteConstants.VFS_XDSL_XWEB);
        XWebSite site = web.getSiteByUrl(path);

        if (site == null) {
            return null;
        }

        return WebSiteGlobalVariable.with(site, this::genSiteHtml);
    }

    private void registerWebLoader() {
        ComponentModelConfig config = new ComponentModelConfig();

        config.modelType(WebSiteConstants.MODEL_TYPE_XWEB);
        config.loader(WebSiteConstants.FILE_TYPE_WEB_XML, this::loadWeb);

        this.cleanup.append(ResourceComponentManager.instance().registerComponentModelConfig(config));
    }

    private XWeb loadWeb(String path) {
        return XWeb.parseFromVirtualPath(path);
    }

    private void registerGlobalVars() {
        Cancellable cancellable = new Cancellable();

        EvalGlobalRegistry.instance()
                          .registerVariable(WebSiteConstants.VAR_GLOBAL_SITE, WebSiteGlobalVariable.instance());
        cancellable.appendOnCancel(r -> EvalGlobalRegistry.instance()
                                                          .unregisterVariable(WebSiteConstants.VAR_GLOBAL_SITE));

        this.cleanup.append(cancellable);
    }

    private String genSiteHtml() {
        XNode node = XNodeParser.instance().parseFromVirtualPath(WebSiteConstants.VFS_XDSL_SITE_HTML);
        String xdefPath = node.attrText("x:schema");

        Object model = new DslModelParser(xdefPath).parseFromNode(node);
        XNode htmlNode = WebDslModelHelper.toHtmlNode(xdefPath, model);

        String html = htmlNode.html().replaceAll("\n\\s*", "");
        html = StringHelper.unescapeXml(html);

        return "<!DOCTYPE html>" + html;
    }
}
