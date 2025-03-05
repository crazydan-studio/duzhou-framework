package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiStyle;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/styles.xdef <p>
 * 样式的组成结构节点，称其为**结构节点**
 * - 只能引用在上下文环境中能够访问到的其他原子或复合样式
 * - 在上下文中无法得到其定义的样式，视为原子样式，其将直接映射到运行时环境中的原生样式
 * - 在结构节点上设置的属性以 `${color}` 形式引用其所在复合样式上定义的同名属性 `color`
 * - 在结构节点属性上设置的引用值或确定值，必须与其样式定义的属性类型一致
 * - 复合样式的结构节点，在最终将被层层展开直到仅由原子样式组成，展开过程称为**结构展开**
 * - 按结构节点定义的顺序依次合并各层级的原子样式，最终得到仅为原子样式的结构节点
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
