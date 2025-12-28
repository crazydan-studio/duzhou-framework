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

package io.crazydan.duzhou.framework.ui.statement;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.ui.domain.type.XuiExpr;
import io.nop.api.core.util.SourceLocation;
import io.nop.api.core.validate.IValidationErrorCollector;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_VAR;
import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_FOR;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_FOR_AT_LEAST_ITEMS_OR_BEGIN_END_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_FOR_BEGIN_END_SHOULD_BE_PAIRED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_FOR_ONLY_ITEMS_OR_BEGIN_END_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_TAG_ATTR_REQUIRED;
import static io.nop.xlang.XLangErrors.ARG_ATTR_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-12-28
 */
public interface XuiStatementFor {

    String getVar();

    XuiExpr getItems();

    XuiExpr getBegin();

    XuiExpr getEnd();

    default void validate(SourceLocation loc, IValidationErrorCollector collector) {
        if (StringHelper.isBlank(getVar())) {
            collector.buildError(ERR_TAG_ATTR_REQUIRED)
                     .loc(loc)
                     .param(ARG_TAG_NAME, TAG_NAME_FOR)
                     .param(ARG_ATTR_NAME, ATTR_NAME_VAR)
                     .addToCollector(collector);
        }
        if (getItems() != null) {
            if (getBegin() != null || getEnd() != null) {
                collector.buildError(ERR_FOR_ONLY_ITEMS_OR_BEGIN_END_ALLOWED) //
                         .loc(loc).addToCollector(collector);
            }
        } else {
            if (getBegin() == null && getEnd() == null) {
                collector.buildError(ERR_FOR_AT_LEAST_ITEMS_OR_BEGIN_END_ALLOWED) //
                         .loc(loc).addToCollector(collector);
            } else if (getBegin() == null || getEnd() == null) {
                collector.buildError(ERR_FOR_BEGIN_END_SHOULD_BE_PAIRED) //
                         .loc(loc).addToCollector(collector);
            }
        }
    }
}
