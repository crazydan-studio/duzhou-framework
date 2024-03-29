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

package io.crazydan.duzhou.framework.gateway.web.adaptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import io.crazydan.duzhou.framework.gateway.core.GatewayConfigs;
import io.crazydan.duzhou.framework.gateway.core.utils.WebStaticResourcesHelper;
import io.nop.commons.util.StringHelper;
import io.nop.core.resource.ResourceConstants;
import io.nop.core.resource.ResourceHelper;
import io.quarkus.vertx.http.runtime.HttpBuildTimeConfig;
import io.quarkus.vertx.http.runtime.HttpCompressionHandler;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.MimeMapping;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.FileSystemAccess;
import io.vertx.ext.web.handler.StaticHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import static io.crazydan.duzhou.framework.gateway.core.utils.WebStaticResourcesHelper.GZIP_SUFFIX;

/**
 * 基于 Quarkus 的静态资源路由器
 * <p/>
 * 由 Quarkus 扫描并加载。确保支持可读取 classpath 和系统文件下的静态资源，
 * 同时保证该路由处理在过滤器链的尾部
 * <p/>
 * 注: Nop 未在过滤器中提供向客户端返回文件流的接口，故而，
 * 只能在各个启动器中为不同的服务实现提供静态资源的路由接口
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-15
 */
@ApplicationScoped
public class QuarkusStaticResourceRouter {
    private Handler<RoutingContext> handler;

    public void setupRouter(@Observes Router router, HttpBuildTimeConfig httpBuildTimeConfig) {
        router.route().handler(rc -> {
            // Note: 在过滤器 WebSiteHttpServerFilter 中已对不存在的静态资源做了处理，
            // 仅已存在的静态资源的请求才会路由到这里
            getHandler(httpBuildTimeConfig).handle(rc);
        });
    }

    /** 延迟构建 Handler，以确保能够获取到最终的静态资源根路径 */
    public Handler<RoutingContext> getHandler(HttpBuildTimeConfig httpBuildTimeConfig) {
        if (this.handler == null) {
            // https://quarkus.io/guides/http-reference#from-a-local-directory
            String path = GatewayConfigs.WEB_STATIC_RESOURCES_PATH.get();
            String ns = ResourceHelper.getPathNamespace(path);

            // Note：由上层过滤器确保静态资源配置的有效性
            String root = ResourceHelper.removeNamespace(path, ns);

            if (ResourceConstants.FILE_NS.equals(ns)) {
                this.handler = StaticHandler.create(FileSystemAccess.ROOT, root);
            } else if (ResourceConstants.CLASSPATH_NS.equals(ns)) {
                this.handler = StaticHandler.create(FileSystemAccess.RELATIVE, root);
            }

            // 根据配置启用压缩：本质就是判断是否需要移除值为 HttpHeaders.IDENTITY
            // 的响应头 HttpHeaders.CONTENT_ENCODING，若移除，则会对其 body 进行压缩
            if (httpBuildTimeConfig.enableCompression) {
                Set<String> compressedMediaTypes
                        = new HashSet<>(httpBuildTimeConfig.compressMediaTypes.orElse(new ArrayList<>()));

                this.handler = new HttpCompressionHandler(this.handler, compressedMediaTypes);
            }

            this.handler = new GzipStaticHandler(this.handler);
        }

        return this.handler;
    }

    /** 若请求的资源有已压缩版本，则重路由到其压缩文件 */
    private static class GzipStaticHandler implements Handler<RoutingContext> {
        private final Handler<RoutingContext> routeHandler;

        private GzipStaticHandler(Handler<RoutingContext> routeHandler) {
            this.routeHandler = routeHandler;
        }

        @Override
        public void handle(RoutingContext rc) {
            String path = rc.normalizedPath();

            // 代码改进自 io.nop.quarkus.web.filter.ZipContentEncodingFilterRegistrar
            // 若请求资源存在压缩版本，则重新路由到其压缩文件
            if (WebStaticResourcesHelper.isFile(path + GZIP_SUFFIX)) {
                rc.reroute(path + GZIP_SUFFIX);
                return;
            }

            // 设置压缩资源的响应头
            if (path.endsWith(GZIP_SUFFIX)) {
                String fileName = StringHelper.fileFullName(path);
                fileName = StringHelper.removeTail(fileName, GZIP_SUFFIX);

                HttpServerResponse response = rc.response();
                response.putHeader(HttpHeaders.CONTENT_ENCODING, "gzip");

                String contentType = MimeMapping.getMimeTypeForFilename(fileName);
                if (contentType != null) {
                    if (contentType.startsWith("text")) {
                        contentType = contentType + ";charset=UTF-8";
                    }
                    response.putHeader(HttpHeaders.CONTENT_TYPE, contentType);
                }
            }

            this.routeHandler.handle(rc);
        }
    }
}
