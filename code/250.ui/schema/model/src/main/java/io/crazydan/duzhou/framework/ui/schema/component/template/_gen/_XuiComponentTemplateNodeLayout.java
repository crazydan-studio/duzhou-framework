package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 根据 `xui:name` 对其**直接子组件**（含 `<slot/>` 和 `<native/>`，但不含 `<if/>`、`<for/>` 等控制节点）进行布局控制。
 * >
 * > 缺省将按照子组件声明顺序排列，并由 UI Vendor 做默认布局。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeLayout extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayout implements io.crazydan.duzhou.framework.ui.XuiNamed{
    
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
        
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
    }

    public XuiComponentTemplateNodeLayout cloneInstance(){
        XuiComponentTemplateNodeLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeLayout instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
    }

    protected XuiComponentTemplateNodeLayout newInstance(){
        return (XuiComponentTemplateNodeLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
