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
public abstract class _XuiComponentTemplateNodeText extends io.nop.core.resource.component.AbstractComponentModel implements io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed{
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
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
    private java.util.Map<java.lang.String,java.lang.Object> _attrs ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _value ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.lang.String get$tag(){
      return _$tag;
    }

    
    public void set$tag(java.lang.String value){
        checkAllowChange();
        
        this._$tag = value;
           
    }

    
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
    
    public java.util.Map<java.lang.String,java.lang.Object> getAttrs(){
      return _attrs;
    }

    
    public void setAttrs(java.util.Map<java.lang.String,java.lang.Object> value){
        checkAllowChange();
        
        this._attrs = value;
           
    }

    
    public boolean hasAttrs(){
        return this._attrs != null && !this._attrs.isEmpty();
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
        
           this._attrs = io.nop.api.core.util.FreezeHelper.deepFreeze(this._attrs);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("asHtml",this.getAsHtml());
        out.putNotNull("attrs",this.getAttrs());
        out.putNotNull("value",this.getValue());
    }

    public XuiComponentTemplateNodeText cloneInstance(){
        XuiComponentTemplateNodeText instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeText instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setAsHtml(this.getAsHtml());
        instance.setAttrs(this.getAttrs());
        instance.setValue(this.getValue());
    }

    protected XuiComponentTemplateNodeText newInstance(){
        return (XuiComponentTemplateNodeText) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
