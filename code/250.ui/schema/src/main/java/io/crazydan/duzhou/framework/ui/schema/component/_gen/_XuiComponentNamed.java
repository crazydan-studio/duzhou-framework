package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 组件命名元素
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentNamed extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$type ;
    
    /**
     *  必填
     * xml name: name
     * 组件组成元素的名字，其将作为差量运算的定位坐标，在相同父节点范围内具备唯一性。
     * 一般采用 `{业务数据名}-{组件类型名}` 形式命名；
     */
    private java.lang.String _name ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.lang.String get$type(){
      return _$type;
    }

    
    public void set$type(java.lang.String value){
        checkAllowChange();
        
        this._$type = value;
           
    }

    
    /**
     * 必填
     * xml name: name
     *  组件组成元素的名字，其将作为差量运算的定位坐标，在相同父节点范围内具备唯一性。
     * 一般采用 `{业务数据名}-{组件类型名}` 形式命名；
     */
    
    public java.lang.String getName(){
      return _name;
    }

    
    public void setName(java.lang.String value){
        checkAllowChange();
        
        this._name = value;
           
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
        
        out.putNotNull("$type",this.get$type());
        out.putNotNull("name",this.getName());
    }

    public XuiComponentNamed cloneInstance(){
        XuiComponentNamed instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentNamed instance){
        super.copyTo(instance);
        
        instance.set$type(this.get$type());
        instance.setName(this.getName());
    }

    protected XuiComponentNamed newInstance(){
        return (XuiComponentNamed) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
