package io.crazydan.duzhou.framework.app_dev.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppService;

/** {@link DevAppService} 的业务处理模型 */
@BizModel("DevAppService")
public class DevAppServiceBizModel extends CrudBizModel<DevAppService> {

    public DevAppServiceBizModel() {
        setEntityName(DevAppService.class.getName());
    }
}
