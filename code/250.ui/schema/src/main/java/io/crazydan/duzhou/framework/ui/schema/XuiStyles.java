package io.crazydan.duzhou.framework.ui.schema;

import java.util.List;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.schema._gen._XuiStyles;
import io.nop.api.core.util.INeedInit;
import io.nop.commons.util.StringHelper;
import io.nop.xlang.xdsl.DslModelHelper;

public class XuiStyles extends _XuiStyles implements INeedInit {
    private XuiStyleThemes themes;

    public XuiStyles() {
    }

    @Override
    public void init() {
        String themesPath = StringHelper.toString(prop_get("xui:themes"), null);
        this.themes = loadThemes(themesPath);

        // TODO 检查结构节点的样式定义是否存在、引用的属性类型是否一致，不存在样式定义的结构节点视为原子样式，其与运行时原生样式直接对应
        // TODO 将结构节点中的属性的确定值转换为对应的类型对象

        getDefs().forEach((name, def) -> def.init());
    }

    public XuiStyleThemes getThemes() {
        return this.themes;
    }

    /**
     * 展开指定名字的样式
     * <p/>
     * - 原子样式不做展开，直接返回
     * - 复合样式将做层级展开，直到全部为原子样式，并对同名的原子样式做合并
     */
    public XuiStyleDef expand(String name) {
        return doExpand(getDef(name));
    }

    /** {@link #expand 展开}全部样式 */
    public List<XuiStyleDef> expandAll() {
        return getDefs().values().stream().map(this::doExpand).collect(Collectors.toList());
    }

    private XuiStyleThemes loadThemes(String path) {
        if (StringHelper.isBlank(path)) {
            return null;
        }

        return (XuiStyleThemes) DslModelHelper.loadDslModelFromPath(path);
    }

    private XuiStyleDef doExpand(XuiStyleDef def) {
        return def;
    }
}
