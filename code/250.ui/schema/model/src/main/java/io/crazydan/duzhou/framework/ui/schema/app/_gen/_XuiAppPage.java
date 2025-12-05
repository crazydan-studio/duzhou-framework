package io.crazydan.duzhou.framework.ui.schema.app._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.app.XuiAppPage;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/app.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiAppPage extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: path
     * 
     */
    private java.lang.String _path ;
    
    /**
     *  
     * xml name: url
     * 
     */
    private java.lang.String _url ;
    
    /**
     * 
     * xml name: path
     *  
     */
    
    public java.lang.String getPath(){
      return _path;
    }

    
    public void setPath(java.lang.String value){
        checkAllowChange();
        
        this._path = value;
           
    }

    
    /**
     * 
     * xml name: url
     *  
     */
    
    public java.lang.String getUrl(){
      return _url;
    }

    
    public void setUrl(java.lang.String value){
        checkAllowChange();
        
        this._url = value;
           
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
        
        out.putNotNull("path",this.getPath());
        out.putNotNull("url",this.getUrl());
    }

    public XuiAppPage cloneInstance(){
        XuiAppPage instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiAppPage instance){
        super.copyTo(instance);
        
        instance.setPath(this.getPath());
        instance.setUrl(this.getUrl());
    }

    protected XuiAppPage newInstance(){
        return (XuiAppPage) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
