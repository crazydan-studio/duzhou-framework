package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentEvent;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentEvent extends io.crazydan.duzhou.framework.ui.schema.component.XuiComponentNamed {
    
    /**
     *  
     * xml name: arg
     * 
     */
    private java.lang.String _arg ;
    
    /**
     *  
     * xml name: 
     * 
     */
    private io.nop.core.lang.xml.XNode _body ;
    
    /**
     *  
     * xml name: type
     * 
     */
    private java.lang.String _type ;
    
    /**
     * 
     * xml name: arg
     *  
     */
    
    public java.lang.String getArg(){
      return _arg;
    }

    
    public void setArg(java.lang.String value){
        checkAllowChange();
        
        this._arg = value;
           
    }

    
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
     * xml name: type
     *  
     */
    
    public java.lang.String getType(){
      return _type;
    }

    
    public void setType(java.lang.String value){
        checkAllowChange();
        
        this._type = value;
           
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
        
        out.putNotNull("arg",this.getArg());
        out.putNotNull("body",this.getBody());
        out.putNotNull("type",this.getType());
    }

    public XuiComponentEvent cloneInstance(){
        XuiComponentEvent instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentEvent instance){
        super.copyTo(instance);
        
        instance.setArg(this.getArg());
        instance.setBody(this.getBody());
        instance.setType(this.getType());
    }

    protected XuiComponentEvent newInstance(){
        return (XuiComponentEvent) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
