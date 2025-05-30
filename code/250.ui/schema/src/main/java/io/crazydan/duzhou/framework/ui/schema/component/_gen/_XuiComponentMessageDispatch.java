package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessageDispatch;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 消息派发器：监听组件内的交互事件（原生视图元素的事件或组件自定义事件）并派发业务消息给当前组件
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentMessageDispatch extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
    /**
     *  可选
     * xml name: data
     * 消息所携带的数据，以 `${xxx}` 形式构造数据，并可通过 `$event` 引用事件对象上的数据；
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> _data ;
    
    /**
     *  必填
     * xml name: for
     * 消息类型，如 `User_Login_Start`、`User_Login_Finish` 等；
     */
    private java.lang.String _for ;
    
    /**
     *  必填
     * xml name: on
     * 事件名称，如 `click`、`input` 等；
     */
    private java.lang.String _on ;
    
    /**
     * 可选
     * xml name: data
     *  消息所携带的数据，以 `${xxx}` 形式构造数据，并可通过 `$event` 引用事件对象上的数据；
     */
    
    public io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> getData(){
      return _data;
    }

    
    public void setData(io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> value){
        checkAllowChange();
        
        this._data = value;
           
    }

    
    /**
     * 必填
     * xml name: for
     *  消息类型，如 `User_Login_Start`、`User_Login_Finish` 等；
     */
    
    public java.lang.String getFor(){
      return _for;
    }

    
    public void setFor(java.lang.String value){
        checkAllowChange();
        
        this._for = value;
           
    }

    
    /**
     * 必填
     * xml name: on
     *  事件名称，如 `click`、`input` 等；
     */
    
    public java.lang.String getOn(){
      return _on;
    }

    
    public void setOn(java.lang.String value){
        checkAllowChange();
        
        this._on = value;
           
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
        
        out.putNotNull("data",this.getData());
        out.putNotNull("for",this.getFor());
        out.putNotNull("on",this.getOn());
    }

    public XuiComponentMessageDispatch cloneInstance(){
        XuiComponentMessageDispatch instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentMessageDispatch instance){
        super.copyTo(instance);
        
        instance.setData(this.getData());
        instance.setFor(this.getFor());
        instance.setOn(this.getOn());
    }

    protected XuiComponentMessageDispatch newInstance(){
        return (XuiComponentMessageDispatch) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
