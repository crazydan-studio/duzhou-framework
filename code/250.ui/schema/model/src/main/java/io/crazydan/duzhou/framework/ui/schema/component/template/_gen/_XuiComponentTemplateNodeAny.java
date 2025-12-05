package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeAny;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > - 标签名需符合组件的命名规范（`component-name`）；
 * > - 标签名对应的是 `<import/>` 所引入的外部组件；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeAny extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeSlottable {
    
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
        
        out.putNotNull("attrs",this.getAttrs());
    }

    public XuiComponentTemplateNodeAny cloneInstance(){
        XuiComponentTemplateNodeAny instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeAny instance){
        super.copyTo(instance);
        
        instance.setAttrs(this.getAttrs());
    }

    protected XuiComponentTemplateNodeAny newInstance(){
        return (XuiComponentTemplateNodeAny) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
