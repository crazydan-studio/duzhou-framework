package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiComponent;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component.xdef <p>
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
 * 组件定义
 * - 公共配置放在基础组件定义中，其余组件通过 x:extends 继承这些公共配置
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponent extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: import
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
     * 组件导入指令定义
     * TODO 限定 as 为驼峰形式（首字母大写、可包含下划线）
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> _imports = KeyedList.emptyList();
    
    /**
     *  
     * xml name: messages
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage> _messages = KeyedList.emptyList();
    
    /**
     *  
     * xml name: props
     * 
     */
    private io.nop.core.lang.xml.XNode _props ;
    
    /**
     *  
     * xml name: state
     * 
     */
    private io.nop.core.lang.xml.XNode _state ;
    
    /**
     *  
     * xml name: template
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
     * 组件模板定义
     */
    private io.crazydan.duzhou.framework.ui.schema.component.XuiComponentTemplate _template ;
    
    /**
     * 
     * xml name: import
     *  - 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
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
     * 组件导入指令定义
     * TODO 限定 as 为驼峰形式（首字母大写、可包含下划线）
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> getImports(){
      return _imports;
    }

    
    public void setImports(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> value){
        checkAllowChange();
        
        this._imports = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport::getAs);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport getImport(String name){
        return this._imports.getByKey(name);
    }

    public boolean hasImport(String name){
        return this._imports.containsKey(name);
    }

    public void addImport(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> list = this.getImports();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport::getAs);
            setImports(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_imports(){
        return this._imports.keySet();
    }

    public boolean hasImports(){
        return !this._imports.isEmpty();
    }
    
    /**
     * 
     * xml name: messages
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage> getMessages(){
      return _messages;
    }

    
    public void setMessages(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage> value){
        checkAllowChange();
        
        this._messages = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage::getName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage getMessage(String name){
        return this._messages.getByKey(name);
    }

    public boolean hasMessage(String name){
        return this._messages.containsKey(name);
    }

    public void addMessage(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage> list = this.getMessages();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage::getName);
            setMessages(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_messages(){
        return this._messages.keySet();
    }

    public boolean hasMessages(){
        return !this._messages.isEmpty();
    }
    
    /**
     * 
     * xml name: props
     *  
     */
    
    public io.nop.core.lang.xml.XNode getProps(){
      return _props;
    }

    
    public void setProps(io.nop.core.lang.xml.XNode value){
        checkAllowChange();
        
        this._props = value;
           
    }

    
    /**
     * 
     * xml name: state
     *  
     */
    
    public io.nop.core.lang.xml.XNode getState(){
      return _state;
    }

    
    public void setState(io.nop.core.lang.xml.XNode value){
        checkAllowChange();
        
        this._state = value;
           
    }

    
    /**
     * 
     * xml name: template
     *  - 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
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
     * 组件模板定义
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentTemplate getTemplate(){
      return _template;
    }

    
    public void setTemplate(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentTemplate value){
        checkAllowChange();
        
        this._template = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._imports = io.nop.api.core.util.FreezeHelper.deepFreeze(this._imports);
            
           this._messages = io.nop.api.core.util.FreezeHelper.deepFreeze(this._messages);
            
           this._template = io.nop.api.core.util.FreezeHelper.deepFreeze(this._template);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("imports",this.getImports());
        out.putNotNull("messages",this.getMessages());
        out.putNotNull("props",this.getProps());
        out.putNotNull("state",this.getState());
        out.putNotNull("template",this.getTemplate());
    }

    public XuiComponent cloneInstance(){
        XuiComponent instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponent instance){
        super.copyTo(instance);
        
        instance.setImports(this.getImports());
        instance.setMessages(this.getMessages());
        instance.setProps(this.getProps());
        instance.setState(this.getState());
        instance.setTemplate(this.getTemplate());
    }

    protected XuiComponent newInstance(){
        return (XuiComponent) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
