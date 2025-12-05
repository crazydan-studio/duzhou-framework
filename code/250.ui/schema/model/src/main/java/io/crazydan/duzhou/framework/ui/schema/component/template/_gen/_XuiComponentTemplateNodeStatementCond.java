package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementCond;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 在 `test` 表达式的结果为 `true` 时，获得其子节点。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeStatementCond extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNode {
    
    /**
     *  条件表达式
     * xml name: test
     * > 如 `${name != null}`
     */
    private java.lang.String _test ;
    
    /**
     * 条件表达式
     * xml name: test
     *  > 如 `${name != null}`
     */
    
    public java.lang.String getTest(){
      return _test;
    }

    
    public void setTest(java.lang.String value){
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

    public XuiComponentTemplateNodeStatementCond cloneInstance(){
        XuiComponentTemplateNodeStatementCond instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeStatementCond instance){
        super.copyTo(instance);
        
        instance.setTest(this.getTest());
    }

    protected XuiComponentTemplateNodeStatementCond newInstance(){
        return (XuiComponentTemplateNodeStatementCond) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
