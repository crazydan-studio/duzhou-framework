package io.crazydan.duzhou.framework.ui.schema.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyles;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/styles.xdef <p>
 * > 用于统一定义基础样式（含基础布局样式），方便在组件部件样式的定义中直接引用。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyles extends io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefs {
    

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

    public XuiStyles cloneInstance(){
        XuiStyles instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyles instance){
        super.copyTo(instance);
        
    }

    protected XuiStyles newInstance(){
        return (XuiStyles) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
