package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiStyleVar;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/themes.xdef <p>
 * 变量定义，标签文本内容为变量值
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleVar extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  必填
     * xml name: type
     * 变量类型
     */
    private java.lang.String _type ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _value ;
    
    /**
     * 必填
     * xml name: type
     *  变量类型
     */
    
    public java.lang.String getType(){
      return _type;
    }

    
    public void setType(java.lang.String value){
        checkAllowChange();
        
        this._type = value;
           
    }

    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.lang.String getValue(){
      return _value;
    }

    
    public void setValue(java.lang.String value){
        checkAllowChange();
        
        this._value = value;
           
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
        
        out.putNotNull("type",this.getType());
        out.putNotNull("value",this.getValue());
    }

    public XuiStyleVar cloneInstance(){
        XuiStyleVar instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleVar instance){
        super.copyTo(instance);
        
        instance.setType(this.getType());
        instance.setValue(this.getValue());
    }

    protected XuiStyleVar newInstance(){
        return (XuiStyleVar) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
