package io.crazydan.duzhou.framework.app_dev.biz;

import java.io.File;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevApp;
import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppService;
import io.nop.api.core.annotations.biz.BizModel;
import io.nop.api.core.annotations.biz.BizMutation;
import io.nop.api.core.annotations.core.Name;
import io.nop.biz.crud.CrudBizModel;
import io.nop.codegen.XCodeGenerator;
import io.nop.commons.util.FileHelper;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.xlang.api.XLang;

/** {@link DevAppService} 的业务处理模型 */
@BizModel("DevAppService")
public class DevAppServiceBizModel extends CrudBizModel<DevAppService> {

    public DevAppServiceBizModel() {
        setEntityName(DevAppService.class.getName());
    }

    @BizMutation
    public void release(@Name("id") String id, @Name("releaseDir") String releaseDir) throws Exception {
        DevAppService service = dao().getEntityById(id);
        dao().batchLoadPropsForEntity(service,
                                      "app,entities"
                                      + ",entities.columns,entities.relations"
                                      + ",entities.relations.source,entities.relations.target");
        DevApp app = service.getApp();

        FileHelper.assureParent(new File(releaseDir, "/any"));

        XCodeGenerator gen = new XCodeGenerator("/duzhou/templates/app-modeler", releaseDir);

        IEvalScope scope = XLang.newEvalScope();
        scope.setLocalValue("app", app);
        scope.setLocalValue("service", service);

        gen.execute("/", scope);
    }
}
