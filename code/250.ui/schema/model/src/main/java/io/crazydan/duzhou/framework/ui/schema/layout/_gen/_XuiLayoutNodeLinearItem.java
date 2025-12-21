package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeLinearItem;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > - 布局项本身也是**布局节点**，但其不可嵌套其他布局节点，其为布局树的终点；
 * > - 布局项所对应的是确定的**待布局目标**，比如组件；
 * > - 通过 `pattern` 其可以匹配多个待布局目标，且这些目标拥有相同的布局配置，
 * >   一般用于布局 `<for/>` 循环产生的节点；
 * > - 若 `pattern` 未匹配到任何待布局目标，则该布局项将被视为不存在，其不影响其他布局节点；
 * > - 若某个/某些待布局目标没有被任何布局项匹配，则其不参与布局，也不存在于 UI 树中；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeLinearItem extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStyled {
    
    /**
     *  匹配规则
     * xml name: pattern
     * > 用于匹配待布局目标唯一名字 `xui:name` 的**正则表达式**
     */
    private java.lang.String _pattern ;
    
    /**
     * 匹配规则
     * xml name: pattern
     *  > 用于匹配待布局目标唯一名字 `xui:name` 的**正则表达式**
     */
    
    public java.lang.String getPattern(){
      return _pattern;
    }

    
    public void setPattern(java.lang.String value){
        checkAllowChange();
        
        this._pattern = value;
           
    }

    

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
        
        out.putNotNull("pattern",this.getPattern());
    }

    public XuiLayoutNodeLinearItem cloneInstance(){
        XuiLayoutNodeLinearItem instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeLinearItem instance){
        super.copyTo(instance);
        
        instance.setPattern(this.getPattern());
    }

    protected XuiLayoutNodeLinearItem newInstance(){
        return (XuiLayoutNodeLinearItem) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
