package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondElse;
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
public abstract class _XuiComponentCondElse extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {
    

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

    public XuiComponentCondElse cloneInstance(){
        XuiComponentCondElse instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentCondElse instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentCondElse newInstance(){
        return (XuiComponentCondElse) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
