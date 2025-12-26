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

import java.util.stream.Collectors;

import io.nop.api.core.beans.ErrorBean;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.exceptions.NopRebuildException;
import io.nop.api.core.validate.ListValidationErrorCollector;
import io.nop.core.exceptions.ErrorMessageManager;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_ERRORS_COLLECTED;
import static io.nop.xlang.XLangErrors.ARG_DETAIL;

/**
 * 收集错误信息，以便于一次性{@link #throwErrors() 抛出}
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-12-25
 */
public class XuiErrorCollector extends ListValidationErrorCollector {

    @Override
    public void addException(Throwable e) {
        ErrorBean error = ErrorMessageManager.instance().buildErrorMessage(null, e, false, false, false);
        addError(error);
    }

    public void throwErrors() {
        if (isEmpty()) {
            return;
        } else if (getErrors().size() == 1) {
            throw NopRebuildException.rebuild(getErrors().get(0));
        }

        String msg = "0. " + getErrors().stream()
                                        .map(NopRebuildException::rebuild)
                                        .map(NopException::getMessage)
                                        .collect(Collectors.joining("\n0. "));
        throw new NopException(ERR_ERRORS_COLLECTED).param(ARG_DETAIL, msg);
    }
}
