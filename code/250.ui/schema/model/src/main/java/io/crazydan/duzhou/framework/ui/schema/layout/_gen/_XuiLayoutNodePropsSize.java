package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodePropsSize extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  类型
     * xml name: type
     * > 注意，在 `type` 为 `value-specified` 时，必须指定该节点的值，
     * > 如 `<width type="value-specified">12u</width>`
     */
    private io.crazydan.duzhou.framework.ui.layout.XuiLayoutSize _type ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _value ;
    
    /**
     * 类型
     * xml name: type
     *  > 注意，在 `type` 为 `value-specified` 时，必须指定该节点的值，
     * > 如 `<width type="value-specified">12u</width>`
     */
    
    public io.crazydan.duzhou.framework.ui.layout.XuiLayoutSize getType(){
      return _type;
    }

    
    public void setType(io.crazydan.duzhou.framework.ui.layout.XuiLayoutSize value){
        checkAllowChange();
        
        this._type = value;
           
    }

    
    /**
     * 
     * xml name: 
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getValue(){
      return _value;
    }

    
    public void setValue(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
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

    public XuiLayoutNodePropsSize cloneInstance(){
        XuiLayoutNodePropsSize instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodePropsSize instance){
        super.copyTo(instance);
        
        instance.setType(this.getType());
        instance.setValue(this.getValue());
    }

    protected XuiLayoutNodePropsSize newInstance(){
        return (XuiLayoutNodePropsSize) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
