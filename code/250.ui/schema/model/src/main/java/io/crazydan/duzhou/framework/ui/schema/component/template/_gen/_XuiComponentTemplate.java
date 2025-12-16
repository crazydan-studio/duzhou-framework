package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 用于定义组件的组成结构，并对其部件进行布局控制和样式设定。
 * >
 * > 组件组成结构 `<body/>` 中的节点称为**结构节点**，其包含 `<if/>`、`<for/>` 等**控制节点**，
 * > 以及由导入的外部组件实例组成的**组件节点**，其中，组件节点也称为当前组件的组成**部件**。
 * > 部件为组件的核心组成元素，其为组件的视觉呈现，并负责与用户的交互响应。
 * >
 * > 组件的结构节点均以 `xui:name` 作为唯一属性，以支持对其结构中的任意节点进行差量定制。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplate extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNode {
    

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
