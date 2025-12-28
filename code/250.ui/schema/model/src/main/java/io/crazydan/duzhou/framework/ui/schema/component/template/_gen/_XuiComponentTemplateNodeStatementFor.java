package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementFor;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 由导入的外部组件实例构成的结构树，其仅包含控制节点和组件节点。
 * >
 * > 控制节点根据组件内部状态和外部设定的属性数据控制组件节点在何种状态下挂载到结构树中，
 * > 从而支持**数据响应式**的组件渲染。
 * >
 * > 而组件节点仅表示当前组件由哪些元素组成，而以何种视觉效果呈现则由 `<layout/>` 单独控制，
 * > 在结构树中不包含与布局相关的节点和样式，组件节点之间的先后顺序也不代表其显示的位置。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeStatementFor extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeBody implements io.crazydan.duzhou.framework.ui.statement.XuiStatementFor{
    
    /**
     *  循环起始值
     * xml name: begin
     * > 按数值循环时的初始值，如 `0`
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiExpr _begin ;
    
    /**
     *  循环结束值
     * xml name: end
     * > 按数值循环时的结束值，如 `10`
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiExpr _end ;
    
    /**
     *  循环元素序号的变量名
     * xml name: index
     * > 如 `idx`
     */
    private java.lang.String _index ;
    
    /**
     *  待循环变量
     * xml name: items
     * > 列表类型的变量，依次遍历其中的元素，如 `${props.users}`
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiExpr _items ;
    
    /**
     *  循环步进值
     * xml name: step
     * > 按数值循环时的步进值，如 `2`
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiExpr _step ;
    
    /**
     *  循环元素的变量名
     * xml name: var
     * > 如 `user`
     */
    private java.lang.String _var ;
    
    /**
     * 循环起始值
     * xml name: begin
     *  > 按数值循环时的初始值，如 `0`
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiExpr getBegin(){
      return _begin;
    }

    
    public void setBegin(io.crazydan.duzhou.framework.ui.domain.type.XuiExpr value){
        checkAllowChange();
        
        this._begin = value;
           
    }

    
    /**
     * 循环结束值
     * xml name: end
     *  > 按数值循环时的结束值，如 `10`
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiExpr getEnd(){
      return _end;
    }

    
    public void setEnd(io.crazydan.duzhou.framework.ui.domain.type.XuiExpr value){
        checkAllowChange();
        
        this._end = value;
           
    }

    
    /**
     * 循环元素序号的变量名
     * xml name: index
     *  > 如 `idx`
     */
    
    public java.lang.String getIndex(){
      return _index;
    }

    
    public void setIndex(java.lang.String value){
        checkAllowChange();
        
        this._index = value;
           
    }

    
    /**
     * 待循环变量
     * xml name: items
     *  > 列表类型的变量，依次遍历其中的元素，如 `${props.users}`
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiExpr getItems(){
      return _items;
    }

    
    public void setItems(io.crazydan.duzhou.framework.ui.domain.type.XuiExpr value){
        checkAllowChange();
        
        this._items = value;
           
    }

    
    /**
     * 循环步进值
     * xml name: step
     *  > 按数值循环时的步进值，如 `2`
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiExpr getStep(){
      return _step;
    }

    
    public void setStep(io.crazydan.duzhou.framework.ui.domain.type.XuiExpr value){
        checkAllowChange();
        
        this._step = value;
           
    }

    
    /**
     * 循环元素的变量名
     * xml name: var
     *  > 如 `user`
     */
    
    public java.lang.String getVar(){
      return _var;
    }

    
    public void setVar(java.lang.String value){
        checkAllowChange();
        
        this._var = value;
           
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
        
        out.putNotNull("begin",this.getBegin());
        out.putNotNull("end",this.getEnd());
        out.putNotNull("index",this.getIndex());
        out.putNotNull("items",this.getItems());
        out.putNotNull("step",this.getStep());
        out.putNotNull("var",this.getVar());
    }

    public XuiComponentTemplateNodeStatementFor cloneInstance(){
        XuiComponentTemplateNodeStatementFor instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeStatementFor instance){
        super.copyTo(instance);
        
        instance.setBegin(this.getBegin());
        instance.setEnd(this.getEnd());
        instance.setIndex(this.getIndex());
        instance.setItems(this.getItems());
        instance.setStep(this.getStep());
        instance.setVar(this.getVar());
    }

    protected XuiComponentTemplateNodeStatementFor newInstance(){
        return (XuiComponentTemplateNodeStatementFor) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
