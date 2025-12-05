package io.crazydan.duzhou.framework.ui.schema.app._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.app.XuiApp;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/app.xdef <p>
 * > 一个独立的 UI 应用是系统中相关功能的集合体，比如，控制台、门户等
 * >
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiApp extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: pages
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.app.XuiAppPages _pages ;
    
    /**
     * 
     * xml name: pages
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.app.XuiAppPages getPages(){
      return _pages;
    }

    
    public void setPages(io.crazydan.duzhou.framework.ui.schema.app.XuiAppPages value){
        checkAllowChange();
        
        this._pages = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._pages = io.nop.api.core.util.FreezeHelper.deepFreeze(this._pages);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("pages",this.getPages());
    }

    public XuiApp cloneInstance(){
        XuiApp instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiApp instance){
        super.copyTo(instance);
        
        instance.setPages(this.getPages());
    }

    protected XuiApp newInstance(){
        return (XuiApp) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
