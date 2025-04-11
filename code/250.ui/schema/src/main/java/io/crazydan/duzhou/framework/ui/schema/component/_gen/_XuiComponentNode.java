package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNode;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 组件（树）节点
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentNode extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {
    
    /**
     *  可选
     * xml name: props
     * 以对象形式向组件传递属性，其最终将被展开并合并到组件对象的属性列表中；
     */
    private java.lang.Object _props ;
    
    /**
     * 可选
     * xml name: props
     *  以对象形式向组件传递属性，其最终将被展开并合并到组件对象的属性列表中；
     */
    
    public java.lang.Object getProps(){
      return _props;
    }

    
    public void setProps(java.lang.Object value){
        checkAllowChange();
        
        this._props = value;
           
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
        
        out.putNotNull("props",this.getProps());
    }

    public XuiComponentNode cloneInstance(){
        XuiComponentNode instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentNode instance){
        super.copyTo(instance);
        
        instance.setProps(this.getProps());
    }

    protected XuiComponentNode newInstance(){
        return (XuiComponentNode) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
