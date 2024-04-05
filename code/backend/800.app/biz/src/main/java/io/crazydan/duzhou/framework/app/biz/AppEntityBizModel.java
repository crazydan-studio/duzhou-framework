package io.crazydan.duzhou.framework.app.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app.orm.entity.AppEntity;

/** {@link AppEntity} 的业务处理模型 */
@BizModel("AppEntity")
public class AppEntityBizModel extends CrudBizModel<AppEntity> {

    public AppEntityBizModel() {
        setEntityName(AppEntity.class.getName());
    }
}
