package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWebSiteImage;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [53:6:0:0]/duzhou/web/site.xdef <p>
 * 站点图片资源
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWebSiteImage extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  可选
     * xml name: loading
     * 载入动画图片的资源路径
     */
    private java.lang.String _loading ;
    
    /**
     *  必填
     * xml name: logo
     * 站点 Logo 图片的资源路径
     */
    private java.lang.String _logo ;
    
    /**
     * 可选
     * xml name: loading
     *  载入动画图片的资源路径
     */
    
    public java.lang.String getLoading(){
      return _loading;
    }

    
    public void setLoading(java.lang.String value){
        checkAllowChange();
        
        this._loading = value;
           
    }

    
    /**
     * 必填
     * xml name: logo
     *  站点 Logo 图片的资源路径
     */
    
    public java.lang.String getLogo(){
      return _logo;
    }

    
    public void setLogo(java.lang.String value){
        checkAllowChange();
        
        this._logo = value;
           
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
        
        out.putNotNull("loading",this.getLoading());
        out.putNotNull("logo",this.getLogo());
    }

    public XWebSiteImage cloneInstance(){
        XWebSiteImage instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWebSiteImage instance){
        super.copyTo(instance);
        
        instance.setLoading(this.getLoading());
        instance.setLogo(this.getLogo());
    }

    protected XWebSiteImage newInstance(){
        return (XWebSiteImage) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
