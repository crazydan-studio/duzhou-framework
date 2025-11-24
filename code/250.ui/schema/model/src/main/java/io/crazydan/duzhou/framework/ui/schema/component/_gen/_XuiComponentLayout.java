package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > - 布局将影响运行时的节点嵌套关系，从而保证布局的准确性；
 * > - 仅用于控制所在组件中的直接视图组件。注意，`Validation`、`Animation` 为逻辑组件；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentLayout extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
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
        
        out.putNotNull("type",this.getType());
    }

    public XuiComponentLayout cloneInstance(){
        XuiComponentLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentLayout instance){
        super.copyTo(instance);
        
        instance.setType(this.getType());
    }

    protected XuiComponentLayout newInstance(){
        return (XuiComponentLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
