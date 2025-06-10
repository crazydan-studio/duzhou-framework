package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessageDispatch;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 监听组件内的交互事件（原生视图元素的事件或组件自定义事件）并派发业务消息给当前组件
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentMessageDispatch extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
    /**
     *  消息所携带的数据
     * xml name: data
     * > (可选) 以 `${xxx}` 形式构造数据，并可通过 `$event` 引用事件对象上的数据
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> _data ;
    
    /**
     *  消息类型
     * xml name: msg
     * > (必填) 如 `User_Login_Start`、`User_Login_Finish` 等
     */
    private java.lang.String _msg ;
    
    /**
     *  事件名称
     * xml name: when
     * > (必填) 如 `click`、`input` 等
     */
    private java.lang.String _when ;
    
    /**
     * 消息所携带的数据
     * xml name: data
     *  > (可选) 以 `${xxx}` 形式构造数据，并可通过 `$event` 引用事件对象上的数据
     */
    
    public io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> getData(){
      return _data;
    }

    
    public void setData(io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> value){
        checkAllowChange();
        
        this._data = value;
           
    }

    
    /**
     * 消息类型
     * xml name: msg
     *  > (必填) 如 `User_Login_Start`、`User_Login_Finish` 等
     */
    
    public java.lang.String getMsg(){
      return _msg;
    }

    
    public void setMsg(java.lang.String value){
        checkAllowChange();
        
        this._msg = value;
           
    }

    
    /**
     * 事件名称
     * xml name: when
     *  > (必填) 如 `click`、`input` 等
     */
    
    public java.lang.String getWhen(){
      return _when;
    }

    
    public void setWhen(java.lang.String value){
        checkAllowChange();
        
        this._when = value;
           
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
        out.putNotNull("msg",this.getMsg());
        out.putNotNull("when",this.getWhen());
    }

    public XuiComponentMessageDispatch cloneInstance(){
        XuiComponentMessageDispatch instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentMessageDispatch instance){
        super.copyTo(instance);
        
        instance.setData(this.getData());
        instance.setMsg(this.getMsg());
        instance.setWhen(this.getWhen());
    }

    protected XuiComponentMessageDispatch newInstance(){
        return (XuiComponentMessageDispatch) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
