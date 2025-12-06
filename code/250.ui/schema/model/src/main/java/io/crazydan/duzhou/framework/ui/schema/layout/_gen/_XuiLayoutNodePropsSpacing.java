package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsSpacing;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodePropsSpacing extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: all
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _all ;
    
    /**
     *  
     * xml name: bottom
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _bottom ;
    
    /**
     *  
     * xml name: column
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _column ;
    
    /**
     *  
     * xml name: left
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _left ;
    
    /**
     *  
     * xml name: right
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _right ;
    
    /**
     *  
     * xml name: row
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _row ;
    
    /**
     *  
     * xml name: top
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _top ;
    
    /**
     * 
     * xml name: all
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getAll(){
      return _all;
    }

    
    public void setAll(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._all = value;
           
    }

    
    /**
     * 
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
     * 
     * xml name: column
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getColumn(){
      return _column;
    }

    
    public void setColumn(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._column = value;
           
    }

    
    /**
     * 
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
     * 
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
     * 
     * xml name: row
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.domain.type.XuiSize getRow(){
      return _row;
    }

    
    public void setRow(io.crazydan.duzhou.framework.ui.domain.type.XuiSize value){
        checkAllowChange();
        
        this._row = value;
           
    }

    
    /**
     * 
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
