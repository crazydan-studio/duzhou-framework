package io.crazydan.duzhou.framework.app_dev.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityProp;

/** {@link DevAppEntityProp} 的业务处理模型 */
@BizModel("DevAppEntityProp")
public class DevAppEntityPropBizModel extends CrudBizModel<DevAppEntityProp> {

    public DevAppEntityPropBizModel() {
        setEntityName(DevAppEntityProp.class.getName());
    }
}
