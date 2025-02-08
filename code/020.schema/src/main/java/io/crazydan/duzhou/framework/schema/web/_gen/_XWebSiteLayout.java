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
import io.crazydan.duzhou.framework.schema.web.XWebSiteLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/schema/web/site.xdef <p>
 * 站点布局配置
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWebSiteLayout extends io.nop.core.resource.component.AbstractComponentModel {

    /**
     *  可选
     * xml name: bgColor
     * 背景色
     */
    private java.lang.String _bgColor  = "#fff";

    /**
     *  必须
     * xml name: config
     * 布局的配置数据函数，通过该函数返回页面渲染引擎所支持的数据结构（JSON 形式）。
     * 在该函数内，可通过变量 `site` 引用当前站点的模型对象
     */
    private io.nop.core.lang.eval.IEvalAction _config ;

    /**
     *  必填
     * xml name: html
     * HTML 页面 DSL 定义文件的 VPath 路径，在其 xpl
     * 脚本中可通过全局变量 `$site` 引用当前站点的模型对象
     */
    private java.lang.String _html ;

    /**
     *
     * xml name: scripts
     * js 脚本（构建后的）资源。有顺序性，入口脚本需放在列表的最开始位置
     */
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> _scripts = KeyedList.emptyList();

    /**
     *  可选
     * xml name: spinner
     * 载入动画图片的资源路径
     */
    private java.lang.String _spinner ;

    /**
     *
     * xml name: styles
     * css 样式资源。有顺序性，放在前面的最先被加载
     */
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> _styles = KeyedList.emptyList();

    /**
     * 可选
     * xml name: bgColor
     *  背景色
     */

    public java.lang.String getBgColor(){
      return _bgColor;
    }


    public void setBgColor(java.lang.String value){
        checkAllowChange();

        this._bgColor = value;

    }


    /**
     * 必须
     * xml name: config
     *  布局的配置数据函数，通过该函数返回页面渲染引擎所支持的数据结构（JSON 形式）。
     * 在该函数内，可通过变量 `site` 引用当前站点的模型对象
     */

    public io.nop.core.lang.eval.IEvalAction getConfig(){
      return _config;
    }


    public void setConfig(io.nop.core.lang.eval.IEvalAction value){
        checkAllowChange();

        this._config = value;

    }


    /**
     * 必填
     * xml name: html
     *  HTML 页面 DSL 定义文件的 VPath 路径，在其 xpl
     * 脚本中可通过全局变量 `$site` 引用当前站点的模型对象
     */

    public java.lang.String getHtml(){
      return _html;
    }


    public void setHtml(java.lang.String value){
        checkAllowChange();

        this._html = value;

    }


    /**
     *
     * xml name: scripts
     *  js 脚本（构建后的）资源。有顺序性，入口脚本需放在列表的最开始位置
     */

    public java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> getScripts(){
      return _scripts;
    }


    public void setScripts(java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> value){
        checkAllowChange();

        this._scripts = KeyedList.fromList(value, io.crazydan.duzhou.framework.schema.web.XWebSiteScript::getName);

    }


    public io.crazydan.duzhou.framework.schema.web.XWebSiteScript getScript(String name){
        return this._scripts.getByKey(name);
    }

    public boolean hasScript(String name){
        return this._scripts.containsKey(name);
    }

    public void addScript(io.crazydan.duzhou.framework.schema.web.XWebSiteScript item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> list = this.getScripts();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.schema.web.XWebSiteScript::getName);
            setScripts(list);
        }
        list.add(item);
    }

    public java.util.Set<String> keySet_scripts(){
        return this._scripts.keySet();
    }

    public boolean hasScripts(){
        return !this._scripts.isEmpty();
    }

    /**
     * 可选
     * xml name: spinner
     *  载入动画图片的资源路径
     */

    public java.lang.String getSpinner(){
      return _spinner;
    }


    public void setSpinner(java.lang.String value){
        checkAllowChange();

        this._spinner = value;

    }


    /**
     *
     * xml name: styles
     *  css 样式资源。有顺序性，放在前面的最先被加载
     */

    public java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> getStyles(){
      return _styles;
    }


    public void setStyles(java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> value){
        checkAllowChange();

        this._styles = KeyedList.fromList(value, io.crazydan.duzhou.framework.schema.web.XWebSiteStyle::getName);

    }


    public io.crazydan.duzhou.framework.schema.web.XWebSiteStyle getStyle(String name){
        return this._styles.getByKey(name);
    }

    public boolean hasStyle(String name){
        return this._styles.containsKey(name);
    }

    public void addStyle(io.crazydan.duzhou.framework.schema.web.XWebSiteStyle item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> list = this.getStyles();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.schema.web.XWebSiteStyle::getName);
            setStyles(list);
        }
        list.add(item);
    }

    public java.util.Set<String> keySet_styles(){
        return this._styles.keySet();
    }

    public boolean hasStyles(){
        return !this._styles.isEmpty();
    }


    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code

           this._scripts = io.nop.api.core.util.FreezeHelper.deepFreeze(this._scripts);

           this._styles = io.nop.api.core.util.FreezeHelper.deepFreeze(this._styles);

        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);

        out.putNotNull("bgColor",this.getBgColor());
        out.putNotNull("config",this.getConfig());
        out.putNotNull("html",this.getHtml());
        out.putNotNull("scripts",this.getScripts());
        out.putNotNull("spinner",this.getSpinner());
        out.putNotNull("styles",this.getStyles());
    }

    public XWebSiteLayout cloneInstance(){
        XWebSiteLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWebSiteLayout instance){
        super.copyTo(instance);

        instance.setBgColor(this.getBgColor());
        instance.setConfig(this.getConfig());
        instance.setHtml(this.getHtml());
        instance.setScripts(this.getScripts());
        instance.setSpinner(this.getSpinner());
        instance.setStyles(this.getStyles());
    }

    protected XWebSiteLayout newInstance(){
        return (XWebSiteLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
