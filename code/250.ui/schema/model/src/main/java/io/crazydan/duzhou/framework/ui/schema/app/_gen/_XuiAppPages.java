package io.crazydan.duzhou.framework.ui.schema.app._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.app.XuiAppPages;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/app.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiAppPages extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage> _children = KeyedList.emptyList();
    
    /**
     *  
     * xml name: entry
     * 
     */
    private java.lang.String _entry ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage> value){
        checkAllowChange();
        
        this._children = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage::getUrl);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage getChild(String name){
        return this._children.getByKey(name);
    }

    public boolean hasChild(String name){
        return this._children.containsKey(name);
    }

    public void addChild(io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage> list = this.getChildren();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage::getUrl);
            setChildren(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_children(){
        return this._children.keySet();
    }

    public boolean hasChildren(){
        return !this._children.isEmpty();
    }
    
    /**
     * 
     * xml name: entry
     *  
     */
    
    public java.lang.String getEntry(){
      return _entry;
    }

    
    public void setEntry(java.lang.String value){
        checkAllowChange();
        
        this._entry = value;
           
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
        out.putNotNull("entry",this.getEntry());
    }

    public XuiAppPages cloneInstance(){
        XuiAppPages instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiAppPages instance){
        super.copyTo(instance);
        
        instance.setChildren(this.getChildren());
        instance.setEntry(this.getEntry());
    }

    protected XuiAppPages newInstance(){
        return (XuiAppPages) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
