package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentAnimation;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 动画组件
 * - 可自嵌套，从而支持一组动画；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentAnimation extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNested {
    
    /**
     *  
     * xml name: enter
     * 
     */
    private java.lang.String _enter ;
    
    /**
     *  
     * xml name: exit
     * 
     */
    private java.lang.String _exit ;
    
    /**
     * 
     * xml name: enter
     *  
     */
    
    public java.lang.String getEnter(){
      return _enter;
    }

    
    public void setEnter(java.lang.String value){
        checkAllowChange();
        
        this._enter = value;
           
    }

    
    /**
     * 
     * xml name: exit
     *  
     */
    
    public java.lang.String getExit(){
      return _exit;
    }

    
    public void setExit(java.lang.String value){
        checkAllowChange();
        
        this._exit = value;
           
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
        
        out.putNotNull("enter",this.getEnter());
        out.putNotNull("exit",this.getExit());
    }

    public XuiComponentAnimation cloneInstance(){
        XuiComponentAnimation instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentAnimation instance){
        super.copyTo(instance);
        
        instance.setEnter(this.getEnter());
        instance.setExit(this.getExit());
    }

    protected XuiComponentAnimation newInstance(){
        return (XuiComponentAnimation) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
