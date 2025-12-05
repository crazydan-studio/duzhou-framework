package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeLayoutLinear;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 在水平和垂直方向上布局组件，且溢出内容不做换行处理。
 * >
 * > 线性布局使用文本标记语言：
 * > - `[comp]`: 指代 `xui:name` 属性值为 `comp` 的组件；
 * > - `<`: 表示左对齐；
 * > - `>`: 表示右对齐；
 * > - `^`: 表示顶部对齐；
 * > - `v`: 表示底部对齐；
 * > - `<[comp]>`: 表示使组件 `comp` 水平占满剩余空间，若未指定组件，则以空白占满剩余空间；
 * > - `>[comp]<`: 表示使组件 `comp` 水平居中对齐；
 * > - `^[comp]v`: 表示使组件 `comp` 垂直占满剩余空间，若未指定组件，则以空白占满剩余空间；
 * > - `v[comp]^`: 表示使组件 `comp` 垂直居中对齐；
 * > - `[comp1] [comp2]`: 表示左右放置组件 `comp1` 和 `comp2`；
 * > - `| [comp1] | [comp2] |`: 表示以表格形式放置组件 `comp1` 和 `comp2`，
 * >   也即，`comp1` 与 `comp2` 分别在同一行的两个单元格内。
 * >   上下相邻的以 `|` 为分隔符的多行属于同一个表格；
 * > - `[comp1]\n[comp2]`: 表示上下放置组件 `comp1` 和 `comp2`，其中，`\n` 为换行符，实际不可见，效果为上下两行；
 * > - `{xxx}`: 表示布局嵌套，可在内部反复应用前面的规则，其自身也是一个整体，可像组件一样对其应用水平和垂直布局；
 * > - `()`: 表示设置组件布局配置参数列表，在括号内以 `prop:value` 形式指定参数名和参数值，且参数间以逗号分隔，
 * >   如：`(width:10u,height:20u)` 表示设置宽为 `10u`，长为 `20u`。
 * >   参数列表只能紧跟在 `[comp]` 和 `{xxx}` 之后放置；
 * > - `//`: 表示行注释，其后均为注释内容；
 * > - 组件尺寸（宽高）默认均为自适应内容；
 * > - 根布局的尺寸始终与上层容器的尺寸相同；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeLayoutLinear extends io.nop.core.resource.component.AbstractComponentModel implements io.crazydan.duzhou.framework.ui.XuiLayout{
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     *  布局模式
     * xml name: mode
     * > 缺省为 `column`
     */
    private java.lang.String _mode  = "column";
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _value ;
    
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

    
    /**
     * 布局模式
     * xml name: mode
     *  > 缺省为 `column`
     */
    
    public java.lang.String getMode(){
      return _mode;
    }

    
    public void setMode(java.lang.String value){
        checkAllowChange();
        
        this._mode = value;
           
    }

    
    /**
     * 
     * xml name: 
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
        
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("mode",this.getMode());
        out.putNotNull("value",this.getValue());
    }

    public XuiComponentTemplateNodeLayoutLinear cloneInstance(){
        XuiComponentTemplateNodeLayoutLinear instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeLayoutLinear instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setMode(this.getMode());
        instance.setValue(this.getValue());
    }

    protected XuiComponentTemplateNodeLayoutLinear newInstance(){
        return (XuiComponentTemplateNodeLayoutLinear) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
