package io.crazydan.duzhou.framework.app_dev.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityColumn;

/** {@link DevAppEntityColumn} 的业务处理模型 */
@BizModel("DevAppEntityColumn")
public class DevAppEntityColumnBizModel extends CrudBizModel<DevAppEntityColumn> {

    public DevAppEntityColumnBizModel() {
        setEntityName(DevAppEntityColumn.class.getName());
    }
}
