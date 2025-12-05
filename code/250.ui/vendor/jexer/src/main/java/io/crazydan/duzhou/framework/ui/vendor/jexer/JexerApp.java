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

package io.crazydan.duzhou.framework.ui.vendor.jexer;

import java.io.UnsupportedEncodingException;
import java.util.function.Supplier;

import io.crazydan.duzhou.framework.ui.schema.page.XuiPage;
import io.crazydan.duzhou.framework.ui.util.XuiHelper;
import io.crazydan.duzhou.framework.ui.vendor.core.XuiComponentTreeNode;
import io.crazydan.duzhou.framework.ui.vendor.jexer.page.JexerErrorPage;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.xlang.api.XLang;
import jexer.TApplication;
import jexer.TDesktop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-26
 */
public class JexerApp extends TApplication {
    private static final Logger log = LoggerFactory.getLogger(JexerApp.class);

    public JexerApp() throws UnsupportedEncodingException {
        super(detectBackendType());
    }

    /** 异步加载并渲染页面 */
    public void asyncRender(String pageDslPath, Object data) {
        new Thread(() -> render(pageDslPath, data)).start();
    }

    public void render(String pageDslPath, Object data) {
        doRender(() -> XuiHelper.loadPage(pageDslPath), data);
    }

    public void render(XuiPage page, Object data) {
        doRender(() -> page, data);
    }

    protected void doRender(Supplier<XuiPage> pageGetter, Object data) {
        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("data", data);

        TDesktop desktop;
        try {
            XuiPage page = pageGetter.get();
            XuiComponentTreeNode node = XuiComponentTreeNode.build(page, scope);

            desktop = new JexerPage(this, node);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error("jingwei.xui.render-page-error", e);

            desktop = new JexerErrorPage(this, msg);
        }

        setDesktop(desktop, true);
    }

    protected static TApplication.BackendType detectBackendType() {
        String term = System.getenv("TERM");

        // Note: swing 类型将在独立的弹出窗口中显示字符界面
        return StringHelper.isBlank(term) ? TApplication.BackendType.SWING : TApplication.BackendType.XTERM;
    }
}
