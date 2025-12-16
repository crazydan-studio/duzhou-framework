package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNative;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 本框架自身没有提供组件绘制能力，最终必须由实际运行环境提供底层基础组件的绘制能力，
 * > 比如，在 Web 运行时中，`<Input/>` 组件需要由 html 元素 `<input/>` 提供支持，而 `<Input/>`
 * > 组件自身则主要是对该原生组件的封装层，从而向上层组件屏蔽底层差异，实现对 UI 的跨平台支持。
 * >
 * > 可在原生组件内嵌入包括控制节点在内的子结构，同时可配置布局和消息。
 * > 其子结构最终将以子节点形式传递给 `name` 属性所对应的原生组件，再由具体的原生组件负责处理。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeNative extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeSlottable {
    
    /**
     *  原生组件名
     * xml name: name
     * > 由运行时根据该名字注册原生组件
     */
    private java.lang.String _name ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,java.lang.Object> _props ;
    
    /**
     * 原生组件名
     * xml name: name
     *  > 由运行时根据该名字注册原生组件
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
    
    public java.util.Map<java.lang.String,java.lang.Object> getProps(){
      return _props;
    }

    
    public void setProps(java.util.Map<java.lang.String,java.lang.Object> value){
        checkAllowChange();
        
        this._props = value;
           
    }

    
    public boolean hasProps(){
        return this._props != null && !this._props.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._props = io.nop.api.core.util.FreezeHelper.deepFreeze(this._props);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("name",this.getName());
        out.putNotNull("props",this.getProps());
    }

    public XuiComponentTemplateNodeNative cloneInstance(){
        XuiComponentTemplateNodeNative instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeNative instance){
        super.copyTo(instance);
        
        instance.setName(this.getName());
        instance.setProps(this.getProps());
    }

    protected XuiComponentTemplateNodeNative newInstance(){
        return (XuiComponentTemplateNodeNative) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
