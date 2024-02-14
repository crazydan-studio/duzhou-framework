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

import io.crazydan.duzhou.framework.schema.web.XWebSite;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.eval.global.IGlobalVariableDefinition;
import io.nop.core.reflect.ReflectionManager;
import io.nop.core.type.IGenericType;
import org.codehaus.commons.compiler.util.Producer;

/**
 * 向 xlang 编译期注册的全局变量 `$site`，
 * 以便于在 site 转 html 的过程中能够引用当前的站点数据
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-08
 */
public class WebSiteGlobalVariable implements IGlobalVariableDefinition {
    private final static ThreadLocal<XWebSite> var = new ThreadLocal<>();

    private final IGenericType type = ReflectionManager.instance().buildGenericClassType(XWebSite.class);

    public static WebSiteGlobalVariable instance() {
        return new WebSiteGlobalVariable();
    }

    public static void set(XWebSite site) {
        var.set(site);
    }

    public static XWebSite get() {
        return var.get();
    }

    public static <T> T with(XWebSite site, Producer<T> cb) {
        XWebSite oldSite = get();

        set(site);
        try {
            return cb.produce();
        } finally {
            set(oldSite);
        }
    }

    private WebSiteGlobalVariable() {
    }

    @Override
    public IGenericType getResolvedType() {
        return this.type;
    }

    @Override
    public Object getValue(IEvalScope scope) {
        return get();
    }
}
