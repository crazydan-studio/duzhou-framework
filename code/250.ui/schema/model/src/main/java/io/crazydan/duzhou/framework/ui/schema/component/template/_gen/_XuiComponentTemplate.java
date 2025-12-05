package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > - 所有节点（包括 `<if/>`、`<for/>` 等控制节点）均以 `xui:name` 作为唯一性属性，从而支持对任意节点的差量定制；
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
