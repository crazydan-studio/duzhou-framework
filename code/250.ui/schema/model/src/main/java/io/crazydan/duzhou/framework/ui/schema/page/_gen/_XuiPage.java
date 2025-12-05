package io.crazydan.duzhou.framework.ui.schema.page._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.page.XuiPage;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/page.xdef <p>
 * > 一个包含完整业务处理组件的应用窗口。
 * > 其本质就是一个占满整个视窗的 UI 组件。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiPage extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponent {
    

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

    public XuiPage cloneInstance(){
        XuiPage instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiPage instance){
        super.copyTo(instance);
        
    }

    protected XuiPage newInstance(){
        return (XuiPage) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
