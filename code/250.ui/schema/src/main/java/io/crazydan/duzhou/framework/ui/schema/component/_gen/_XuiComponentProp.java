package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.crazydan.duzhou.framework.ui.XuiExpression;
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentProp;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/props.xdef <p>
 * > - 对于 `String`、`Bool`、`Integer` 等基础类型的属性，其不包含结构，
 * >   故而，仅需配置 `type` 即可；
 * > - 对于结构化的属性，可以在子节点中分解其结构，也可以通过 `obj-meta` 指定对应的
 * >   xmeta 文件位置，并从中获取其完整的模型结构；
 * > - 解析规则
 * >   - 有子结构的，不可配置 `type` 和 `obj-meta`；
 * >   - 没有子结构的，`type` 和 `obj-meta` 必须且只能配置一个；
 * >   - `defaultValue` 可以为对象，如 `${{a: 1, b: 2}}`，用于为结构属性赋缺省值，
 * >     并且，在其子结构属性上设置的缺省值将与该缺省值做覆盖合并；
 * >   - `obj-meta` 对应的 xmeta 将在 `x:gen-extends` 中加载，
 * >     并根据其结构构造当前属性的子结构；
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
     *  属性的缺省值
     * xml name: defaultValue
     * > (可选) 一般以 `${props.xxx}` 形式引用组件的
     * > `props` 变量，或者以 `${false}` 形式指定常量
     */
    private XuiExpression<Object> _defaultValue ;

    /**
     *  对象 XMeta 定义路径
     * xml name: obj-meta
     * > (可选) 仅针对可复用的、结构化的属性
     */
    private java.lang.String _objMeta ;

    /**
     *  属性类型
     * xml name: type
     * > (可选) 仅针对 `String`、`Boolean` 等基础数据类型的属性
     */
    private io.nop.core.type.IGenericType _type ;

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
     * 属性的缺省值
     * xml name: defaultValue
     *  > (可选) 一般以 `${props.xxx}` 形式引用组件的
     * > `props` 变量，或者以 `${false}` 形式指定常量
     */

    public XuiExpression<Object> getDefaultValue(){
      return _defaultValue;
    }


    public void setDefaultValue(XuiExpression<Object> value){
        checkAllowChange();

        this._defaultValue = value;

    }


    /**
     * 对象 XMeta 定义路径
     * xml name: obj-meta
     *  > (可选) 仅针对可复用的、结构化的属性
     */

    public java.lang.String getObjMeta(){
      return _objMeta;
    }


    public void setObjMeta(java.lang.String value){
        checkAllowChange();

        this._objMeta = value;

    }


    /**
     * 属性类型
     * xml name: type
     *  > (可选) 仅针对 `String`、`Boolean` 等基础数据类型的属性
     */

    public io.nop.core.type.IGenericType getType(){
      return _type;
    }


    public void setType(io.nop.core.type.IGenericType value){
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
        out.putNotNull("objMeta",this.getObjMeta());
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
        instance.setObjMeta(this.getObjMeta());
        instance.setType(this.getType());
    }

    protected XuiComponentProp newInstance(){
        return (XuiComponentProp) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
