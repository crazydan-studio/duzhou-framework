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
import io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/themes.xdef <p>
 * 主题变量分组
 * - 引用变量时，均采用 `@var:{groupName}/{varName}` 形式
 * - 引用变量的类型必须与关联数据的类型一致
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleVarGroup extends io.nop.core.resource.component.AbstractComponentModel {

    /**
     *  必填
     * xml name: name
     * 分组标识
     */
    private java.lang.String _name ;

    /**
     *
     * xml name:
     *
     */
    private java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyleVar> _vars = java.util.Collections.emptyMap();

    /**
     * 必填
     * xml name: name
     *  分组标识
     */

    public java.lang.String getName(){
      return _name;
    }


    public void setName(java.lang.String value){
        checkAllowChange();

        this._name = value;

    }


    /**
     *
     * xml name:
     *
     */

    public java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyleVar> getVars(){
      return _vars;
    }


    public void setVars(java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyleVar> value){
        checkAllowChange();

        this._vars = value;

    }


    public io.crazydan.duzhou.framework.ui.schema.XuiStyleVar getVar(String name){
        return this._vars.get(name);
    }

    public boolean hasVar(String name){
        return this._vars.containsKey(name);
    }

    public boolean hasVars(){
        return this._vars != null && !this._vars.isEmpty();
    }


    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code

           this._vars = io.nop.api.core.util.FreezeHelper.deepFreeze(this._vars);

        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);

        out.putNotNull("name",this.getName());
        out.putNotNull("vars",this.getVars());
    }

    public XuiStyleVarGroup cloneInstance(){
        XuiStyleVarGroup instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleVarGroup instance){
        super.copyTo(instance);

        instance.setName(this.getName());
        instance.setVars(this.getVars());
    }

    protected XuiStyleVarGroup newInstance(){
        return (XuiStyleVarGroup) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
