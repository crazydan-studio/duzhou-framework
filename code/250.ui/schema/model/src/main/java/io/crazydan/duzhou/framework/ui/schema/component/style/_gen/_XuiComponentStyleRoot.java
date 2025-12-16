package io.crazydan.duzhou.framework.ui.schema.component.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyleRoot;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/styles.xdef <p>
 * > 特定作用于组件根节点的样式，其与样式只能作用于组件中指定的部件上。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentStyleRoot extends io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyleBase {
    

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
        
    }

    public XuiComponentStyleRoot cloneInstance(){
        XuiComponentStyleRoot instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentStyleRoot instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentStyleRoot newInstance(){
        return (XuiComponentStyleRoot) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
