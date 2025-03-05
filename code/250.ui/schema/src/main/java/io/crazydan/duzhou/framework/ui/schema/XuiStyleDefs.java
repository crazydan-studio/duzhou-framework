package io.crazydan.duzhou.framework.ui.schema;

import io.crazydan.duzhou.framework.ui.schema._gen._XuiStyleDefs;
import io.nop.api.core.util.INeedInit;

public class XuiStyleDefs extends _XuiStyleDefs implements INeedInit {

    public XuiStyleDefs() {
    }

    @Override
    public void init() {
        // TODO 校验属性类型、去掉被移除的属性等：当前 Nop 未支持对 xdef:unknown-attr 属性的校验
        // TODO 检查结构节点的样式定义是否存在、引用的属性类型是否一致，不存在样式定义的结构节点视为原子样式，其与运行时原生样式直接对应
        // TODO 将结构节点中的属性的确定值转换为对应的类型对象
        // TODO 根据主题 vfs 路径解析得到主题对象
    }
}
