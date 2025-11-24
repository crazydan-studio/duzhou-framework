package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentSlot;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > - ；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentSlot extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
    /**
     *  
     * xml name: attrs
     * 
     */
    private io.crazydan.duzhou.framework.ui.XuiExpression<java.util.Map> _attrs ;
    
    /**
     *  
     * xml name: name
     * 
     */
    private java.lang.String _name ;
    
    /**
     * 
     * xml name: attrs
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.XuiExpression<java.util.Map> getAttrs(){
      return _attrs;
    }

    
    public void setAttrs(io.crazydan.duzhou.framework.ui.XuiExpression<java.util.Map> value){
        checkAllowChange();
        
        this._attrs = value;
           
    }

    
    /**
     * 
     * xml name: name
     *  
     */
    
    public java.lang.String getName(){
      return _name;
    }

    
    public void setName(java.lang.String value){
        checkAllowChange();
        
        this._name = value;
           
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
        
        out.putNotNull("attrs",this.getAttrs());
        out.putNotNull("name",this.getName());
    }

    public XuiComponentSlot cloneInstance(){
        XuiComponentSlot instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentSlot instance){
        super.copyTo(instance);
        
        instance.setAttrs(this.getAttrs());
        instance.setName(this.getName());
    }

    protected XuiComponentSlot newInstance(){
        return (XuiComponentSlot) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
