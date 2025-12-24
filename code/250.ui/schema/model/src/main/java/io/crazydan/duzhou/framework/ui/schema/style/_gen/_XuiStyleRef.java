package io.crazydan.duzhou.framework.ui.schema.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.style.XuiStyleRef;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/style/style-ref.xdef <p>
 * > 对已定义样式的引用，如 `<header width="20u" height="match-parent" />`。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiStyleRef extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,java.lang.String> _$props ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.Map<java.lang.String,java.lang.String> get$props(){
      return _$props;
    }

    
    public void set$props(java.util.Map<java.lang.String,java.lang.String> value){
        checkAllowChange();
        
        this._$props = value;
           
    }

    
    public boolean has$props(){
        return this._$props != null && !this._$props.isEmpty();
    }
    
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

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._$props = io.nop.api.core.util.FreezeHelper.deepFreeze(this._$props);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$props",this.get$props());
        out.putNotNull("$tag",this.get$tag());
    }

    public XuiStyleRef cloneInstance(){
        XuiStyleRef instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiStyleRef instance){
        super.copyTo(instance);
        
        instance.set$props(this.get$props());
        instance.set$tag(this.get$tag());
    }

    protected XuiStyleRef newInstance(){
        return (XuiStyleRef) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
