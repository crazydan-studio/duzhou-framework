package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 限定可被嵌套的节点，其中：
 * > - 内置组件 `Text` 可被嵌套，但其子节点只能为 HTML 文本，不可嵌套其他组件；
 * > - 自定义组件可被嵌套，且可嵌套其他组件；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentCondWhen extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {
    
    /**
     *  条件表达式
     * xml name: test
     * > (必填) 如 `${name != null}`
     */
    private io.crazydan.duzhou.framework.ui.XuiExpression<java.lang.Boolean> _test ;
    
    /**
     * 条件表达式
     * xml name: test
     *  > (必填) 如 `${name != null}`
     */
    
    public io.crazydan.duzhou.framework.ui.XuiExpression<java.lang.Boolean> getTest(){
      return _test;
    }

    
    public void setTest(io.crazydan.duzhou.framework.ui.XuiExpression<java.lang.Boolean> value){
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
