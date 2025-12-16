package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNode;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 包含布局、消息、样式，及其内嵌结构。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNode extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed {
    
    /**
     *  内嵌结构
     * xml name: body
     * > 由导入的外部组件实例构成的结构树，其仅包含控制节点和组件节点。
     * >
     * > 控制节点根据组件内部状态和外部设定的属性数据控制组件节点在何种状态下挂载到结构树中，
     * > 从而支持**数据响应式**的组件渲染。
     * >
     * > 而组件节点仅表示当前组件由哪些元素组成，而以何种视觉效果呈现则由 `<layout/>` 单独控制，
     * > 在结构树中不包含与布局相关的节点和样式，组件节点之间的先后顺序也不代表其显示的位置。
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeBody _body ;
    
    /**
     *  消息派发
     * xml name: dispatch
     * > 监听部件上的指定事件，并在事件触发时派发指定的消息。
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch> _dispatches = KeyedList.emptyList();
    
    /**
     *  布局控制
     * xml name: layout
     * > 根据 `xui:name` 对组件结构树中的直接**部件**节点（含 `<slot/>` 和 `<native/>`）进行布局控制。
     * > 对于 `<body/>` 下的非直接部件节点，则需要在其所在的父节点中单独通过 `<layout/>` 进行控制，
     * > 也就是布局控制**不能跨层级**作用于部件。
     * >
     * > 没有在布局中显式控制的组件将不会被布局，也就不会呈现出来。
     * >
     * > 缺省将按照部件的声明顺序排列，并由 UI Vendor 做默认布局。
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout _layout ;
    
    /**
     *  部件样式
     * xml name: styles
     * > 引用在当前组件 `<styles/>` 中所定义的**部件样式**，其子节点标签名必须与已定义的样式名一致，
     * > 且只能配置在该样式上所声明的属性。
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStyles _styles ;
    
    /**
     * 内嵌结构
     * xml name: body
     *  > 由导入的外部组件实例构成的结构树，其仅包含控制节点和组件节点。
     * >
     * > 控制节点根据组件内部状态和外部设定的属性数据控制组件节点在何种状态下挂载到结构树中，
     * > 从而支持**数据响应式**的组件渲染。
     * >
     * > 而组件节点仅表示当前组件由哪些元素组成，而以何种视觉效果呈现则由 `<layout/>` 单独控制，
     * > 在结构树中不包含与布局相关的节点和样式，组件节点之间的先后顺序也不代表其显示的位置。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeBody getBody(){
      return _body;
    }

    
    public void setBody(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeBody value){
        checkAllowChange();
        
        this._body = value;
           
    }

    
    /**
     * 消息派发
     * xml name: dispatch
     *  > 监听部件上的指定事件，并在事件触发时派发指定的消息。
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch> getDispatches(){
      return _dispatches;
    }

    
    public void setDispatches(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch> value){
        checkAllowChange();
        
        this._dispatches = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch::getMsg);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch getDispatch(String name){
        return this._dispatches.getByKey(name);
    }

    public boolean hasDispatch(String name){
        return this._dispatches.containsKey(name);
    }

    public void addDispatch(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch> list = this.getDispatches();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch::getMsg);
            setDispatches(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_dispatches(){
        return this._dispatches.keySet();
    }

    public boolean hasDispatches(){
        return !this._dispatches.isEmpty();
    }
    
    /**
     * 布局控制
     * xml name: layout
     *  > 根据 `xui:name` 对组件结构树中的直接**部件**节点（含 `<slot/>` 和 `<native/>`）进行布局控制。
     * > 对于 `<body/>` 下的非直接部件节点，则需要在其所在的父节点中单独通过 `<layout/>` 进行控制，
     * > 也就是布局控制**不能跨层级**作用于部件。
     * >
     * > 没有在布局中显式控制的组件将不会被布局，也就不会呈现出来。
     * >
     * > 缺省将按照部件的声明顺序排列，并由 UI Vendor 做默认布局。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout getLayout(){
      return _layout;
    }

    
    public void setLayout(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout value){
        checkAllowChange();
        
        this._layout = value;
           
    }

    
    /**
     * 部件样式
     * xml name: styles
     *  > 引用在当前组件 `<styles/>` 中所定义的**部件样式**，其子节点标签名必须与已定义的样式名一致，
     * > 且只能配置在该样式上所声明的属性。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStyles getStyles(){
      return _styles;
    }

    
    public void setStyles(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStyles value){
        checkAllowChange();
        
        this._styles = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._body = io.nop.api.core.util.FreezeHelper.deepFreeze(this._body);
            
           this._dispatches = io.nop.api.core.util.FreezeHelper.deepFreeze(this._dispatches);
            
           this._layout = io.nop.api.core.util.FreezeHelper.deepFreeze(this._layout);
            
           this._styles = io.nop.api.core.util.FreezeHelper.deepFreeze(this._styles);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("body",this.getBody());
        out.putNotNull("dispatches",this.getDispatches());
        out.putNotNull("layout",this.getLayout());
        out.putNotNull("styles",this.getStyles());
    }

    public XuiComponentTemplateNode cloneInstance(){
        XuiComponentTemplateNode instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNode instance){
        super.copyTo(instance);
        
        instance.setBody(this.getBody());
        instance.setDispatches(this.getDispatches());
        instance.setLayout(this.getLayout());
        instance.setStyles(this.getStyles());
    }

    protected XuiComponentTemplateNode newInstance(){
        return (XuiComponentTemplateNode) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
