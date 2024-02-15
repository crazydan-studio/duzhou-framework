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
package io.crazydan.duzhou.framework.starter.filter;

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.starter.QuarkusConstants;
import io.nop.api.core.ioc.BeanContainer;
import io.nop.api.core.util.OrderedComparator;
import io.nop.http.api.server.HttpServerHelper;
import io.nop.http.api.server.IHttpServerFilter;
import io.quarkus.vertx.http.runtime.filters.Filters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class HttpServerFilterRegistrar {
    private List<IHttpServerFilter> filters;

    public synchronized List<IHttpServerFilter> getFilters() {
        if (this.filters == null) {
            this.filters = new ArrayList<>(BeanContainer.instance().getBeansOfType(IHttpServerFilter.class).values());
            this.filters.sort(OrderedComparator.instance());
        }
        return this.filters;
    }

    public void setupFilter(@Observes Filters filters) {
        // 此时 Nop 平台还没有初始化，需在首次访问时再获取过滤器
        filters.register((rc) -> {
            List<IHttpServerFilter> serverFilters = getFilters();

            if (serverFilters.isEmpty()) {
                rc.next();
            } else {
                VertxHttpServerContext ctx = new VertxHttpServerContext(rc);
                HttpServerHelper.runWithFilters(serverFilters, ctx, ctx::proceedAsync);
            }
        }, QuarkusConstants.PRIORITY_APP_FILTER);
    }
}
