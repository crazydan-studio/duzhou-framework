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

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityProp;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  应用 ORM 实体属性: duzhou_dev_app_entity_prop
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _DevAppEntityProp extends DynamicOrmEntity{
    
    /* ID: ID VARCHAR */
    public static final String PROP_NAME_id = "id";
    public static final int PROP_ID_id = 1;
    
    /* 所属实体: ENTITY_ID VARCHAR */
    public static final String PROP_NAME_entityId = "entityId";
    public static final int PROP_ID_entityId = 2;
    
    /* 名称: TITLE VARCHAR */
    public static final String PROP_NAME_title = "title";
    public static final int PROP_ID_title = 3;
    
    /* 属性名: NAME VARCHAR */
    public static final String PROP_NAME_name = "name";
    public static final int PROP_ID_name = 4;
    
    /* 序号: INDEX INTEGER */
    public static final String PROP_NAME_index = "index";
    public static final int PROP_ID_index = 5;
    
    /* 标签: TAG_SET VARCHAR */
    public static final String PROP_NAME_tagSet = "tagSet";
    public static final int PROP_ID_tagSet = 6;
    
    /* 是否为主键: IS_PRIMARY BOOLEAN */
    public static final String PROP_NAME_isPrimary = "isPrimary";
    public static final int PROP_ID_isPrimary = 7;
    
    /* 是否非空: IS_MANDATORY BOOLEAN */
    public static final String PROP_NAME_isMandatory = "isMandatory";
    public static final int PROP_ID_isMandatory = 8;
    
    /* 数据域: DOMAIN_ID VARCHAR */
    public static final String PROP_NAME_domainId = "domainId";
    public static final int PROP_ID_domainId = 9;
    
    /* 引用字典: DICT_ID VARCHAR */
    public static final String PROP_NAME_dictId = "dictId";
    public static final int PROP_ID_dictId = 10;
    
    /* 值类型: VALUE_TYPE VARCHAR */
    public static final String PROP_NAME_valueType = "valueType";
    public static final int PROP_ID_valueType = 11;
    
    /* 值长度: VALUE_PRECISION INTEGER */
    public static final String PROP_NAME_valuePrecision = "valuePrecision";
    public static final int PROP_ID_valuePrecision = 12;
    
    /* 值精度: VALUE_SCALE INTEGER */
    public static final String PROP_NAME_valueScale = "valueScale";
    public static final int PROP_ID_valueScale = 13;
    
    /* 默认值: VALUE_DEFAULT VARCHAR */
    public static final String PROP_NAME_valueDefault = "valueDefault";
    public static final int PROP_ID_valueDefault = 14;
    

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
      
          PROP_ID_TO_NAME[PROP_ID_title] = PROP_NAME_title;
          PROP_NAME_TO_ID.put(PROP_NAME_title, PROP_ID_title);
      
          PROP_ID_TO_NAME[PROP_ID_name] = PROP_NAME_name;
          PROP_NAME_TO_ID.put(PROP_NAME_name, PROP_ID_name);
      
          PROP_ID_TO_NAME[PROP_ID_index] = PROP_NAME_index;
          PROP_NAME_TO_ID.put(PROP_NAME_index, PROP_ID_index);
      
          PROP_ID_TO_NAME[PROP_ID_tagSet] = PROP_NAME_tagSet;
          PROP_NAME_TO_ID.put(PROP_NAME_tagSet, PROP_ID_tagSet);
      
          PROP_ID_TO_NAME[PROP_ID_isPrimary] = PROP_NAME_isPrimary;
          PROP_NAME_TO_ID.put(PROP_NAME_isPrimary, PROP_ID_isPrimary);
      
          PROP_ID_TO_NAME[PROP_ID_isMandatory] = PROP_NAME_isMandatory;
          PROP_NAME_TO_ID.put(PROP_NAME_isMandatory, PROP_ID_isMandatory);
      
          PROP_ID_TO_NAME[PROP_ID_domainId] = PROP_NAME_domainId;
          PROP_NAME_TO_ID.put(PROP_NAME_domainId, PROP_ID_domainId);
      
          PROP_ID_TO_NAME[PROP_ID_dictId] = PROP_NAME_dictId;
          PROP_NAME_TO_ID.put(PROP_NAME_dictId, PROP_ID_dictId);
      
          PROP_ID_TO_NAME[PROP_ID_valueType] = PROP_NAME_valueType;
          PROP_NAME_TO_ID.put(PROP_NAME_valueType, PROP_ID_valueType);
      
          PROP_ID_TO_NAME[PROP_ID_valuePrecision] = PROP_NAME_valuePrecision;
          PROP_NAME_TO_ID.put(PROP_NAME_valuePrecision, PROP_ID_valuePrecision);
      
          PROP_ID_TO_NAME[PROP_ID_valueScale] = PROP_NAME_valueScale;
          PROP_NAME_TO_ID.put(PROP_NAME_valueScale, PROP_ID_valueScale);
      
          PROP_ID_TO_NAME[PROP_ID_valueDefault] = PROP_NAME_valueDefault;
          PROP_NAME_TO_ID.put(PROP_NAME_valueDefault, PROP_ID_valueDefault);
      
    }

    
    /* ID: ID */
    private java.lang.String _id;
    
    /* 所属实体: ENTITY_ID */
    private java.lang.String _entityId;
    
    /* 名称: TITLE */
    private java.lang.String _title;
    
    /* 属性名: NAME */
    private java.lang.String _name;
    
    /* 序号: INDEX */
    private java.lang.Integer _index;
    
    /* 标签: TAG_SET */
    private java.lang.String _tagSet;
    
    /* 是否为主键: IS_PRIMARY */
    private java.lang.Boolean _isPrimary;
    
    /* 是否非空: IS_MANDATORY */
    private java.lang.Boolean _isMandatory;
    
    /* 数据域: DOMAIN_ID */
    private java.lang.String _domainId;
    
    /* 引用字典: DICT_ID */
    private java.lang.String _dictId;
    
    /* 值类型: VALUE_TYPE */
    private java.lang.String _valueType;
    
    /* 值长度: VALUE_PRECISION */
    private java.lang.Integer _valuePrecision;
    
    /* 值精度: VALUE_SCALE */
    private java.lang.Integer _valueScale;
    
    /* 默认值: VALUE_DEFAULT */
    private java.lang.String _valueDefault;
    

    public _DevAppEntityProp(){
        // for debug
    }

    protected DevAppEntityProp newInstance(){
       return new DevAppEntityProp();
    }

    @Override
    public DevAppEntityProp cloneInstance() {
        DevAppEntityProp entity = newInstance();
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
      return "io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityProp";
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
        
            case PROP_ID_title:
               return getTitle();
        
            case PROP_ID_name:
               return getName();
        
            case PROP_ID_index:
               return getIndex();
        
            case PROP_ID_tagSet:
               return getTagSet();
        
            case PROP_ID_isPrimary:
               return getIsPrimary();
        
            case PROP_ID_isMandatory:
               return getIsMandatory();
        
            case PROP_ID_domainId:
               return getDomainId();
        
            case PROP_ID_dictId:
               return getDictId();
        
            case PROP_ID_valueType:
               return getValueType();
        
            case PROP_ID_valuePrecision:
               return getValuePrecision();
        
            case PROP_ID_valueScale:
               return getValueScale();
        
            case PROP_ID_valueDefault:
               return getValueDefault();
        
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
        
            case PROP_ID_title:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_title));
               }
               setTitle(typedValue);
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
        
            case PROP_ID_index:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_index));
               }
               setIndex(typedValue);
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
        
            case PROP_ID_isPrimary:{
               java.lang.Boolean typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toBoolean(value,
                       err-> newTypeConversionError(PROP_NAME_isPrimary));
               }
               setIsPrimary(typedValue);
               break;
            }
        
            case PROP_ID_isMandatory:{
               java.lang.Boolean typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toBoolean(value,
                       err-> newTypeConversionError(PROP_NAME_isMandatory));
               }
               setIsMandatory(typedValue);
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
        
            case PROP_ID_valueType:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_valueType));
               }
               setValueType(typedValue);
               break;
            }
        
            case PROP_ID_valuePrecision:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_valuePrecision));
               }
               setValuePrecision(typedValue);
               break;
            }
        
            case PROP_ID_valueScale:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_valueScale));
               }
               setValueScale(typedValue);
               break;
            }
        
            case PROP_ID_valueDefault:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_valueDefault));
               }
               setValueDefault(typedValue);
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
        
            case PROP_ID_title:{
               onInitProp(propId);
               this._title = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_name:{
               onInitProp(propId);
               this._name = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_index:{
               onInitProp(propId);
               this._index = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_tagSet:{
               onInitProp(propId);
               this._tagSet = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_isPrimary:{
               onInitProp(propId);
               this._isPrimary = (java.lang.Boolean)value;
               
               break;
            }
        
            case PROP_ID_isMandatory:{
               onInitProp(propId);
               this._isMandatory = (java.lang.Boolean)value;
               
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
        
            case PROP_ID_valueType:{
               onInitProp(propId);
               this._valueType = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_valuePrecision:{
               onInitProp(propId);
               this._valuePrecision = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_valueScale:{
               onInitProp(propId);
               this._valueScale = (java.lang.Integer)value;
               
               break;
            }
        
            case PROP_ID_valueDefault:{
               onInitProp(propId);
               this._valueDefault = (java.lang.String)value;
               
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
     * 属性名: NAME
     */
    public java.lang.String getName(){
         onPropGet(PROP_ID_name);
         return _name;
    }

    /**
     * 属性名: NAME
     */
    public void setName(java.lang.String value){
        if(onPropSet(PROP_ID_name,value)){
            this._name = value;
            internalClearRefs(PROP_ID_name);
            
        }
    }
    
    /**
     * 序号: INDEX
     */
    public java.lang.Integer getIndex(){
         onPropGet(PROP_ID_index);
         return _index;
    }

    /**
     * 序号: INDEX
     */
    public void setIndex(java.lang.Integer value){
        if(onPropSet(PROP_ID_index,value)){
            this._index = value;
            internalClearRefs(PROP_ID_index);
            
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
     * 是否为主键: IS_PRIMARY
     */
    public java.lang.Boolean getIsPrimary(){
         onPropGet(PROP_ID_isPrimary);
         return _isPrimary;
    }

    /**
     * 是否为主键: IS_PRIMARY
     */
    public void setIsPrimary(java.lang.Boolean value){
        if(onPropSet(PROP_ID_isPrimary,value)){
            this._isPrimary = value;
            internalClearRefs(PROP_ID_isPrimary);
            
        }
    }
    
    /**
     * 是否非空: IS_MANDATORY
     */
    public java.lang.Boolean getIsMandatory(){
         onPropGet(PROP_ID_isMandatory);
         return _isMandatory;
    }

    /**
     * 是否非空: IS_MANDATORY
     */
    public void setIsMandatory(java.lang.Boolean value){
        if(onPropSet(PROP_ID_isMandatory,value)){
            this._isMandatory = value;
            internalClearRefs(PROP_ID_isMandatory);
            
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
     * 值类型: VALUE_TYPE
     */
    public java.lang.String getValueType(){
         onPropGet(PROP_ID_valueType);
         return _valueType;
    }

    /**
     * 值类型: VALUE_TYPE
     */
    public void setValueType(java.lang.String value){
        if(onPropSet(PROP_ID_valueType,value)){
            this._valueType = value;
            internalClearRefs(PROP_ID_valueType);
            
        }
    }
    
    /**
     * 值长度: VALUE_PRECISION
     */
    public java.lang.Integer getValuePrecision(){
         onPropGet(PROP_ID_valuePrecision);
         return _valuePrecision;
    }

    /**
     * 值长度: VALUE_PRECISION
     */
    public void setValuePrecision(java.lang.Integer value){
        if(onPropSet(PROP_ID_valuePrecision,value)){
            this._valuePrecision = value;
            internalClearRefs(PROP_ID_valuePrecision);
            
        }
    }
    
    /**
     * 值精度: VALUE_SCALE
     */
    public java.lang.Integer getValueScale(){
         onPropGet(PROP_ID_valueScale);
         return _valueScale;
    }

    /**
     * 值精度: VALUE_SCALE
     */
    public void setValueScale(java.lang.Integer value){
        if(onPropSet(PROP_ID_valueScale,value)){
            this._valueScale = value;
            internalClearRefs(PROP_ID_valueScale);
            
        }
    }
    
    /**
     * 默认值: VALUE_DEFAULT
     */
    public java.lang.String getValueDefault(){
         onPropGet(PROP_ID_valueDefault);
         return _valueDefault;
    }

    /**
     * 默认值: VALUE_DEFAULT
     */
    public void setValueDefault(java.lang.String value){
        if(onPropSet(PROP_ID_valueDefault,value)){
            this._valueDefault = value;
            internalClearRefs(PROP_ID_valueDefault);
            
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
