package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStatementChooseWhen;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeStatementChooseWhen extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeBody {
    
    /**
     *  条件表达式
     * xml name: test
     * > 如 `${name != null}`。若其结果为 `true` 则获得其子节点
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiExpr _test ;
    
    /**
     * 条件表达式
     * xml name: test
     *  > 如 `${name != null}`。若其结果为 `true` 则获得其子节点
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiExpr getTest(){
      return _test;
    }

    
    public void setTest(io.crazydan.duzhou.framework.ui.domain.type.XuiExpr value){
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

    public XuiLayoutNodeStatementChooseWhen cloneInstance(){
        XuiLayoutNodeStatementChooseWhen instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeStatementChooseWhen instance){
        super.copyTo(instance);
        
        instance.setTest(this.getTest());
    }

    protected XuiLayoutNodeStatementChooseWhen newInstance(){
        return (XuiLayoutNodeStatementChooseWhen) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
