package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeBody;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 由导入的外部组件实例构成的结构树，其仅包含控制节点和组件节点。
 * >
 * > 控制节点根据组件内部状态和外部设定的属性数据控制组件节点在何种状态下挂载到结构树中，
 * > 从而支持**数据响应式**的组件渲染。
 * >
 * > 而组件节点仅表示当前组件由哪些元素组成，而以何种视觉效果呈现则由 `<layout/>` 单独控制，
 * > 在结构树中不包含与布局相关的节点和样式，组件节点之间的先后顺序也不代表其显示的位置。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeBody extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed {
    
    /**
     *  
     * xml name: 
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed> _children = KeyedList.emptyList();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed> value){
        checkAllowChange();
        
        this._children = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed getChild(String name){
        return this._children.getByKey(name);
    }

    public boolean hasChild(String name){
        return this._children.containsKey(name);
    }

    public void addChild(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed> list = this.getChildren();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed::getXuiName);
            setChildren(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_children(){
        return this._children.keySet();
    }

    public boolean hasChildren(){
        return !this._children.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._children = io.nop.api.core.util.FreezeHelper.deepFreeze(this._children);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("children",this.getChildren());
    }

    public XuiComponentTemplateNodeBody cloneInstance(){
        XuiComponentTemplateNodeBody instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeBody instance){
        super.copyTo(instance);
        
        instance.setChildren(this.getChildren());
    }

    protected XuiComponentTemplateNodeBody newInstance(){
        return (XuiComponentTemplateNodeBody) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
