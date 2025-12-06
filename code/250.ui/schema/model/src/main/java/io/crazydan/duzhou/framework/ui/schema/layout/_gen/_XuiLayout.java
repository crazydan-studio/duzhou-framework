package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > - 布局将影响运行时的节点嵌套关系，从而保证布局的准确性；
 * >
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayout extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode _root ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode getRoot(){
      return _root;
    }

    
    public void setRoot(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode value){
        checkAllowChange();
        
        this._root = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._root = io.nop.api.core.util.FreezeHelper.deepFreeze(this._root);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("root",this.getRoot());
    }

    public XuiLayout cloneInstance(){
        XuiLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayout instance){
        super.copyTo(instance);
        
        instance.setRoot(this.getRoot());
    }

    protected XuiLayout newInstance(){
        return (XuiLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
