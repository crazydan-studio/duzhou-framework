package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutStatementChooseWhen extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayout implements io.crazydan.duzhou.framework.ui.XuiNamed{
    
    /**
     *  条件表达式
     * xml name: test
     * > 如 `${name != null}`。若其结果为 `true` 则获得其子节点
     */
    private java.lang.String _test ;
    
    /**
     * 条件表达式
     * xml name: test
     *  > 如 `${name != null}`。若其结果为 `true` 则获得其子节点
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

    public XuiLayoutStatementChooseWhen cloneInstance(){
        XuiLayoutStatementChooseWhen instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutStatementChooseWhen instance){
        super.copyTo(instance);
        
        instance.setTest(this.getTest());
    }

    protected XuiLayoutStatementChooseWhen newInstance(){
        return (XuiLayoutStatementChooseWhen) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
