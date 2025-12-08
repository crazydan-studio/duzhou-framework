package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStackedItem;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutStackedItem extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     *  层序号
     * xml name: layer
     * > 布局项所处在的 z 轴位置。该值越大，则布局项在 UI 层面越靠近上层。
     * > 不同层的层序号不能相同，但可以不相邻
     */
    private int _layer ;
    
    /**
     *  布局项配置
     * xml name: props
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeSelfProps _props ;
    
    /**
     *  待布局目标
     * xml name: target
     * > 对应目标的 `xui:name`
     */
    private java.lang.String _target ;
    
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
     * 层序号
     * xml name: layer
     *  > 布局项所处在的 z 轴位置。该值越大，则布局项在 UI 层面越靠近上层。
     * > 不同层的层序号不能相同，但可以不相邻
     */
    
    public int getLayer(){
      return _layer;
    }

    
    public void setLayer(int value){
        checkAllowChange();
        
        this._layer = value;
           
    }

    
    /**
     * 布局项配置
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

    
    /**
     * 待布局目标
     * xml name: target
     *  > 对应目标的 `xui:name`
     */
    
    public java.lang.String getTarget(){
      return _target;
    }

    
    public void setTarget(java.lang.String value){
        checkAllowChange();
        
        this._target = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._props = io.nop.api.core.util.FreezeHelper.deepFreeze(this._props);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("layer",this.getLayer());
        out.putNotNull("props",this.getProps());
        out.putNotNull("target",this.getTarget());
    }

    public XuiLayoutStackedItem cloneInstance(){
        XuiLayoutStackedItem instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutStackedItem instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setLayer(this.getLayer());
        instance.setProps(this.getProps());
        instance.setTarget(this.getTarget());
    }

    protected XuiLayoutStackedItem newInstance(){
        return (XuiLayoutStackedItem) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
