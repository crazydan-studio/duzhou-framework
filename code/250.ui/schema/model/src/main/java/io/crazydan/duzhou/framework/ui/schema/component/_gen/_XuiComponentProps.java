package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentProps;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component.xdef <p>
 * > - 用于接受从外部传入的配置数据，其在组件内始终为只读的，且变更响应自上而下单向传递；
 * > - 内置 `children` 属性，用于获取当前组件的嵌套节点；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentProps extends io.crazydan.duzhou.framework.ui.schema.meta.XuiObjSchema {
    

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

    public XuiComponentProps cloneInstance(){
        XuiComponentProps instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentProps instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentProps newInstance(){
        return (XuiComponentProps) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
