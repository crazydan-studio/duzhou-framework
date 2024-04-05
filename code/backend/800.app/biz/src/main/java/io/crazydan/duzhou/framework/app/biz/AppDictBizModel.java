package io.crazydan.duzhou.framework.app.biz;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.biz.crud.CrudBizModel;

import io.crazydan.duzhou.framework.app.orm.entity.AppDict;

/** {@link AppDict} 的业务处理模型 */
@BizModel("AppDict")
public class AppDictBizModel extends CrudBizModel<AppDict> {

    public AppDictBizModel() {
        setEntityName(AppDict.class.getName());
    }
}
