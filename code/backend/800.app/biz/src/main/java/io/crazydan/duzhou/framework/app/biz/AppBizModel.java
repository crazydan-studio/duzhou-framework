package io.crazydan.duzhou.framework.app.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app.orm.entity.App;

/** {@link App} 的业务处理模型 */
@BizModel("App")
public class AppBizModel extends CrudBizModel<App> {

    public AppBizModel() {
        setEntityName(App.class.getName());
    }
}
