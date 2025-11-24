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

package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiStyleDef;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/styles.xdef <p>
 * 样式定义：定义其组成结构和可配置参数
 * - 原子样式：其结构不可拆分，因此，不含子节点
 * - 复合样式：由至少一个原子样式或其他复合样式组成，其必然包含子节点
 * 样式的配置参数将赋值给其组成样式或者传递给运行时原生样式，用于样式的动态控制
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleDef extends io.nop.core.resource.component.AbstractComponentModel {

    /**
     *
     * xml name:
     *
     */
    private java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyle> _styles = java.util.Collections.emptyMap();

    /**
     *
     * xml name:
     *
     */

    public java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyle> getStyles(){
      return _styles;
    }


    public void setStyles(java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyle> value){
        checkAllowChange();

        this._styles = value;

    }


    public io.crazydan.duzhou.framework.ui.schema.XuiStyle getStyle(String name){
        return this._styles.get(name);
    }

    public boolean hasStyle(String name){
        return this._styles.containsKey(name);
    }

    public boolean hasStyles(){
        return this._styles != null && !this._styles.isEmpty();
    }


    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code

           this._styles = io.nop.api.core.util.FreezeHelper.deepFreeze(this._styles);

        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);

        out.putNotNull("styles",this.getStyles());
    }

    public XuiStyleDef cloneInstance(){
        XuiStyleDef instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleDef instance){
        super.copyTo(instance);

        instance.setStyles(this.getStyles());
    }

    protected XuiStyleDef newInstance(){
        return (XuiStyleDef) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
