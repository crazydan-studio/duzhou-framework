package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 用于配置上/下/左/右的空白大小。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodePropsSpacing extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  所有边空白大小
     * xml name: all
     * > 统一设定上/下/左/右的空白大小，但可被其他属性的设定所覆盖
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _all ;
    
    /**
     *  底部空白大小
     * xml name: bottom
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _bottom ;
    
    /**
     *  列方向的空白大小
     * xml name: column
     * > 用于设定上/下两边的空白大小，其可被 `top`/`bottom` 的设定所覆盖
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _column ;
    
    /**
     *  左边空白大小
     * xml name: left
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _left ;
    
    /**
     *  右边空白大小
     * xml name: right
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _right ;
    
    /**
     *  行方向的空白大小
     * xml name: row
     * > 用于设定左/右两侧的空白大小，其可被 `left`/`right` 的设定所覆盖
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _row ;
    
    /**
     *  顶部空白大小
     * xml name: top
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _top ;
    
    /**
     * 所有边空白大小
     * xml name: all
     *  > 统一设定上/下/左/右的空白大小，但可被其他属性的设定所覆盖
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getAll(){
      return _all;
    }

    
    public void setAll(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._all = value;
           
    }

    
    /**
     * 底部空白大小
     * xml name: bottom
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getBottom(){
      return _bottom;
    }

    
    public void setBottom(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._bottom = value;
           
    }

    
    /**
     * 列方向的空白大小
     * xml name: column
     *  > 用于设定上/下两边的空白大小，其可被 `top`/`bottom` 的设定所覆盖
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getColumn(){
      return _column;
    }

    
    public void setColumn(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._column = value;
           
    }

    
    /**
     * 左边空白大小
     * xml name: left
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getLeft(){
      return _left;
    }

    
    public void setLeft(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._left = value;
           
    }

    
    /**
     * 右边空白大小
     * xml name: right
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getRight(){
      return _right;
    }

    
    public void setRight(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._right = value;
           
    }

    
    /**
     * 行方向的空白大小
     * xml name: row
     *  > 用于设定左/右两侧的空白大小，其可被 `left`/`right` 的设定所覆盖
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getRow(){
      return _row;
    }

    
    public void setRow(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._row = value;
           
    }

    
    /**
     * 顶部空白大小
     * xml name: top
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getTop(){
      return _top;
    }

    
    public void setTop(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._top = value;
           
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
        
        out.putNotNull("all",this.getAll());
        out.putNotNull("bottom",this.getBottom());
        out.putNotNull("column",this.getColumn());
        out.putNotNull("left",this.getLeft());
        out.putNotNull("right",this.getRight());
        out.putNotNull("row",this.getRow());
        out.putNotNull("top",this.getTop());
    }

    public XuiLayoutNodePropsSpacing cloneInstance(){
        XuiLayoutNodePropsSpacing instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodePropsSpacing instance){
        super.copyTo(instance);
        
        instance.setAll(this.getAll());
        instance.setBottom(this.getBottom());
        instance.setColumn(this.getColumn());
        instance.setLeft(this.getLeft());
        instance.setRight(this.getRight());
        instance.setRow(this.getRow());
        instance.setTop(this.getTop());
    }

    protected XuiLayoutNodePropsSpacing newInstance(){
        return (XuiLayoutNodePropsSpacing) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
