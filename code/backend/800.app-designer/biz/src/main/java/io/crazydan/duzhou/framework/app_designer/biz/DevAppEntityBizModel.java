package io.crazydan.duzhou.framework.app_designer.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppEntity;

/** {@link DevAppEntity} 的业务处理模型 */
@BizModel("DevAppEntity")
public class DevAppEntityBizModel extends CrudBizModel<DevAppEntity> {

    public DevAppEntityBizModel() {
        setEntityName(DevAppEntity.class.getName());
    }
}
