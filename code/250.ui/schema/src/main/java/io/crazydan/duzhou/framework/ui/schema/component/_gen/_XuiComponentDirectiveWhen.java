package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentDirectiveWhen;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 组件命名元素
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentDirectiveWhen extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentDirective {
    
    /**
     *  
     * xml name: cond
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Boolean> _cond ;
    
    /**
     * 
     * xml name: cond
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Boolean> getCond(){
      return _cond;
    }

    
    public void setCond(io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Boolean> value){
        checkAllowChange();
        
        this._cond = value;
           
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
        
        out.putNotNull("cond",this.getCond());
    }

    public XuiComponentDirectiveWhen cloneInstance(){
        XuiComponentDirectiveWhen instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentDirectiveWhen instance){
        super.copyTo(instance);
        
        instance.setCond(this.getCond());
    }

    protected XuiComponentDirectiveWhen newInstance(){
        return (XuiComponentDirectiveWhen) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
