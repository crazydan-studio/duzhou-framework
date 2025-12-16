package io.crazydan.duzhou.framework.ui.schema.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefNodePatch;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/style-def.xdef <p>
 * > 仅用于针对组件的部件的内部样式的定制修改，如：
 * > ```xml
 * > <component>
 * >  <styles>
 * >    <win-info>
 * >      <header>
 * >        <background color="blue"/>
 * >      </header>
 * >    </win-info>
 * >  </styles>
 * > </component>
 * > ```
 * > 在样式 `win-info` 的第二层结构中 `<background/>` 的作用便是对样式
 * > `win-info` 所作用的部件的内部的 `header` 样式做定制修改。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleDefNodePatch extends io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefNodeBase {
    

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

    public XuiStyleDefNodePatch cloneInstance(){
        XuiStyleDefNodePatch instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleDefNodePatch instance){
        super.copyTo(instance);
        
    }

    protected XuiStyleDefNodePatch newInstance(){
        return (XuiStyleDefNodePatch) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
