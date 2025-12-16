package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStyles;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 引用在当前组件 `<styles/>` 中所定义的**布局样式**（名字以 `layout__` 开头的标签），
 * > 其子节点标签名必须与去掉 `layout__` 后的样式名一致，且只能配置在该样式上所声明的属性。
 * > 此类样式可以在上层组件中作为部件样式被定制修改。
 * >
 * > 也支持直接引用在组件的 `<styles/>` 标签上设置的 `layout` 属性所指向的布局样式库中定义的布局样式，
 * > 但是，引用的此类样式将不能在上层组件中被定制修改。
 * >
 * > 注意，第一种引用样式的优先级高于第二种引用样式。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeStyles extends io.crazydan.duzhou.framework.ui.schema.style.XuiStyleRefs {
    

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

    public XuiLayoutNodeStyles cloneInstance(){
        XuiLayoutNodeStyles instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeStyles instance){
        super.copyTo(instance);
        
    }

    protected XuiLayoutNodeStyles newInstance(){
        return (XuiLayoutNodeStyles) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
