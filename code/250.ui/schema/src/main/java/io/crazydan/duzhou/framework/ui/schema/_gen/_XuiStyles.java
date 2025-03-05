package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiStyles;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/styles.xdef <p>
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
 * 样式定义集
 * - 仅用于声明样式的组成结构，并通过属性支持样式的动态设置
 * - 通过差量机制，在 UI 设计层面支持样式复用、合并、局部修改等设计需求
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyles extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyleDef> _defs = java.util.Collections.emptyMap();
    
    /**
     *  
     * xml name: themes-path
     * 
     */
    private java.lang.String _themesPath ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyleDef> getDefs(){
      return _defs;
    }

    
    public void setDefs(java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyleDef> value){
        checkAllowChange();
        
        this._defs = value;
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.XuiStyleDef getDef(String name){
        return this._defs.get(name);
    }

    public boolean hasDef(String name){
        return this._defs.containsKey(name);
    }
    
    public boolean hasDefs(){
        return this._defs != null && !this._defs.isEmpty();
    }
    
    /**
     * 
     * xml name: themes-path
     *  
     */
    
    public java.lang.String getThemesPath(){
      return _themesPath;
    }

    
    public void setThemesPath(java.lang.String value){
        checkAllowChange();
        
        this._themesPath = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._defs = io.nop.api.core.util.FreezeHelper.deepFreeze(this._defs);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("defs",this.getDefs());
        out.putNotNull("themesPath",this.getThemesPath());
    }

    public XuiStyles cloneInstance(){
        XuiStyles instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyles instance){
        super.copyTo(instance);
        
        instance.setDefs(this.getDefs());
        instance.setThemesPath(this.getThemesPath());
    }

    protected XuiStyles newInstance(){
        return (XuiStyles) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
