package io.crazydan.duzhou.framework.ui.schema.style;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.crazydan.duzhou.framework.ui.schema.style._gen._XuiStyleDefs;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.INeedInit;
import io.nop.xlang.xpl.utils.XplParseHelper;

public class XuiStyleDefs extends _XuiStyleDefs implements INeedInit {
    private boolean allowPatchNode;

    public XuiStyleDefs() {
    }

    /** Note: init 函数将在 {@link #freeze} 之前被调用 */
    @Override
    public void init() {
        checkDefNodes();
    }

    /** 获取指定名字的{@link XuiStyleDef 样式定义} */
    public XuiStyleDef getStyleDef(String name) {
        return getChild(name);
    }

    /** 是否允许补丁节点：只有组件样式定义集才支持补丁节点 */
    public void setAllowPatchNode(boolean allowPatchNode) {
        checkAllowChange();
        this.allowPatchNode = allowPatchNode;
    }

    protected void checkDefNodes() {
        for (XuiStyleDef def : getChildren().values()) {
            for (XuiStyleDefNode defNode : def.getChildren().values()) {
                String defNodeName = defNode.get$tag();
                XuiStyleDef defNodeDef = getStyleDef(defNodeName);

                if (defNode.hasChildren()) {
                    if (!this.allowPatchNode) {
                        // TODO patch node not allowed
                        throw new NopException();
                    }

                    // TODO check parameters, then check patch node's reference and parameters
                } else {
                    // TODO check reference and parameters
                }
            }
        }
    }

    protected void checkRefNodeProps(XuiStyleRef style, XuiStyleDef styleDef, Set<String> availableRefProps) {
        List<String> undefinedProps = new ArrayList<>();

        style.getProps().forEach((prop, value) -> {
            if (styleDef != null && !styleDef.getProps().containsKey(prop)) {
                undefinedProps.add(prop);
            }

            // TODO set style prop type

            if (XplParseHelper.isExpr(value.toString())) {
                //
            }
        });
    }
}
