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

package io.crazydan.duzhou.framework.gateway.web.filter;

import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

import io.crazydan.duzhou.framework.gateway.core.GatewayConstants;
import io.crazydan.duzhou.framework.gateway.core.utils.WebStaticResourcesHelper;
import io.crazydan.duzhou.framework.gateway.web.WebSiteProvider;
import io.nop.http.api.HttpStatus;
import io.nop.http.api.contenttype.ContentType;
import io.nop.http.api.server.IHttpServerContext;
import io.nop.http.api.server.IHttpServerFilter;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.crazydan.duzhou.framework.gateway.core.utils.WebStaticResourcesHelper.GZIP_SUFFIX;

/**
 * HTTP 请求过滤器
 * <p/>
 * 对匹配请求地址的站点，返回站点的入口 HTML 内容
 * <p/>
 * 其实例在 /duzhou/web/beans/default.beans.xml 中注册
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-14
 */
public class WebSiteHttpServerFilter implements IHttpServerFilter {
    private static final Logger log = LoggerFactory.getLogger(WebSiteHttpServerFilter.class);

    // Note: @Inject 仅对可见性在 private 之外的属性有效
    @Inject
    protected WebSiteProvider provider;

    @Override
    public int order() {
        return GatewayConstants.PRIORITY_WEB_SITE_FILTER;
    }

    @Override
    public CompletionStage<Void> filterAsync(IHttpServerContext context, Supplier<CompletionStage<Void>> next) {
        // 耗时的操作不能在 IO 线程上执行
        return context.executeBlocking(() -> doFilter(context, next)) //
                      .exceptionally((e) -> handleError(context, e)) //
                      .thenApply(r -> null);
    }

    private CompletionStage<Void> doFilter(IHttpServerContext context, Supplier<CompletionStage<Void>> next) {
        String path = context.getRequestPath().replaceAll("/+$", "");

        // 忽略 Quarkus 内置的开发、监控等服务请求
        // TODO 根据运行环境（从配置读取）确定可以开放的服务
        if (path.startsWith("/q/")
            // 忽略后台 API 服务接口
            || path.startsWith("/r/") || path.startsWith("/p/") || path.equals("/graphql")) {
            return next.get();
        }

        String html = getSiteHtml(path);
        // 无匹配的站点，继续后续的路由，
        // 如，静态资源路由（由 QuarkusStaticResources 处理）等
        if (html == null) {
            return next.get();
        }

        // 返回站点的入口页面
        context.setResponseContentType(ContentType.TEXT_HTML.getMimeType());
        context.sendResponse(HttpStatus.SC_OK, html);

        return null;
    }

    private String getSiteHtml(String path) {
        String html = this.provider.getSiteHtmlByRequestPath(path);

        // 匹配尾部含 / 的请求
        if (html == null) {
            html = this.provider.getSiteHtmlByRequestPath(path + "/");
        }

        // 无匹配的站点，且不是请求的静态资源，则返回默认站点页面
        if (html == null //
            && !WebStaticResourcesHelper.isFile(path) //
            && !WebStaticResourcesHelper.isFile(path + GZIP_SUFFIX)) {
            // TODO 内置默认页面
            html = this.provider.getSiteHtmlByRequestPath("*");
        }

        return html;
    }

    private CompletionStage<Void> handleError(IHttpServerContext context, Throwable e) {
        context.sendResponse(500, "Server Error");

        log.error("duzhou.error.web.gen-site-html-fail:path={}", context.getRequestPath(), e);

        return null;
    }
}
