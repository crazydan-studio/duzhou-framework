package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentActionDispatch;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/action.xdef <p>
 * TODO 限定 type 为驼峰形式（首字母大写、可包含下划线）
 * 若有多个消息，则合并为组合消息后，再发送
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentActionDispatch extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: data
     * 
     */
    private java.lang.Object _data ;
    
    /**
     *  
     * xml name: type
     * 
     */
    private java.lang.String _type ;
    
    /**
     * 
     * xml name: data
     *  
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
     * xml name: type
     *  
     */
    
    public java.lang.String getType(){
      return _type;
    }

    
    public void setType(java.lang.String value){
        checkAllowChange();
        
        this._type = value;
           
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
        out.putNotNull("type",this.getType());
    }

    public XuiComponentActionDispatch cloneInstance(){
        XuiComponentActionDispatch instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentActionDispatch instance){
        super.copyTo(instance);
        
        instance.setData(this.getData());
        instance.setType(this.getType());
    }

    protected XuiComponentActionDispatch newInstance(){
        return (XuiComponentActionDispatch) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
