package io.crazydan.duzhou.framework.app_dev.biz;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity;
import io.crazydan.duzhou.framework.app_dev.orm.mapper.DevAppEntityMapper;
import io.nop.api.core.annotations.biz.BizModel;
import io.nop.api.core.beans.FieldSelectionBean;
import io.nop.api.core.beans.PageBean;
import io.nop.api.core.beans.query.QueryBean;
import io.nop.biz.crud.CrudBizModel;
import io.nop.core.context.IServiceContext;
import jakarta.inject.Inject;

/** {@link DevAppEntity} 的业务处理模型 */
@BizModel("DevAppEntity")
public class DevAppEntityBizModel extends CrudBizModel<DevAppEntity> {
    @Inject
    protected DevAppEntityMapper mapper;

    public DevAppEntityBizModel() {
        setEntityName(DevAppEntity.class.getName());
    }

    @Override
    public PageBean<DevAppEntity> doFindPage(
            QueryBean query, BiConsumer<QueryBean, IServiceContext> prepareQuery, FieldSelectionBean selection,
            IServiceContext context
    ) {
        PageBean<DevAppEntity> page = super.doFindPage(query, prepareQuery, selection, context);
        patchData(page.getItems());

        return page;
    }

    @Override
    public List<DevAppEntity> doFindList(
            QueryBean query, BiConsumer<QueryBean, IServiceContext> prepareQuery, FieldSelectionBean selection,
            IServiceContext context
    ) {
        List<DevAppEntity> list = super.doFindList(query, prepareQuery, selection, context);
        patchData(list);

        return list;
    }

    private void patchData(Collection<DevAppEntity> entities) {
        Map<String, Long> results = this.mapper.countColumns(entities.stream()
                                                                     .map(DevAppEntity::getId)
                                                                     .collect(Collectors.toList()));
        entities.forEach(entity -> {
            entity.setColumnSize(results.getOrDefault(entity.getId(), 0L));
        });
    }
}
