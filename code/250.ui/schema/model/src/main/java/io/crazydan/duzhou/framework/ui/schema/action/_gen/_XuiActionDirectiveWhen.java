package io.crazydan.duzhou.framework.ui.schema.action._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.action.XuiActionDirectiveWhen;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/action/node.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiActionDirectiveWhen extends io.crazydan.duzhou.framework.ui.schema.action.XuiActionDirective {
    
    /**
     *  
     * xml name: cond
     * 
     */
    private java.lang.String _cond ;
    
    /**
     * 
     * xml name: cond
     *  
     */
    
    public java.lang.String getCond(){
      return _cond;
    }

    
    public void setCond(java.lang.String value){
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

    public XuiActionDirectiveWhen cloneInstance(){
        XuiActionDirectiveWhen instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiActionDirectiveWhen instance){
        super.copyTo(instance);
        
        instance.setCond(this.getCond());
    }

    protected XuiActionDirectiveWhen newInstance(){
        return (XuiActionDirectiveWhen) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
