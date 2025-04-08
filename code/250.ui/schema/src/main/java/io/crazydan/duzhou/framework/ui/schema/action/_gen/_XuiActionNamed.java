package io.crazydan.duzhou.framework.ui.schema.action._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.action.XuiActionNamed;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/action/node.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiActionNamed extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$type ;
    
    /**
     *  
     * xml name: name
     * 
     */
    private java.lang.String _name ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.lang.String get$type(){
      return _$type;
    }

    
    public void set$type(java.lang.String value){
        checkAllowChange();
        
        this._$type = value;
           
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
        
        out.putNotNull("$type",this.get$type());
        out.putNotNull("name",this.getName());
    }

    public XuiActionNamed cloneInstance(){
        XuiActionNamed instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiActionNamed instance){
        super.copyTo(instance);
        
        instance.set$type(this.get$type());
        instance.setName(this.getName());
    }

    protected XuiActionNamed newInstance(){
        return (XuiActionNamed) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
