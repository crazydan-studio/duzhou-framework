/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.gateway.web.test;

import io.crazydan.duzhou.framework.gateway.web.GatewayWebBaseTest;
import io.crazydan.duzhou.framework.gateway.web.WebDslModelHelper;
import io.crazydan.duzhou.framework.gateway.web.WebSiteGlobalVariable;
import io.crazydan.duzhou.framework.schema.web.XWeb;
import io.crazydan.duzhou.framework.schema.web.XWebSite;
import io.crazydan.duzhou.framework.schema.web.XWebSiteResource;
import io.crazydan.duzhou.framework.schema.web.XWebSiteScript;
import io.crazydan.duzhou.framework.schema.web.XWebSiteStyle;
import io.nop.commons.collections.KeyedList;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.eval.global.EvalGlobalRegistry;
import io.nop.core.lang.json.JsonTool;
import io.nop.core.lang.xml.XNode;
import io.nop.core.lang.xml.parse.XNodeParser;
import io.nop.core.model.object.DynamicObject;
import io.nop.xlang.api.ExprEvalAction;
import io.nop.xlang.api.XLang;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.ast.XLangOutputMode;
import io.nop.xlang.xdsl.DslModelParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-07
 */
public class SiteGenTest extends GatewayWebBaseTest {
    private static final String WEB_XDSL = "/duzhou/web/app.web.xml";

    /** site -> html 的 dsl：支持对 html 做差量修订 */
    private static final String SITE_HTML_XDSL = "/duzhou/web/app.site-html.xml";
    private static final String SITE_HTML_EXTENDS_XDSL = "/duzhou/web/app.site-html.extends.xml";

    /** site -> html 的 xpl 函数：不支持对 html 做差量修订 */
    private static final String SITE_HTML_XPL = "/duzhou/web/app.site-html.xpl";

    @BeforeAll
    public static void prepare() {
        // 解析 XDSL 时，在 x:gen-extends 等 xpl 函数和脚本中使用的变量只能注册为全局变量
        EvalGlobalRegistry.instance().registerVariable("$site", WebSiteGlobalVariable.instance());

        XWeb web = XWeb.parseFromVirtualPath(WEB_XDSL);
        XWebSite site = web.getSite("admin");

        WebSiteGlobalVariable.set(site);
    }

    @Test
    public void test_Gen_Site_Html() {
        XWebSite site = WebSiteGlobalVariable.get();

        XNode node = XNodeParser.instance().parseFromVirtualPath(SITE_HTML_XDSL);
        String xdefPath = node.attrText("x:schema");

        DslModelParser parser = new DslModelParser(xdefPath);
        DynamicObject obj = (DynamicObject) parser.parseFromNode(node);
        log.info(JsonTool.serialize(obj, true));

        Assertions.assertEquals(site.getLocale(), obj.prop_get("lang"));

        KeyedList<?> headLinks = (KeyedList<?>) obj.getComplexProp("head.links");
        KeyedList<?> headStyles = (KeyedList<?>) obj.getComplexProp("head.styles");
        Assertions.assertEquals(site.getLogo(), ((DynamicObject) headLinks.getByKey("icon:logo")).prop_get("href"));
        Assertions.assertTrue(((DynamicObject) headStyles.getByKey("style:global")).prop_get("body")
                                                                                   .toString()
                                                                                   .contains(
                                                                                           "data:image/svg+xml;base64,"));

        String title = obj.getComplexProp("head.title").toString();
        Assertions.assertEquals(site.getSubTitle() + " | " + site.getTitle(), title);

        KeyedList<?> bodyLinks = (KeyedList<?>) obj.getComplexProp("body.links");
        KeyedList<?> bodyScripts = (KeyedList<?>) obj.getComplexProp("body.scripts");
        for (XWebSiteStyle style : site.getLayout().getStyles()) {
            DynamicObject link = (DynamicObject) bodyLinks.getByKey("css:" + style.getName());

            Assertions.assertEquals(style.getUrl(), link.prop_get("href"));
        }
        for (XWebSiteScript script : site.getLayout().getScripts()) {
            DynamicObject s = (DynamicObject) bodyLinks.getByKey("js:" + script.getName());

            if (s == null) {
                s = (DynamicObject) bodyScripts.getByKey("js:" + script.getName());
                Assertions.assertEquals(script.getUrl(), s.prop_get("src"));
            } else {
                Assertions.assertEquals(script.getUrl(), s.prop_get("href"));
            }
        }

        String siteConfig = ((DynamicObject) bodyScripts.getByKey("js:site-config")).prop_get("body").toString();
        for (XWebSiteResource resource : site.getResources()) {
            Assertions.assertTrue(siteConfig.contains("\"label\":\"" + resource.getDisplayName() + "\""));
            Assertions.assertTrue(siteConfig.contains("\"url\":\"" + resource.getId() + "\""));
            Assertions.assertTrue(siteConfig.contains("\"schemaApi\":\"" + resource.getUrl() + "\""));
        }

        XNode htmlNode = WebDslModelHelper.toHtmlNode(parser.getRequiredSchema(), obj);

        String html = "<!DOCTYPE html>" + htmlNode.html().replaceAll("\n\\s*", "");
        log.info(StringHelper.unescapeXml(html));
    }

    @Test
    public void test_Extends_Gen_Site_Html() {
        XWebSite site = WebSiteGlobalVariable.get();

        XNode node = XNodeParser.instance().parseFromVirtualPath(SITE_HTML_EXTENDS_XDSL);
        String xdefPath = node.attrText("x:schema");

        DslModelParser parser = new DslModelParser(xdefPath);
        DynamicObject obj = (DynamicObject) parser.parseFromNode(node);
        log.info(JsonTool.serialize(obj, true));

        Assertions.assertEquals("zh_CN", obj.prop_get("lang"));

        String title = obj.getComplexProp("head.title").toString();
        Assertions.assertEquals(site.getSubTitle() + " - " + site.getTitle() + " (开发中...)", title);

        KeyedList<?> bodyDivs = (KeyedList<?>) obj.getComplexProp("body.divs");
        Assertions.assertNotNull(bodyDivs.getByKey("tips"));
        Assertions.assertNotNull(bodyDivs.getByKey("text"));

        XNode htmlNode = WebDslModelHelper.toHtmlNode(parser.getRequiredSchema(), obj);

        XNode htmlBodyNode = htmlNode.childByTag("body");
        Assertions.assertEquals("<span>This is just a tip.</span>", htmlBodyNode.childByAttr("id", "tips").innerHtml());
        Assertions.assertEquals("<span>Hi,</span> This is a text. <span>:)</span>",
                                htmlBodyNode.childByAttr("id", "text").innerHtml());

        String html = "<!DOCTYPE html>" + htmlNode.html().replaceAll("\n\\s*", "");
        log.info(StringHelper.unescapeXml(html));
    }

    @Test
    public void test_Gen_Site_Html_by_Xpl() {
        XWebSite site = WebSiteGlobalVariable.get();

        XLangCompileTool compiler = XLang.newCompileTool();
        // Note：在编译 xpl 时需要获取 ${} 中的变量，但在编译器中无法注入该变量，
        // 故而，需要忽略未注册变量，以确保编译能够正常进行
        compiler.getScope().setAllowUnregisteredScopeVar(true);

        XNode node = XNodeParser.instance().parseFromVirtualPath(SITE_HTML_XPL);
        ExprEvalAction action = compiler.compileTagBody(node, XLangOutputMode.node);

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("site", site);

        // 通过 #generateNode 函数执行 xpl 函数并得到 XNode 树
        XNode htmlNode = action.generateNode(scope);
        String html = htmlNode.innerHtml();
        log.info(StringHelper.unescapeXml(html));
    }
}
