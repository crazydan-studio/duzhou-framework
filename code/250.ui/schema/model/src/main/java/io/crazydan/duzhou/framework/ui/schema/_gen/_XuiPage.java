package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiPage;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/page.xdef <p>
 * > - 组件的缺省配置定义在 `/duzhou/ui/component/default.xui` 中，所有组件均自动扩展自该组件，
 * >   若需要排除该缺省配置，则需在 `x:extends` 的 v-path 列表中包含 `none` 值，如，`none,/xx/xx.xui`；
 * > - TODO 在组件上定义 `base-styles` 和 `base-themes` 属性，用于指定基础样式和主题库的路径。通过在缺省扩展模型上配置这些属性，实现对组件库的统一样式管理；
 * >
 * > 组件设计原则：
 * > - 组件的**属性只应与其本身的特性有关**，与业务意义无关 —— 自身特性是自然特性，业务意义是附加特性；
 * >   - 例如，「主要」、「次要」和「危险」作用到按钮组件上的表现主要是颜色发生了变化，
 * >     应该用表示按钮的自然特性「颜色」的 `color` 属性来满足同样的需求，
 * >     所以，应该采用 `<Button color="danger">危险按钮</Button>` 形式，
 * >     而不是 `<Button type="danger">危险按钮</Button>`；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiPage extends io.crazydan.duzhou.framework.ui.schema.XuiComponent {
    
    /**
     *  页面标题
     * xml name: title
     * > (可选) 可引用页面内的状态数据，从而支持动态设定标题
     */
    private java.lang.String _title ;
    
    /**
     * 页面标题
     * xml name: title
     *  > (可选) 可引用页面内的状态数据，从而支持动态设定标题
     */
    
    public java.lang.String getTitle(){
      return _title;
    }

    
    public void setTitle(java.lang.String value){
        checkAllowChange();
        
        this._title = value;
           
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
        
        out.putNotNull("title",this.getTitle());
    }

    public XuiPage cloneInstance(){
        XuiPage instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiPage instance){
        super.copyTo(instance);
        
        instance.setTitle(this.getTitle());
    }

    protected XuiPage newInstance(){
        return (XuiPage) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
