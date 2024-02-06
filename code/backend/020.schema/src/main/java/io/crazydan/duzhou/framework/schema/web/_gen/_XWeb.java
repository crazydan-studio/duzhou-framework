package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWeb;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [27:2:0:0]/duzhou/web/web.xdef <p>
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
     * xml name: site
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
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSite> _sites = KeyedList.emptyList();
    
    /**
     * 
     * xml name: site
     *  ~ 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
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
        
           this._sites = io.nop.api.core.util.FreezeHelper.deepFreeze(this._sites);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("sites",this.getSites());
    }

    public XWeb cloneInstance(){
        XWeb instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWeb instance){
        super.copyTo(instance);
        
        instance.setSites(this.getSites());
    }

    protected XWeb newInstance(){
        return (XWeb) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
