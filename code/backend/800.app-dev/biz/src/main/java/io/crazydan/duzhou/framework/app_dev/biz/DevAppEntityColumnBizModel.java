package io.crazydan.duzhou.framework.app_dev.biz;

import java.util.Map;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityColumn;
import io.nop.api.core.annotations.biz.BizModel;
import io.nop.api.core.beans.FieldSelectionBean;
import io.nop.api.core.beans.query.QueryBean;
import io.nop.biz.crud.CrudBizModel;
import io.nop.biz.crud.EntityData;
import io.nop.core.context.IServiceContext;
import io.nop.dao.api.IEntityDao;

import static io.nop.api.core.beans.FilterBeans.eq;

/** {@link DevAppEntityColumn} 的业务处理模型 */
@BizModel("DevAppEntityColumn")
public class DevAppEntityColumnBizModel extends CrudBizModel<DevAppEntityColumn> {

    public DevAppEntityColumnBizModel() {
        setEntityName(DevAppEntityColumn.class.getName());
    }

    @Override
    protected EntityData<DevAppEntityColumn> buildEntityDataForSave(
            Map<String, Object> data, FieldSelectionBean inputSelection, IServiceContext context
    ) {
        Object entityId = data.get("entityId");
        if (entityId != null) {
            QueryBean query = new QueryBean();
            query.setLimit(1);
            query.setFilter(eq("entityId", entityId));
            query.addOrderField("propId", true);

            IEntityDao<DevAppEntityColumn> dao = this.dao();
            DevAppEntityColumn column = dao.findFirstByQuery(query);

            int propId = 0;
            if (column != null) {
                propId = column.getPropId();
            }
            data.put("propId", propId + Math.max((int) data.getOrDefault("propId", 0), 1));
        }

        return super.buildEntityDataForSave(data, inputSelection, context);
    }
}
