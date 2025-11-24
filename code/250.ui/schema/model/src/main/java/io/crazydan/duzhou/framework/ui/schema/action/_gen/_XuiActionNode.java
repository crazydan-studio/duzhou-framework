package io.crazydan.duzhou.framework.ui.schema.action._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.action.XuiActionNode;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/action/node.xdef <p>
 * 逻辑操作节点定义：单独定义，以支持在指定包下创建 class
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiActionNode extends io.crazydan.duzhou.framework.ui.schema.action.XuiActionNested {
    

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

    public XuiActionNode cloneInstance(){
        XuiActionNode instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiActionNode instance){
        super.copyTo(instance);
        
    }

    protected XuiActionNode newInstance(){
        return (XuiActionNode) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
