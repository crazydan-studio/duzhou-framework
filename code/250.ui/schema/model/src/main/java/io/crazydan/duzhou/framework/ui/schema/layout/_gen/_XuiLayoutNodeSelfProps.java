package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeSelfProps;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeSelfProps extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  高度
     * xml name: height
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize _height ;
    
    /**
     *  内边距
     * xml name: padding
     * > 用于配置上/下/左/右的空白大小。
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing _padding ;
    
    /**
     *  对齐模式
     * xml name: self-align
     * > 节点自身在父节点中的对齐模式，其将覆盖父节点 `<node-align/>` 所设置的缺省配置。
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign _selfAlign ;
    
    /**
     *  宽度
     * xml name: width
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize _width ;
    
    /**
     * 高度
     * xml name: height
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize getHeight(){
      return _height;
    }

    
    public void setHeight(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize value){
        checkAllowChange();
        
        this._height = value;
           
    }

    
    /**
     * 内边距
     * xml name: padding
     *  > 用于配置上/下/左/右的空白大小。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing getPadding(){
      return _padding;
    }

    
    public void setPadding(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing value){
        checkAllowChange();
        
        this._padding = value;
           
    }

    
    /**
     * 对齐模式
     * xml name: self-align
     *  > 节点自身在父节点中的对齐模式，其将覆盖父节点 `<node-align/>` 所设置的缺省配置。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign getSelfAlign(){
      return _selfAlign;
    }

    
    public void setSelfAlign(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign value){
        checkAllowChange();
        
        this._selfAlign = value;
           
    }

    
    /**
     * 宽度
     * xml name: width
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize getWidth(){
      return _width;
    }

    
    public void setWidth(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize value){
        checkAllowChange();
        
        this._width = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._height = io.nop.api.core.util.FreezeHelper.deepFreeze(this._height);
            
           this._padding = io.nop.api.core.util.FreezeHelper.deepFreeze(this._padding);
            
           this._selfAlign = io.nop.api.core.util.FreezeHelper.deepFreeze(this._selfAlign);
            
           this._width = io.nop.api.core.util.FreezeHelper.deepFreeze(this._width);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("height",this.getHeight());
        out.putNotNull("padding",this.getPadding());
        out.putNotNull("selfAlign",this.getSelfAlign());
        out.putNotNull("width",this.getWidth());
    }

    public XuiLayoutNodeSelfProps cloneInstance(){
        XuiLayoutNodeSelfProps instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeSelfProps instance){
        super.copyTo(instance);
        
        instance.setHeight(this.getHeight());
        instance.setPadding(this.getPadding());
        instance.setSelfAlign(this.getSelfAlign());
        instance.setWidth(this.getWidth());
    }

    protected XuiLayoutNodeSelfProps newInstance(){
        return (XuiLayoutNodeSelfProps) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
