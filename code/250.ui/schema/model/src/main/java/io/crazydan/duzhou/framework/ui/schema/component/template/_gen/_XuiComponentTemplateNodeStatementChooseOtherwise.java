package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseOtherwise;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 由导入的外部组件实例构成的结构树，其仅包含控制节点和组件节点。
 * >
 * > 控制节点根据组件内部状态和外部设定的属性数据控制组件节点在何种状态下挂载到结构树中，
 * > 从而支持**数据响应式**的组件渲染。
 * >
 * > 而组件节点仅表示当前组件由哪些元素组成，而以何种视觉效果呈现则由 `<layout/>` 单独控制，
 * > 在结构树中不包含与布局相关的节点和样式，组件节点之间的先后顺序也不代表其显示的位置。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeStatementChooseOtherwise extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeBody {
    

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

    public XuiComponentTemplateNodeStatementChooseOtherwise cloneInstance(){
        XuiComponentTemplateNodeStatementChooseOtherwise instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeStatementChooseOtherwise instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentTemplateNodeStatementChooseOtherwise newInstance(){
        return (XuiComponentTemplateNodeStatementChooseOtherwise) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
