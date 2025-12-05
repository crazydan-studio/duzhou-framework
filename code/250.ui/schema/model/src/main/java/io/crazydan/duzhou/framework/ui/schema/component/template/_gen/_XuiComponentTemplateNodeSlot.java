package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeSlot;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 组件插槽用于控制其嵌套子组件的嵌入位置。
 * >
 * > - 可以在插槽节点中嵌入其他组件（包括原生组件），用以作为该插槽位置的**缺省**嵌入内容；
 * > - 其缺省的内嵌结构同样支持条件、循环控制，以及消息派发和布局控制；
 * > - 不支持在其缺省的内嵌结构中嵌入 `<slot/>`；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeSlot extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNode {
    
    /**
     *  
     * xml name: attrs
     * 
     */
    private java.lang.Object _attrs ;
    
    /**
     *  
     * xml name: name
     * 
     */
    private java.lang.String _name  = "default";
    
    /**
     * 
     * xml name: attrs
     *  
     */
    
    public java.lang.Object getAttrs(){
      return _attrs;
    }

    
    public void setAttrs(java.lang.Object value){
        checkAllowChange();
        
        this._attrs = value;
           
    }

    
    /**
     * 
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
        
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("attrs",this.getAttrs());
        out.putNotNull("name",this.getName());
    }

    public XuiComponentTemplateNodeSlot cloneInstance(){
        XuiComponentTemplateNodeSlot instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeSlot instance){
        super.copyTo(instance);
        
        instance.setAttrs(this.getAttrs());
        instance.setName(this.getName());
    }

    protected XuiComponentTemplateNodeSlot newInstance(){
        return (XuiComponentTemplateNodeSlot) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
