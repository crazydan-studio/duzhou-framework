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

import java.util.Map;

import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;
import static io.crazydan.duzhou.framework.commons.StringHelper.trimAndParseInt;

/**
 * 布局跨越量
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
public class XuiLayoutSpan implements ISourceLocationGetter, IJsonSerializable {
    private final SourceLocation loc;

    /** 水平方向上可跨越的数量 */
    public final XuiExpression<Integer> row;
    /** 垂直方向上可跨越的数量 */
    public final XuiExpression<Integer> col;

    XuiLayoutSpan(SourceLocation loc, XuiExpression<Integer> row, XuiExpression<Integer> col) {
        this.loc = loc;
        this.row = row;
        this.col = col;
    }

    /**  */
    public static XuiLayoutSpan create(ValueWithLocation vl) {
        if (vl == null) {
            return null;
        }

        SourceLocation loc = vl.getLocation();
        Object value = vl.getValue();
        if (value instanceof String || value == null) {
            XuiExpression<Integer> val = expr(vl);

            return new XuiLayoutSpan(loc, val, val);
        }

        assert value instanceof Map;
        // Note: ValueWithLocation 中的位置为值的开始位置
        Map<String, ValueWithLocation> props = (Map<String, ValueWithLocation>) value;

        XuiExpression<Integer> row = expr(props.get("row"));
        XuiExpression<Integer> col = expr(props.get("col"));

        return new XuiLayoutSpan(loc, row, col);
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }

    /**
     * 转换为 xml 属性的对象表达式，
     * 如，<code>{ {row: 2} }</code> 或 <code>{ {row: props.span} }</code>
     */
    public String toXmlAttrExpr(String exprPrefix, String exprSuffix) {
        StringBuilder sb = new StringBuilder();

        Object row = this.row != null ? this.row.getVariable() : null;
        Object col = this.col != null ? this.col.getVariable() : null;

        ifNotNull(row, (v) -> sb.append("row:").append(v).append(","));
        ifNotNull(col, (v) -> sb.append("col:").append(v).append(","));

        return sb.length() > 0 ? exprPrefix + '{' + sb + '}' + exprSuffix : null;
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.beginObject(null);

        out.putNotNull("loc", this.loc);
        out.putNotNull("row", this.row);
        out.putNotNull("col", this.col);

        out.endObject();
    }

    private static XuiExpression<Integer> expr(ValueWithLocation vl) {
        return XuiExpression.create(Integer.class, vl, (loc, v) -> trimAndParseInt(v, 10));
    }
}
