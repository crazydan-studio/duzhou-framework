package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiApp;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/app.xdef <p>
 * - 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * - Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
 * -
 * - This program is free software: you can redistribute it and/or modify
 * - it under the terms of the GNU Lesser General Public License as published by
 * - the Free Software Foundation, either version 3 of the License, or
 * - (at your option) any later version.
 * -
 * - This program is distributed in the hope that it will be useful,
 * - but WITHOUT ANY WARRANTY; without even the implied warranty of
 * - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * - GNU Lesser General Public License for more details.
 * -
 * - You should have received a copy of the GNU Lesser General Public License
 * - along with this program.
 * - If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 * 应用定义：用于组织应用的页面
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiApp extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: code
     * 应用唯一标识；
     */
    private java.lang.String _code ;
    
    /**
     *  
     * xml name: copyright
     * 应用的版权声明内容
     */
    private java.lang.String _copyright ;
    
    /**
     *  
     * xml name: description
     * 应用的描述内容
     */
    private java.lang.String _description ;
    
    /**
     *  
     * xml name: license
     * 应用许可协议名称或编码；
     */
    private java.lang.String _license ;
    
    /**
     *  
     * xml name: logo
     * 应用 logo 地址；
     */
    private java.lang.String _logo ;
    
    /**
     *  
     * xml name: pages
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> _pageRefs = KeyedList.emptyList();
    
    /**
     *  
     * xml name: title
     * 应用标题；
     */
    private java.lang.String _title ;
    
    /**
     *  
     * xml name: version
     * 应用版本号；
     */
    private java.lang.String _version ;
    
    /**
     * 
     * xml name: code
     *  应用唯一标识；
     */
    
    public java.lang.String getCode(){
      return _code;
    }

    
    public void setCode(java.lang.String value){
        checkAllowChange();
        
        this._code = value;
           
    }

    
    /**
     * 
     * xml name: copyright
     *  应用的版权声明内容
     */
    
    public java.lang.String getCopyright(){
      return _copyright;
    }

    
    public void setCopyright(java.lang.String value){
        checkAllowChange();
        
        this._copyright = value;
           
    }

    
    /**
     * 
     * xml name: description
     *  应用的描述内容
     */
    
    public java.lang.String getDescription(){
      return _description;
    }

    
    public void setDescription(java.lang.String value){
        checkAllowChange();
        
        this._description = value;
           
    }

    
    /**
     * 
     * xml name: license
     *  应用许可协议名称或编码；
     */
    
    public java.lang.String getLicense(){
      return _license;
    }

    
    public void setLicense(java.lang.String value){
        checkAllowChange();
        
        this._license = value;
           
    }

    
    /**
     * 
     * xml name: logo
     *  应用 logo 地址；
     */
    
    public java.lang.String getLogo(){
      return _logo;
    }

    
    public void setLogo(java.lang.String value){
        checkAllowChange();
        
        this._logo = value;
           
    }

    
    /**
     * 
     * xml name: pages
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> getPageRefs(){
      return _pageRefs;
    }

    
    public void setPageRefs(java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> value){
        checkAllowChange();
        
        this._pageRefs = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef::getName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef getPageRef(String name){
        return this._pageRefs.getByKey(name);
    }

    public boolean hasPageRef(String name){
        return this._pageRefs.containsKey(name);
    }

    public void addPageRef(io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> list = this.getPageRefs();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef::getName);
            setPageRefs(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_pageRefs(){
        return this._pageRefs.keySet();
    }

    public boolean hasPageRefs(){
        return !this._pageRefs.isEmpty();
    }
    
    /**
     * 
     * xml name: title
     *  应用标题；
     */
    
    public java.lang.String getTitle(){
      return _title;
    }

    
    public void setTitle(java.lang.String value){
        checkAllowChange();
        
        this._title = value;
           
    }

    
    /**
     * 
     * xml name: version
     *  应用版本号；
     */
    
    public java.lang.String getVersion(){
      return _version;
    }

    
    public void setVersion(java.lang.String value){
        checkAllowChange();
        
        this._version = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._pageRefs = io.nop.api.core.util.FreezeHelper.deepFreeze(this._pageRefs);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("code",this.getCode());
        out.putNotNull("copyright",this.getCopyright());
        out.putNotNull("description",this.getDescription());
        out.putNotNull("license",this.getLicense());
        out.putNotNull("logo",this.getLogo());
        out.putNotNull("pageRefs",this.getPageRefs());
        out.putNotNull("title",this.getTitle());
        out.putNotNull("version",this.getVersion());
    }

    public XuiApp cloneInstance(){
        XuiApp instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiApp instance){
        super.copyTo(instance);
        
        instance.setCode(this.getCode());
        instance.setCopyright(this.getCopyright());
        instance.setDescription(this.getDescription());
        instance.setLicense(this.getLicense());
        instance.setLogo(this.getLogo());
        instance.setPageRefs(this.getPageRefs());
        instance.setTitle(this.getTitle());
        instance.setVersion(this.getVersion());
    }

    protected XuiApp newInstance(){
        return (XuiApp) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
