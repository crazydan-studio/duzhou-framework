package io.crazydan.duzhou.framework.ui.schema.component;

import io.crazydan.duzhou.framework.ui.schema.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentImport;
import io.nop.core.resource.component.ResourceComponentManager;

public class XuiComponentImport extends _XuiComponentImport {
    public static final String NATIVE_IMPORT_PREFIX = "native:";

    public XuiComponentImport() {
    }

    /** 导入的是否为原生组件 */
    public boolean isNative() {
        return getFrom().startsWith(NATIVE_IMPORT_PREFIX);
    }

    /** 若导入的为原生组件，则返回原生组件类型，否则，返回 null */
    public String getNativeType() {
        return isNative() ? getFrom().substring(NATIVE_IMPORT_PREFIX.length()) : null;
    }

    /**
     * 获取导入组件对应的 {@link XuiComponent} 模型对象，
     * 若导入的是{@link #isNative() 原生组件}，则返回 null
     */
    public XuiComponent getModel() {
        if (isNative()) {
            return null;
        }

        String resourcePath = getFrom();
        return (XuiComponent) ResourceComponentManager.instance().loadComponentModel(resourcePath);
    }
}
