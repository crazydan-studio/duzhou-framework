package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentValidation;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * > - 可自嵌套，从而支持分层校验；
 * > - 根据校验模型的校验结果，以红色边框和浮动提示方式标记校验失败的输入区域；
 * > - 仅在目标的值 `value` 发生变化时才根据校验结果显示提示信息，其余状态则不做处理；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentValidation extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {
    
    /**
     *  
     * xml name: target
     * 
     */
    private java.lang.String _target ;
    
    /**
     * 
     * xml name: target
     *  
     */
    
    public java.lang.String getTarget(){
      return _target;
    }

    
    public void setTarget(java.lang.String value){
        checkAllowChange();
        
        this._target = value;
           
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
        
        out.putNotNull("target",this.getTarget());
    }

    public XuiComponentValidation cloneInstance(){
        XuiComponentValidation instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentValidation instance){
        super.copyTo(instance);
        
        instance.setTarget(this.getTarget());
    }

    protected XuiComponentValidation newInstance(){
        return (XuiComponentValidation) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
