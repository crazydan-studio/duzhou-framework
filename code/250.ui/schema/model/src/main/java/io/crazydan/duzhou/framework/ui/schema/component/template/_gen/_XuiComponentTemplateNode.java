package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNode;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNode extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed {
    
    /**
     *  节点结构树
     * xml name: body
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeBody _body ;
    
    /**
     *  消息派发
     * xml name: dispatch
     * > 监听指定的事件，在事件触发时派发指定的消息。
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeDispatch> _dispatches = KeyedList.emptyList();
    
    /**
     *  布局控制
     * xml name: layout
     * > 根据 `xui:name` 对其**直接子组件**（含 `<slot/>` 和 `<native/>`，但不含 `<if/>`、`<for/>` 等控制节点）进行布局控制。
     * >
     * > 缺省将按照子组件声明顺序排列，并由 UI Vendor 做默认布局。
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout _layout ;
    
    /**
     * 节点结构树
     * xml name: body
     *  
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
     *  > 监听指定的事件，在事件触发时派发指定的消息。
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
     *  > 根据 `xui:name` 对其**直接子组件**（含 `<slot/>` 和 `<native/>`，但不含 `<if/>`、`<for/>` 等控制节点）进行布局控制。
     * >
     * > 缺省将按照子组件声明顺序排列，并由 UI Vendor 做默认布局。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout getLayout(){
      return _layout;
    }

    
    public void setLayout(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout value){
        checkAllowChange();
        
        this._layout = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._body = io.nop.api.core.util.FreezeHelper.deepFreeze(this._body);
            
           this._dispatches = io.nop.api.core.util.FreezeHelper.deepFreeze(this._dispatches);
            
           this._layout = io.nop.api.core.util.FreezeHelper.deepFreeze(this._layout);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("body",this.getBody());
        out.putNotNull("dispatches",this.getDispatches());
        out.putNotNull("layout",this.getLayout());
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
    }

    protected XuiComponentTemplateNode newInstance(){
        return (XuiComponentTemplateNode) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
