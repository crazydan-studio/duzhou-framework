package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutLinear;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 在行/列方向上进行布局控制，与
 * > [css flexbox](https://css-tricks.com/snippets/css/a-guide-to-flexbox/)
 * > 类似。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutLinear extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutLinearNode implements io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutRoot{
    

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

    public XuiLayoutLinear cloneInstance(){
        XuiLayoutLinear instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutLinear instance){
        super.copyTo(instance);
        
    }

    protected XuiLayoutLinear newInstance(){
        return (XuiLayoutLinear) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
