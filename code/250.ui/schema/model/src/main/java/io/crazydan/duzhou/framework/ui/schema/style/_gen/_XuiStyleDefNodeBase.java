package io.crazydan.duzhou.framework.ui.schema.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefNodeBase;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/style-def.xdef <p>
 * > 对已定义样式的引用，如 `<header width="20u" height="match-parent" />`。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleDefNodeBase extends io.crazydan.duzhou.framework.ui.schema.style.XuiStyleRef {
    

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

    public XuiStyleDefNodeBase cloneInstance(){
        XuiStyleDefNodeBase instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleDefNodeBase instance){
        super.copyTo(instance);
        
    }

    protected XuiStyleDefNodeBase newInstance(){
        return (XuiStyleDefNodeBase) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
