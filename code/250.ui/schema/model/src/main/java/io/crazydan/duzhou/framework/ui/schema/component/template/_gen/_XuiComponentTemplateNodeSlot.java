package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeSlot;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 对于其实例可嵌入子节点的组件，可通过 `<slot/>` 控制其实例的内嵌节点的插入位置。
 * > 其 `name` 与组件实例的子节点上的 `xui:slot` 相对应，最终，与 `name` 同值的
 * > `xui:slot` 节点将被嵌入在该 `<slot/>` 所在的位置，从而实现组件结构的动态性。
 * >
 * > 在 `<slot/>` 内可提供缺省内容，在上层组件没有为该组件实例放置对应名字的
 * > `xui:slot` 节点时，将在该位置插入该缺省内容。
 * >
 * > 注意：
 * > - 不支持 `<slot/>` 嵌套使用；
 * > - 在同一层级内，不能出现相同 `name` 值的 `<slot/>`；
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
     *  插槽名
     * xml name: name
     * > 通过名字查找并确定所要插入的节点。
     * > 缺省为 `default`，即组件实例内的所有子节点均按其定义顺序插入该位置
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
     * 插槽名
     * xml name: name
     *  > 通过名字查找并确定所要插入的节点。
     * > 缺省为 `default`，即组件实例内的所有子节点均按其定义顺序插入该位置
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
