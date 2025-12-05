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

package io.crazydan.duzhou.framework.ui.vendor.jexer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 组件注册器
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-25
 */
public class JexerComponentRegistry {
    private static final Map<String, JexerComponent.Creator> components = new ConcurrentHashMap<>();

    public static Runnable register(String name, JexerComponent.Creator creator) {
        components.put(name, creator);

        return () -> components.remove(name, creator);
    }

    public static JexerComponent.Creator get(String name) {
        return components.get(name);
    }
}
