package io.crazydan.duzhou.framework.ui.schema.component.style._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.style.XuiComponentStyles;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/styles.xdef <p>
 * > 为组件的部件定义相应的样式。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentStyles extends io.crazydan.duzhou.framework.ui.schema.style.XuiStyleDefs {
    
    /**
     *  基础布局样式库
     * xml name: layout
     * > 基础布局样式库的 vpath 资源路径。仅针对布局样式，
     * > 在布局样式定义（名字以 `layout__` 开头的标签）中，
     * > 只能引用该样式库中预定义的样式，或者当前样式库中定义的布局样式
     */
    private java.lang.String _layout ;
    
    /**
     *  基础视觉样式库
     * xml name: view
     * > 基础视觉样式库的 vpath 资源路径。仅针对部件样式，
     * > 在部件样式定义（名字不以 `layout__` 开头的标签）中，
     * > 只能引用该样式库中预定义的样式，或者当前样式库中定义的部件样式
     */
    private java.lang.String _view ;
    
    /**
     * 基础布局样式库
     * xml name: layout
     *  > 基础布局样式库的 vpath 资源路径。仅针对布局样式，
     * > 在布局样式定义（名字以 `layout__` 开头的标签）中，
     * > 只能引用该样式库中预定义的样式，或者当前样式库中定义的布局样式
     */
    
    public java.lang.String getLayout(){
      return _layout;
    }

    
    public void setLayout(java.lang.String value){
        checkAllowChange();
        
        this._layout = value;
           
    }

    
    /**
     * 基础视觉样式库
     * xml name: view
     *  > 基础视觉样式库的 vpath 资源路径。仅针对部件样式，
     * > 在部件样式定义（名字不以 `layout__` 开头的标签）中，
     * > 只能引用该样式库中预定义的样式，或者当前样式库中定义的部件样式
     */
    
    public java.lang.String getView(){
      return _view;
    }

    
    public void setView(java.lang.String value){
        checkAllowChange();
        
        this._view = value;
           
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
        
        out.putNotNull("layout",this.getLayout());
        out.putNotNull("view",this.getView());
    }

    public XuiComponentStyles cloneInstance(){
        XuiComponentStyles instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentStyles instance){
        super.copyTo(instance);
        
        instance.setLayout(this.getLayout());
        instance.setView(this.getView());
    }

    protected XuiComponentStyles newInstance(){
        return (XuiComponentStyles) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
