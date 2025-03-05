package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiStyleThemes;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/themes.xdef <p>
 * - 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * - Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
 * -
 * - This program is free software: you can redistribute it and/or modify
 * - it under the terms of the GNU Lesser General Public License as published by
 * - the Free Software Foundation, either version 3 of the License, or
 * - (at your option) any later version.
 * -
 * - This program is distributed in the hope that it will be useful,
 * - but WITHOUT ANY WARRANTY; without even the implied warranty of
 * - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * - GNU Lesser General Public License for more details.
 * -
 * - You should have received a copy of the GNU Lesser General Public License
 * - along with this program.
 * - If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 * 主题定义
 * - 定义一组样式中可引用的变量，通过同一变量的不同值实现不同的主题
 * - 如何管理和切换主题，则由运行时构建器处理
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleThemes extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme> _themes = KeyedList.emptyList();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme> getThemes(){
      return _themes;
    }

    
    public void setThemes(java.util.List<io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme> value){
        checkAllowChange();
        
        this._themes = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme::getName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme getTheme(String name){
        return this._themes.getByKey(name);
    }

    public boolean hasTheme(String name){
        return this._themes.containsKey(name);
    }

    public void addTheme(io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme> list = this.getThemes();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.XuiStyleTheme::getName);
            setThemes(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_themes(){
        return this._themes.keySet();
    }

    public boolean hasThemes(){
        return !this._themes.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._themes = io.nop.api.core.util.FreezeHelper.deepFreeze(this._themes);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("themes",this.getThemes());
    }

    public XuiStyleThemes cloneInstance(){
        XuiStyleThemes instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleThemes instance){
        super.copyTo(instance);
        
        instance.setThemes(this.getThemes());
    }

    protected XuiStyleThemes newInstance(){
        return (XuiStyleThemes) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
