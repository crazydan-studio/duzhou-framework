package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementFor;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * >
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeStatementFor extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNested {
    
    /**
     *  
     * xml name: begin
     * 
     */
    private java.lang.Integer _begin ;
    
    /**
     *  
     * xml name: end
     * 
     */
    private java.lang.Integer _end ;
    
    /**
     *  循环元素序号的变量名
     * xml name: index
     * > 如 `idx`
     */
    private java.lang.String _index ;
    
    /**
     *  待循环变量
     * xml name: items
     * > 如 `${props.users}`
     */
    private java.lang.String _items ;
    
    /**
     *  循环元素的变量名
     * xml name: var
     * > 如 `user`
     */
    private java.lang.String _var ;
    
    /**
     * 
     * xml name: begin
     *  
     */
    
    public java.lang.Integer getBegin(){
      return _begin;
    }

    
    public void setBegin(java.lang.Integer value){
        checkAllowChange();
        
        this._begin = value;
           
    }

    
    /**
     * 
     * xml name: end
     *  
     */
    
    public java.lang.Integer getEnd(){
      return _end;
    }

    
    public void setEnd(java.lang.Integer value){
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
     *  > 如 `${props.users}`
     */
    
    public java.lang.String getItems(){
      return _items;
    }

    
    public void setItems(java.lang.String value){
        checkAllowChange();
        
        this._items = value;
           
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
        instance.setVar(this.getVar());
    }

    protected XuiComponentTemplateNodeStatementFor newInstance(){
        return (XuiComponentTemplateNodeStatementFor) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
