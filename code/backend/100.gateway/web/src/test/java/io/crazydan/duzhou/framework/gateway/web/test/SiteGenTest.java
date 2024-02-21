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
        for (String[] pair : new String[][] {
                new String[] { SITE_HTML_XDSL, "gen.site.html" },
                new String[] { SITE_HTML_EXTENDS_XDSL, "gen.extends.site.html" }
        }) {
            String dslPath = pair[0];
            String casePath = pair[1];

            XNode node = XNodeParser.instance().parseFromVirtualPath(dslPath);
            String xdefPath = node.attrText("x:schema");

            DslModelParser parser = new DslModelParser(xdefPath);
            DynamicObject obj = (DynamicObject) parser.parseFromNode(node);
            String json = JsonTool.serialize(obj, true);

            this.log.info(dslPath + ":json={}", json);
            Assertions.assertEquals(attachmentJsonText(casePath + ".json"), json);

            XNode htmlNode = WebDslModelHelper.toHtmlNode(parser.getRequiredSchema(), obj);
            String html = "<!DOCTYPE html>" + htmlNode.html().replaceAll("\n\\s*", "");
            html = StringHelper.unescapeXml(html);

            this.log.info(dslPath + ":html={}", html);
            Assertions.assertEquals(attachmentText(casePath), html + "\n");
        }
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
        this.log.info(StringHelper.unescapeXml(html));
    }
}
