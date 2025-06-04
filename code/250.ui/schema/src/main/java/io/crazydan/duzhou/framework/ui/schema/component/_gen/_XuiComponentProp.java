package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentProp;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/props.xdef <p>
 * 属性定义
 * - 对于 string、bool、int 等基础类型的属性，其不包含结构，故而，仅需配置 `type` 即可；
 * - 对于结构化的属性，可以在子节点中分解其结构，也可以通过 `meta-path` 指定对应的
 * xmeta 文件位置，并从中获取其完整的模型结构；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentProp extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.component.XuiComponentProp> _children = java.util.Collections.emptyMap();
    
    /**
     *  可选
     * xml name: defaultValue
     * 属性的缺省值，一般以 `${props.xxx}` 形式引用组件的
     * `props` 变量，或者以 `${false}` 形式指定常量；
     */
    private io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> _defaultValue ;
    
    /**
     *  可选
     * xml name: meta-path
     * 属性结构的 xmeta 定义文件的 vpath 路径，仅针对可复用的、结构化的属性；
     */
    private java.lang.String _metaPath ;
    
    /**
     *  可选
     * xml name: type
     * 属性类型，仅针对 string、bool 等基础数据类型的属性；
     */
    private io.nop.commons.type.StdDataType _type ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.component.XuiComponentProp> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.component.XuiComponentProp> value){
        checkAllowChange();
        
        this._children = value;
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentProp getChild(String name){
        return this._children.get(name);
    }

    public boolean hasChild(String name){
        return this._children.containsKey(name);
    }
    
    public boolean hasChildren(){
        return this._children != null && !this._children.isEmpty();
    }
    
    /**
     * 可选
     * xml name: defaultValue
     *  属性的缺省值，一般以 `${props.xxx}` 形式引用组件的
     * `props` 变量，或者以 `${false}` 形式指定常量；
     */
    
    public io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> getDefaultValue(){
      return _defaultValue;
    }

    
    public void setDefaultValue(io.crazydan.duzhou.framework.ui.schema.XuiExpression<java.lang.Object> value){
        checkAllowChange();
        
        this._defaultValue = value;
           
    }

    
    /**
     * 可选
     * xml name: meta-path
     *  属性结构的 xmeta 定义文件的 vpath 路径，仅针对可复用的、结构化的属性；
     */
    
    public java.lang.String getMetaPath(){
      return _metaPath;
    }

    
    public void setMetaPath(java.lang.String value){
        checkAllowChange();
        
        this._metaPath = value;
           
    }

    
    /**
     * 可选
     * xml name: type
     *  属性类型，仅针对 string、bool 等基础数据类型的属性；
     */
    
    public io.nop.commons.type.StdDataType getType(){
      return _type;
    }

    
    public void setType(io.nop.commons.type.StdDataType value){
        checkAllowChange();
        
        this._type = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._children = io.nop.api.core.util.FreezeHelper.deepFreeze(this._children);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("children",this.getChildren());
        out.putNotNull("defaultValue",this.getDefaultValue());
        out.putNotNull("metaPath",this.getMetaPath());
        out.putNotNull("type",this.getType());
    }

    public XuiComponentProp cloneInstance(){
        XuiComponentProp instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentProp instance){
        super.copyTo(instance);
        
        instance.setChildren(this.getChildren());
        instance.setDefaultValue(this.getDefaultValue());
        instance.setMetaPath(this.getMetaPath());
        instance.setType(this.getType());
    }

    protected XuiComponentProp newInstance(){
        return (XuiComponentProp) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
