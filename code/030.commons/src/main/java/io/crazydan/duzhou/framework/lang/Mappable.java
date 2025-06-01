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

package io.crazydan.duzhou.framework.lang;

import java.util.LinkedHashMap;
import java.util.Map;

import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-06-01
 */
public interface Mappable extends ISourceLocationGetter, IJsonSerializable {

    default Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();

        toMap(map);

        return map;
    }

    void toMap(Map<String, Object> map);

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    default void serializeToJson(IJsonHandler out) {
        out.beginObject(null);

        out.putNotNull("loc", getLocation());

        toMap().forEach(out::putNotNull);

        out.endObject();
    }
}
