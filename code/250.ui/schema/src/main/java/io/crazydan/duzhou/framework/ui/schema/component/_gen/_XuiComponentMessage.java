package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/message.xdef <p>
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
 * 组件消息处理器定义：单独定义，以支持在指定包下创建 class
 * - 每个消息均由唯一 `type` 表示，并以 `data` 作为负载数据的引用名字；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentMessage extends io.crazydan.duzhou.framework.ui.schema.action.XuiActionNode {
    
    /**
     *  必填
     * xml name: type
     * 消息类型，其值需为首字母大写的驼峰形式（可包含下划线）；
     */
    private java.lang.String _type ;
    
    /**
     * 必填
     * xml name: type
     *  消息类型，其值需为首字母大写的驼峰形式（可包含下划线）；
     */
    
    public java.lang.String getType(){
      return _type;
    }

    
    public void setType(java.lang.String value){
        checkAllowChange();
        
        this._type = value;
           
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
    }

    public XuiComponentMessage cloneInstance(){
        XuiComponentMessage instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentMessage instance){
        super.copyTo(instance);
        
        instance.setType(this.getType());
    }

    protected XuiComponentMessage newInstance(){
        return (XuiComponentMessage) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
