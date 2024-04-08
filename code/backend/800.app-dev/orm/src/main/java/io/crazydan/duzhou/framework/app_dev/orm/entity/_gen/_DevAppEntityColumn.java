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

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityColumn;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  应用 ORM 实体字段: duzhou_dev_app_entity_column
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _DevAppEntityColumn extends DynamicOrmEntity{
    
    /* ID: ID VARCHAR */
    public static final String PROP_NAME_id = "id";
    public static final int PROP_ID_id = 1;
    
    /* 所属实体: ENTITY_ID VARCHAR */
    public static final String PROP_NAME_entityId = "entityId";
    public static final int PROP_ID_entityId = 2;
    
    /* 显示名称: DISPLAY_NAME VARCHAR */
    public static final String PROP_NAME_displayName = "displayName";
    public static final int PROP_ID_displayName = 3;
    
    /* 字段名: NAME VARCHAR */
    public static final String PROP_NAME_name = "name";
    public static final int PROP_ID_name = 4;
    
    /* 编号: PROP_ID INTEGER */
    public static final String PROP_NAME_propId = "propId";
    public static final int PROP_ID_propId = 5;
    
    /* 标签: TAG_SET VARCHAR */
    public static final String PROP_NAME_tagSet = "tagSet";
    public static final int PROP_ID_tagSet = 6;
    
    /* 是否主键: PRIMARY BOOLEAN */
    public static final String PROP_NAME_primary = "primary";
    public static final int PROP_ID_primary = 7;
    
    /* 是否非空: MANDATORY BOOLEAN */
    public static final String PROP_NAME_mandatory = "mandatory";
    public static final int PROP_ID_mandatory = 8;
    
    /* 数据域: DOMAIN_ID VARCHAR */
    public static final String PROP_NAME_domainId = "domainId";
    public static final int PROP_ID_domainId = 9;
    
    /* 引用字典: DICT_ID VARCHAR */
    public static final String PROP_NAME_dictId = "dictId";
    public static final int PROP_ID_dictId = 10;
    
    /* 字段类型: STD_SQL_TYPE VARCHAR */
    public static final String PROP_NAME_stdSqlType = "stdSqlType";
    public static final int PROP_ID_stdSqlType = 11;
    
    /* 字段长度: PRECISION INTEGER */
    public static final String PROP_NAME_precision = "precision";
    public static final int PROP_ID_precision = 12;
    
    /* 小数位数: SCALE INTEGER */
    public static final String PROP_NAME_scale = "scale";
    public static final int PROP_ID_scale = 13;
    
    /* 默认值: DEFAULT_VALUE VARCHAR */
    public static final String PROP_NAME_defaultValue = "defaultValue";
    public static final int PROP_ID_defaultValue = 14;
    

    private static int _PROP_ID_BOUND = 15;

    
    /* relation:  */
    public static final String PROP_NAME_entity = "entity";
    
    /* relation:  */
    public static final String PROP_NAME_domain = "domain";
    
    /* relation:  */
    public static final String PROP_NAME_dict = "dict";
    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_id);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_id};

    private static final String[] PROP_ID_TO_NAME = new String[15];
    private static final Map<String,Integer> PROP_NAME_TO_ID = new HashMap<>();
    static{
      
          PROP_ID_TO_NAME[PROP_ID_id] = PROP_NAME_id;
          PROP_NAME_TO_ID.put(PROP_NAME_id, PROP_ID_id);
      
          PROP_ID_TO_NAME[PROP_ID_entityId] = PROP_NAME_entityId;
          PROP_NAME_TO_ID.put(PROP_NAME_entityId, PROP_ID_entityId);
      
          PROP_ID_TO_NAME[PROP_ID_displayName] = PROP_NAME_displayName;
          PROP_NAME_TO_ID.put(PROP_NAME_displayName, PROP_ID_displayName);
      
          PROP_ID_TO_NAME[PROP_ID_name] = PROP_NAME_name;
          PROP_NAME_TO_ID.put(PROP_NAME_name, PROP_ID_name);
      
          PROP_ID_TO_NAME[PROP_ID_propId] = PROP_NAME_propId;
          PROP_NAME_TO_ID.put(PROP_NAME_propId, PROP_ID_propId);
      
          PROP_ID_TO_NAME[PROP_ID_tagSet] = PROP_NAME_tagSet;
          PROP_NAME_TO_ID.put(PROP_NAME_tagSet, PROP_ID_tagSet);
      
          PROP_ID_TO_NAME[PROP_ID_primary] = PROP_NAME_primary;
          PROP_NAME_TO_ID.put(PROP_NAME_primary, PROP_ID_primary);
      
          PROP_ID_TO_NAME[PROP_ID_mandatory] = PROP_NAME_mandatory;
          PROP_NAME_TO_ID.put(PROP_NAME_mandatory, PROP_ID_mandatory);
      
          PROP_ID_TO_NAME[PROP_ID_domainId] = PROP_NAME_domainId;
          PROP_NAME_TO_ID.put(PROP_NAME_domainId, PROP_ID_domainId);
      
          PROP_ID_TO_NAME[PROP_ID_dictId] = PROP_NAME_dictId;
          PROP_NAME_TO_ID.put(PROP_NAME_dictId, PROP_ID_dictId);
      
          PROP_ID_TO_NAME[PROP_ID_stdSqlType] = PROP_NAME_stdSqlType;
          PROP_NAME_TO_ID.put(PROP_NAME_stdSqlType, PROP_ID_stdSqlType);
      
          PROP_ID_TO_NAME[PROP_ID_precision] = PROP_NAME_precision;
          PROP_NAME_TO_ID.put(PROP_NAME_precision, PROP_ID_precision);
      
          PROP_ID_TO_NAME[PROP_ID_scale] = PROP_NAME_scale;
          PROP_NAME_TO_ID.put(PROP_NAME_scale, PROP_ID_scale);
      
          PROP_ID_TO_NAME[PROP_ID_defaultValue] = PROP_NAME_defaultValue;
          PROP_NAME_TO_ID.put(PROP_NAME_defaultValue, PROP_ID_defaultValue);
      
    }

    
    /* ID: ID */
    private java.lang.String _id;
    
    /* 所属实体: ENTITY_ID */
    private java.lang.String _entityId;
    
    /* 显示名称: DISPLAY_NAME */
    private java.lang.String _displayName;
    
    /* 字段名: NAME */
    private java.lang.String _name;
    
    /* 编号: PROP_ID */
    private java.lang.Integer _propId;
    
    /* 标签: TAG_SET */
    private java.lang.String _tagSet;
    
    /* 是否主键: PRIMARY */
    private java.lang.Boolean _primary;
    
    /* 是否非空: MANDATORY */
    private java.lang.Boolean _mandatory;
    
    /* 数据域: DOMAIN_ID */
    private java.lang.String _domainId;
    
    /* 引用字典: DICT_ID */
    private java.lang.String _dictId;
    
    /* 字段类型: STD_SQL_TYPE */
    private java.lang.String _stdSqlType;
    
    /* 字段长度: PRECISION */
    private java.lang.Integer _precision;
    
    /* 小数位数: SCALE */
    private java.lang.Integer _scale;
    
    /* 默认值: DEFAULT_VALUE */
    private java.lang.String _defaultValue;
    

    public _DevAppEntityColumn(){
        // for debug
    }

    protected DevAppEntityColumn newInstance(){
       return new DevAppEntityColumn();
    }

    @Override
    public DevAppEntityColumn cloneInstance() {
        DevAppEntityColumn entity = newInstance();
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
      return "io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityColumn";
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
        
            case PROP_ID_entityId:
               return getEntityId();
        
            case PROP_ID_displayName:
               return getDisplayName();
        
            case PROP_ID_name:
               return getName();
        
            case PROP_ID_propId:
               return getPropId();
        
            case PROP_ID_tagSet:
               return getTagSet();
        
            case PROP_ID_primary:
               return getPrimary();
        
            case PROP_ID_mandatory:
               return getMandatory();
        
            case PROP_ID_domainId:
               return getDomainId();
        
            case PROP_ID_dictId:
               return getDictId();
        
            case PROP_ID_stdSqlType:
               return getStdSqlType();
        
            case PROP_ID_precision:
               return getPrecision();
        
            case PROP_ID_scale:
               return getScale();
        
            case PROP_ID_defaultValue:
               return getDefaultValue();
        
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
        
            case PROP_ID_entityId:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_entityId));
               }
               setEntityId(typedValue);
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
        
            case PROP_ID_name:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_name));
               }
               setName(typedValue);
               break;
            }
        
            case PROP_ID_propId:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_propId));
               }
               setPropId(typedValue);
               break;
            }
        
            case PROP_ID_tagSet:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_tagSet));
               }
               setTagSet(typedValue);
               break;
            }
        
            case PROP_ID_primary:{
               java.lang.Boolean typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toBoolean(value,
                       err-> newTypeConversionError(PROP_NAME_primary));
               }
               setPrimary(typedValue);
               break;
            }
        
            case PROP_ID_mandatory:{
               java.lang.Boolean typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toBoolean(value,
                       err-> newTypeConversionError(PROP_NAME_mandatory));
               }
               setMandatory(typedValue);
               break;
            }
        
            case PROP_ID_domainId:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_domainId));
               }
               setDomainId(typedValue);
               break;
            }
        
            case PROP_ID_dictId:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_dictId));
               }
               setDictId(typedValue);
               break;
            }
        
            case PROP_ID_stdSqlType:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_stdSqlType));
               }
               setStdSqlType(typedValue);
               break;
            }
        
            case PROP_ID_precision:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_precision));
               }
               setPrecision(typedValue);
               break;
            }
        
            case PROP_ID_scale:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_scale));
               }
               setScale(typedValue);
               break;
            }
        
            case PROP_ID_defaultValue:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_defaultValue));
               }
               setDefaultValue(typedValue);
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
        
            case PROP_ID_entityId:{
               onInitProp(propId);
               this._entityId = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_displayName:{
               onInitProp(propId);
               this._displayName = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_name:{
               onInitProp(propId);
               this._name = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_propId:{
               onInitProp(propId);
               this._propId = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_tagSet:{
               onInitProp(propId);
               this._tagSet = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_primary:{
               onInitProp(propId);
               this._primary = (java.lang.Boolean)value;
               
               break;
            }
        
            case PROP_ID_mandatory:{
               onInitProp(propId);
               this._mandatory = (java.lang.Boolean)value;
               
               break;
            }
        
            case PROP_ID_domainId:{
               onInitProp(propId);
               this._domainId = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_dictId:{
               onInitProp(propId);
               this._dictId = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_stdSqlType:{
               onInitProp(propId);
               this._stdSqlType = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_precision:{
               onInitProp(propId);
               this._precision = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_scale:{
               onInitProp(propId);
               this._scale = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_defaultValue:{
               onInitProp(propId);
               this._defaultValue = (java.lang.String)value;
               
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
     * 所属实体: ENTITY_ID
     */
    public java.lang.String getEntityId(){
         onPropGet(PROP_ID_entityId);
         return _entityId;
    }

    /**
     * 所属实体: ENTITY_ID
     */
    public void setEntityId(java.lang.String value){
        if(onPropSet(PROP_ID_entityId,value)){
            this._entityId = value;
            internalClearRefs(PROP_ID_entityId);
            
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
     * 字段名: NAME
     */
    public java.lang.String getName(){
         onPropGet(PROP_ID_name);
         return _name;
    }

    /**
     * 字段名: NAME
     */
    public void setName(java.lang.String value){
        if(onPropSet(PROP_ID_name,value)){
            this._name = value;
            internalClearRefs(PROP_ID_name);
            
        }
    }
    
    /**
     * 编号: PROP_ID
     */
    public java.lang.Integer getPropId(){
         onPropGet(PROP_ID_propId);
         return _propId;
    }

    /**
     * 编号: PROP_ID
     */
    public void setPropId(java.lang.Integer value){
        if(onPropSet(PROP_ID_propId,value)){
            this._propId = value;
            internalClearRefs(PROP_ID_propId);
            
        }
    }
    
    /**
     * 标签: TAG_SET
     */
    public java.lang.String getTagSet(){
         onPropGet(PROP_ID_tagSet);
         return _tagSet;
    }

    /**
     * 标签: TAG_SET
     */
    public void setTagSet(java.lang.String value){
        if(onPropSet(PROP_ID_tagSet,value)){
            this._tagSet = value;
            internalClearRefs(PROP_ID_tagSet);
            
        }
    }
    
    /**
     * 是否主键: PRIMARY
     */
    public java.lang.Boolean getPrimary(){
         onPropGet(PROP_ID_primary);
         return _primary;
    }

    /**
     * 是否主键: PRIMARY
     */
    public void setPrimary(java.lang.Boolean value){
        if(onPropSet(PROP_ID_primary,value)){
            this._primary = value;
            internalClearRefs(PROP_ID_primary);
            
        }
    }
    
    /**
     * 是否非空: MANDATORY
     */
    public java.lang.Boolean getMandatory(){
         onPropGet(PROP_ID_mandatory);
         return _mandatory;
    }

    /**
     * 是否非空: MANDATORY
     */
    public void setMandatory(java.lang.Boolean value){
        if(onPropSet(PROP_ID_mandatory,value)){
            this._mandatory = value;
            internalClearRefs(PROP_ID_mandatory);
            
        }
    }
    
    /**
     * 数据域: DOMAIN_ID
     */
    public java.lang.String getDomainId(){
         onPropGet(PROP_ID_domainId);
         return _domainId;
    }

    /**
     * 数据域: DOMAIN_ID
     */
    public void setDomainId(java.lang.String value){
        if(onPropSet(PROP_ID_domainId,value)){
            this._domainId = value;
            internalClearRefs(PROP_ID_domainId);
            
        }
    }
    
    /**
     * 引用字典: DICT_ID
     */
    public java.lang.String getDictId(){
         onPropGet(PROP_ID_dictId);
         return _dictId;
    }

    /**
     * 引用字典: DICT_ID
     */
    public void setDictId(java.lang.String value){
        if(onPropSet(PROP_ID_dictId,value)){
            this._dictId = value;
            internalClearRefs(PROP_ID_dictId);
            
        }
    }
    
    /**
     * 字段类型: STD_SQL_TYPE
     */
    public java.lang.String getStdSqlType(){
         onPropGet(PROP_ID_stdSqlType);
         return _stdSqlType;
    }

    /**
     * 字段类型: STD_SQL_TYPE
     */
    public void setStdSqlType(java.lang.String value){
        if(onPropSet(PROP_ID_stdSqlType,value)){
            this._stdSqlType = value;
            internalClearRefs(PROP_ID_stdSqlType);
            
        }
    }
    
    /**
     * 字段长度: PRECISION
     */
    public java.lang.Integer getPrecision(){
         onPropGet(PROP_ID_precision);
         return _precision;
    }

    /**
     * 字段长度: PRECISION
     */
    public void setPrecision(java.lang.Integer value){
        if(onPropSet(PROP_ID_precision,value)){
            this._precision = value;
            internalClearRefs(PROP_ID_precision);
            
        }
    }
    
    /**
     * 小数位数: SCALE
     */
    public java.lang.Integer getScale(){
         onPropGet(PROP_ID_scale);
         return _scale;
    }

    /**
     * 小数位数: SCALE
     */
    public void setScale(java.lang.Integer value){
        if(onPropSet(PROP_ID_scale,value)){
            this._scale = value;
            internalClearRefs(PROP_ID_scale);
            
        }
    }
    
    /**
     * 默认值: DEFAULT_VALUE
     */
    public java.lang.String getDefaultValue(){
         onPropGet(PROP_ID_defaultValue);
         return _defaultValue;
    }

    /**
     * 默认值: DEFAULT_VALUE
     */
    public void setDefaultValue(java.lang.String value){
        if(onPropSet(PROP_ID_defaultValue,value)){
            this._defaultValue = value;
            internalClearRefs(PROP_ID_defaultValue);
            
        }
    }
    
    /**
     * 
     */
    public io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity getEntity(){
       return (io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity)internalGetRefEntity(PROP_NAME_entity);
    }

    public void setEntity(io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity refEntity){
   
           if(refEntity == null){
           
                   this.setEntityId(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_entity, refEntity,()->{
           
                           this.setEntityId(refEntity.getId());
                       
           });
           }
       
    }
       
    /**
     * 
     */
    public io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppDomain getDomain(){
       return (io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppDomain)internalGetRefEntity(PROP_NAME_domain);
    }

    public void setDomain(io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppDomain refEntity){
   
           if(refEntity == null){
           
                   this.setDomainId(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_domain, refEntity,()->{
           
                           this.setDomainId(refEntity.getId());
                       
           });
           }
       
    }
       
    /**
     * 
     */
    public io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppDict getDict(){
       return (io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppDict)internalGetRefEntity(PROP_NAME_dict);
    }

    public void setDict(io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppDict refEntity){
   
           if(refEntity == null){
           
                   this.setDictId(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_dict, refEntity,()->{
           
                           this.setDictId(refEntity.getId());
                       
           });
           }
       
    }
       
}
// resume CPD analysis - CPD-ON
