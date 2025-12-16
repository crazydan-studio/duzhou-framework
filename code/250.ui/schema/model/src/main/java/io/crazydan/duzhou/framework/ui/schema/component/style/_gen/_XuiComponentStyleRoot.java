package io.crazydan.duzhou.framework.ui.schema.component.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyleRoot;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/styles.xdef <p>
 * > 特定作用于组件根节点的样式，其与样式只能作用于组件中指定的部件上。
 * >
 * > 不管组件内部有零个还是多个部件，其最终都会由唯一的根节点包裹其内部结构，
 * > 并可由上层组件在该根节点上定制样式，其内部配置的根样式也将作用于该根节点。
 * > 相当于 `component/template` 便是该根节点，因此，对根样式的缺省引用方式实际等价于
 * > `<component><template> <styles> <root/> </styles> </template></component>`。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentStyleRoot extends io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyleBase {
    

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

    public XuiComponentStyleRoot cloneInstance(){
        XuiComponentStyleRoot instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentStyleRoot instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentStyleRoot newInstance(){
        return (XuiComponentStyleRoot) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
