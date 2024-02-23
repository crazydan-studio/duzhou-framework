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
import java.util.Map;

import io.crazydan.duzhou.framework.starter.QuarkusConstants;
import io.nop.api.core.ioc.BeanContainer;
import io.nop.api.core.util.OrderedComparator;
import io.nop.commons.util.CollectionHelper;
import io.nop.core.initialize.CoreInitialization;
import io.nop.http.api.server.HttpServerHelper;
import io.nop.http.api.server.IHttpServerFilter;
import io.quarkus.vertx.http.runtime.filters.Filters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

/**
 * 注册 Nop {@link IHttpServerFilter} 过滤器，
 * 并在 Quarkus 过滤链的最靠前的位置做过滤处理
 */
@ApplicationScoped
public class HttpServerFilterRegistrar {
    private List<IHttpServerFilter> filters;

    public void setupFilter(@Observes Filters filters) {
        // 此时 Nop 平台还没有初始化，需在首次访问时再获取过滤器
        filters.register((rc) -> {
            List<IHttpServerFilter> serverFilters = getFilters();

            if (CollectionHelper.isEmpty(serverFilters)) {
                rc.next();
            } else {
                VertxHttpServerContext ctx = new VertxHttpServerContext(rc);
                HttpServerHelper.runWithFilters(serverFilters, ctx, ctx::proceedAsync);
            }
        }, QuarkusConstants.PRIORITY_APP_FILTER);
    }

    public synchronized List<IHttpServerFilter> getFilters() {
        // Nop 未初始化完毕，则直接返回 null
        if (!CoreInitialization.isInitialized()) {
            return null;
        }
        // Nop 初始完毕后，则直接从 Bean 容器查找，且无论是否存在都不再尝试查找
        if (this.filters == null) {
            Map<String, IHttpServerFilter> map = BeanContainer.instance().getBeansOfType(IHttpServerFilter.class);

            this.filters = new ArrayList<>(map.values());
            this.filters.sort(OrderedComparator.instance());
        }
        return this.filters;
    }
}
