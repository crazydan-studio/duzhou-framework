package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentDirectiveEach;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * Start: 组件指令
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentDirectiveEach extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentDirective {
    
    /**
     *  
     * xml name: for
     * 
     */
    private java.lang.String _for ;
    
    /**
     *  
     * xml name: index
     * 
     */
    private java.lang.String _index ;
    
    /**
     *  
     * xml name: item
     * 
     */
    private java.lang.String _item ;
    
    /**
     * 
     * xml name: for
     *  
     */
    
    public java.lang.String getFor(){
      return _for;
    }

    
    public void setFor(java.lang.String value){
        checkAllowChange();
        
        this._for = value;
           
    }

    
    /**
     * 
     * xml name: index
     *  
     */
    
    public java.lang.String getIndex(){
      return _index;
    }

    
    public void setIndex(java.lang.String value){
        checkAllowChange();
        
        this._index = value;
           
    }

    
    /**
     * 
     * xml name: item
     *  
     */
    
    public java.lang.String getItem(){
      return _item;
    }

    
    public void setItem(java.lang.String value){
        checkAllowChange();
        
        this._item = value;
           
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
        
        out.putNotNull("for",this.getFor());
        out.putNotNull("index",this.getIndex());
        out.putNotNull("item",this.getItem());
    }

    public XuiComponentDirectiveEach cloneInstance(){
        XuiComponentDirectiveEach instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentDirectiveEach instance){
        super.copyTo(instance);
        
        instance.setFor(this.getFor());
        instance.setIndex(this.getIndex());
        instance.setItem(this.getItem());
    }

    protected XuiComponentDirectiveEach newInstance(){
        return (XuiComponentDirectiveEach) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
