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

import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.schema.XuiExpression;
import io.nop.api.core.annotations.data.DataBean;
import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.core.lang.json.IJsonSerializable;

/**
 * 布局尺寸
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-26
 */
@DataBean
public class XuiLayoutSize implements ISourceLocationGetter, IJsonSerializable {
    private static final XuiLayoutSize match_parent = new XuiLayoutSize(Type.match_parent);
    private static final XuiLayoutSize fill_remains = new XuiLayoutSize(Type.fill_remains);
    private static final XuiLayoutSize wrap_content = new XuiLayoutSize(Type.wrap_content);

    /** {@link XuiLayoutSize} 类型 */
    public enum Type {
        /** 与父容器相同 */
        match_parent,
        /** 占满剩余空间 */
        fill_remains,
        /** 自适应内容 */
        wrap_content,

        /** 设定值 */
        with_specified,
    }

    private final SourceLocation loc;

    /** 类型 */
    public final Type type;
    /** {@link Type#with_specified} 对应的值 */
    public final XuiExpression<XuiSize> value;

    XuiLayoutSize(Type type) {
        this.loc = null;
        this.type = type;
        this.value = null;
    }

    XuiLayoutSize(SourceLocation loc, XuiExpression<XuiSize> value) {
        this.loc = loc;
        this.type = Type.with_specified;
        this.value = value;
    }

    public static XuiLayoutSize match_parent() {
        return match_parent;
    }

    public static XuiLayoutSize fill_remains() {
        return fill_remains;
    }

    public static XuiLayoutSize wrap_content() {
        return wrap_content;
    }

    /**
     * @param vl
     *         其 {@link ValueWithLocation#getValue()} 只能为 {@link String} 类型，
     *         且其可以为 <code>${a.b.c}</code> 形式的动态表达式，也可以为
     *         <code>1x</code>、<code>50%</code> 等形式的尺寸常量，
     *         但 {@link XuiSize#parse} 对其常量的解析结果不能为 <code>null</code>
     */
    public static XuiLayoutSize with_specified(ValueWithLocation vl) {
        XuiExpression<XuiSize> value = XuiSize.expr(vl);
        assert value != null;

        return new XuiLayoutSize(vl.getLocation(), value);
    }

    @Override
    public SourceLocation getLocation() {
        return this.loc;
    }

    /**
     * 转换为 xml 属性的对象表达式，
     * 如，<code>{ 'match_parent' }</code>、<code>{ '1x' }</code>
     * 或 <code>{ props.width }</code>
     */
    public String toXmlAttrExpr(String exprPrefix, String exprSuffix) {
        Object val = this.type.name();

        if (this.type == Type.with_specified) {
            assert this.value != null;

            val = this.value.getValue();
            if (!(val instanceof XuiSize)) {
                return val != null ? exprPrefix + val + exprSuffix : null;
            }
        }
        return exprPrefix + '\'' + val + '\'' + exprSuffix;
    }

    /** Note: 在无公共的无参构造函数时，必须实现 {@link IJsonSerializable} 接口 */
    @Override
    public void serializeToJson(IJsonHandler out) {
        out.stringValue(null, toString());
    }

    @Override
    public String toString() {
        if (this.type == Type.with_specified) {
            assert this.value != null;
            return this.value.toString();
        }
        return this.type.name();
    }
}
