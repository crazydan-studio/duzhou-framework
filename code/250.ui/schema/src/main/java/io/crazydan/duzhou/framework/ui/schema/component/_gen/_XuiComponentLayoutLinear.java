package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentLayoutLinear;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 使用文本标记指示布局
 * - [comp]: 指代 xui:name 为 comp 的组件；
 * - <: 表示左对齐；
 * - >: 表示右对齐；
 * - ^: 表示顶部对齐；
 * - v: 表示底部对齐；
 * - <[comp]>: 表示使组件 comp 水平占满剩余空间，若未指定组件，则以空白占满剩余空间；
 * - >[comp]<: 表示使组件 comp 水平居中对齐；
 * - ^[comp]v: 表示使组件 comp 垂直占满剩余空间，若未指定组件，则以空白占满剩余空间；
 * - v[comp]^: 表示使组件 comp 垂直居中对齐；
 * - [comp1] [comp2]: 表示左右放置组件 comp1 和 comp2；
 * - | [comp1] | [comp2] |: 表示以表格形式放置组件 comp1 和 comp2，也即，comp1 与 comp2 分别在同一行的两个单元格内。
 * 上下相邻的以 | 为分隔符的多行属于同一个表格；
 * - [comp1]\n[comp2]: 表示上下放置组件 comp1 和 comp2，其中，\n 为换行符，实际不可见，效果为上下两行；
 * - {xxx}: 表示布局嵌套，可在内部反复应用前面的规则，其自身也是一个整体，可像组件一样对其应用水平和垂直布局；
 * - (): 表示设置组件布局配置参数列表，在括号内以 prop:value 形式指定参数名和参数值，且参数间以逗号分隔，
 * 如：(width:10em,height:20em) 表示设置宽为 10em，长为 20em。参数列表只能紧跟在 [comp] 和 {xxx} 之后放置；
 * - //: 表示行注释，其后均为注释内容；
 * - 组件尺寸（宽高）默认均为自适应内容；
 * - 根布局的尺寸始终与上层容器的尺寸相同；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentLayoutLinear extends io.nop.core.resource.component.AbstractComponentModel implements io.crazydan.duzhou.framework.ui.schema.XuiLayout{
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$type ;
    
    /**
     *  必填
     * xml name: mode
     * 布局模式，缺省为 column；
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
    
    public java.lang.String get$type(){
      return _$type;
    }

    
    public void set$type(java.lang.String value){
        checkAllowChange();
        
        this._$type = value;
           
    }

    
    /**
     * 必填
     * xml name: mode
     *  布局模式，缺省为 column；
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
        
        out.putNotNull("$type",this.get$type());
        out.putNotNull("mode",this.getMode());
        out.putNotNull("value",this.getValue());
    }

    public XuiComponentLayoutLinear cloneInstance(){
        XuiComponentLayoutLinear instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentLayoutLinear instance){
        super.copyTo(instance);
        
        instance.set$type(this.get$type());
        instance.setMode(this.getMode());
        instance.setValue(this.getValue());
    }

    protected XuiComponentLayoutLinear newInstance(){
        return (XuiComponentLayoutLinear) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
