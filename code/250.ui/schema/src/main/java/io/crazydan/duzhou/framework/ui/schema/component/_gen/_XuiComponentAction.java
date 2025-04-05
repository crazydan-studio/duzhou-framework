package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentAction;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/action.xdef <p>
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
 * 组件逻辑定义
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentAction extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: dispatch
     * TODO 限定 type 为驼峰形式（首字母大写、可包含下划线）
     * 若有多个消息，则合并为组合消息后，再发送
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch> _messages = KeyedList.emptyList();
    
    /**
     *  
     * xml name: state
     * 
     */
    private io.nop.core.lang.xml.IXNodeGenerator _state ;
    
    /**
     * 
     * xml name: dispatch
     *  TODO 限定 type 为驼峰形式（首字母大写、可包含下划线）
     * 若有多个消息，则合并为组合消息后，再发送
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch> getMessages(){
      return _messages;
    }

    
    public void setMessages(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch> value){
        checkAllowChange();
        
        this._messages = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch::getType);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch getMessage(String name){
        return this._messages.getByKey(name);
    }

    public boolean hasMessage(String name){
        return this._messages.containsKey(name);
    }

    public void addMessage(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch> list = this.getMessages();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch::getType);
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
     * xml name: state
     *  
     */
    
    public io.nop.core.lang.xml.IXNodeGenerator getState(){
      return _state;
    }

    
    public void setState(io.nop.core.lang.xml.IXNodeGenerator value){
        checkAllowChange();
        
        this._state = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._messages = io.nop.api.core.util.FreezeHelper.deepFreeze(this._messages);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("messages",this.getMessages());
        out.putNotNull("state",this.getState());
    }

    public XuiComponentAction cloneInstance(){
        XuiComponentAction instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentAction instance){
        super.copyTo(instance);
        
        instance.setMessages(this.getMessages());
        instance.setState(this.getState());
    }

    protected XuiComponentAction newInstance(){
        return (XuiComponentAction) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
