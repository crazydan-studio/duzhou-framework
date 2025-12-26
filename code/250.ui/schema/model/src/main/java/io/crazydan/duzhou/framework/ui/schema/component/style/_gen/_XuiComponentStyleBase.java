package io.crazydan.duzhou.framework.ui.schema.component.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyleBase;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/styles.xdef <p>
 * > 以标签名作为样式名（小写字母且以短横线分隔），定义样式的可配置属性（驼峰形式）及其组成。
 * >
 * > 每个样式定义最多包含两层组成结构，其中，第一层用于定义样式本身的组成，
 * > 第二层则是用于调整第一层样式的内部结构。
 * > 不包含任何组成结构的样式，称为**原子样式**，即其结构不可拆分。
 * > 而包含一层或两层结构的样式，则称为**复合样式**，即其由多个样式组合而成，
 * > 其组成元素可以是原子样式，也可为其他复合样式，且第二层结构只能针对其复合样式元素。
 * >
 * > 两层结构的样式仅应用在组件部件上，同时在该部件内部为其自身的部件定义了相应的样式，
 * > 此时，第一层结构对应的是部件内部的同名样式，对该样式上的配置也将应用在其内部部件上，
 * > 而第二层结构则将在与部件内的同名样式的结构做合并后，再应用到其内部部件上，
 * > 从而实现对部件内部样式的部分调整。
 * >
 * > 如果是在基础样式集合中，则不需要定义两层结构，直接在第一层结构追加最终的原子样式配置即可，
 * > 因为，复合样式最终都将被展开为原子样式。
 * >
 * > 在样式结构中只能引用在同一定义集合中的原子样式或复合样式，且不能出现递归引用。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentStyleBase extends io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDef {
    

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

    public XuiComponentStyleBase cloneInstance(){
        XuiComponentStyleBase instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentStyleBase instance){
        super.copyTo(instance);
        
    }

    protected XuiComponentStyleBase newInstance(){
        return (XuiComponentStyleBase) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
