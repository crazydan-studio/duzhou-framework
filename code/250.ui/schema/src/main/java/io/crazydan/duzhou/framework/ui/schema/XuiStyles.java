package io.crazydan.duzhou.framework.ui.schema;

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
        // TODO 根据主题 vfs 路径解析得到主题对象

        getDefs().forEach((name, def) -> def.init());
    }

    public XuiStyleThemes getThemes() {
        return this.themes;
    }

    private XuiStyleThemes loadThemes(String path) {
        if (StringHelper.isBlank(path)) {
            return null;
        }

        return (XuiStyleThemes) DslModelHelper.loadDslModelFromPath(path);
    }
}
