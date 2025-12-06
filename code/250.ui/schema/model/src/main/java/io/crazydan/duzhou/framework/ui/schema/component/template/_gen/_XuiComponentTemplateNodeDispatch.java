package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.crazydan.duzhou.framework.ui.XuiNamed;
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 监听指定的事件，在事件触发时派发指定的消息。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeDispatch extends io.nop.core.resource.component.AbstractComponentModel implements
                                                                                                                      XuiNamed {

    /**
     *
     * xml name:
     *
     */
    private java.lang.String _$tag ;

    /**
     *  消息所携带的数据
     * xml name: data
     * > 以 `${xxx}` 形式构造数据，并可通过 `$event` 引用事件对象上的数据
     */
    private java.lang.Object _data ;

    /**
     *  事件名称
     * xml name: events
     * > 待监听的事件，如 `click`、`input` 等。若有多个事件，则通过逗号分隔
     */
    private java.util.Set<java.lang.String> _events ;

    /**
     *  消息名
     * xml name: msg
     * > 待派发的消息，如 `User_Login_Start`、`User_Login_Finish` 等
     */
    private java.lang.String _msg ;

    /**
     *
     * xml name:
     *
     */

    public java.lang.String get$tag(){
      return _$tag;
    }


    public void set$tag(java.lang.String value){
        checkAllowChange();

        this._$tag = value;

    }


    /**
     * 消息所携带的数据
     * xml name: data
     *  > 以 `${xxx}` 形式构造数据，并可通过 `$event` 引用事件对象上的数据
     */

    public java.lang.Object getData(){
      return _data;
    }


    public void setData(java.lang.Object value){
        checkAllowChange();

        this._data = value;

    }


    /**
     * 事件名称
     * xml name: events
     *  > 待监听的事件，如 `click`、`input` 等。若有多个事件，则通过逗号分隔
     */

    public java.util.Set<java.lang.String> getEvents(){
      return _events;
    }


    public void setEvents(java.util.Set<java.lang.String> value){
        checkAllowChange();

        this._events = value;

    }


    /**
     * 消息名
     * xml name: msg
     *  > 待派发的消息，如 `User_Login_Start`、`User_Login_Finish` 等
     */

    public java.lang.String getMsg(){
      return _msg;
    }


    public void setMsg(java.lang.String value){
        checkAllowChange();

        this._msg = value;

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

        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("data",this.getData());
        out.putNotNull("events",this.getEvents());
        out.putNotNull("msg",this.getMsg());
    }

    public XuiComponentTemplateNodeDispatch cloneInstance(){
        XuiComponentTemplateNodeDispatch instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeDispatch instance){
        super.copyTo(instance);

        instance.set$tag(this.get$tag());
        instance.setData(this.getData());
        instance.setEvents(this.getEvents());
        instance.setMsg(this.getMsg());
    }

    protected XuiComponentTemplateNodeDispatch newInstance(){
        return (XuiComponentTemplateNodeDispatch) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
