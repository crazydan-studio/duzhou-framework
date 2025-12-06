package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStacked;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > - 包含至少两个布局子节点，且子节点可通过 `xui:when` 控制是否显示；
 * > - 该布局及其各个子节点的布局空间均占满当前组件；
 * > - 子节点之间为一层一层的堆叠效果，并通过属性 `layer` 来控制相对间的层级关系；
 * > - 弹出层、遮罩等，均通过该布局进行控制；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeStacked extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode {
    

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

    public XuiLayoutNodeStacked cloneInstance(){
        XuiLayoutNodeStacked instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeStacked instance){
        super.copyTo(instance);
        
    }

    protected XuiLayoutNodeStacked newInstance(){
        return (XuiLayoutNodeStacked) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
