package io.crazydan.duzhou.framework.ui.schema.action._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.action.XuiActionHttpBase;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/action/node.xdef <p>
 * 发送 HTTP 请求
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiActionHttpBase extends io.crazydan.duzhou.framework.ui.schema.action.XuiActionNamed {
    
    /**
     *  
     * xml name: data
     * 请求携带的数据；
     */
    private java.lang.Object _data ;
    
    /**
     *  
     * xml name: error
     * 请求发生异常所触发的消息名称，并自动携带异常信息；
     */
    private java.lang.String _error ;
    
    /**
     *  
     * xml name: success
     * 请求成功所触发的消息名称，并自动携带响应数据；
     */
    private java.lang.String _success ;
    
    /**
     *  
     * xml name: url
     * 请求 URL 地址；
     */
    private java.lang.String _url ;
    
    /**
     * 
     * xml name: data
     *  请求携带的数据；
     */
    
    public java.lang.Object getData(){
      return _data;
    }

    
    public void setData(java.lang.Object value){
        checkAllowChange();
        
        this._data = value;
           
    }

    
    /**
     * 
     * xml name: error
     *  请求发生异常所触发的消息名称，并自动携带异常信息；
     */
    
    public java.lang.String getError(){
      return _error;
    }

    
    public void setError(java.lang.String value){
        checkAllowChange();
        
        this._error = value;
           
    }

    
    /**
     * 
     * xml name: success
     *  请求成功所触发的消息名称，并自动携带响应数据；
     */
    
    public java.lang.String getSuccess(){
      return _success;
    }

    
    public void setSuccess(java.lang.String value){
        checkAllowChange();
        
        this._success = value;
           
    }

    
    /**
     * 
     * xml name: url
     *  请求 URL 地址；
     */
    
    public java.lang.String getUrl(){
      return _url;
    }

    
    public void setUrl(java.lang.String value){
        checkAllowChange();
        
        this._url = value;
           
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
        out.putNotNull("error",this.getError());
        out.putNotNull("success",this.getSuccess());
        out.putNotNull("url",this.getUrl());
    }

    public XuiActionHttpBase cloneInstance(){
        XuiActionHttpBase instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiActionHttpBase instance){
        super.copyTo(instance);
        
        instance.setData(this.getData());
        instance.setError(this.getError());
        instance.setSuccess(this.getSuccess());
        instance.setUrl(this.getUrl());
    }

    protected XuiActionHttpBase newInstance(){
        return (XuiActionHttpBase) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
