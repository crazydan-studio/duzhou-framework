package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeItem;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 用于配置和映射待布局的项目。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeItem extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed {
    
    /**
     *  布局项匹配模式
     * xml name: pattern
     * > 用于匹配目标唯一名字 `xui:name` 的正则表达式。
     * > 未被 `pattern` 匹配的目标项将不参与布局
     */
    private java.lang.String _pattern ;
    
    /**
     *  布局项配置
     * xml name: props
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeProps _props ;
    
    /**
     * 布局项匹配模式
     * xml name: pattern
     *  > 用于匹配目标唯一名字 `xui:name` 的正则表达式。
     * > 未被 `pattern` 匹配的目标项将不参与布局
     */
    
    public java.lang.String getPattern(){
      return _pattern;
    }

    
    public void setPattern(java.lang.String value){
        checkAllowChange();
        
        this._pattern = value;
           
    }

    
    /**
     * 布局项配置
     * xml name: props
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeProps getProps(){
      return _props;
    }

    
    public void setProps(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeProps value){
        checkAllowChange();
        
        this._props = value;
           
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
        
        out.putNotNull("pattern",this.getPattern());
        out.putNotNull("props",this.getProps());
    }

    public XuiLayoutNodeItem cloneInstance(){
        XuiLayoutNodeItem instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeItem instance){
        super.copyTo(instance);
        
        instance.setPattern(this.getPattern());
        instance.setProps(this.getProps());
    }

    protected XuiLayoutNodeItem newInstance(){
        return (XuiLayoutNodeItem) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
