package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component.xdef <p>
 * > 一个视觉交互控件。
 * >
 * > - 引用的变量名不能以 `$` 或 `_` 开头，否则，在动态生成组件树时，其会被视为全局变量，
 * >   而全局变量需要通过 `EvalGlobalRegistry#registerVariable` 注册；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponent extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  组件导入指令
     * xml name: import
     * >
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> _imports = KeyedList.emptyList();
    
    /**
     *  组件部件样式定义集
     * xml name: styles
     * > 为组件的部件定义相应的样式。
     */
    private io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles _styles ;
    
    /**
     *  组件结构
     * xml name: template
     * > 用于定义组件的组成结构，并对其部件进行布局控制和样式设定。
     * >
     * > 组件组成结构 `<body/>` 中的节点称为**结构节点**，其包含 `<if/>`、`<for/>` 等**控制节点**，
     * > 以及由导入的外部组件实例组成的**组件节点**，其中，组件节点也称为当前组件的组成**部件**。
     * > 部件为组件的核心组成元素，其为组件的视觉呈现，并负责与用户的交互响应。
     * >
     * > 组件的结构节点均以 `xui:name` 作为唯一属性，以支持对其结构中的任意节点进行差量定制。
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate _template ;
    
    /**
     * 组件导入指令
     * xml name: import
     *  >
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> getImports(){
      return _imports;
    }

    
    public void setImports(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> value){
        checkAllowChange();
        
        this._imports = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport::getAs);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport getImport(String name){
        return this._imports.getByKey(name);
    }

    public boolean hasImport(String name){
        return this._imports.containsKey(name);
    }

    public void addImport(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> list = this.getImports();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport::getAs);
            setImports(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_imports(){
        return this._imports.keySet();
    }

    public boolean hasImports(){
        return !this._imports.isEmpty();
    }
    
    /**
     * 组件部件样式定义集
     * xml name: styles
     *  > 为组件的部件定义相应的样式。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles getStyles(){
      return _styles;
    }

    
    public void setStyles(io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles value){
        checkAllowChange();
        
        this._styles = value;
           
    }

    
    /**
     * 组件结构
     * xml name: template
     *  > 用于定义组件的组成结构，并对其部件进行布局控制和样式设定。
     * >
     * > 组件组成结构 `<body/>` 中的节点称为**结构节点**，其包含 `<if/>`、`<for/>` 等**控制节点**，
     * > 以及由导入的外部组件实例组成的**组件节点**，其中，组件节点也称为当前组件的组成**部件**。
     * > 部件为组件的核心组成元素，其为组件的视觉呈现，并负责与用户的交互响应。
     * >
     * > 组件的结构节点均以 `xui:name` 作为唯一属性，以支持对其结构中的任意节点进行差量定制。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate getTemplate(){
      return _template;
    }

    
    public void setTemplate(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate value){
        checkAllowChange();
        
        this._template = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._imports = io.nop.api.core.util.FreezeHelper.deepFreeze(this._imports);
            
           this._styles = io.nop.api.core.util.FreezeHelper.deepFreeze(this._styles);
            
           this._template = io.nop.api.core.util.FreezeHelper.deepFreeze(this._template);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("imports",this.getImports());
        out.putNotNull("styles",this.getStyles());
        out.putNotNull("template",this.getTemplate());
    }

    public XuiComponent cloneInstance(){
        XuiComponent instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponent instance){
        super.copyTo(instance);
        
        instance.setImports(this.getImports());
        instance.setStyles(this.getStyles());
        instance.setTemplate(this.getTemplate());
    }

    protected XuiComponent newInstance(){
        return (XuiComponent) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
