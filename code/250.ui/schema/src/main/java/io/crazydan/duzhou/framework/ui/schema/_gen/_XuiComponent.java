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
 * - 组件的缺省配置定义在 `/duzhou/ui/component/default.xui` 中，所有组件均自动扩展自该组件，
 * 若需要排除该缺省配置，则需在 `x:extends` 的 v-path 列表中包含 `none` 值，如，`none,/xx/xx.xui`；
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
     * 组件导入指令定义：单独定义，以支持在指定包下创建 class
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> _imports = KeyedList.emptyList();
    
    /**
     *  
     * xml name: messages
     * 逻辑层只用处理消息，而无需关注消息如何触发和发送；视图层只用发送消息，而无需调用函数
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage> _messages = KeyedList.emptyList();
    
    /**
     *  
     * xml name: template
     * 组件属性
     * - 用于接受从外部传入的配置数据，其在组件内始终为只读的，且变更响应自上而下单向传递；
     * - 内置 `children` 属性，用于获取当前组件的嵌套节点；
     * <props xdef:ref="component/props.xdef" />
     * state 为组件内部状态数据，仅组件内可读、可修改，响应式更新
     * - 对象结构的数据始终不为 null，自动按照其结构为各个属性初始化为指定的默认值：
     * - 字符串类型默认为空，布尔类型默认为 false 等
     * <state xdef:body-type="map"
     * xdef:bean-prop="states" xdef:bean-child-name="state"
     * >
     * <xdef:unknown-tag xdef:ref="component/prop.xdef" />
     * </state>
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
     * 组件导入指令定义：单独定义，以支持在指定包下创建 class
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
     *  逻辑层只用处理消息，而无需关注消息如何触发和发送；视图层只用发送消息，而无需调用函数
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage> getMessages(){
      return _messages;
    }

    
    public void setMessages(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage> value){
        checkAllowChange();
        
        this._messages = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage::getType);
           
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
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage::getType);
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
     * xml name: template
     *  组件属性
     * - 用于接受从外部传入的配置数据，其在组件内始终为只读的，且变更响应自上而下单向传递；
     * - 内置 `children` 属性，用于获取当前组件的嵌套节点；
     * <props xdef:ref="component/props.xdef" />
     * state 为组件内部状态数据，仅组件内可读、可修改，响应式更新
     * - 对象结构的数据始终不为 null，自动按照其结构为各个属性初始化为指定的默认值：
     * - 字符串类型默认为空，布尔类型默认为 false 等
     * <state xdef:body-type="map"
     * xdef:bean-prop="states" xdef:bean-child-name="state"
     * >
     * <xdef:unknown-tag xdef:ref="component/prop.xdef" />
     * </state>
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
        instance.setTemplate(this.getTemplate());
    }

    protected XuiComponent newInstance(){
        return (XuiComponent) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
