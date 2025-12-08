package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStacked;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 在 z 轴上对**待布局目标**进行布局。
 * > 若某层涉及复杂布局，则需要将该层组件全部放在同一个容器组件节点中，
 * > 再对该容器组件与其他兄弟节点做堆叠布局。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutStacked extends io.nop.core.resource.component.AbstractComponentModel implements io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutRoot{
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     *  
     * xml name: body
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem> _body = KeyedList.emptyList();
    
    /**
     *  布局配置
     * xml name: props
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeSelfProps _props ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.lang.String get$tag(){
      return _$tag;
    }

    
    public void set$tag(java.lang.String value){
        checkAllowChange();
        
        this._$tag = value;
           
    }

    
    /**
     * 
     * xml name: body
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem> getBody(){
      return _body;
    }

    
    public void setBody(java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem> value){
        checkAllowChange();
        
        this._body = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem getItem(String name){
        return this._body.getByKey(name);
    }

    public boolean hasItem(String name){
        return this._body.containsKey(name);
    }

    public void addItem(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem> list = this.getBody();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem::getXuiName);
            setBody(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_body(){
        return this._body.keySet();
    }

    public boolean hasBody(){
        return !this._body.isEmpty();
    }
    
    /**
     * 布局配置
     * xml name: props
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeSelfProps getProps(){
      return _props;
    }

    
    public void setProps(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeSelfProps value){
        checkAllowChange();
        
        this._props = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._body = io.nop.api.core.util.FreezeHelper.deepFreeze(this._body);
            
           this._props = io.nop.api.core.util.FreezeHelper.deepFreeze(this._props);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("body",this.getBody());
        out.putNotNull("props",this.getProps());
    }

    public XuiLayoutStacked cloneInstance(){
        XuiLayoutStacked instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutStacked instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setBody(this.getBody());
        instance.setProps(this.getProps());
    }

    protected XuiLayoutStacked newInstance(){
        return (XuiLayoutStacked) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
