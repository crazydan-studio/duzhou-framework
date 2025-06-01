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

package io.crazydan.duzhou.framework;

import io.nop.api.core.exceptions.ErrorCode;

import static io.nop.api.core.exceptions.ErrorCode.define;
import static io.nop.xlang.XLangErrors.ARG_ACTUAL_TYPE;
import static io.nop.xlang.XLangErrors.ARG_ALLOWED_NAMES;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-06-01
 */
public interface CommonErrors {
    ErrorCode ERR_CODE_SNIPPET_UNSUPPORTED_DATA_TYPE = define("duzhou.err.commons.code-snippet.unsupported-data-type",
                                                              "不支持转换为代码片段的数据类型 [{"
                                                              + ARG_ACTUAL_TYPE
                                                              + "}]，仅支持类型 [{"
                                                              + ARG_ALLOWED_NAMES
                                                              + "}]",
                                                              ARG_ACTUAL_TYPE,
                                                              ARG_ALLOWED_NAMES);
}
