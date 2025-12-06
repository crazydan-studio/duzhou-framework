package io.crazydan.duzhou.framework.ui.schema.component.template;

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.ui.XuiNamed;
import io.crazydan.duzhou.framework.ui.domain.GenericStdDomainHandlers;
import io.crazydan.duzhou.framework.ui.schema.component.template._gen._XuiComponentTemplateNodeNested;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.INeedInit;

import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_INVALID_TAG_NAME;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_DISPATCHES_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED;
import static io.nop.xlang.XLangErrors.ARG_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;

public class XuiComponentTemplateNodeNested extends _XuiComponentTemplateNodeNested implements INeedInit {

    public XuiComponentTemplateNodeNested() {

    }

    @Override
    public void init() {
        checkTagName();
        checkMultiLayouts();
        checkMultiDispatches();
        checkSlotInSlot();
        // TODO xui:slot 不能存在重名

        getChildren().forEach((child) -> {
            if (child instanceof INeedInit) {
                ((INeedInit) child).init();
            }
        });
    }

    protected void checkTagName() {
        String tagName = get$tag();

        if (this instanceof XuiComponentTemplateNodeAny) {
            if (!GenericStdDomainHandlers.isValidComponentName(tagName)) {
                throw new NopException(ERR_COMPONENT_INVALID_TAG_NAME).source(this).param(ARG_TAG_NAME, tagName);
            }
        }
    }

    protected void checkMultiLayouts() {
        boolean exists = false;
        for (XuiNamed child : getChildren()) {
            if (child instanceof XuiComponentTemplateNodeLayout) {
                XuiComponentTemplateNodeLayout layout = (XuiComponentTemplateNodeLayout) child;
                if (exists) {
                    throw new NopException(ERR_COMPONENT_MULTIPLE_LAYOUTS_NOT_ALLOWED).source(layout)
                                                                                      .param(ARG_TAG_NAME, get$tag());
                } else {
                    exists = true;
                }
            }
        }
    }

    protected void checkMultiDispatches() {
        List<String> messages = new ArrayList<>();

        for (XuiNamed child : getChildren()) {
            if (child instanceof XuiComponentTemplateNodeDispatch) {
                XuiComponentTemplateNodeDispatch dispatch = (XuiComponentTemplateNodeDispatch) child;
                String msg = dispatch.getMsg();

                if (messages.contains(msg)) {
                    throw new NopException(ERR_COMPONENT_MULTIPLE_DISPATCHES_NOT_ALLOWED).source(dispatch)
                                                                                         .param(ARG_TAG_NAME, get$tag())
                                                                                         .param(ARG_NAME, msg);
                } else {
                    messages.add(msg);
                }
            }
        }
    }

    protected void checkSlotInSlot() {
        if (isSlot() && hasSlotInDepth()) {
            throw new NopException(ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED).source(this);
        }
    }

    protected boolean isSlot() {
        return this instanceof XuiComponentTemplateNodeSlot;
    }

    private boolean hasSlotInDepth() {
        for (XuiNamed child : getChildren()) {
            if (child instanceof XuiComponentTemplateNodeNested) {
                if (((XuiComponentTemplateNodeNested) child).isSlot() //
                    || ((XuiComponentTemplateNodeNested) child).hasSlotInDepth() //
                ) {
                    return true;
                }
            }
        }
        return false;
    }
}

