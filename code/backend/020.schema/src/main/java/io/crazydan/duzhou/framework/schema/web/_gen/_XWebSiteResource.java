package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWebSiteResource;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [91:10:0:0]/duzhou/schema/web/site.xdef <p>
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
     *  可选
     * xml name: displayName
     * 资源名称，一般作为导航菜单名称
     */
    private java.lang.String _displayName ;
    
    /**
     *  可选
     * xml name: icon
     * 图标 css 类名
     */
    private java.lang.String _icon ;
    
    /**
     *  必填
     * xml name: id
     * 资源唯一标识，任意层级的资源均需在同一个 `Site` 内保持唯一性
     */
    private java.lang.String _id ;
    
    /**
     *  可选
     * xml name: url
     * 资源的页面 DSL 路径。
     * 以 `redirect:` 开头的，将视为外部资源，在访问时直接跳转
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
     * 可选
     * xml name: displayName
     *  资源名称，一般作为导航菜单名称
     */
    
    public java.lang.String getDisplayName(){
      return _displayName;
    }

    
    public void setDisplayName(java.lang.String value){
        checkAllowChange();
        
        this._displayName = value;
           
    }

    
    /**
     * 可选
     * xml name: icon
     *  图标 css 类名
     */
    
    public java.lang.String getIcon(){
      return _icon;
    }

    
    public void setIcon(java.lang.String value){
        checkAllowChange();
        
        this._icon = value;
           
    }

    
    /**
     * 必填
     * xml name: id
     *  资源唯一标识，任意层级的资源均需在同一个 `Site` 内保持唯一性
     */
    
    public java.lang.String getId(){
      return _id;
    }

    
    public void setId(java.lang.String value){
        checkAllowChange();
        
        this._id = value;
           
    }

    
    /**
     * 可选
     * xml name: url
     *  资源的页面 DSL 路径。
     * 以 `redirect:` 开头的，将视为外部资源，在访问时直接跳转
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
