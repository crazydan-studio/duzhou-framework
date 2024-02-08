package io.crazydan.duzhou.framework.schema.web;

import java.util.Map;

import io.crazydan.duzhou.framework.schema.web._gen._XWebSite;
import io.nop.core.lang.eval.IEvalAction;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.xlang.api.XLang;

public class XWebSite extends _XWebSite {
    public XWebSite() {

    }

    public Map<String, Object> renderLayout() {
        IEvalAction render = getLayout().getRender();

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("site", this);

        return (Map<String, Object>) render.invoke(scope);
    }
}
