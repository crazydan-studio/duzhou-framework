package io.crazydan.duzhou.framework.ui.schema.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleRefs;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/style-refs.xdef <p>
 * > 引用已定义样式并提供具体的配置参数，再向组件或布局应用所配置的样式。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleRefs extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.List<io.crazydan.duzhou.framework.ui.schema.style.XuiStyleRef> _children ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.style.XuiStyleRef> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.List<io.crazydan.duzhou.framework.ui.schema.style.XuiStyleRef> value){
        checkAllowChange();
        
        this._children = value;
           
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

    public XuiStyleRefs cloneInstance(){
        XuiStyleRefs instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleRefs instance){
        super.copyTo(instance);
        
        instance.setChildren(this.getChildren());
    }

    protected XuiStyleRefs newInstance(){
        return (XuiStyleRefs) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
