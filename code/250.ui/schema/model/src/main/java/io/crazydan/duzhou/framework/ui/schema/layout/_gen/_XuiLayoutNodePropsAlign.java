package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodePropsAlign extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  列内对齐模式
     * xml name: column
     * > 在列方向上的对齐模式
     */
    private io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign _column ;
    
    /**
     *  行内对齐模式
     * xml name: row
     * > 在行方向上的对齐模式
     */
    private io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign _row ;
    
    /**
     * 列内对齐模式
     * xml name: column
     *  > 在列方向上的对齐模式
     */
    
    public io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign getColumn(){
      return _column;
    }

    
    public void setColumn(io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign value){
        checkAllowChange();
        
        this._column = value;
           
    }

    
    /**
     * 行内对齐模式
     * xml name: row
     *  > 在行方向上的对齐模式
     */
    
    public io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign getRow(){
      return _row;
    }

    
    public void setRow(io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign value){
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

    public XuiLayoutNodePropsAlign cloneInstance(){
        XuiLayoutNodePropsAlign instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodePropsAlign instance){
        super.copyTo(instance);
        
        instance.setColumn(this.getColumn());
        instance.setRow(this.getRow());
    }

    protected XuiLayoutNodePropsAlign newInstance(){
        return (XuiLayoutNodePropsAlign) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
