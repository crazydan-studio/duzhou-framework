package io.crazydan.duzhou.framework.app_designer.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppDomain;

/** {@link DevAppDomain} 的业务处理模型 */
@BizModel("DevAppDomain")
public class DevAppDomainBizModel extends CrudBizModel<DevAppDomain> {

    public DevAppDomainBizModel() {
        setEntityName(DevAppDomain.class.getName());
    }
}
