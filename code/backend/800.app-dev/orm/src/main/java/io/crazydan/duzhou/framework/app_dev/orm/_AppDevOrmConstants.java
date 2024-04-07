package io.crazydan.duzhou.framework.app_dev.orm;

@SuppressWarnings({"PMD","java:S116"})
public interface _AppDevOrmConstants {

    
    /**
     * 应用状态: 待定 
     */
    int APP_STATUS_unset = -1;
                        
    /**
     * 应用状态: 已禁用 
     */
    int APP_STATUS_disabled = 0;
                        
    /**
     * 应用状态: 已启用 
     */
    int APP_STATUS_enabled = 1;
                        
    /**
     * 服务状态: 待定 
     */
    int SERVICE_STATUS_unset = -1;
                        
    /**
     * 服务状态: 已禁用 
     */
    int SERVICE_STATUS_disabled = 0;
                        
    /**
     * 服务状态: 已启用 
     */
    int SERVICE_STATUS_enabled = 1;
                        
    /**
     * 实体标签: 不生成代码 
     */
    String ENTITY_TAGSET_not_gen = "not-gen";
                        
    /**
     * 实体标签: 删除该对象 
     */
    String ENTITY_TAGSET_del = "del";
                        
    /**
     * 实体标签: 需生成 SqlMapper 代码 
     */
    String ENTITY_TAGSET_mapper = "mapper";
                        
    /**
     * 实体字段标签: 不生成代码 
     */
    String ENTITY_PROP_TAGSET_not_gen = "not-gen";
                        
    /**
     * 实体字段标签: 生成唯一主键 UUID 字符串或递增序列
     */
    String ENTITY_PROP_TAGSET_seq = "seq";
                        
    /**
     * 实体字段标签: 删除该属性 
     */
    String ENTITY_PROP_TAGSET_del = "del";
                        
    /**
     * 实体字段标签: 生成随机值 
     */
    String ENTITY_PROP_TAGSET_var = "var";
                        
    /**
     * 实体字段标签: 在日志中打印掩码 在日志中隐藏敏感信息
     */
    String ENTITY_PROP_TAGSET_masked = "masked";
                        
    /**
     * 实体字段标签: 值不可见 
     */
    String ENTITY_PROP_TAGSET_not_pub = "not-pub";
                        
    /**
     * 实体字段标签: 默认排序属性（升序） 
     */
    String ENTITY_PROP_TAGSET_sort = "sort";
                        
    /**
     * 实体字段标签: 默认排序属性（降序） 
     */
    String ENTITY_PROP_TAGSET_sort_desc = "sort-desc";
                        
    /**
     * 实体关联类型: 一对一关联 
     */
    String ENTITY_RELATION_TYPE_to_one = "to-one";
                        
    /**
     * 实体关联类型: 一对多关联 
     */
    String ENTITY_RELATION_TYPE_to_many = "to-many";
                        
    /**
     * 实体关联标签: 该关联可访问 
     */
    String ENTITY_RELATION_TAGSET_pub = "pub";
                        
    /**
     * 实体关联标签: 对端关联可访问 
     */
    String ENTITY_RELATION_TAGSET_ref_pub = "ref-pub";
                        
    /**
     * 实体关联标签: 级联删除 
     */
    String ENTITY_RELATION_TAGSET_ref_cascade_delete = "ref-cascade-delete";
                        
    
}
    
