package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWebSite;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [39:2:0:0]/duzhou/schema/web/site.xdef <p>
 * ~ 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * ~ Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
 * ~
 * ~ This program is free software: you can redistribute it and/or modify
 * ~ it under the terms of the GNU Lesser General Public License as published by
 * ~ the Free Software Foundation, either version 3 of the License, or
 * ~ (at your option) any later version.
 * ~
 * ~ This program is distributed in the hope that it will be useful,
 * ~ but WITHOUT ANY WARRANTY; without even the implied warranty of
 * ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * ~ GNU Lesser General Public License for more details.
 * ~
 * ~ You should have received a copy of the GNU Lesser General Public License
 * ~ along with this program.
 * ~ If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 * 站点（`Site`）：
 * 每个 Web 端均由多个站点组成，
 * 而一个站点就是一个独立的多页面应用（SPA），
 * 其通过布局器（`Layout`）来控制其下的资源（`Resource`）组织结构。
 * 注：登录、门户、后台管理等，均为一个站点
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWebSite extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  必填
     * xml name: id
     * 站点的唯一标识，需在同一个 `Web` 内保持唯一性
     */
    private java.lang.String _id ;
    
    /**
     *  必须
     * xml name: layout
     * 站点布局器
     */
    private io.crazydan.duzhou.framework.schema.web.XWebSiteLayout _layout ;
    
    /**
     *  可选
     * xml name: locale
     * 多语言环境下的当前语言，默认为 `zh`
     */
    private java.lang.String _locale  = "zh";
    
    /**
     *  必填
     * xml name: logo
     * 站点 Logo 图片的资源路径
     */
    private java.lang.String _logo ;
    
    /**
     *  可选
     * xml name: resources
     * 资源列表
     */
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> _resources = KeyedList.emptyList();
    
    /**
     *  可选
     * xml name: runInEnv
     * 所处的运行环境。用于在前端显示开发状态信息、开启调试等。
     * 默认为 `development`
     */
    private io.crazydan.duzhou.framework.schema.RunInEnv _runInEnv ;
    
    /**
     *  可选
     * xml name: subTitle
     * 站点副标题
     */
    private java.lang.String _subTitle ;
    
    /**
     *  必填
     * xml name: title
     * 站点主标题
     */
    private java.lang.String _title ;
    
    /**
     *  可选
     * xml name: url
     * 站点的访问地址，其路径相对于应用的 url。
     * 以 `redirect:` 开头的，将视为外部站点，在访问资源时直接跳转
     */
    private java.lang.String _url ;
    
    /**
     * 必填
     * xml name: id
     *  站点的唯一标识，需在同一个 `Web` 内保持唯一性
     */
    
    public java.lang.String getId(){
      return _id;
    }

    
    public void setId(java.lang.String value){
        checkAllowChange();
        
        this._id = value;
           
    }

    
    /**
     * 必须
     * xml name: layout
     *  站点布局器
     */
    
    public io.crazydan.duzhou.framework.schema.web.XWebSiteLayout getLayout(){
      return _layout;
    }

    
    public void setLayout(io.crazydan.duzhou.framework.schema.web.XWebSiteLayout value){
        checkAllowChange();
        
        this._layout = value;
           
    }

    
    /**
     * 可选
     * xml name: locale
     *  多语言环境下的当前语言，默认为 `zh`
     */
    
    public java.lang.String getLocale(){
      return _locale;
    }

    
    public void setLocale(java.lang.String value){
        checkAllowChange();
        
        this._locale = value;
           
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

    
    /**
     * 可选
     * xml name: resources
     *  资源列表
     */
    
    public java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> getResources(){
      return _resources;
    }

    
    public void setResources(java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> value){
        checkAllowChange();
        
        this._resources = KeyedList.fromList(value, io.crazydan.duzhou.framework.schema.web.XWebSiteResource::getId);
           
    }

    
    public io.crazydan.duzhou.framework.schema.web.XWebSiteResource getResource(String name){
        return this._resources.getByKey(name);
    }

    public boolean hasResource(String name){
        return this._resources.containsKey(name);
    }

    public void addResource(io.crazydan.duzhou.framework.schema.web.XWebSiteResource item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteResource> list = this.getResources();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.schema.web.XWebSiteResource::getId);
            setResources(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_resources(){
        return this._resources.keySet();
    }

    public boolean hasResources(){
        return !this._resources.isEmpty();
    }
    
    /**
     * 可选
     * xml name: runInEnv
     *  所处的运行环境。用于在前端显示开发状态信息、开启调试等。
     * 默认为 `development`
     */
    
    public io.crazydan.duzhou.framework.schema.RunInEnv getRunInEnv(){
      return _runInEnv;
    }

    
    public void setRunInEnv(io.crazydan.duzhou.framework.schema.RunInEnv value){
        checkAllowChange();
        
        this._runInEnv = value;
           
    }

    
    /**
     * 可选
     * xml name: subTitle
     *  站点副标题
     */
    
    public java.lang.String getSubTitle(){
      return _subTitle;
    }

    
    public void setSubTitle(java.lang.String value){
        checkAllowChange();
        
        this._subTitle = value;
           
    }

    
    /**
     * 必填
     * xml name: title
     *  站点主标题
     */
    
    public java.lang.String getTitle(){
      return _title;
    }

    
    public void setTitle(java.lang.String value){
        checkAllowChange();
        
        this._title = value;
           
    }

    
    /**
     * 可选
     * xml name: url
     *  站点的访问地址，其路径相对于应用的 url。
     * 以 `redirect:` 开头的，将视为外部站点，在访问资源时直接跳转
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
        
           this._layout = io.nop.api.core.util.FreezeHelper.deepFreeze(this._layout);
            
           this._resources = io.nop.api.core.util.FreezeHelper.deepFreeze(this._resources);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("id",this.getId());
        out.putNotNull("layout",this.getLayout());
        out.putNotNull("locale",this.getLocale());
        out.putNotNull("logo",this.getLogo());
        out.putNotNull("resources",this.getResources());
        out.putNotNull("runInEnv",this.getRunInEnv());
        out.putNotNull("subTitle",this.getSubTitle());
        out.putNotNull("title",this.getTitle());
        out.putNotNull("url",this.getUrl());
    }

    public XWebSite cloneInstance(){
        XWebSite instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWebSite instance){
        super.copyTo(instance);
        
        instance.setId(this.getId());
        instance.setLayout(this.getLayout());
        instance.setLocale(this.getLocale());
        instance.setLogo(this.getLogo());
        instance.setResources(this.getResources());
        instance.setRunInEnv(this.getRunInEnv());
        instance.setSubTitle(this.getSubTitle());
        instance.setTitle(this.getTitle());
        instance.setUrl(this.getUrl());
    }

    protected XWebSite newInstance(){
        return (XWebSite) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
