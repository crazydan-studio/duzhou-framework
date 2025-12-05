package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component.xdef <p>
 * > 一个视觉交互控件
 * >
 * > - 引用的变量名不能以 `$` 或 `_` 开头，否则，在动态生成组件树时，其会被视为全局变量，
 * >   而全局变量需要通过 `EvalGlobalRegistry#registerVariable` 注册；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponent extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  组件导入
     * xml name: import
     * >
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport> _imports = KeyedList.emptyList();
    
    /**
     *  组件树
     * xml name: template
     * > - 所有节点（包括 `<if/>`、`<for/>` 等控制节点）均以 `xui:name` 作为唯一性属性，从而支持对任意节点的差量定制；
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate _template ;
    
    /**
     * 组件导入
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
     * 组件树
     * xml name: template
     *  > - 所有节点（包括 `<if/>`、`<for/>` 等控制节点）均以 `xui:name` 作为唯一性属性，从而支持对任意节点的差量定制；
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
            
           this._template = io.nop.api.core.util.FreezeHelper.deepFreeze(this._template);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("imports",this.getImports());
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
        instance.setTemplate(this.getTemplate());
    }

    protected XuiComponent newInstance(){
        return (XuiComponent) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
