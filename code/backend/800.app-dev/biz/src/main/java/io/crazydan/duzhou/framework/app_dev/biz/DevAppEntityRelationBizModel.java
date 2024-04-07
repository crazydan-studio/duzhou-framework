package io.crazydan.duzhou.framework.app_dev.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityRelation;

/** {@link DevAppEntityRelation} 的业务处理模型 */
@BizModel("DevAppEntityRelation")
public class DevAppEntityRelationBizModel extends CrudBizModel<DevAppEntityRelation> {

    public DevAppEntityRelationBizModel() {
        setEntityName(DevAppEntityRelation.class.getName());
    }
}
