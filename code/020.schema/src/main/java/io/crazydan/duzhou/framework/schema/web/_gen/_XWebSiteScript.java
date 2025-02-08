/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWebSiteScript;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/schema/web/site.xdef <p>
 *
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWebSiteScript extends io.nop.core.resource.component.AbstractComponentModel {

    /**
     *  必填
     * xml name: name
     * 脚本名称，便于做差量修正支持
     */
    private java.lang.String _name ;

    /**
     *  必填
     * xml name: url
     * 脚本资源路径
     */
    private java.lang.String _url ;

    /**
     * 必填
     * xml name: name
     *  脚本名称，便于做差量修正支持
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
     *  脚本资源路径
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

    public XWebSiteScript cloneInstance(){
        XWebSiteScript instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWebSiteScript instance){
        super.copyTo(instance);

        instance.setName(this.getName());
        instance.setUrl(this.getUrl());
    }

    protected XWebSiteScript newInstance(){
        return (XWebSiteScript) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
