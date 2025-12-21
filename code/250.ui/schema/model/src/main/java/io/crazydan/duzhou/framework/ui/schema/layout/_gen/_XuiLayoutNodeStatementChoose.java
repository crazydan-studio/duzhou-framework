package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStatementChoose;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeStatementChoose extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed {
    
    /**
     *  
     * xml name: 
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> _children = KeyedList.emptyList();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> value){
        checkAllowChange();
        
        this._children = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed getChild(String name){
        return this._children.getByKey(name);
    }

    public boolean hasChild(String name){
        return this._children.containsKey(name);
    }

    public void addChild(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> list = this.getChildren();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed::getXuiName);
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

    public XuiLayoutNodeStatementChoose cloneInstance(){
        XuiLayoutNodeStatementChoose instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeStatementChoose instance){
        super.copyTo(instance);
        
        instance.setChildren(this.getChildren());
    }

    protected XuiLayoutNodeStatementChoose newInstance(){
        return (XuiLayoutNodeStatementChoose) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
