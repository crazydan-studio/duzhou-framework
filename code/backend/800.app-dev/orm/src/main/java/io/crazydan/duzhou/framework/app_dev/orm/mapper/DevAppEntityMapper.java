package io.crazydan.duzhou.framework.app_dev.orm.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.nop.api.core.annotations.core.Name;
import io.nop.api.core.annotations.orm.SqlLibMapper;

@SqlLibMapper("/duzhou/app_dev/orm/mapper/DevAppEntity.sql-lib.xml")
public interface DevAppEntityMapper {

    default Map<String, Long> countColumns(Collection<String> entityIds) {
        return doCountColumns(entityIds).stream()
                                        .collect(Collectors.toMap(m -> (String) m.get("entityId"),
                                                                  m -> (Long) m.get("total")));
    }

    List<Map<String, Object>> doCountColumns(@Name("entityIds") Collection<String> entityIds);
}
