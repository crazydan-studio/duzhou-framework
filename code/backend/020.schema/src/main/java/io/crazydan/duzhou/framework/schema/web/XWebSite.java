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

    /** 获取入口 html 页面 {@link XNode} 节点 */
    public XNode getLayoutHtml() {
        if (this.layoutHtmlNode == null) {
            XNode node = XDslHelper.loadXNodeFromResource(getLayout().getHtml(), new Object[] { "$site", this });

            this.layoutHtmlNode = XDslHelper.toHtmlNode(node);
        }

        return this.layoutHtmlNode;
    }

    /** 获取布局器的配置数据 */
    public Map<String, Object> getLayoutConfig() {
        IEvalAction config = getLayout().getConfig();

        return EvalExecutor.exec(config, new Object[] { "site", this });
    }
}
