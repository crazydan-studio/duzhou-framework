package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 根据 `xui:name` 对组件结构树中的直接**部件**节点（含 `<slot/>` 和 `<native/>`）进行布局控制。
 * > 对于 `<body/>` 下的非直接部件节点，则需要在其所在的父节点中单独通过 `<layout/>` 进行控制，
 * > 也就是布局控制**不能跨层级**作用于部件。
 * >
 * > 没有在布局中显式控制的组件将不会被布局，也就不会呈现出来。
 * >
 * > 缺省将按照部件的声明顺序排列，并由 UI Vendor 做默认布局。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeLayout extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayout {
    

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

    public XuiComponentTemplateNodeLayout cloneInstance(){
        XuiComponentTemplateNodeLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeLayout instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentTemplateNodeLayout newInstance(){
        return (XuiComponentTemplateNodeLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
