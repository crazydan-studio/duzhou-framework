package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWebSiteStyle;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [71:14:0:0]/duzhou/schema/web/site.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWebSiteStyle extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  必填
     * xml name: name
     * 样式名称，便于做差量修正支持
     */
    private java.lang.String _name ;
    
    /**
     *  必填
     * xml name: url
     * 样式资源路径
     */
    private java.lang.String _url ;
    
    /**
     * 必填
     * xml name: name
     *  样式名称，便于做差量修正支持
     */
    
    public java.lang.String getName(){
      return _name;
    }

    
    public void setName(java.lang.String value){
        checkAllowChange();
        
        this._name = value;
           
    }

    
    /**
     * 必填
     * xml name: url
     *  样式资源路径
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
        
        out.putNotNull("name",this.getName());
        out.putNotNull("url",this.getUrl());
    }

    public XWebSiteStyle cloneInstance(){
        XWebSiteStyle instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWebSiteStyle instance){
        super.copyTo(instance);
        
        instance.setName(this.getName());
        instance.setUrl(this.getUrl());
    }

    protected XWebSiteStyle newInstance(){
        return (XWebSiteStyle) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
