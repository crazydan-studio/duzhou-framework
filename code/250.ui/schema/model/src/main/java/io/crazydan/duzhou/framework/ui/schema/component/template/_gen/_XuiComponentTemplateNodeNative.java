package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNative;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 原生组件一般**仅用在**基础组件内，并由 UI Vendor 根据组件配置提供具体的实现。
 * > 在基础组件之上扩展构造各种功能组件，再在功能组件之上定制业务组件。
 * >
 * > - 可以在原生组件内嵌入其他组件（含基础组件、原生组件）；
 * > - 其内嵌结构支持条件、循环控制，以及消息派发和布局控制；
 * > - 除 `name` 外，由 UI Vendor 自行根据原生组件的支持情况添加额外的配置属性，
 * >   且可通过 `${xxx}` 表达式引用变量；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeNative extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeSlottable {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,java.lang.Object> _attrs ;
    
    /**
     *  原生组件名
     * xml name: name
     * 
     */
    private java.lang.String _name ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.Map<java.lang.String,java.lang.Object> getAttrs(){
      return _attrs;
    }

    
    public void setAttrs(java.util.Map<java.lang.String,java.lang.Object> value){
        checkAllowChange();
        
        this._attrs = value;
           
    }

    
    public boolean hasAttrs(){
        return this._attrs != null && !this._attrs.isEmpty();
    }
    
    /**
     * 原生组件名
     * xml name: name
     *  
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
        
           this._attrs = io.nop.api.core.util.FreezeHelper.deepFreeze(this._attrs);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("attrs",this.getAttrs());
        out.putNotNull("name",this.getName());
    }

    public XuiComponentTemplateNodeNative cloneInstance(){
        XuiComponentTemplateNodeNative instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeNative instance){
        super.copyTo(instance);
        
        instance.setAttrs(this.getAttrs());
        instance.setName(this.getName());
    }

    protected XuiComponentTemplateNodeNative newInstance(){
        return (XuiComponentTemplateNodeNative) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
