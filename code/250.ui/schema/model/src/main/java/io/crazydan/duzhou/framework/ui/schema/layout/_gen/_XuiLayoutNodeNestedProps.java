package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNestedProps;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 对子节点整体进行布局控制。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeNestedProps extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeSelfProps {
    
    /**
     *  子节点对齐模式
     * xml name: node-align
     * > 用于配置子节点的缺省对齐模式，其可以被子节点的 `<self-align/>` 设置所覆盖。
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign _nodeAlign ;
    
    /**
     *  子节点间隔
     * xml name: node-gap
     * > 用于配置子节点之间的间隔。
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsGap _nodeGap ;
    
    /**
     * 子节点对齐模式
     * xml name: node-align
     *  > 用于配置子节点的缺省对齐模式，其可以被子节点的 `<self-align/>` 设置所覆盖。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign getNodeAlign(){
      return _nodeAlign;
    }

    
    public void setNodeAlign(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsAlign value){
        checkAllowChange();
        
        this._nodeAlign = value;
           
    }

    
    /**
     * 子节点间隔
     * xml name: node-gap
     *  > 用于配置子节点之间的间隔。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsGap getNodeGap(){
      return _nodeGap;
    }

    
    public void setNodeGap(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodePropsGap value){
        checkAllowChange();
        
        this._nodeGap = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._nodeAlign = io.nop.api.core.util.FreezeHelper.deepFreeze(this._nodeAlign);
            
           this._nodeGap = io.nop.api.core.util.FreezeHelper.deepFreeze(this._nodeGap);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("nodeAlign",this.getNodeAlign());
        out.putNotNull("nodeGap",this.getNodeGap());
    }

    public XuiLayoutNodeNestedProps cloneInstance(){
        XuiLayoutNodeNestedProps instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeNestedProps instance){
        super.copyTo(instance);
        
        instance.setNodeAlign(this.getNodeAlign());
        instance.setNodeGap(this.getNodeGap());
    }

    protected XuiLayoutNodeNestedProps newInstance(){
        return (XuiLayoutNodeNestedProps) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
