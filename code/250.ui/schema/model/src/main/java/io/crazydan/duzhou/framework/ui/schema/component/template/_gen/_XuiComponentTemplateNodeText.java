package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeText;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 用于在组件内放置文本内容。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeText extends io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed {
    
    /**
     *  是否为 XML 片段
     * xml name: as-xml
     * > 若为 `true`，则将其文本视为 XML 片段，在渲染时对其内容不做转义，
     * > 在 Web 运行环境下需自行处理 XSS 攻击防护
     */
    private java.lang.Boolean _asXml ;
    
    /**
     *  部件样式
     * xml name: styles
     * > 引用在当前组件 `<styles/>` 中所定义的**部件样式**，其子节点标签名必须与已定义的样式名一致，
     * > 且只能配置在该样式上所声明的属性。
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStyles _styles ;
    
    /**
     *  
     * xml name: body
     * 
     */
    private java.lang.String _value ;
    
    /**
     * 是否为 XML 片段
     * xml name: as-xml
     *  > 若为 `true`，则将其文本视为 XML 片段，在渲染时对其内容不做转义，
     * > 在 Web 运行环境下需自行处理 XSS 攻击防护
     */
    
    public java.lang.Boolean getAsXml(){
      return _asXml;
    }

    
    public void setAsXml(java.lang.Boolean value){
        checkAllowChange();
        
        this._asXml = value;
           
    }

    
    /**
     * 部件样式
     * xml name: styles
     *  > 引用在当前组件 `<styles/>` 中所定义的**部件样式**，其子节点标签名必须与已定义的样式名一致，
     * > 且只能配置在该样式上所声明的属性。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStyles getStyles(){
      return _styles;
    }

    
    public void setStyles(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStyles value){
        checkAllowChange();
        
        this._styles = value;
           
    }

    
    /**
     * 
     * xml name: body
     *  
     */
    
    public java.lang.String getValue(){
      return _value;
    }

    
    public void setValue(java.lang.String value){
        checkAllowChange();
        
        this._value = value;
           
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
        
        out.putNotNull("asXml",this.getAsXml());
        out.putNotNull("styles",this.getStyles());
        out.putNotNull("value",this.getValue());
    }

    public XuiComponentTemplateNodeText cloneInstance(){
        XuiComponentTemplateNodeText instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeText instance){
        super.copyTo(instance);
        
        instance.setAsXml(this.getAsXml());
        instance.setStyles(this.getStyles());
        instance.setValue(this.getValue());
    }

    protected XuiComponentTemplateNodeText newInstance(){
        return (XuiComponentTemplateNodeText) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
