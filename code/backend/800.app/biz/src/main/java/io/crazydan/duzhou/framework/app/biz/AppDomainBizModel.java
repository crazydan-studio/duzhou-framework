package io.crazydan.duzhou.framework.app.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app.orm.entity.AppDomain;

/** {@link AppDomain} 的业务处理模型 */
@BizModel("AppDomain")
public class AppDomainBizModel extends CrudBizModel<AppDomain> {

    public AppDomainBizModel() {
        setEntityName(AppDomain.class.getName());
    }
}
