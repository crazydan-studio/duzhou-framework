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

import io.crazydan.duzhou.framework.commons.XDslHelper;
import io.crazydan.duzhou.framework.gateway.web.GatewayWebBaseTest;
import io.crazydan.duzhou.framework.schema.web.XWeb;
import io.crazydan.duzhou.framework.schema.web.XWebSite;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.json.JsonTool;
import io.nop.core.lang.xml.XNode;
import io.nop.core.lang.xml.parse.XNodeParser;
import io.nop.xlang.api.ExprEvalAction;
import io.nop.xlang.api.XLang;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.ast.XLangOutputMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-07
 */
public class SiteGenTest extends GatewayWebBaseTest {
    private static final String WEB_XDSL = "/duzhou/web/app.web.xml";

    /** site -> html 的 xpl 函数：不支持对 html 做差量修订 */
    private static final String SITE_HTML_XPL = "/duzhou/web/app.site-html.xpl";

    @Test
    public void test_Gen_Site_Html() {
        XWeb web = XWeb.parseFromVirtualPath(WEB_XDSL);
        String json = JsonTool.serialize(web, true).replaceAll("\\s+\"(location|config|xmlns:.+)\".+", "");
        this.log.info(json);
        Assertions.assertEquals(attachmentJsonText("gen.app.web.json"), json);

        for (XWebSite site : web.getSites()) {
            XNode node = site.getLayoutHtml();
            String html = toHtml(node);
            this.log.info(html);

            String expected = toHtml(attachmentXml("gen." + site.getId() + ".site.html"));
            Assertions.assertEquals(expected, html);
        }
    }

    @Test
    public void test_Gen_Site_Html_by_Xpl() {
        XWeb web = XWeb.parseFromVirtualPath(WEB_XDSL);

        for (XWebSite site : web.getSites()) {
            XLangCompileTool compiler = XLang.newCompileTool();
            // Note：在编译 xpl 时需要获取 ${} 中的变量，但在编译器中无法注入该变量，
            // 故而，需要忽略未注册变量，以确保编译能够正常进行
            compiler.getScope().setAllowUnregisteredScopeVar(true);

            XNode node = XNodeParser.instance().parseFromVirtualPath(SITE_HTML_XPL);
            ExprEvalAction action = compiler.compileTagBody(node, XLangOutputMode.node);

            IEvalScope scope = XLang.newEvalScope();
            scope.setLocalValue("site", site);

            // 通过 #generateNode 函数执行 xpl 函数并得到 XNode 树
            XNode htmlNode = XDslHelper.toHtmlNode(action.generateNode(scope).firstChild());
            String html = toHtml(htmlNode);
            this.log.info(html);

            String expected = toHtml(attachmentXml("xpl-gen." + site.getId() + ".site.html"));
            Assertions.assertEquals(expected, html);
        }
    }

    private String toHtml(XNode node) {
        return node.html().replaceAll("(?m)^\\s+| +$", "");
    }
}
