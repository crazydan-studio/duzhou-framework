package io.crazydan.duzhou.framework.ui.schema.component._gen;

import java.util.List;

import io.crazydan.duzhou.framework.ui.XuiExpression;
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentEach;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 限定可被嵌套的节点，其中：
 * > - 内置组件 `Text` 可被嵌套，但其子节点只能为 HTML 文本，不可嵌套其他组件；
 * > - 内置组件 `Validation` 可被嵌套，且其可自嵌套，也可嵌套其他组件；
 * > - 内置组件 `Animation` 可被嵌套，且其可自嵌套，也可嵌套其他组件；
 * > - 自定义组件可被嵌套，且可嵌套其他组件；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentEach extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {

    /**
     *  循环元素序号的变量名
     * xml name: index
     * > (可选) 如 `i`
     */
    private java.lang.String _index ;

    /**
     *  循环元素的变量名
     * xml name: item
     * > (必填) 如 `user`
     */
    private java.lang.String _item ;

    /**
     *  代表循环变量唯一性的取值表达式
     * xml name: key
     * > (可选) 如 `user.id`，即，`user` 元素的 `id` 属性值
     */
    private java.lang.String _key ;

    /**
     *  待循环变量
     * xml name: list
     * > (必填) 如 `props.users`
     */
    private XuiExpression<List> _list ;

    /**
     * 循环元素序号的变量名
     * xml name: index
     *  > (可选) 如 `i`
     */

    public java.lang.String getIndex(){
      return _index;
    }


    public void setIndex(java.lang.String value){
        checkAllowChange();

        this._index = value;

    }


    /**
     * 循环元素的变量名
     * xml name: item
     *  > (必填) 如 `user`
     */

    public java.lang.String getItem(){
      return _item;
    }


    public void setItem(java.lang.String value){
        checkAllowChange();

        this._item = value;

    }


    /**
     * 代表循环变量唯一性的取值表达式
     * xml name: key
     *  > (可选) 如 `user.id`，即，`user` 元素的 `id` 属性值
     */

    public java.lang.String getKey(){
      return _key;
    }


    public void setKey(java.lang.String value){
        checkAllowChange();

        this._key = value;

    }


    /**
     * 待循环变量
     * xml name: list
     *  > (必填) 如 `props.users`
     */

    public XuiExpression<List> getList(){
      return _list;
    }


    public void setList(XuiExpression<List> value){
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
