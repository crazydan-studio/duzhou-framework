package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCond;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > 其为嵌套节点的基础模型。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentCond extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
    /**
     *  缺省条件
     * xml name: else
     * > 限定可被嵌套的节点，其中：
     * > - 内置组件 `Text` 可被嵌套，但其子节点只能为 HTML 文本，不可嵌套其他组件；
     * > - 自定义组件可被嵌套，且可嵌套其他组件；
     */
    private io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondElse _else ;
    
    /**
     *  指定条件
     * xml name: when
     * > 限定可被嵌套的节点，其中：
     * > - 内置组件 `Text` 可被嵌套，但其子节点只能为 HTML 文本，不可嵌套其他组件；
     * > - 自定义组件可被嵌套，且可嵌套其他组件；
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen> _whens = KeyedList.emptyList();
    
    /**
     * 缺省条件
     * xml name: else
     *  > 限定可被嵌套的节点，其中：
     * > - 内置组件 `Text` 可被嵌套，但其子节点只能为 HTML 文本，不可嵌套其他组件；
     * > - 自定义组件可被嵌套，且可嵌套其他组件；
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondElse getElse(){
      return _else;
    }

    
    public void setElse(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondElse value){
        checkAllowChange();
        
        this._else = value;
           
    }

    
    /**
     * 指定条件
     * xml name: when
     *  > 限定可被嵌套的节点，其中：
     * > - 内置组件 `Text` 可被嵌套，但其子节点只能为 HTML 文本，不可嵌套其他组件；
     * > - 自定义组件可被嵌套，且可嵌套其他组件；
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen> getWhens(){
      return _whens;
    }

    
    public void setWhens(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen> value){
        checkAllowChange();
        
        this._whens = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen getWhen(String name){
        return this._whens.getByKey(name);
    }

    public boolean hasWhen(String name){
        return this._whens.containsKey(name);
    }

    public void addWhen(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen> list = this.getWhens();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.XuiComponentCondWhen::getXuiName);
            setWhens(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_whens(){
        return this._whens.keySet();
    }

    public boolean hasWhens(){
        return !this._whens.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._else = io.nop.api.core.util.FreezeHelper.deepFreeze(this._else);
            
           this._whens = io.nop.api.core.util.FreezeHelper.deepFreeze(this._whens);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("else",this.getElse());
        out.putNotNull("whens",this.getWhens());
    }

    public XuiComponentCond cloneInstance(){
        XuiComponentCond instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentCond instance){
        super.copyTo(instance);
        
        instance.setElse(this.getElse());
        instance.setWhens(this.getWhens());
    }

    protected XuiComponentCond newInstance(){
        return (XuiComponentCond) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
