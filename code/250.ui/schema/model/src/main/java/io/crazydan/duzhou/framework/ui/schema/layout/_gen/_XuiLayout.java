package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > - 布局将影响运行时的节点嵌套关系，从而保证布局的准确性；
 * > - 一般可将布局根节点的高宽均设置为 `match-parent`，从而支持通过上层组件控制其实际尺寸；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayout extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeBody {
    

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

    public XuiLayout cloneInstance(){
        XuiLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayout instance){
        super.copyTo(instance);
        
    }

    protected XuiLayout newInstance(){
        return (XuiLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
