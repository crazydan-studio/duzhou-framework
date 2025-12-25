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

package io.crazydan.duzhou.framework.ui;

import java.util.ArrayList;
import java.util.List;

import io.nop.api.core.beans.ErrorBean;
import io.nop.api.core.exceptions.ErrorCode;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.validate.IValidationErrorCollector;
import jakarta.ws.rs.NotSupportedException;

/**
 * 收集 {@link NopException} 以便于格式化 {@link ErrorCode} 信息
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-12-25
 */
public class XuiErrorCollector implements IValidationErrorCollector {
    private final List<Throwable> errors = new ArrayList<>();

    @Override
    public void addError(ErrorBean error) {
        throw new NotSupportedException("Use addException() instead of!");
    }

    @Override
    public void addException(Throwable e) {
        this.errors.add(e);
    }

    public void throwErrors() {
        if (this.errors.isEmpty()) {
            return;
        }
        // TODO 抛出收集到的异常信息
    }
}
