package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiStyleDef;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/styles.xdef <p>
 * 样式定义
 * - 原子样式：其结构不可拆分，因此，不含子节点
 * - 复合样式：由至少一个原子样式或其他复合样式组成，其必然包含子节点
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleDef extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyle> _styles = java.util.Collections.emptyMap();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyle> getStyles(){
      return _styles;
    }

    
    public void setStyles(java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.XuiStyle> value){
        checkAllowChange();
        
        this._styles = value;
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.XuiStyle getStyle(String name){
        return this._styles.get(name);
    }

    public boolean hasStyle(String name){
        return this._styles.containsKey(name);
    }
    
    public boolean hasStyles(){
        return this._styles != null && !this._styles.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._styles = io.nop.api.core.util.FreezeHelper.deepFreeze(this._styles);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("styles",this.getStyles());
    }

    public XuiStyleDef cloneInstance(){
        XuiStyleDef instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleDef instance){
        super.copyTo(instance);
        
        instance.setStyles(this.getStyles());
    }

    protected XuiStyleDef newInstance(){
        return (XuiStyleDef) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
