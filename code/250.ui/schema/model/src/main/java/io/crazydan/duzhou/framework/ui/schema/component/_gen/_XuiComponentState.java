package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentState;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component.xdef <p>
 * > - 定义组件内部状态数据，仅组件内可读、可修改，响应式更新；
 * > - 对象结构的数据始终不为 `null`，自动按照其结构为各个属性初始化为指定的默认值：
 * >   - 字符串类型默认为空，布尔类型默认为 `false` 等；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentState extends io.crazydan.duzhou.framework.ui.schema.meta.XuiObjSchema {
    

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

    public XuiComponentState cloneInstance(){
        XuiComponentState instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentState instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentState newInstance(){
        return (XuiComponentState) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
