package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 在 `test` 表达式的结果为 `true` 时，获得其子节点。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeStatementChooseWhen extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementCond {
    

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

    public XuiComponentTemplateNodeStatementChooseWhen cloneInstance(){
        XuiComponentTemplateNodeStatementChooseWhen instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeStatementChooseWhen instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentTemplateNodeStatementChooseWhen newInstance(){
        return (XuiComponentTemplateNodeStatementChooseWhen) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
