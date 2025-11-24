package io.crazydan.duzhou.framework.ui.schema.action._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.action.XuiActionStateData;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/action/node.xdef <p>
 * 支持按照对象结构或者点分形式（`a.b.c`）为属性赋值
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiActionStateData extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private io.nop.core.lang.xml.XNode _body ;
    
    /**
     *  
     * xml name: reset
     * 是否重置对象，即，将对象的属性均设置为缺省值；
     */
    private java.lang.Boolean _reset ;
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public io.nop.core.lang.xml.XNode getBody(){
      return _body;
    }

    
    public void setBody(io.nop.core.lang.xml.XNode value){
        checkAllowChange();
        
        this._body = value;
           
    }

    
    /**
     * 
     * xml name: reset
     *  是否重置对象，即，将对象的属性均设置为缺省值；
     */
    
    public java.lang.Boolean getReset(){
      return _reset;
    }

    
    public void setReset(java.lang.Boolean value){
        checkAllowChange();
        
        this._reset = value;
           
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
        
        out.putNotNull("body",this.getBody());
        out.putNotNull("reset",this.getReset());
    }

    public XuiActionStateData cloneInstance(){
        XuiActionStateData instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiActionStateData instance){
        super.copyTo(instance);
        
        instance.setBody(this.getBody());
        instance.setReset(this.getReset());
    }

    protected XuiActionStateData newInstance(){
        return (XuiActionStateData) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
