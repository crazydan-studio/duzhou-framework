package io.crazydan.duzhou.framework.schema.web;

import java.util.Map;

import io.crazydan.duzhou.framework.commons.EvalExecutor;
import io.crazydan.duzhou.framework.commons.XDslHelper;
import io.crazydan.duzhou.framework.schema.web._gen._XWebSite;
import io.nop.core.lang.eval.IEvalAction;
import io.nop.core.lang.xml.XNode;

public class XWebSite extends _XWebSite {
    private XNode layoutHtmlNode;

    public XWebSite() {

    }

    /**
     * 初始化接口，以支持在 `web.xdef` 的 `xdef:post-parse`
     * 节点中对所有站点进行初始化，从而确保站点 HTML
     * 所引用的全局变量在单一线程中完成使用，避免出现多线程并发问题
     */
    public void init() {
        XNode node = XDslHelper.loadXNodeFromResource(getLayout().getHtml(), new Object[] { "$site", this });
        this.layoutHtmlNode = XDslHelper.toHtmlNode(node);
    }

    /** 获取入口 html 页面 {@link XNode} 节点 */
    public XNode getLayoutHtmlNode() {
        return this.layoutHtmlNode;
    }

    /** 获取布局器的配置数据 */
    public Map<String, Object> getLayoutConfig() {
        IEvalAction config = getLayout().getConfig();

        return EvalExecutor.exec(config, new Object[] { "site", this });
    }
}
