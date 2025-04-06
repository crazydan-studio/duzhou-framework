package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentLayoutLinear;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 使用文本标记指示布局
 * - [comp]: 指代 name 为 comp 的组件；
 * - <: 表示左对齐；
 * - >: 表示右对齐；
 * - ^: 表示顶部对齐；
 * - v: 表示底部对齐；
 * - <[comp]>: 表示使组件 comp 水平占满剩余空间，若未指定组件，则以空白占满剩余空间；
 * - >[comp]<: 表示使组件 comp 水平居中对齐；
 * - ^[comp]v: 表示使组件 comp 垂直占满剩余空间；
 * - v[comp]^: 表示使组件 comp 垂直居中对齐；
 * - [comp1] [comp2]: 表示左右放置组件 comp1 和 comp2；
 * - [comp1] | [comp2]: 表示左右放置组件 comp1 和 comp2；
 * - [comp1]\n[comp2]: 表示上下放置组件 comp1 和 comp2，其中，\n 为换行符，实际不可见，效果为上下两行；
 * - {xxx}: 表示布局嵌套，可在内部反复应用前面的规则，或者将其视为一个整体，可对其应用水平和垂直布局；
 * - 组件尺寸（宽高）默认均为自适应内容；
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
        instance.setValue(this.getValue());
    }

    protected XuiComponentLayoutLinear newInstance(){
        return (XuiComponentLayoutLinear) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
