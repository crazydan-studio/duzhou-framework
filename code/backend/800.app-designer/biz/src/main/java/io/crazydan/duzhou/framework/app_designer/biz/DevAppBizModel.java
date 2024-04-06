package io.crazydan.duzhou.framework.app_designer.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_designer.orm.entity.DevApp;

/** {@link DevApp} 的业务处理模型 */
@BizModel("DevApp")
public class DevAppBizModel extends CrudBizModel<DevApp> {

    public DevAppBizModel() {
        setEntityName(DevApp.class.getName());
    }
}
