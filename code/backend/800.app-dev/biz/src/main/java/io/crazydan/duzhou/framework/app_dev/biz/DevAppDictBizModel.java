package io.crazydan.duzhou.framework.app_dev.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppDict;

/** {@link DevAppDict} 的业务处理模型 */
@BizModel("DevAppDict")
public class DevAppDictBizModel extends CrudBizModel<DevAppDict> {

    public DevAppDictBizModel() {
        setEntityName(DevAppDict.class.getName());
    }
}
