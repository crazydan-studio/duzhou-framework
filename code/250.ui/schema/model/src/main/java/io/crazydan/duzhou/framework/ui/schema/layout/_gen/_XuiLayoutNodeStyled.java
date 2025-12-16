package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStyled;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeStyled extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  布局样式
     * xml name: styles
     * > 引用在当前组件 `<styles/>` 中所定义的**布局样式**（名字以 `layout__` 开头的标签），
     * > 其子节点标签名必须与去掉 `layout__` 后的样式名一致，且只能配置在该样式上所声明的属性。
     * > 此类样式可以在上层组件中作为部件样式被定制修改。
     * >
     * > 也支持直接引用在组件的 `<styles/>` 标签上设置的 `layout` 属性所指向的布局样式库中定义的布局样式，
     * > 但是，引用的此类样式将不能在上层组件中被定制修改。
     * >
     * > 注意，第一种引用样式的优先级高于第二种引用样式。
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStyles _styles ;
    
    /**
     * 布局样式
     * xml name: styles
     *  > 引用在当前组件 `<styles/>` 中所定义的**布局样式**（名字以 `layout__` 开头的标签），
     * > 其子节点标签名必须与去掉 `layout__` 后的样式名一致，且只能配置在该样式上所声明的属性。
     * > 此类样式可以在上层组件中作为部件样式被定制修改。
     * >
     * > 也支持直接引用在组件的 `<styles/>` 标签上设置的 `layout` 属性所指向的布局样式库中定义的布局样式，
     * > 但是，引用的此类样式将不能在上层组件中被定制修改。
     * >
     * > 注意，第一种引用样式的优先级高于第二种引用样式。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStyles getStyles(){
      return _styles;
    }

    
    public void setStyles(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStyles value){
        checkAllowChange();
        
        this._styles = value;
           
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

    public XuiLayoutNodeStyled cloneInstance(){
        XuiLayoutNodeStyled instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeStyled instance){
        super.copyTo(instance);
        
        instance.setStyles(this.getStyles());
    }

    protected XuiLayoutNodeStyled newInstance(){
        return (XuiLayoutNodeStyled) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
