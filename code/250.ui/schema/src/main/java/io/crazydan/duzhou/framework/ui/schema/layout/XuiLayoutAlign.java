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

package io.crazydan.duzhou.framework.ui.schema.layout;

import java.util.HashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.lang.CodeSnippet;
import io.crazydan.duzhou.framework.lang.MappableCodeSnippet;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.SourceLocation;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局对齐方式
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-26
 */
@DataBean
public class XuiLayoutAlign implements MappableCodeSnippet {
    /** 缓存枚举组合，以避免重复构建相同对象 */
    private static final Map<String, XuiLayoutAlign> aligns = new HashMap<>(9);

    /** 对齐方向 */
    public enum Direction implements CodeSnippet {
        /** 向起始位置对齐 */
        start,
        /** 向中心位置对齐 */
        center,
        /** 向终止位置对齐 */
        end,
        ;

        @Override
        public String toCodeSnippet(char strQuote) {
            return strQuote + name() + strQuote;
        }
    }

    /** 水平方向上的对齐方向 */
    public final Direction row;
    /** 垂直方向上的对齐方向 */
    public final Direction col;

    XuiLayoutAlign(Direction row, Direction col) {
        this.row = row;
        this.col = col;
    }

    public static XuiLayoutAlign create(Direction row, Direction col) {
        return aligns.computeIfAbsent(row + "_" + col, (key) -> new XuiLayoutAlign(row, col));
    }

    public XuiLayoutAlign row(Direction row) {
        return create(row, this.col);
    }

    public XuiLayoutAlign col(Direction col) {
        return create(this.row, col);
    }

    @Override
    public SourceLocation getLocation() {
        return null;
    }

    @Override
    public void toMap(Map<String, Object> map) {
        ifNotNull(this.row, (v) -> map.put("row", v));
        ifNotNull(this.col, (v) -> map.put("col", v));
    }
}
