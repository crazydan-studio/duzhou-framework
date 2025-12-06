package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeProps;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeProps extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed {
    
    /**
     *  布局对齐模式
     * xml name: align
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign _align ;
    
    /**
     *  布局项间隔
     * xml name: gap
     * > 控制行/列方向上的布局项之间的间隔。
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsGap _gap ;
    
    /**
     *  高度
     * xml name: height
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize _height ;
    
    /**
     *  布局空白
     * xml name: padding
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing _padding ;
    
    /**
     *  宽度
     * xml name: width
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSize _width ;
    
    /**
     * 布局对齐模式
     * xml name: align
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign getAlign(){
      return _align;
    }

    
    public void setAlign(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign value){
        checkAllowChange();
        
        this._align = value;
           
    }

    
    /**
     * 布局项间隔
     * xml name: gap
     *  > 控制行/列方向上的布局项之间的间隔。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsGap getGap(){
      return _gap;
    }

    
    public void setGap(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsGap value){
        checkAllowChange();
        
        this._gap = value;
           
    }

    
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
     * 布局空白
     * xml name: padding
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing getPadding(){
      return _padding;
    }

    
    public void setPadding(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing value){
        checkAllowChange();
        
        this._padding = value;
           
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
        
           this._align = io.nop.api.core.util.FreezeHelper.deepFreeze(this._align);
            
           this._gap = io.nop.api.core.util.FreezeHelper.deepFreeze(this._gap);
            
           this._height = io.nop.api.core.util.FreezeHelper.deepFreeze(this._height);
            
           this._padding = io.nop.api.core.util.FreezeHelper.deepFreeze(this._padding);
            
           this._width = io.nop.api.core.util.FreezeHelper.deepFreeze(this._width);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("align",this.getAlign());
        out.putNotNull("gap",this.getGap());
        out.putNotNull("height",this.getHeight());
        out.putNotNull("padding",this.getPadding());
        out.putNotNull("width",this.getWidth());
    }

    public XuiLayoutNodeProps cloneInstance(){
        XuiLayoutNodeProps instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeProps instance){
        super.copyTo(instance);
        
        instance.setAlign(this.getAlign());
        instance.setGap(this.getGap());
        instance.setHeight(this.getHeight());
        instance.setPadding(this.getPadding());
        instance.setWidth(this.getWidth());
    }

    protected XuiLayoutNodeProps newInstance(){
        return (XuiLayoutNodeProps) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
