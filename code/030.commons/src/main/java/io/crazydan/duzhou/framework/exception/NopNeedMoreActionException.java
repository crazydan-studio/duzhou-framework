/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2026 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.exception;

import java.util.Map;

import io.nop.api.core.exceptions.NopException;
import io.nop.core.lang.json.JsonTool;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2026-02-10
 */
public class NopNeedMoreActionException extends NopException {
    public static final String ERROR_CODE = "nop.err.data.need-more-action";

    public NopNeedMoreActionException(String form) {
        super(ERROR_CODE, null, true, true);
        description(form);
    }

    public NopNeedMoreActionException(Map form) {
        this(JsonTool.stringify(form));
    }
}
