package io.crazydan.duzhou.framework.app.orm.entity._gen;

import io.nop.orm.model.IEntityModel;
import io.nop.orm.support.DynamicOrmEntity;
import io.nop.orm.support.OrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.orm.IOrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.api.core.convert.ConvertHelper;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

import io.crazydan.duzhou.framework.app.orm.entity.AppDomain;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  域定义: duzhou_app_domain
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _AppDomain extends DynamicOrmEntity{
    
    /* ID: ID VARCHAR */
    public static final String PROP_NAME_id = "id";
    public static final int PROP_ID_id = 1;
    
    /* 所属应用: APP_ID VARCHAR */
    public static final String PROP_NAME_appId = "appId";
    public static final int PROP_ID_appId = 2;
    
    /* 名称: TITLE VARCHAR */
    public static final String PROP_NAME_title = "title";
    public static final int PROP_ID_title = 3;
    
    /* 编码: CODE VARCHAR */
    public static final String PROP_NAME_code = "code";
    public static final int PROP_ID_code = 4;
    
    /* 标准域: STD_DOMAIN VARCHAR */
    public static final String PROP_NAME_stdDomain = "stdDomain";
    public static final int PROP_ID_stdDomain = 5;
    
    /* 标准 SQL 数据类型: STD_SQL_TYPE VARCHAR */
    public static final String PROP_NAME_stdSqlType = "stdSqlType";
    public static final int PROP_ID_stdSqlType = 6;
    
    /* 数据长度: PRECISION INTEGER */
    public static final String PROP_NAME_precision = "precision";
    public static final int PROP_ID_precision = 7;
    
    /* 小数位数: SCALE INTEGER */
    public static final String PROP_NAME_scale = "scale";
    public static final int PROP_ID_scale = 8;
    

    private static int _PROP_ID_BOUND = 9;

    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_id);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_id};

    private static final String[] PROP_ID_TO_NAME = new String[9];
    private static final Map<String,Integer> PROP_NAME_TO_ID = new HashMap<>();
    static{
      
          PROP_ID_TO_NAME[PROP_ID_id] = PROP_NAME_id;
          PROP_NAME_TO_ID.put(PROP_NAME_id, PROP_ID_id);
      
          PROP_ID_TO_NAME[PROP_ID_appId] = PROP_NAME_appId;
          PROP_NAME_TO_ID.put(PROP_NAME_appId, PROP_ID_appId);
      
          PROP_ID_TO_NAME[PROP_ID_title] = PROP_NAME_title;
          PROP_NAME_TO_ID.put(PROP_NAME_title, PROP_ID_title);
      
          PROP_ID_TO_NAME[PROP_ID_code] = PROP_NAME_code;
          PROP_NAME_TO_ID.put(PROP_NAME_code, PROP_ID_code);
      
          PROP_ID_TO_NAME[PROP_ID_stdDomain] = PROP_NAME_stdDomain;
          PROP_NAME_TO_ID.put(PROP_NAME_stdDomain, PROP_ID_stdDomain);
      
          PROP_ID_TO_NAME[PROP_ID_stdSqlType] = PROP_NAME_stdSqlType;
          PROP_NAME_TO_ID.put(PROP_NAME_stdSqlType, PROP_ID_stdSqlType);
      
          PROP_ID_TO_NAME[PROP_ID_precision] = PROP_NAME_precision;
          PROP_NAME_TO_ID.put(PROP_NAME_precision, PROP_ID_precision);
      
          PROP_ID_TO_NAME[PROP_ID_scale] = PROP_NAME_scale;
          PROP_NAME_TO_ID.put(PROP_NAME_scale, PROP_ID_scale);
      
    }

    
    /* ID: ID */
    private java.lang.String _id;
    
    /* 所属应用: APP_ID */
    private java.lang.String _appId;
    
    /* 名称: TITLE */
    private java.lang.String _title;
    
    /* 编码: CODE */
    private java.lang.String _code;
    
    /* 标准域: STD_DOMAIN */
    private java.lang.String _stdDomain;
    
    /* 标准 SQL 数据类型: STD_SQL_TYPE */
    private java.lang.String _stdSqlType;
    
    /* 数据长度: PRECISION */
    private java.lang.Integer _precision;
    
    /* 小数位数: SCALE */
    private java.lang.Integer _scale;
    

    public _AppDomain(){
        // for debug
    }

    protected AppDomain newInstance(){
       return new AppDomain();
    }

    @Override
    public AppDomain cloneInstance() {
        AppDomain entity = newInstance();
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
      return "io.crazydan.duzhou.framework.app.orm.entity.AppDomain";
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
        
            case PROP_ID_appId:
               return getAppId();
        
            case PROP_ID_title:
               return getTitle();
        
            case PROP_ID_code:
               return getCode();
        
            case PROP_ID_stdDomain:
               return getStdDomain();
        
            case PROP_ID_stdSqlType:
               return getStdSqlType();
        
            case PROP_ID_precision:
               return getPrecision();
        
            case PROP_ID_scale:
               return getScale();
        
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
        
            case PROP_ID_stdDomain:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_stdDomain));
               }
               setStdDomain(typedValue);
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
        
            case PROP_ID_stdDomain:{
               onInitProp(propId);
               this._stdDomain = (java.lang.String)value;
               
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
     * 标准域: STD_DOMAIN
     */
    public java.lang.String getStdDomain(){
         onPropGet(PROP_ID_stdDomain);
         return _stdDomain;
    }

    /**
     * 标准域: STD_DOMAIN
     */
    public void setStdDomain(java.lang.String value){
        if(onPropSet(PROP_ID_stdDomain,value)){
            this._stdDomain = value;
            internalClearRefs(PROP_ID_stdDomain);
            
        }
    }
    
    /**
     * 标准 SQL 数据类型: STD_SQL_TYPE
     */
    public java.lang.String getStdSqlType(){
         onPropGet(PROP_ID_stdSqlType);
         return _stdSqlType;
    }

    /**
     * 标准 SQL 数据类型: STD_SQL_TYPE
     */
    public void setStdSqlType(java.lang.String value){
        if(onPropSet(PROP_ID_stdSqlType,value)){
            this._stdSqlType = value;
            internalClearRefs(PROP_ID_stdSqlType);
            
        }
    }
    
    /**
     * 数据长度: PRECISION
     */
    public java.lang.Integer getPrecision(){
         onPropGet(PROP_ID_precision);
         return _precision;
    }

    /**
     * 数据长度: PRECISION
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
    
}
// resume CPD analysis - CPD-ON
