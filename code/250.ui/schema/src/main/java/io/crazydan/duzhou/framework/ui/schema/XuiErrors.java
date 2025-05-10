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

package io.crazydan.duzhou.framework.ui.schema;

import io.nop.api.core.exceptions.ErrorCode;

import static io.nop.api.core.exceptions.ErrorCode.define;
import static io.nop.xlang.XLangErrors.ARG_VALUE;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-09
 */
public interface XuiErrors {
    String ARG_LEFT_MARK = "leftMark";
    String ARG_RIGHT_MARK = "rightMark";

    ErrorCode ERR_LAYOUT_LINEAR_UNKNOWN_LINEAR_MODE = define("duzhou.err.xui.layout-linear.unknown-linear-mode",
                                                             "Unknown linear layout mode [{" + ARG_VALUE + "}]",
                                                             ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_UNKNOWN_MARK = define("duzhou.err.xui.layout-linear.unknown-mark",
                                                      "Unknown mark '{" + ARG_VALUE + "}'",
                                                      ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_NOT_ALLOW_SPACES_AFTER_ALIGN_MARK = define(
            "duzhou.err.xui.layout-linear.not-allow-spaces-after-align-mark",
            "No spaces are expected after align mark");
    ErrorCode ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK = define("duzhou.err.xui.layout-linear.duplicated-align-mark",
                                                               "Duplicated align mark '{" + ARG_VALUE + "}'",
                                                               ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK = define(
            "duzhou.err.xui.layout-linear.no-right-mark-for-left-mark",
            "No right mark '{" + ARG_RIGHT_MARK + "}' can be found for the left mark '{" + ARG_LEFT_MARK + "}'");
    ErrorCode ERR_LAYOUT_LINEAR_NO_END_MARK_FOR_TABLE_CELL = define(
            "duzhou.err.xui.layout-linear.no-end-mark-for-table-cell",
            "No end mark '|' is specified for the table cell");
}
