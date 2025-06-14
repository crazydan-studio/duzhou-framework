package io.crazydan.duzhou.framework.ui.schema.app._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/app/page-ref.xdef <p>
 * 页面引用：单独定义，以支持在指定包下创建 class
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiAppPageRef extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  必填
     * xml name: name
     * 页面唯一标识；
     */
    private java.lang.String _name ;
    
    /**
     *  必填
     * xml name: path
     * 页面资源的 v-path 路径；
     */
    private java.lang.String _path ;
    
    /**
     *  可选
     * xml name: title
     * 页面标题，其可与应用标题一起组合成完整标题。但以页面资源内的 title 优先；
     */
    private java.lang.String _title ;
    
    /**
     *  必填
     * xml name: url
     * 访问页面的 URL 地址；
     */
    private java.lang.String _url ;
    
    /**
     * 必填
     * xml name: name
     *  页面唯一标识；
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
     * xml name: path
     *  页面资源的 v-path 路径；
     */
    
    public java.lang.String getPath(){
      return _path;
    }

    
    public void setPath(java.lang.String value){
        checkAllowChange();
        
        this._path = value;
           
    }

    
    /**
     * 可选
     * xml name: title
     *  页面标题，其可与应用标题一起组合成完整标题。但以页面资源内的 title 优先；
     */
    
    public java.lang.String getTitle(){
      return _title;
    }

    
    public void setTitle(java.lang.String value){
        checkAllowChange();
        
        this._title = value;
           
    }

    
    /**
     * 必填
     * xml name: url
     *  访问页面的 URL 地址；
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
        out.putNotNull("path",this.getPath());
        out.putNotNull("title",this.getTitle());
        out.putNotNull("url",this.getUrl());
    }

    public XuiAppPageRef cloneInstance(){
        XuiAppPageRef instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiAppPageRef instance){
        super.copyTo(instance);
        
        instance.setName(this.getName());
        instance.setPath(this.getPath());
        instance.setTitle(this.getTitle());
        instance.setUrl(this.getUrl());
    }

    protected XuiAppPageRef newInstance(){
        return (XuiAppPageRef) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
