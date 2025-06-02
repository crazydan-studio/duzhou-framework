package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 条件选择的指定条件
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentCondWhen extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {
    
    /**
     *  必填
     * xml name: test
     * 条件表达式；
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Boolean> _test ;
    
    /**
     * 必填
     * xml name: test
     *  条件表达式；
     */
    
    public io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Boolean> getTest(){
      return _test;
    }

    
    public void setTest(io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Boolean> value){
        checkAllowChange();
        
        this._test = value;
           
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
        
        out.putNotNull("test",this.getTest());
    }

    public XuiComponentCondWhen cloneInstance(){
        XuiComponentCondWhen instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentCondWhen instance){
        super.copyTo(instance);
        
        instance.setTest(this.getTest());
    }

    protected XuiComponentCondWhen newInstance(){
        return (XuiComponentCondWhen) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
