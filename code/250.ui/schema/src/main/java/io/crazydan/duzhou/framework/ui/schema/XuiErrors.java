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

import static io.crazydan.duzhou.framework.commons.TextScannerHelper.ARG_LEFT_PAIR;
import static io.crazydan.duzhou.framework.commons.TextScannerHelper.ARG_RIGHT_PAIR;
import static io.nop.api.core.exceptions.ErrorCode.define;
import static io.nop.xlang.XLangErrors.ARG_NAMES;
import static io.nop.xlang.XLangErrors.ARG_VALUE;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-09
 */
public interface XuiErrors {
    ErrorCode ERR_DOMAIN_TYPE_UNKNOWN_SIZE = define("duzhou.err.xui.domain-type.unknown-size",
                                                    "未识别的尺寸数据 [{"
                                                    + ARG_VALUE
                                                    + "}]，仅 [{"
                                                    + ARG_NAMES
                                                    + "}] 才是有效的尺寸单位",
                                                    ARG_VALUE,
                                                    ARG_NAMES);

    ErrorCode ERR_LAYOUT_LINEAR_UNKNOWN_LINEAR_MODE = define("duzhou.err.xui.layout-linear.unknown-linear-mode",
                                                             "未知的线性布局模式 [{" + ARG_VALUE + "}]",
                                                             ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_UNKNOWN_MARK = define("duzhou.err.xui.layout-linear.unknown-mark",
                                                      "未知的标记符 '{" + ARG_VALUE + "}'",
                                                      ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_NOT_ALLOW_SPACES_AFTER_ALIGN_MARK = define(
            "duzhou.err.xui.layout-linear.not-allow-spaces-after-align-mark",
            "在对齐方向标记符（<>^v）之后不能有空白字符");
    ErrorCode ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK = define("duzhou.err.xui.layout-linear.duplicated-align-mark",
                                                               "存在重复的对齐标记符 '{" + ARG_VALUE + "}'",
                                                               ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK = define(
            "duzhou.err.xui.layout-linear.no-right-mark-for-left-mark",
            "没有为配对的左标记符 '{" + ARG_LEFT_PAIR + "}' 指定对应的右标记符 '{" + ARG_RIGHT_PAIR + "}'");
    ErrorCode ERR_LAYOUT_LINEAR_NO_END_MARK_FOR_TABLE_CELL = define(
            "duzhou.err.xui.layout-linear.no-end-mark-for-table-cell",
            "没有为表格单元格指定相应的结束标记符 '|'");
    ErrorCode ERR_LAYOUT_LINEAR_NO_PROP_VALUE_SPECIFIED = define("duzhou.err.xui.layout-linear.no-prop-value-specified",
                                                                 "没有为配置参数 '{" + ARG_VALUE + "}' 指定值");
}
