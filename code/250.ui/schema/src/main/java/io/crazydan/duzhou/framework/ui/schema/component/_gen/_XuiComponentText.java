package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentText;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 只包含文本内容的组件
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentText extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
    /**
     *  是否为 HTML 片段
     * xml name: as-html
     * > (可选) 若为 `true`，则将其文本作为 HTML 渲染，但需自行处理 XSS 攻击
     */
    private java.lang.Boolean _asHtml ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.String> _value ;
    
    /**
     * 是否为 HTML 片段
     * xml name: as-html
     *  > (可选) 若为 `true`，则将其文本作为 HTML 渲染，但需自行处理 XSS 攻击
     */
    
    public java.lang.Boolean getAsHtml(){
      return _asHtml;
    }

    
    public void setAsHtml(java.lang.Boolean value){
        checkAllowChange();
        
        this._asHtml = value;
           
    }

    
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
        
        out.putNotNull("asHtml",this.getAsHtml());
        out.putNotNull("value",this.getValue());
    }

    public XuiComponentText cloneInstance(){
        XuiComponentText instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentText instance){
        super.copyTo(instance);
        
        instance.setAsHtml(this.getAsHtml());
        instance.setValue(this.getValue());
    }

    protected XuiComponentText newInstance(){
        return (XuiComponentText) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
