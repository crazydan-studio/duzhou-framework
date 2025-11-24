package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentTemplate;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 定义组件结构，其中：
 * > - `xui:layout`: 控制直接节点的布局结构；
 * > - `xui:style`: 设置组件的展示风格；
 * > - `xui:dispatch`: 将事件转换为消息后再发送；
 * > - `xui:each`: 循环渲染，遍历循环量，构造组件的内部节点；
 * > - `xui:cond`: 条件渲染，满足条件时才构造相应的内部节点；
 * > TODO 在组件上设置的属性类型需与组件定义中 `props` 对应属性的类型一致
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplate extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNode {
    

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

    public XuiComponentTemplate cloneInstance(){
        XuiComponentTemplate instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplate instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentTemplate newInstance(){
        return (XuiComponentTemplate) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
