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

import io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppService;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  应用服务: duzhou_dev_app_service
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _DevAppService extends DynamicOrmEntity{
    
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
    
    /* 所属应用: APP_ID VARCHAR */
    public static final String PROP_NAME_appId = "appId";
    public static final int PROP_ID_appId = 7;
    
    /* 名称: TITLE VARCHAR */
    public static final String PROP_NAME_title = "title";
    public static final int PROP_ID_title = 8;
    
    /* 编码: CODE VARCHAR */
    public static final String PROP_NAME_code = "code";
    public static final int PROP_ID_code = 9;
    
    /* 状态: STATUS INTEGER */
    public static final String PROP_NAME_status = "status";
    public static final int PROP_ID_status = 10;
    
    /* 版本号: VERSION VARCHAR */
    public static final String PROP_NAME_version = "version";
    public static final int PROP_ID_version = 11;
    
    /* 说明: DESCRIPTION VARCHAR */
    public static final String PROP_NAME_description = "description";
    public static final int PROP_ID_description = 12;
    

    private static int _PROP_ID_BOUND = 13;

    
    /* relation:  */
    public static final String PROP_NAME_app = "app";
    
    /* relation:  */
    public static final String PROP_NAME_entities = "entities";
    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_id);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_id};

    private static final String[] PROP_ID_TO_NAME = new String[13];
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
      
          PROP_ID_TO_NAME[PROP_ID_appId] = PROP_NAME_appId;
          PROP_NAME_TO_ID.put(PROP_NAME_appId, PROP_ID_appId);
      
          PROP_ID_TO_NAME[PROP_ID_title] = PROP_NAME_title;
          PROP_NAME_TO_ID.put(PROP_NAME_title, PROP_ID_title);
      
          PROP_ID_TO_NAME[PROP_ID_code] = PROP_NAME_code;
          PROP_NAME_TO_ID.put(PROP_NAME_code, PROP_ID_code);
      
          PROP_ID_TO_NAME[PROP_ID_status] = PROP_NAME_status;
          PROP_NAME_TO_ID.put(PROP_NAME_status, PROP_ID_status);
      
          PROP_ID_TO_NAME[PROP_ID_version] = PROP_NAME_version;
          PROP_NAME_TO_ID.put(PROP_NAME_version, PROP_ID_version);
      
          PROP_ID_TO_NAME[PROP_ID_description] = PROP_NAME_description;
          PROP_NAME_TO_ID.put(PROP_NAME_description, PROP_ID_description);
      
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
    
    /* 所属应用: APP_ID */
    private java.lang.String _appId;
    
    /* 名称: TITLE */
    private java.lang.String _title;
    
    /* 编码: CODE */
    private java.lang.String _code;
    
    /* 状态: STATUS */
    private java.lang.Integer _status;
    
    /* 版本号: VERSION */
    private java.lang.String _version;
    
    /* 说明: DESCRIPTION */
    private java.lang.String _description;
    

    public _DevAppService(){
        // for debug
    }

    protected DevAppService newInstance(){
       return new DevAppService();
    }

    @Override
    public DevAppService cloneInstance() {
        DevAppService entity = newInstance();
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
      return "io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppService";
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
        
            case PROP_ID_appId:
               return getAppId();
        
            case PROP_ID_title:
               return getTitle();
        
            case PROP_ID_code:
               return getCode();
        
            case PROP_ID_status:
               return getStatus();
        
            case PROP_ID_version:
               return getVersion();
        
            case PROP_ID_description:
               return getDescription();
        
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
        
            case PROP_ID_appId:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_appId));
               }
               setAppId(typedValue);
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
        
            case PROP_ID_code:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_code));
               }
               setCode(typedValue);
               break;
            }
        
            case PROP_ID_status:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_status));
               }
               setStatus(typedValue);
               break;
            }
        
            case PROP_ID_version:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_version));
               }
               setVersion(typedValue);
               break;
            }
        
            case PROP_ID_description:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_description));
               }
               setDescription(typedValue);
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
        
            case PROP_ID_appId:{
               onInitProp(propId);
               this._appId = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_title:{
               onInitProp(propId);
               this._title = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_code:{
               onInitProp(propId);
               this._code = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_status:{
               onInitProp(propId);
               this._status = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_version:{
               onInitProp(propId);
               this._version = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_description:{
               onInitProp(propId);
               this._description = (java.lang.String)value;
               
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
     * 所属应用: APP_ID
     */
    public java.lang.String getAppId(){
         onPropGet(PROP_ID_appId);
         return _appId;
    }

    /**
     * 所属应用: APP_ID
     */
    public void setAppId(java.lang.String value){
        if(onPropSet(PROP_ID_appId,value)){
            this._appId = value;
            internalClearRefs(PROP_ID_appId);
            
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
     * 编码: CODE
     */
    public java.lang.String getCode(){
         onPropGet(PROP_ID_code);
         return _code;
    }

    /**
     * 编码: CODE
     */
    public void setCode(java.lang.String value){
        if(onPropSet(PROP_ID_code,value)){
            this._code = value;
            internalClearRefs(PROP_ID_code);
            
        }
    }
    
    /**
     * 状态: STATUS
     */
    public java.lang.Integer getStatus(){
         onPropGet(PROP_ID_status);
         return _status;
    }

    /**
     * 状态: STATUS
     */
    public void setStatus(java.lang.Integer value){
        if(onPropSet(PROP_ID_status,value)){
            this._status = value;
            internalClearRefs(PROP_ID_status);
            
        }
    }
    
    /**
     * 版本号: VERSION
     */
    public java.lang.String getVersion(){
         onPropGet(PROP_ID_version);
         return _version;
    }

    /**
     * 版本号: VERSION
     */
    public void setVersion(java.lang.String value){
        if(onPropSet(PROP_ID_version,value)){
            this._version = value;
            internalClearRefs(PROP_ID_version);
            
        }
    }
    
    /**
     * 说明: DESCRIPTION
     */
    public java.lang.String getDescription(){
         onPropGet(PROP_ID_description);
         return _description;
    }

    /**
     * 说明: DESCRIPTION
     */
    public void setDescription(java.lang.String value){
        if(onPropSet(PROP_ID_description,value)){
            this._description = value;
            internalClearRefs(PROP_ID_description);
            
        }
    }
    
    /**
     * 
     */
    public io.crazydan.duzhou.framework.app_designer.orm.entity.DevApp getApp(){
       return (io.crazydan.duzhou.framework.app_designer.orm.entity.DevApp)internalGetRefEntity(PROP_NAME_app);
    }

    public void setApp(io.crazydan.duzhou.framework.app_designer.orm.entity.DevApp refEntity){
   
           if(refEntity == null){
           
                   this.setAppId(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_app, refEntity,()->{
           
                           this.setAppId(refEntity.getId());
                       
           });
           }
       
    }
       
    private final OrmEntitySet<io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppEntity> _entities = new OrmEntitySet<>(this, PROP_NAME_entities,
        io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppEntity.PROP_NAME_service, null,io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppEntity.class);

    /**
     * 。 refPropName: service, keyProp: {rel.keyProp}
     */
    public IOrmEntitySet<io.crazydan.duzhou.framework.app_designer.orm.entity.DevAppEntity> getEntities(){
       return _entities;
    }
       
}
// resume CPD analysis - CPD-ON
