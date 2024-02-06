package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWebSiteResource;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [53:10:0:0]/duzhou/web/site.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWebSiteResource extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: children
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> _children = KeyedList.emptyList();
    
    /**
     *  
     * xml name: displayName
     * 
     */
    private java.lang.String _displayName ;
    
    /**
     *  
     * xml name: icon
     * 
     */
    private java.lang.String _icon ;
    
    /**
     *  
     * xml name: id
     * 
     */
    private java.lang.String _id ;
    
    /**
     *  
     * xml name: url
     * 
     */
    private java.lang.String _url ;
    
    /**
     * 
     * xml name: children
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> value){
        checkAllowChange();
        
        this._children = KeyedList.fromList(value, io.crazydan.duzhou.framework.schema.web.XWebSiteResource::getId);
           
    }

    
    public io.crazydan.duzhou.framework.schema.web.XWebSiteResource getResource(String name){
        return this._children.getByKey(name);
    }

    public boolean hasResource(String name){
        return this._children.containsKey(name);
    }

    public void addResource(io.crazydan.duzhou.framework.schema.web.XWebSiteResource item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> list = this.getChildren();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.schema.web.XWebSiteResource::getId);
            setChildren(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_children(){
        return this._children.keySet();
    }

    public boolean hasChildren(){
        return !this._children.isEmpty();
    }
    
    /**
     * 
     * xml name: displayName
     *  
     */
    
    public java.lang.String getDisplayName(){
      return _displayName;
    }

    
    public void setDisplayName(java.lang.String value){
        checkAllowChange();
        
        this._displayName = value;
           
    }

    
    /**
     * 
     * xml name: icon
     *  
     */
    
    public java.lang.String getIcon(){
      return _icon;
    }

    
    public void setIcon(java.lang.String value){
        checkAllowChange();
        
        this._icon = value;
           
    }

    
    /**
     * 
     * xml name: id
     *  
     */
    
    public java.lang.String getId(){
      return _id;
    }

    
    public void setId(java.lang.String value){
        checkAllowChange();
        
        this._id = value;
           
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
        
           this._children = io.nop.api.core.util.FreezeHelper.deepFreeze(this._children);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("children",this.getChildren());
        out.putNotNull("displayName",this.getDisplayName());
        out.putNotNull("icon",this.getIcon());
        out.putNotNull("id",this.getId());
        out.putNotNull("url",this.getUrl());
    }

    public XWebSiteResource cloneInstance(){
        XWebSiteResource instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWebSiteResource instance){
        super.copyTo(instance);
        
        instance.setChildren(this.getChildren());
        instance.setDisplayName(this.getDisplayName());
        instance.setIcon(this.getIcon());
        instance.setId(this.getId());
        instance.setUrl(this.getUrl());
    }

    protected XWebSiteResource newInstance(){
        return (XWebSiteResource) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
