package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentEach;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 遍历器 - each
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentEach extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {
    
    /**
     *  可选
     * xml name: index
     * 循环元素序号的变量名，如 `i`；
     */
    private java.lang.String _index ;
    
    /**
     *  必填
     * xml name: item
     * 循环元素的变量名，如 `user`；
     */
    private java.lang.String _item ;
    
    /**
     *  可选
     * xml name: key
     * 代表循环变量唯一性的取值表达式，如 `user.id`，即，`user` 元素的 `id` 属性值；
     */
    private java.lang.String _key ;
    
    /**
     *  必填
     * xml name: list
     * 待循环变量，如 `props.users`；
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.util.List> _list ;
    
    /**
     * 可选
     * xml name: index
     *  循环元素序号的变量名，如 `i`；
     */
    
    public java.lang.String getIndex(){
      return _index;
    }

    
    public void setIndex(java.lang.String value){
        checkAllowChange();
        
        this._index = value;
           
    }

    
    /**
     * 必填
     * xml name: item
     *  循环元素的变量名，如 `user`；
     */
    
    public java.lang.String getItem(){
      return _item;
    }

    
    public void setItem(java.lang.String value){
        checkAllowChange();
        
        this._item = value;
           
    }

    
    /**
     * 可选
     * xml name: key
     *  代表循环变量唯一性的取值表达式，如 `user.id`，即，`user` 元素的 `id` 属性值；
     */
    
    public java.lang.String getKey(){
      return _key;
    }

    
    public void setKey(java.lang.String value){
        checkAllowChange();
        
        this._key = value;
           
    }

    
    /**
     * 必填
     * xml name: list
     *  待循环变量，如 `props.users`；
     */
    
    public io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.util.List> getList(){
      return _list;
    }

    
    public void setList(io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.util.List> value){
        checkAllowChange();
        
        this._list = value;
           
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
        
        out.putNotNull("index",this.getIndex());
        out.putNotNull("item",this.getItem());
        out.putNotNull("key",this.getKey());
        out.putNotNull("list",this.getList());
    }

    public XuiComponentEach cloneInstance(){
        XuiComponentEach instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentEach instance){
        super.copyTo(instance);
        
        instance.setIndex(this.getIndex());
        instance.setItem(this.getItem());
        instance.setKey(this.getKey());
        instance.setList(this.getList());
    }

    protected XuiComponentEach newInstance(){
        return (XuiComponentEach) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
