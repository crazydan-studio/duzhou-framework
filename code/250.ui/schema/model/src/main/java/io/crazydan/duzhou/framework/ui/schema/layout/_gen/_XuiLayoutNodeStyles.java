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
