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
import java.util.function.Function;

import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

import static io.crazydan.duzhou.framework.commons.ObjectHelper.ifNotNull;

/**
 * 布局间隔
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-14
 */
@DataBean
public class XuiLayoutGap implements ISourceLocationGetter, IJsonSerializable {
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

    /**
     * 转换为 xml 属性的对象表达式，
     * 如，<code>{ {row: 'start'} }</code>
     */
    public String toXmlAttrExpr(String exprPrefix, String exprSuffix, Function<XuiSize, Object> sizeConverter) {
        StringBuilder sb = new StringBuilder();

        String row = XuiSize.toXmlAttrExpr(this.row, sizeConverter);
        String col = XuiSize.toXmlAttrExpr(this.col, sizeConverter);
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
}
