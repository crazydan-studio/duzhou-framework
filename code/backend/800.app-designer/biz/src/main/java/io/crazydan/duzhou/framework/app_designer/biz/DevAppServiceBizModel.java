package io.crazydan.duzhou.framework.app_designer.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppService;

/** {@link DevAppService} 的业务处理模型 */
@BizModel("DevAppService")
public class DevAppServiceBizModel extends CrudBizModel<DevAppService> {

    public DevAppServiceBizModel() {
        setEntityName(DevAppService.class.getName());
    }
}
