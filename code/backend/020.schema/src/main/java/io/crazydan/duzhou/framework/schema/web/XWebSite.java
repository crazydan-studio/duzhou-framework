package io.crazydan.duzhou.framework.schema.web;

import java.util.Map;

import io.crazydan.duzhou.framework.commons.EvalExecutor;
import io.crazydan.duzhou.framework.commons.XDslHelper;
import io.crazydan.duzhou.framework.schema.web._gen._XWebSite;
import io.nop.core.lang.eval.IEvalAction;
import io.nop.core.lang.xml.XNode;

public class XWebSite extends _XWebSite {
    public XWebSite() {

    }

    /** 获取入口 html 页面 {@link XNode} 节点 */
    public XNode getLayoutHtml() {
        // TODO 若 node 结构不再变化，可以放在成员变量中以缓存解析结果
        XNode node = XDslHelper.loadXNodeFromResource(getLayout().getHtml(), new Object[] { "$site", this });

        return XDslHelper.toHtml(node);
    }

    /** 获取布局器的配置数据 */
    public Map<String, Object> getLayoutConfig() {
        IEvalAction config = getLayout().getConfig();

        return EvalExecutor.exec(config, new Object[] { "site", this });
    }
}
