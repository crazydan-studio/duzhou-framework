package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsGap;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 控制行/列方向上的布局项之间的间隔。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodePropsGap extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  列内间隔
     * xml name: column
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _column ;
    
    /**
     *  行内间隔
     * xml name: row
     * 
     */
    private io.crazydan.duzhou.framework.ui.domain.type.XuiSize _row ;
    
    /**
     * 列内间隔
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
     * 行内间隔
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
        
        out.putNotNull("column",this.getColumn());
        out.putNotNull("row",this.getRow());
    }

    public XuiLayoutNodePropsGap cloneInstance(){
        XuiLayoutNodePropsGap instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodePropsGap instance){
        super.copyTo(instance);
        
        instance.setColumn(this.getColumn());
        instance.setRow(this.getRow());
    }

    protected XuiLayoutNodePropsGap newInstance(){
        return (XuiLayoutNodePropsGap) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
