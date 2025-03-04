package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiStyle;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/styles.xdef <p>
 * 样式的组成节点
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyle extends io.nop.core.resource.component.AbstractComponentModel {
    

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

    public XuiStyle cloneInstance(){
        XuiStyle instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyle instance){
        super.copyTo(instance);
        
    }

    protected XuiStyle newInstance(){
        return (XuiStyle) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
