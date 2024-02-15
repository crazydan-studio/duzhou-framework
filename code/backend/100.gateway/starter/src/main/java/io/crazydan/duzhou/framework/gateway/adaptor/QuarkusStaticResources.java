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

package io.crazydan.duzhou.framework.gateway.adaptor;

import io.crazydan.duzhou.framework.gateway.core.GatewayConfigs;
import io.crazydan.duzhou.framework.gateway.web.utils.WebSiteResourceHelper;
import io.nop.core.resource.IResource;
import io.nop.core.resource.ResourceConstants;
import io.nop.core.resource.ResourceHelper;
import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FileSystemAccess;
import io.vertx.ext.web.handler.StaticHandler;
import jakarta.enterprise.event.Observes;

/**
 * 基于 Quarkus 的静态资源路由控制
 * <p/>
 * 由 Quarkus 扫描并加载。确保支持可读取 classpath 和系统文件下的静态资源
 * <p/>
 * 注: Nop 未在过滤器中提供向客户端返回文件流的接口，故而，
 * 只能在各个启动器中为不同的服务实现提供静态资源的路由接口
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-15
 */
public class QuarkusStaticResources {
    private StaticHandler handler;

    public void route(@Observes StartupEvent event, Router router) {
        router.route().handler(context -> {
            String path = context.request().path();

            IResource resource = WebSiteResourceHelper.getResource(path);
            if (resource != null && resource.exists() && !resource.isDirectory()) {
                getHandler().handle(context);
                return;
            }

            context.next();
        });
    }

    public StaticHandler getHandler() {
        // Note：需要延迟到首次请求时，再创建 StaticHandler，
        // 以确保能够获取到最终的静态资源根路径
        if (this.handler == null) {
            // https://quarkus.io/guides/http-reference#from-a-local-directory
            String path = GatewayConfigs.WEB_STATIC_RESOURCES_PATH.get();
            String ns = ResourceHelper.getPathNamespace(path);
            if (ns == null) {
                return null;
            }

            String root = ResourceHelper.removeNamespace(path, ns);
            if (ResourceConstants.FILE_NS.equals(ns)) {
                this.handler = StaticHandler.create(FileSystemAccess.ROOT, root);
            } else if (ResourceConstants.CLASSPATH_NS.equals(ns)) {
                this.handler = StaticHandler.create(FileSystemAccess.RELATIVE, root);
            } else {
                return null;
            }
        }

        return this.handler;
    }
}
