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
import io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/themes.xdef <p>
 * 主题定义
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleTheme extends io.nop.core.resource.component.AbstractComponentModel {

    /**
     *  必填
     * xml name: name
     * 主题标识
     */
    private java.lang.String _name ;

    /**
     *  可选
     * xml name: title
     * 主题名称
     */
    private java.lang.String _title ;

    /**
     *
     * xml name:
     *
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup> _varGroups = KeyedList.emptyList();

    /**
     * 必填
     * xml name: name
     *  主题标识
     */

    public java.lang.String getName(){
      return _name;
    }


    public void setName(java.lang.String value){
        checkAllowChange();

        this._name = value;

    }


    /**
     * 可选
     * xml name: title
     *  主题名称
     */

    public java.lang.String getTitle(){
      return _title;
    }


    public void setTitle(java.lang.String value){
        checkAllowChange();

        this._title = value;

    }


    /**
     *
     * xml name:
     *
     */

    public java.util.List<io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup> getVarGroups(){
      return _varGroups;
    }


    public void setVarGroups(java.util.List<io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup> value){
        checkAllowChange();

        this._varGroups = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup::getName);

    }


    public io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup getVarGroup(String name){
        return this._varGroups.getByKey(name);
    }

    public boolean hasVarGroup(String name){
        return this._varGroups.containsKey(name);
    }

    public void addVarGroup(io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup> list = this.getVarGroups();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.XuiStyleVarGroup::getName);
            setVarGroups(list);
        }
        list.add(item);
    }

    public java.util.Set<String> keySet_varGroups(){
        return this._varGroups.keySet();
    }

    public boolean hasVarGroups(){
        return !this._varGroups.isEmpty();
    }


    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code

           this._varGroups = io.nop.api.core.util.FreezeHelper.deepFreeze(this._varGroups);

        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);

        out.putNotNull("name",this.getName());
        out.putNotNull("title",this.getTitle());
        out.putNotNull("varGroups",this.getVarGroups());
    }

    public XuiStyleTheme cloneInstance(){
        XuiStyleTheme instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleTheme instance){
        super.copyTo(instance);

        instance.setName(this.getName());
        instance.setTitle(this.getTitle());
        instance.setVarGroups(this.getVarGroups());
    }

    protected XuiStyleTheme newInstance(){
        return (XuiStyleTheme) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
