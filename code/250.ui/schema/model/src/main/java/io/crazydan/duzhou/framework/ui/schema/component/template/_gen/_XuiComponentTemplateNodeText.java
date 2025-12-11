package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeText;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 只包含文本内容的组件。必须通过 `<import/>` 显式导入。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeText extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed {
    
    /**
     *  是否为 HTML 片段
     * xml name: as-html
     * > 若为 `true`，则将其文本作为 HTML 渲染，但需自行处理 XSS 攻击
     */
    private java.lang.Boolean _asHtml ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,java.lang.Object> _props ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _value ;
    
    /**
     * 是否为 HTML 片段
     * xml name: as-html
     *  > 若为 `true`，则将其文本作为 HTML 渲染，但需自行处理 XSS 攻击
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
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.lang.String getValue(){
      return _value;
    }

    
    public void setValue(java.lang.String value){
        checkAllowChange();
        
        this._value = value;
           
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
        
        out.putNotNull("asHtml",this.getAsHtml());
        out.putNotNull("props",this.getProps());
        out.putNotNull("value",this.getValue());
    }

    public XuiComponentTemplateNodeText cloneInstance(){
        XuiComponentTemplateNodeText instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeText instance){
        super.copyTo(instance);
        
        instance.setAsHtml(this.getAsHtml());
        instance.setProps(this.getProps());
        instance.setValue(this.getValue());
    }

    protected XuiComponentTemplateNodeText newInstance(){
        return (XuiComponentTemplateNodeText) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
