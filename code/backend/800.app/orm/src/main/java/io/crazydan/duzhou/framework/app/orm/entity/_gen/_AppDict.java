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

import io.crazydan.duzhou.framework.app.orm.entity.AppDict;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  字典定义: duzhou_app_dict
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _AppDict extends DynamicOrmEntity{
    
    /* ID: EID VARCHAR */
    public static final String PROP_NAME_eid = "eid";
    public static final int PROP_ID_eid = 1;
    
    /* 所属应用: APP_ID VARCHAR */
    public static final String PROP_NAME_appId = "appId";
    public static final int PROP_ID_appId = 2;
    
    /* 名称: TITLE VARCHAR */
    public static final String PROP_NAME_title = "title";
    public static final int PROP_ID_title = 3;
    
    /* 编码: CODE VARCHAR */
    public static final String PROP_NAME_code = "code";
    public static final int PROP_ID_code = 4;
    
    /* 字典项类型: OPTION_TYPE VARCHAR */
    public static final String PROP_NAME_optionType = "optionType";
    public static final int PROP_ID_optionType = 5;
    
    /* 字典项列表: OPTIONS VARCHAR */
    public static final String PROP_NAME_options = "options";
    public static final int PROP_ID_options = 6;
    

    private static int _PROP_ID_BOUND = 7;

    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_eid);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_eid};

    private static final String[] PROP_ID_TO_NAME = new String[7];
    private static final Map<String,Integer> PROP_NAME_TO_ID = new HashMap<>();
    static{
      
          PROP_ID_TO_NAME[PROP_ID_eid] = PROP_NAME_eid;
          PROP_NAME_TO_ID.put(PROP_NAME_eid, PROP_ID_eid);
      
          PROP_ID_TO_NAME[PROP_ID_appId] = PROP_NAME_appId;
          PROP_NAME_TO_ID.put(PROP_NAME_appId, PROP_ID_appId);
      
          PROP_ID_TO_NAME[PROP_ID_title] = PROP_NAME_title;
          PROP_NAME_TO_ID.put(PROP_NAME_title, PROP_ID_title);
      
          PROP_ID_TO_NAME[PROP_ID_code] = PROP_NAME_code;
          PROP_NAME_TO_ID.put(PROP_NAME_code, PROP_ID_code);
      
          PROP_ID_TO_NAME[PROP_ID_optionType] = PROP_NAME_optionType;
          PROP_NAME_TO_ID.put(PROP_NAME_optionType, PROP_ID_optionType);
      
          PROP_ID_TO_NAME[PROP_ID_options] = PROP_NAME_options;
          PROP_NAME_TO_ID.put(PROP_NAME_options, PROP_ID_options);
      
    }

    
    /* ID: EID */
    private java.lang.String _eid;
    
    /* 所属应用: APP_ID */
    private java.lang.String _appId;
    
    /* 名称: TITLE */
    private java.lang.String _title;
    
    /* 编码: CODE */
    private java.lang.String _code;
    
    /* 字典项类型: OPTION_TYPE */
    private java.lang.String _optionType;
    
    /* 字典项列表: OPTIONS */
    private java.lang.String _options;
    

    public _AppDict(){
        // for debug
    }

    protected AppDict newInstance(){
       return new AppDict();
    }

    @Override
    public AppDict cloneInstance() {
        AppDict entity = newInstance();
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
      return "io.crazydan.duzhou.framework.app.orm.entity.AppDict";
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
    
        return buildSimpleId(PROP_ID_eid);
     
    }

    @Override
    public boolean orm_isPrimary(int propId) {
        
            return propId == PROP_ID_eid;
          
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
        
            case PROP_ID_eid:
               return getEid();
        
            case PROP_ID_appId:
               return getAppId();
        
            case PROP_ID_title:
               return getTitle();
        
            case PROP_ID_code:
               return getCode();
        
            case PROP_ID_optionType:
               return getOptionType();
        
            case PROP_ID_options:
               return getOptions();
        
           default:
              return super.orm_propValue(propId);
        }
    }

    

    @Override
    public void orm_propValue(int propId, Object value){
        switch(propId){
        
            case PROP_ID_eid:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_eid));
               }
               setEid(typedValue);
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
        
            case PROP_ID_optionType:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_optionType));
               }
               setOptionType(typedValue);
               break;
            }
        
            case PROP_ID_options:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_options));
               }
               setOptions(typedValue);
               break;
            }
        
           default:
              super.orm_propValue(propId,value);
        }
    }

    @Override
    public void orm_internalSet(int propId, Object value) {
        switch(propId){
        
            case PROP_ID_eid:{
               onInitProp(propId);
               this._eid = (java.lang.String)value;
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
        
            case PROP_ID_optionType:{
               onInitProp(propId);
               this._optionType = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_options:{
               onInitProp(propId);
               this._options = (java.lang.String)value;
               
               break;
            }
        
           default:
              super.orm_internalSet(propId,value);
        }
    }

    
    /**
     * ID: EID
     */
    public java.lang.String getEid(){
         onPropGet(PROP_ID_eid);
         return _eid;
    }

    /**
     * ID: EID
     */
    public void setEid(java.lang.String value){
        if(onPropSet(PROP_ID_eid,value)){
            this._eid = value;
            internalClearRefs(PROP_ID_eid);
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
     * 字典项类型: OPTION_TYPE
     */
    public java.lang.String getOptionType(){
         onPropGet(PROP_ID_optionType);
         return _optionType;
    }

    /**
     * 字典项类型: OPTION_TYPE
     */
    public void setOptionType(java.lang.String value){
        if(onPropSet(PROP_ID_optionType,value)){
            this._optionType = value;
            internalClearRefs(PROP_ID_optionType);
            
        }
    }
    
    /**
     * 字典项列表: OPTIONS
     */
    public java.lang.String getOptions(){
         onPropGet(PROP_ID_options);
         return _options;
    }

    /**
     * 字典项列表: OPTIONS
     */
    public void setOptions(java.lang.String value){
        if(onPropSet(PROP_ID_options,value)){
            this._options = value;
            internalClearRefs(PROP_ID_options);
            
        }
    }
    
}
// resume CPD analysis - CPD-ON
