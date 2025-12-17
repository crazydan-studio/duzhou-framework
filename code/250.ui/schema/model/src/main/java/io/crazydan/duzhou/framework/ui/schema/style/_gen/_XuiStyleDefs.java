package io.crazydan.duzhou.framework.ui.schema.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefs;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/style-defs.xdef <p>
 * > 用于定义一组样式，便于引用。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleDefs extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDef> _children = java.util.Collections.emptyMap();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDef> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDef> value){
        checkAllowChange();
        
        this._children = value;
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDef getChild(String name){
        return this._children.get(name);
    }

    public boolean hasChild(String name){
        return this._children.containsKey(name);
    }
    
    public boolean hasChildren(){
        return this._children != null && !this._children.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._children = io.nop.api.core.util.FreezeHelper.deepFreeze(this._children);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("children",this.getChildren());
    }

    public XuiStyleDefs cloneInstance(){
        XuiStyleDefs instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleDefs instance){
        super.copyTo(instance);
        
        instance.setChildren(this.getChildren());
    }

    protected XuiStyleDefs newInstance(){
        return (XuiStyleDefs) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
