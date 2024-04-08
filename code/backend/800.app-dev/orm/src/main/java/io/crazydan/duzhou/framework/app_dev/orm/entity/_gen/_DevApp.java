package io.crazydan.duzhou.framework.app_dev.orm.entity._gen;

import io.nop.orm.model.IEntityModel;
import io.nop.orm.support.DynamicOrmEntity;
import io.nop.orm.support.OrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.orm.IOrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.api.core.convert.ConvertHelper;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevApp;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  应用: duzhou_dev_app
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _DevApp extends DynamicOrmEntity{
    
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
    
    /* 显示名称: DISPLAY_NAME VARCHAR */
    public static final String PROP_NAME_displayName = "displayName";
    public static final int PROP_ID_displayName = 7;
    
    /* 编码: CODE VARCHAR */
    public static final String PROP_NAME_code = "code";
    public static final int PROP_ID_code = 8;
    
    /* 版本号: VERSION VARCHAR */
    public static final String PROP_NAME_version = "version";
    public static final int PROP_ID_version = 9;
    
    /* 图片: LOGO VARCHAR */
    public static final String PROP_NAME_logo = "logo";
    public static final int PROP_ID_logo = 10;
    
    /* 代码包名: CLASS_PKG_NAME VARCHAR */
    public static final String PROP_NAME_classPkgName = "classPkgName";
    public static final int PROP_ID_classPkgName = 11;
    

    private static int _PROP_ID_BOUND = 12;

    
    /* relation:  */
    public static final String PROP_NAME_services = "services";
    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_id);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_id};

    private static final String[] PROP_ID_TO_NAME = new String[12];
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
      
          PROP_ID_TO_NAME[PROP_ID_displayName] = PROP_NAME_displayName;
          PROP_NAME_TO_ID.put(PROP_NAME_displayName, PROP_ID_displayName);
      
          PROP_ID_TO_NAME[PROP_ID_code] = PROP_NAME_code;
          PROP_NAME_TO_ID.put(PROP_NAME_code, PROP_ID_code);
      
          PROP_ID_TO_NAME[PROP_ID_version] = PROP_NAME_version;
          PROP_NAME_TO_ID.put(PROP_NAME_version, PROP_ID_version);
      
          PROP_ID_TO_NAME[PROP_ID_logo] = PROP_NAME_logo;
          PROP_NAME_TO_ID.put(PROP_NAME_logo, PROP_ID_logo);
      
          PROP_ID_TO_NAME[PROP_ID_classPkgName] = PROP_NAME_classPkgName;
          PROP_NAME_TO_ID.put(PROP_NAME_classPkgName, PROP_ID_classPkgName);
      
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
    
    /* 显示名称: DISPLAY_NAME */
    private java.lang.String _displayName;
    
    /* 编码: CODE */
    private java.lang.String _code;
    
    /* 版本号: VERSION */
    private java.lang.String _version;
    
    /* 图片: LOGO */
    private java.lang.String _logo;
    
    /* 代码包名: CLASS_PKG_NAME */
    private java.lang.String _classPkgName;
    

    public _DevApp(){
        // for debug
    }

    protected DevApp newInstance(){
       return new DevApp();
    }

    @Override
    public DevApp cloneInstance() {
        DevApp entity = newInstance();
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
      return "io.crazydan.duzhou.framework.app_dev.orm.entity.DevApp";
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
        
            case PROP_ID_displayName:
               return getDisplayName();
        
            case PROP_ID_code:
               return getCode();
        
            case PROP_ID_version:
               return getVersion();
        
            case PROP_ID_logo:
               return getLogo();
        
            case PROP_ID_classPkgName:
               return getClassPkgName();
        
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
        
            case PROP_ID_displayName:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_displayName));
               }
               setDisplayName(typedValue);
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
        
            case PROP_ID_version:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_version));
               }
               setVersion(typedValue);
               break;
            }
        
            case PROP_ID_logo:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_logo));
               }
               setLogo(typedValue);
               break;
            }
        
            case PROP_ID_classPkgName:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_classPkgName));
               }
               setClassPkgName(typedValue);
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
        
            case PROP_ID_displayName:{
               onInitProp(propId);
               this._displayName = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_code:{
               onInitProp(propId);
               this._code = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_version:{
               onInitProp(propId);
               this._version = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_logo:{
               onInitProp(propId);
               this._logo = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_classPkgName:{
               onInitProp(propId);
               this._classPkgName = (java.lang.String)value;
               
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
     * 显示名称: DISPLAY_NAME
     */
    public java.lang.String getDisplayName(){
         onPropGet(PROP_ID_displayName);
         return _displayName;
    }

    /**
     * 显示名称: DISPLAY_NAME
     */
    public void setDisplayName(java.lang.String value){
        if(onPropSet(PROP_ID_displayName,value)){
            this._displayName = value;
            internalClearRefs(PROP_ID_displayName);
            
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
     * 图片: LOGO
     */
    public java.lang.String getLogo(){
         onPropGet(PROP_ID_logo);
         return _logo;
    }

    /**
     * 图片: LOGO
     */
    public void setLogo(java.lang.String value){
        if(onPropSet(PROP_ID_logo,value)){
            this._logo = value;
            internalClearRefs(PROP_ID_logo);
            
        }
    }
    
    /**
     * 代码包名: CLASS_PKG_NAME
     */
    public java.lang.String getClassPkgName(){
         onPropGet(PROP_ID_classPkgName);
         return _classPkgName;
    }

    /**
     * 代码包名: CLASS_PKG_NAME
     */
    public void setClassPkgName(java.lang.String value){
        if(onPropSet(PROP_ID_classPkgName,value)){
            this._classPkgName = value;
            internalClearRefs(PROP_ID_classPkgName);
            
        }
    }
    
    private final OrmEntitySet<io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppService> _services = new OrmEntitySet<>(this, PROP_NAME_services,
        io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppService.PROP_NAME_app, null,io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppService.class);

    /**
     * 。 refPropName: app, keyProp: {rel.keyProp}
     */
    public IOrmEntitySet<io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppService> getServices(){
       return _services;
    }
       
}
// resume CPD analysis - CPD-ON
