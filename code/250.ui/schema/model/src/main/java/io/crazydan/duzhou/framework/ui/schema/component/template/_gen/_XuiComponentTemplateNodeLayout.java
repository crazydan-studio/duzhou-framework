package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > - 布局将影响运行时的节点嵌套关系，从而保证布局的准确性；
 * > - 仅用于控制所在组件中的直接视图组件；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeLayout extends io.nop.core.resource.component.AbstractComponentModel implements io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed{
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private io.crazydan.duzhou.framework.ui.XuiLayout _type ;
    
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
     * 
     * xml name: 
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.XuiLayout getType(){
      return _type;
    }

    
    public void setType(io.crazydan.duzhou.framework.ui.XuiLayout value){
        checkAllowChange();
        
        this._type = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._type = io.nop.api.core.util.FreezeHelper.deepFreeze(this._type);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("type",this.getType());
    }

    public XuiComponentTemplateNodeLayout cloneInstance(){
        XuiComponentTemplateNodeLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeLayout instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setType(this.getType());
    }

    protected XuiComponentTemplateNodeLayout newInstance(){
        return (XuiComponentTemplateNodeLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
