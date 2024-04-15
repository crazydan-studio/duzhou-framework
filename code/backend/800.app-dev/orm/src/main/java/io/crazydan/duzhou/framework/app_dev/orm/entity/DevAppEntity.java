package io.crazydan.duzhou.framework.app_dev.orm.entity;

import io.nop.api.core.annotations.biz.BizObjName;
import io.crazydan.duzhou.framework.app_dev.orm.entity._gen._DevAppEntity;



/** 应用 ORM 实体的 ORM 实体 */
@BizObjName("DevAppEntity")
public class DevAppEntity extends _DevAppEntity {
    private long columnSize;

    public long getColumnSize() {
        return this.columnSize;
    }

    public void setColumnSize(long columnSize) {
        this.columnSize = columnSize;
    }
}

