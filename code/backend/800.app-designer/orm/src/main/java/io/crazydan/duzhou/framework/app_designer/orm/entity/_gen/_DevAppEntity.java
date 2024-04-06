package io.crazydan.duzhou.framework.app_designer.orm.entity._gen;

import io.nop.orm.model.IEntityModel;
import io.nop.orm.support.DynamicOrmEntity;
import io.nop.orm.support.OrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.orm.IOrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.api.core.convert.ConvertHelper;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

import io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppEntity;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  应用 ORM 实体: duzhou_dev_app_entity
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _DevAppEntity extends DynamicOrmEntity{
    
    /* ID: ID VARCHAR */
    public static final String PROP_NAME_id = "id";
    public static final int PROP_ID_id = 1;
    
    /* 创建人: CREATED_BY VARCHAR */
    public static final String PROP_NAME_createdBy = "createdBy";
    public static final int PROP_ID_createdBy = 2;
    
    /* 创建时间: CREATED_AT TIMESTAMP */
    public static final String PROP_NAME_createdAt = "createdAt";
    public static final int PROP_ID_createdAt = 3;
    
    /* 更新人: UPDATED_BY VARCHAR */
    public static final String PROP_NAME_updatedBy = "updatedBy";
    public static final int PROP_ID_updatedBy = 4;
    
    /* 更新时间: UPDATED_AT TIMESTAMP */
    public static final String PROP_NAME_updatedAt = "updatedAt";
    public static final int PROP_ID_updatedAt = 5;
    
    /* 数据版本: VER INTEGER */
    public static final String PROP_NAME_ver = "ver";
    public static final int PROP_ID_ver = 6;
    
    /* 所属服务: SERVICE_ID VARCHAR */
    public static final String PROP_NAME_serviceId = "serviceId";
    public static final int PROP_ID_serviceId = 7;
    
    /* 名称: TITLE VARCHAR */
    public static final String PROP_NAME_title = "title";
    public static final int PROP_ID_title = 8;
    
    /* 类名: CLASS_NAME VARCHAR */
    public static final String PROP_NAME_className = "className";
    public static final int PROP_ID_className = 9;
    
    /* 数据库表名: TABLE_NAME VARCHAR */
    public static final String PROP_NAME_tableName = "tableName";
    public static final int PROP_ID_tableName = 10;
    
    /* 字段列表: COLUMNS VARCHAR */
    public static final String PROP_NAME_columns = "columns";
    public static final int PROP_ID_columns = 11;
    
    /* 唯一键列表: UNIQUE_KEYS VARCHAR */
    public static final String PROP_NAME_uniqueKeys = "uniqueKeys";
    public static final int PROP_ID_uniqueKeys = 12;
    
    /* 关联列表: RELATIONS VARCHAR */
    public static final String PROP_NAME_relations = "relations";
    public static final int PROP_ID_relations = 13;
    

    private static int _PROP_ID_BOUND = 14;

    
    /* relation:  */
    public static final String PROP_NAME_service = "service";
    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_id);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_id};

    private static final String[] PROP_ID_TO_NAME = new String[14];
    private static final Map<String,Integer> PROP_NAME_TO_ID = new HashMap<>();
    static{
      
          PROP_ID_TO_NAME[PROP_ID_id] = PROP_NAME_id;
          PROP_NAME_TO_ID.put(PROP_NAME_id, PROP_ID_id);
      
          PROP_ID_TO_NAME[PROP_ID_createdBy] = PROP_NAME_createdBy;
          PROP_NAME_TO_ID.put(PROP_NAME_createdBy, PROP_ID_createdBy);
      
          PROP_ID_TO_NAME[PROP_ID_createdAt] = PROP_NAME_createdAt;
          PROP_NAME_TO_ID.put(PROP_NAME_createdAt, PROP_ID_createdAt);
      
          PROP_ID_TO_NAME[PROP_ID_updatedBy] = PROP_NAME_updatedBy;
          PROP_NAME_TO_ID.put(PROP_NAME_updatedBy, PROP_ID_updatedBy);
      
          PROP_ID_TO_NAME[PROP_ID_updatedAt] = PROP_NAME_updatedAt;
          PROP_NAME_TO_ID.put(PROP_NAME_updatedAt, PROP_ID_updatedAt);
      
          PROP_ID_TO_NAME[PROP_ID_ver] = PROP_NAME_ver;
          PROP_NAME_TO_ID.put(PROP_NAME_ver, PROP_ID_ver);
      
          PROP_ID_TO_NAME[PROP_ID_serviceId] = PROP_NAME_serviceId;
          PROP_NAME_TO_ID.put(PROP_NAME_serviceId, PROP_ID_serviceId);
      
          PROP_ID_TO_NAME[PROP_ID_title] = PROP_NAME_title;
          PROP_NAME_TO_ID.put(PROP_NAME_title, PROP_ID_title);
      
          PROP_ID_TO_NAME[PROP_ID_className] = PROP_NAME_className;
          PROP_NAME_TO_ID.put(PROP_NAME_className, PROP_ID_className);
      
          PROP_ID_TO_NAME[PROP_ID_tableName] = PROP_NAME_tableName;
          PROP_NAME_TO_ID.put(PROP_NAME_tableName, PROP_ID_tableName);
      
          PROP_ID_TO_NAME[PROP_ID_columns] = PROP_NAME_columns;
          PROP_NAME_TO_ID.put(PROP_NAME_columns, PROP_ID_columns);
      
          PROP_ID_TO_NAME[PROP_ID_uniqueKeys] = PROP_NAME_uniqueKeys;
          PROP_NAME_TO_ID.put(PROP_NAME_uniqueKeys, PROP_ID_uniqueKeys);
      
          PROP_ID_TO_NAME[PROP_ID_relations] = PROP_NAME_relations;
          PROP_NAME_TO_ID.put(PROP_NAME_relations, PROP_ID_relations);
      
    }

    
    /* ID: ID */
    private java.lang.String _id;
    
    /* 创建人: CREATED_BY */
    private java.lang.String _createdBy;
    
    /* 创建时间: CREATED_AT */
    private java.sql.Timestamp _createdAt;
    
    /* 更新人: UPDATED_BY */
    private java.lang.String _updatedBy;
    
    /* 更新时间: UPDATED_AT */
    private java.sql.Timestamp _updatedAt;
    
    /* 数据版本: VER */
    private java.lang.Integer _ver;
    
    /* 所属服务: SERVICE_ID */
    private java.lang.String _serviceId;
    
    /* 名称: TITLE */
    private java.lang.String _title;
    
    /* 类名: CLASS_NAME */
    private java.lang.String _className;
    
    /* 数据库表名: TABLE_NAME */
    private java.lang.String _tableName;
    
    /* 字段列表: COLUMNS */
    private java.lang.String _columns;
    
    /* 唯一键列表: UNIQUE_KEYS */
    private java.lang.String _uniqueKeys;
    
    /* 关联列表: RELATIONS */
    private java.lang.String _relations;
    

    public _DevAppEntity(){
        // for debug
    }

    protected DevAppEntity newInstance(){
       return new DevAppEntity();
    }

    @Override
    public DevAppEntity cloneInstance() {
        DevAppEntity entity = newInstance();
        orm_forEachInitedProp((value, propId) -> {
            entity.onInitProp(propId);
        });
        return entity;
    }

    @Override
    public String orm_entityName() {
      // 如果存在实体模型对象，则以模型对象上的设置为准
      IEntityModel entityModel = orm_entityModel();
      if(entityModel != null)
          return entityModel.getName();
      return "io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppEntity";
    }

    @Override
    public int orm_propIdBound(){
      IEntityModel entityModel = orm_entityModel();
      if(entityModel != null)
          return entityModel.getPropIdBound();
      return _PROP_ID_BOUND;
    }

    @Override
    public Object orm_id() {
    
        return buildSimpleId(PROP_ID_id);
     
    }

    @Override
    public boolean orm_isPrimary(int propId) {
        
            return propId == PROP_ID_id;
          
    }

    @Override
    public String orm_propName(int propId) {
        if(propId >= PROP_ID_TO_NAME.length)
            return super.orm_propName(propId);
        String propName = PROP_ID_TO_NAME[propId];
        if(propName == null)
           return super.orm_propName(propId);
        return propName;
    }

    @Override
    public int orm_propId(String propName) {
        Integer propId = PROP_NAME_TO_ID.get(propName);
        if(propId == null)
            return super.orm_propId(propName);
        return propId;
    }

    @Override
    public Object orm_propValue(int propId) {
        switch(propId){
        
            case PROP_ID_id:
               return getId();
        
            case PROP_ID_createdBy:
               return getCreatedBy();
        
            case PROP_ID_createdAt:
               return getCreatedAt();
        
            case PROP_ID_updatedBy:
               return getUpdatedBy();
        
            case PROP_ID_updatedAt:
               return getUpdatedAt();
        
            case PROP_ID_ver:
               return getVer();
        
            case PROP_ID_serviceId:
               return getServiceId();
        
            case PROP_ID_title:
               return getTitle();
        
            case PROP_ID_className:
               return getClassName();
        
            case PROP_ID_tableName:
               return getTableName();
        
            case PROP_ID_columns:
               return getColumns();
        
            case PROP_ID_uniqueKeys:
               return getUniqueKeys();
        
            case PROP_ID_relations:
               return getRelations();
        
           default:
              return super.orm_propValue(propId);
        }
    }

    

    @Override
    public void orm_propValue(int propId, Object value){
        switch(propId){
        
            case PROP_ID_id:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_id));
               }
               setId(typedValue);
               break;
            }
        
            case PROP_ID_createdBy:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_createdBy));
               }
               setCreatedBy(typedValue);
               break;
            }
        
            case PROP_ID_createdAt:{
               java.sql.Timestamp typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toTimestamp(value,
                       err-> newTypeConversionError(PROP_NAME_createdAt));
               }
               setCreatedAt(typedValue);
               break;
            }
        
            case PROP_ID_updatedBy:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_updatedBy));
               }
               setUpdatedBy(typedValue);
               break;
            }
        
            case PROP_ID_updatedAt:{
               java.sql.Timestamp typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toTimestamp(value,
                       err-> newTypeConversionError(PROP_NAME_updatedAt));
               }
               setUpdatedAt(typedValue);
               break;
            }
        
            case PROP_ID_ver:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_ver));
               }
               setVer(typedValue);
               break;
            }
        
            case PROP_ID_serviceId:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_serviceId));
               }
               setServiceId(typedValue);
               break;
            }
        
            case PROP_ID_title:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_title));
               }
               setTitle(typedValue);
               break;
            }
        
            case PROP_ID_className:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_className));
               }
               setClassName(typedValue);
               break;
            }
        
            case PROP_ID_tableName:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_tableName));
               }
               setTableName(typedValue);
               break;
            }
        
            case PROP_ID_columns:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_columns));
               }
               setColumns(typedValue);
               break;
            }
        
            case PROP_ID_uniqueKeys:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_uniqueKeys));
               }
               setUniqueKeys(typedValue);
               break;
            }
        
            case PROP_ID_relations:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_relations));
               }
               setRelations(typedValue);
               break;
            }
        
           default:
              super.orm_propValue(propId,value);
        }
    }

    @Override
    public void orm_internalSet(int propId, Object value) {
        switch(propId){
        
            case PROP_ID_id:{
               onInitProp(propId);
               this._id = (java.lang.String)value;
               orm_id(); // 如果是设置主键字段，则触发watcher
               break;
            }
        
            case PROP_ID_createdBy:{
               onInitProp(propId);
               this._createdBy = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_createdAt:{
               onInitProp(propId);
               this._createdAt = (java.sql.Timestamp)value;
               
               break;
            }
        
            case PROP_ID_updatedBy:{
               onInitProp(propId);
               this._updatedBy = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_updatedAt:{
               onInitProp(propId);
               this._updatedAt = (java.sql.Timestamp)value;
               
               break;
            }
        
            case PROP_ID_ver:{
               onInitProp(propId);
               this._ver = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_serviceId:{
               onInitProp(propId);
               this._serviceId = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_title:{
               onInitProp(propId);
               this._title = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_className:{
               onInitProp(propId);
               this._className = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_tableName:{
               onInitProp(propId);
               this._tableName = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_columns:{
               onInitProp(propId);
               this._columns = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_uniqueKeys:{
               onInitProp(propId);
               this._uniqueKeys = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_relations:{
               onInitProp(propId);
               this._relations = (java.lang.String)value;
               
               break;
            }
        
           default:
              super.orm_internalSet(propId,value);
        }
    }

    
    /**
     * ID: ID
     */
    public java.lang.String getId(){
         onPropGet(PROP_ID_id);
         return _id;
    }

    /**
     * ID: ID
     */
    public void setId(java.lang.String value){
        if(onPropSet(PROP_ID_id,value)){
            this._id = value;
            internalClearRefs(PROP_ID_id);
            orm_id();
        }
    }
    
    /**
     * 创建人: CREATED_BY
     */
    public java.lang.String getCreatedBy(){
         onPropGet(PROP_ID_createdBy);
         return _createdBy;
    }

    /**
     * 创建人: CREATED_BY
     */
    public void setCreatedBy(java.lang.String value){
        if(onPropSet(PROP_ID_createdBy,value)){
            this._createdBy = value;
            internalClearRefs(PROP_ID_createdBy);
            
        }
    }
    
    /**
     * 创建时间: CREATED_AT
     */
    public java.sql.Timestamp getCreatedAt(){
         onPropGet(PROP_ID_createdAt);
         return _createdAt;
    }

    /**
     * 创建时间: CREATED_AT
     */
    public void setCreatedAt(java.sql.Timestamp value){
        if(onPropSet(PROP_ID_createdAt,value)){
            this._createdAt = value;
            internalClearRefs(PROP_ID_createdAt);
            
        }
    }
    
    /**
     * 更新人: UPDATED_BY
     */
    public java.lang.String getUpdatedBy(){
         onPropGet(PROP_ID_updatedBy);
         return _updatedBy;
    }

    /**
     * 更新人: UPDATED_BY
     */
    public void setUpdatedBy(java.lang.String value){
        if(onPropSet(PROP_ID_updatedBy,value)){
            this._updatedBy = value;
            internalClearRefs(PROP_ID_updatedBy);
            
        }
    }
    
    /**
     * 更新时间: UPDATED_AT
     */
    public java.sql.Timestamp getUpdatedAt(){
         onPropGet(PROP_ID_updatedAt);
         return _updatedAt;
    }

    /**
     * 更新时间: UPDATED_AT
     */
    public void setUpdatedAt(java.sql.Timestamp value){
        if(onPropSet(PROP_ID_updatedAt,value)){
            this._updatedAt = value;
            internalClearRefs(PROP_ID_updatedAt);
            
        }
    }
    
    /**
     * 数据版本: VER
     */
    public java.lang.Integer getVer(){
         onPropGet(PROP_ID_ver);
         return _ver;
    }

    /**
     * 数据版本: VER
     */
    public void setVer(java.lang.Integer value){
        if(onPropSet(PROP_ID_ver,value)){
            this._ver = value;
            internalClearRefs(PROP_ID_ver);
            
        }
    }
    
    /**
     * 所属服务: SERVICE_ID
     */
    public java.lang.String getServiceId(){
         onPropGet(PROP_ID_serviceId);
         return _serviceId;
    }

    /**
     * 所属服务: SERVICE_ID
     */
    public void setServiceId(java.lang.String value){
        if(onPropSet(PROP_ID_serviceId,value)){
            this._serviceId = value;
            internalClearRefs(PROP_ID_serviceId);
            
        }
    }
    
    /**
     * 名称: TITLE
     */
    public java.lang.String getTitle(){
         onPropGet(PROP_ID_title);
         return _title;
    }

    /**
     * 名称: TITLE
     */
    public void setTitle(java.lang.String value){
        if(onPropSet(PROP_ID_title,value)){
            this._title = value;
            internalClearRefs(PROP_ID_title);
            
        }
    }
    
    /**
     * 类名: CLASS_NAME
     */
    public java.lang.String getClassName(){
         onPropGet(PROP_ID_className);
         return _className;
    }

    /**
     * 类名: CLASS_NAME
     */
    public void setClassName(java.lang.String value){
        if(onPropSet(PROP_ID_className,value)){
            this._className = value;
            internalClearRefs(PROP_ID_className);
            
        }
    }
    
    /**
     * 数据库表名: TABLE_NAME
     */
    public java.lang.String getTableName(){
         onPropGet(PROP_ID_tableName);
         return _tableName;
    }

    /**
     * 数据库表名: TABLE_NAME
     */
    public void setTableName(java.lang.String value){
        if(onPropSet(PROP_ID_tableName,value)){
            this._tableName = value;
            internalClearRefs(PROP_ID_tableName);
            
        }
    }
    
    /**
     * 字段列表: COLUMNS
     */
    public java.lang.String getColumns(){
         onPropGet(PROP_ID_columns);
         return _columns;
    }

    /**
     * 字段列表: COLUMNS
     */
    public void setColumns(java.lang.String value){
        if(onPropSet(PROP_ID_columns,value)){
            this._columns = value;
            internalClearRefs(PROP_ID_columns);
            
        }
    }
    
    /**
     * 唯一键列表: UNIQUE_KEYS
     */
    public java.lang.String getUniqueKeys(){
         onPropGet(PROP_ID_uniqueKeys);
         return _uniqueKeys;
    }

    /**
     * 唯一键列表: UNIQUE_KEYS
     */
    public void setUniqueKeys(java.lang.String value){
        if(onPropSet(PROP_ID_uniqueKeys,value)){
            this._uniqueKeys = value;
            internalClearRefs(PROP_ID_uniqueKeys);
            
        }
    }
    
    /**
     * 关联列表: RELATIONS
     */
    public java.lang.String getRelations(){
         onPropGet(PROP_ID_relations);
         return _relations;
    }

    /**
     * 关联列表: RELATIONS
     */
    public void setRelations(java.lang.String value){
        if(onPropSet(PROP_ID_relations,value)){
            this._relations = value;
            internalClearRefs(PROP_ID_relations);
            
        }
    }
    
    /**
     * 
     */
    public io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppService getService(){
       return (io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppService)internalGetRefEntity(PROP_NAME_service);
    }

    public void setService(io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppService refEntity){
   
           if(refEntity == null){
           
                   this.setServiceId(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_service, refEntity,()->{
           
                           this.setServiceId(refEntity.getId());
                       
           });
           }
       
    }
       
}
// resume CPD analysis - CPD-ON
