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
import io.crazydan.duzhou.framework.ui.schema.XuiStyleVar;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/themes.xdef <p>
 * 变量定义，标签文本内容为变量值
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleVar extends io.nop.core.resource.component.AbstractComponentModel {

    /**
     *  必填
     * xml name: type
     * 变量类型
     */
    private java.lang.String _type ;

    /**
     *
     * xml name:
     *
     */
    private java.lang.String _value ;

    /**
     * 必填
     * xml name: type
     *  变量类型
     */

    public java.lang.String getType(){
      return _type;
    }


    public void setType(java.lang.String value){
        checkAllowChange();

        this._type = value;

    }


    /**
     *
     * xml name:
     *
     */

    public java.lang.String getValue(){
      return _value;
    }


    public void setValue(java.lang.String value){
        checkAllowChange();

        this._value = value;

    }



    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code

        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);

        out.putNotNull("type",this.getType());
        out.putNotNull("value",this.getValue());
    }

    public XuiStyleVar cloneInstance(){
        XuiStyleVar instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleVar instance){
        super.copyTo(instance);

        instance.setType(this.getType());
        instance.setValue(this.getValue());
    }

    protected XuiStyleVar newInstance(){
        return (XuiStyleVar) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
