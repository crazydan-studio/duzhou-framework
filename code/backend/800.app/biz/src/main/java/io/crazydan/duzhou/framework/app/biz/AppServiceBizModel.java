package io.crazydan.duzhou.framework.app.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app.orm.entity.AppService;

/** {@link AppService} 的业务处理模型 */
@BizModel("AppService")
public class AppServiceBizModel extends CrudBizModel<AppService> {

    public AppServiceBizModel() {
        setEntityName(AppService.class.getName());
    }
}
