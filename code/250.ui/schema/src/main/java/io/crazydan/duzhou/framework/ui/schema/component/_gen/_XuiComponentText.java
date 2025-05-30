package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentText;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * Start: 内置组件
 * 将内置组件定义在基础节点中，以支持在 xui:each、xui:when 等嵌套节点中定义这些类型的直接节点
 * 文本组件：只包含文本内容的组件
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentText extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
    /**
     *  
     * xml name: 
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.String> _value ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.String> getValue(){
      return _value;
    }

    
    public void setValue(io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.String> value){
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
        
        out.putNotNull("value",this.getValue());
    }

    public XuiComponentText cloneInstance(){
        XuiComponentText instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentText instance){
        super.copyTo(instance);
        
        instance.setValue(this.getValue());
    }

    protected XuiComponentText newInstance(){
        return (XuiComponentText) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
