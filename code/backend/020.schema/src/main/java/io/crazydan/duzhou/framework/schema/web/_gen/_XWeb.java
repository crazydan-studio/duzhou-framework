package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWeb;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/schema/web.xdef <p>
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
 * 应用的 Web 端：
 * 每个应用都只有一个 Web 端，
 * 其 Web 端的页面和组织方式均集中定义在 Web DSL 中
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWeb extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: defaultSite
     * Note：若要支持 x:prototype，则当前节点必须指定 xdef:unique-attr（针对集合）
     * 或者在其父节点上指定 xdef:key-attr。处理逻辑见 io.nop.xlang.delta.DeltaMerger.processPrototype
     * [必须] 默认站点：在访问未定义的站点时，返回该站点页面
     */
    private io.crazydan.duzhou.framework.schema.web.XWebSite _defaultSite ;
    
    /**
     *  
     * xml name: site
     * 已定义的站点
     */
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSite> _sites = KeyedList.emptyList();
    
    /**
     * 
     * xml name: defaultSite
     *  Note：若要支持 x:prototype，则当前节点必须指定 xdef:unique-attr（针对集合）
     * 或者在其父节点上指定 xdef:key-attr。处理逻辑见 io.nop.xlang.delta.DeltaMerger.processPrototype
     * [必须] 默认站点：在访问未定义的站点时，返回该站点页面
     */
    
    public io.crazydan.duzhou.framework.schema.web.XWebSite getDefaultSite(){
      return _defaultSite;
    }

    
    public void setDefaultSite(io.crazydan.duzhou.framework.schema.web.XWebSite value){
        checkAllowChange();
        
        this._defaultSite = value;
           
    }

    
    /**
     * 
     * xml name: site
     *  已定义的站点
     */
    
    public java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSite> getSites(){
      return _sites;
    }

    
    public void setSites(java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSite> value){
        checkAllowChange();
        
        this._sites = KeyedList.fromList(value, io.crazydan.duzhou.framework.schema.web.XWebSite::getId);
           
    }

    
    public io.crazydan.duzhou.framework.schema.web.XWebSite getSite(String name){
        return this._sites.getByKey(name);
    }

    public boolean hasSite(String name){
        return this._sites.containsKey(name);
    }

    public void addSite(io.crazydan.duzhou.framework.schema.web.XWebSite item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSite> list = this.getSites();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.schema.web.XWebSite::getId);
            setSites(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_sites(){
        return this._sites.keySet();
    }

    public boolean hasSites(){
        return !this._sites.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._defaultSite = io.nop.api.core.util.FreezeHelper.deepFreeze(this._defaultSite);
            
           this._sites = io.nop.api.core.util.FreezeHelper.deepFreeze(this._sites);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("defaultSite",this.getDefaultSite());
        out.putNotNull("sites",this.getSites());
    }

    public XWeb cloneInstance(){
        XWeb instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWeb instance){
        super.copyTo(instance);
        
        instance.setDefaultSite(this.getDefaultSite());
        instance.setSites(this.getSites());
    }

    protected XWeb newInstance(){
        return (XWeb) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
