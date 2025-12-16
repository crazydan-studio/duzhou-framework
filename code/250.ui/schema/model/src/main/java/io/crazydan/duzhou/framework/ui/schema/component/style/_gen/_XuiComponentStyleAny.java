package io.crazydan.duzhou.framework.ui.schema.component.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyleAny;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/styles.xdef <p>
 * >
 * > 标签名为 `layout__xxx` 形式的样式将被视为布局样式，仅可在布局节点中应用，且仅引用
 * > `xxx` 部分所代表的名字，如 `<styles> <xxx .../> </styles>`。
 * > 而 `xxx__hover`、`xxx__focus` 等形式的样式则会被视为针对组件部件的
 * > `hover`、`focus` 等状态所应用的样式，其中 `xxx` 部分代表的是组件部件的一般样式的名字，
 * > 如 `header` 部件所应用的样式 `<styles> <header .../> <header__hover .../> </styles>`。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentStyleAny extends io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyleBase {
    

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

    public XuiComponentStyleAny cloneInstance(){
        XuiComponentStyleAny instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentStyleAny instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentStyleAny newInstance(){
        return (XuiComponentStyleAny) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
