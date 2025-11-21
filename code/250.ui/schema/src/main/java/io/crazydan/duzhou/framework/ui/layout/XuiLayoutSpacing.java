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

package io.crazydan.duzhou.framework.ui.layout;

import java.util.Map;

import io.crazydan.duzhou.framework.lang.MappableCodeSnippet;
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.XuiExpression;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.firstNonNull;
import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局空白
 * <p/>
 * 用于内边距、外边距的配置
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-10
 */
@DataBean
public class XuiLayoutSpacing implements MappableCodeSnippet {
    private final SourceLocation loc;

    public final XuiExpression<XuiSize> left;
    public final XuiExpression<XuiSize> right;
    public final XuiExpression<XuiSize> top;
    public final XuiExpression<XuiSize> bottom;

    XuiLayoutSpacing(
            SourceLocation loc, //
            XuiExpression<XuiSize> left, XuiExpression<XuiSize> right, //
            XuiExpression<XuiSize> top, XuiExpression<XuiSize> bottom
    ) {
        this.loc = loc;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**  */
    public static XuiLayoutSpacing create(ValueWithLocation vl) {
        if (vl == null) {
            return null;
        }

        SourceLocation loc = vl.getLocation();
        Object value = vl.getValue();
        if (value instanceof String || value == null) {
            XuiExpression<XuiSize> size = XuiSize.expr(vl);

            return new XuiLayoutSpacing(loc, size, size, size, size);
        }

        assert value instanceof Map;
        // Note: ValueWithLocation 中的位置为值的开始位置
        Map<String, ValueWithLocation> props = (Map<String, ValueWithLocation>) value;

        XuiExpression<XuiSize> row = XuiSize.expr(props.get("row"));
        XuiExpression<XuiSize> left = firstNonNull(XuiSize.expr(props.get("left")), row);
        XuiExpression<XuiSize> right = firstNonNull(XuiSize.expr(props.get("right")), row);

        XuiExpression<XuiSize> col = XuiSize.expr(props.get("col"));
        XuiExpression<XuiSize> top = firstNonNull(XuiSize.expr(props.get("top")), col);
        XuiExpression<XuiSize> bottom = firstNonNull(XuiSize.expr(props.get("bottom")), col);

        return new XuiLayoutSpacing(loc, left, right, top, bottom);
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }

    @Override
    public void toMap(Map<String, Object> map) {
        ifNotNull(this.left, (v) -> map.put("left", v));
        ifNotNull(this.right, (v) -> map.put("right", v));
        ifNotNull(this.top, (v) -> map.put("top", v));
        ifNotNull(this.bottom, (v) -> map.put("bottom", v));
    }
}
