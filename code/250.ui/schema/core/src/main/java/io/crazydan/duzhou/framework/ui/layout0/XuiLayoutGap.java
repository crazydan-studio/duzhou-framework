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

package io.crazydan.duzhou.framework.ui.layout0;

import java.util.Map;

import io.crazydan.duzhou.framework.lang.MappableCodeSnippet;
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.XuiExpression;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局间隔
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
@DataBean
public class XuiLayoutGap implements MappableCodeSnippet {
    private final SourceLocation loc;

    /** 水平方向上的间隔 */
    public final XuiExpression<XuiSize> row;
    /** 垂直方向上的间隔 */
    public final XuiExpression<XuiSize> col;

    XuiLayoutGap(SourceLocation loc, XuiExpression<XuiSize> row, XuiExpression<XuiSize> col) {
        this.loc = loc;
        this.row = row;
        this.col = col;
    }

    /**  */
    public static XuiLayoutGap create(ValueWithLocation vl) {
        if (vl == null) {
            return null;
        }

        SourceLocation loc = vl.getLocation();
        Object value = vl.getValue();
        if (value instanceof String || value == null) {
            XuiExpression<XuiSize> size = XuiSize.expr(vl);

            return new XuiLayoutGap(loc, size, size);
        }

        assert value instanceof Map;
        // Note: ValueWithLocation 中的位置为值的开始位置
        Map<String, ValueWithLocation> props = (Map<String, ValueWithLocation>) value;

        XuiExpression<XuiSize> row = XuiSize.expr(props.get("row"));
        XuiExpression<XuiSize> col = XuiSize.expr(props.get("col"));

        return new XuiLayoutGap(loc, row, col);
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }

    @Override
    public void toMap(Map<String, Object> map) {
        ifNotNull(this.row, (v) -> map.put("row", v));
        ifNotNull(this.col, (v) -> map.put("col", v));
    }
}
