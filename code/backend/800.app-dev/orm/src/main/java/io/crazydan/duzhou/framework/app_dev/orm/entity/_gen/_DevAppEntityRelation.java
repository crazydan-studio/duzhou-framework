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

import io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityRelation;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  应用 ORM 实体关联: duzhou_dev_app_entity_relation
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _DevAppEntityRelation extends DynamicOrmEntity{
    
    /* ID: ID VARCHAR */
    public static final String PROP_NAME_id = "id";
    public static final int PROP_ID_id = 1;
    
    /* 关联源实体: SOURCE_ID VARCHAR */
    public static final String PROP_NAME_sourceId = "sourceId";
    public static final int PROP_ID_sourceId = 2;
    
    /* 关联目标实体: TARGET_ID VARCHAR */
    public static final String PROP_NAME_targetId = "targetId";
    public static final int PROP_ID_targetId = 3;
    
    /* 关联源实体侧属性名: SOURCE_PROP_NAME VARCHAR */
    public static final String PROP_NAME_sourcePropName = "sourcePropName";
    public static final int PROP_ID_sourcePropName = 4;
    
    /* 关联目标实体侧属性名: TARGET_PROP_NAME VARCHAR */
    public static final String PROP_NAME_targetPropName = "targetPropName";
    public static final int PROP_ID_targetPropName = 5;
    
    /* 关联类型: TYPE VARCHAR */
    public static final String PROP_NAME_type = "type";
    public static final int PROP_ID_type = 6;
    
    /* 标签: TAG_SET VARCHAR */
    public static final String PROP_NAME_tagSet = "tagSet";
    public static final int PROP_ID_tagSet = 7;
    
    /* 中间表表名: TABLE_NAME VARCHAR */
    public static final String PROP_NAME_tableName = "tableName";
    public static final int PROP_ID_tableName = 8;
    
    /* 关联条件列表: JOIN_ON_CONDS VARCHAR */
    public static final String PROP_NAME_joinOnCondsJsonText = "joinOnCondsJsonText";
    public static final int PROP_ID_joinOnCondsJsonText = 9;
    

    private static int _PROP_ID_BOUND = 10;

    
    /* relation:  */
    public static final String PROP_NAME_source = "source";
    
    /* relation:  */
    public static final String PROP_NAME_target = "target";
    
    /* alias: joinOnCondsJsonTextComponent.data 关联条件列表 */
    public static final String PROP_NAME_joinOnConds = "joinOnConds";
    
    /* component:  */
    public static final String PROP_NAME_joinOnCondsJsonTextComponent = "joinOnCondsJsonTextComponent";
    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_id);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_id};

    private static final String[] PROP_ID_TO_NAME = new String[10];
    private static final Map<String,Integer> PROP_NAME_TO_ID = new HashMap<>();
    static{
      
          PROP_ID_TO_NAME[PROP_ID_id] = PROP_NAME_id;
          PROP_NAME_TO_ID.put(PROP_NAME_id, PROP_ID_id);
      
          PROP_ID_TO_NAME[PROP_ID_sourceId] = PROP_NAME_sourceId;
          PROP_NAME_TO_ID.put(PROP_NAME_sourceId, PROP_ID_sourceId);
      
          PROP_ID_TO_NAME[PROP_ID_targetId] = PROP_NAME_targetId;
          PROP_NAME_TO_ID.put(PROP_NAME_targetId, PROP_ID_targetId);
      
          PROP_ID_TO_NAME[PROP_ID_sourcePropName] = PROP_NAME_sourcePropName;
          PROP_NAME_TO_ID.put(PROP_NAME_sourcePropName, PROP_ID_sourcePropName);
      
          PROP_ID_TO_NAME[PROP_ID_targetPropName] = PROP_NAME_targetPropName;
          PROP_NAME_TO_ID.put(PROP_NAME_targetPropName, PROP_ID_targetPropName);
      
          PROP_ID_TO_NAME[PROP_ID_type] = PROP_NAME_type;
          PROP_NAME_TO_ID.put(PROP_NAME_type, PROP_ID_type);
      
          PROP_ID_TO_NAME[PROP_ID_tagSet] = PROP_NAME_tagSet;
          PROP_NAME_TO_ID.put(PROP_NAME_tagSet, PROP_ID_tagSet);
      
          PROP_ID_TO_NAME[PROP_ID_tableName] = PROP_NAME_tableName;
          PROP_NAME_TO_ID.put(PROP_NAME_tableName, PROP_ID_tableName);
      
          PROP_ID_TO_NAME[PROP_ID_joinOnCondsJsonText] = PROP_NAME_joinOnCondsJsonText;
          PROP_NAME_TO_ID.put(PROP_NAME_joinOnCondsJsonText, PROP_ID_joinOnCondsJsonText);
      
    }

    
    /* ID: ID */
    private java.lang.String _id;
    
    /* 关联源实体: SOURCE_ID */
    private java.lang.String _sourceId;
    
    /* 关联目标实体: TARGET_ID */
    private java.lang.String _targetId;
    
    /* 关联源实体侧属性名: SOURCE_PROP_NAME */
    private java.lang.String _sourcePropName;
    
    /* 关联目标实体侧属性名: TARGET_PROP_NAME */
    private java.lang.String _targetPropName;
    
    /* 关联类型: TYPE */
    private java.lang.String _type;
    
    /* 标签: TAG_SET */
    private java.lang.String _tagSet;
    
    /* 中间表表名: TABLE_NAME */
    private java.lang.String _tableName;
    
    /* 关联条件列表: JOIN_ON_CONDS */
    private java.lang.String _joinOnCondsJsonText;
    

    public _DevAppEntityRelation(){
        // for debug
    }

    protected DevAppEntityRelation newInstance(){
       return new DevAppEntityRelation();
    }

    @Override
    public DevAppEntityRelation cloneInstance() {
        DevAppEntityRelation entity = newInstance();
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
      return "io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntityRelation";
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
        
            case PROP_ID_sourceId:
               return getSourceId();
        
            case PROP_ID_targetId:
               return getTargetId();
        
            case PROP_ID_sourcePropName:
               return getSourcePropName();
        
            case PROP_ID_targetPropName:
               return getTargetPropName();
        
            case PROP_ID_type:
               return getType();
        
            case PROP_ID_tagSet:
               return getTagSet();
        
            case PROP_ID_tableName:
               return getTableName();
        
            case PROP_ID_joinOnCondsJsonText:
               return getJoinOnCondsJsonText();
        
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
        
            case PROP_ID_sourceId:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_sourceId));
               }
               setSourceId(typedValue);
               break;
            }
        
            case PROP_ID_targetId:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_targetId));
               }
               setTargetId(typedValue);
               break;
            }
        
            case PROP_ID_sourcePropName:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_sourcePropName));
               }
               setSourcePropName(typedValue);
               break;
            }
        
            case PROP_ID_targetPropName:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_targetPropName));
               }
               setTargetPropName(typedValue);
               break;
            }
        
            case PROP_ID_type:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_type));
               }
               setType(typedValue);
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
        
            case PROP_ID_tableName:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_tableName));
               }
               setTableName(typedValue);
               break;
            }
        
            case PROP_ID_joinOnCondsJsonText:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_joinOnCondsJsonText));
               }
               setJoinOnCondsJsonText(typedValue);
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
        
            case PROP_ID_sourceId:{
               onInitProp(propId);
               this._sourceId = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_targetId:{
               onInitProp(propId);
               this._targetId = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_sourcePropName:{
               onInitProp(propId);
               this._sourcePropName = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_targetPropName:{
               onInitProp(propId);
               this._targetPropName = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_type:{
               onInitProp(propId);
               this._type = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_tagSet:{
               onInitProp(propId);
               this._tagSet = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_tableName:{
               onInitProp(propId);
               this._tableName = (java.lang.String)value;
               
               break;
            }
        
            case PROP_ID_joinOnCondsJsonText:{
               onInitProp(propId);
               this._joinOnCondsJsonText = (java.lang.String)value;
               
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
     * 关联源实体: SOURCE_ID
     */
    public java.lang.String getSourceId(){
         onPropGet(PROP_ID_sourceId);
         return _sourceId;
    }

    /**
     * 关联源实体: SOURCE_ID
     */
    public void setSourceId(java.lang.String value){
        if(onPropSet(PROP_ID_sourceId,value)){
            this._sourceId = value;
            internalClearRefs(PROP_ID_sourceId);
            
        }
    }
    
    /**
     * 关联目标实体: TARGET_ID
     */
    public java.lang.String getTargetId(){
         onPropGet(PROP_ID_targetId);
         return _targetId;
    }

    /**
     * 关联目标实体: TARGET_ID
     */
    public void setTargetId(java.lang.String value){
        if(onPropSet(PROP_ID_targetId,value)){
            this._targetId = value;
            internalClearRefs(PROP_ID_targetId);
            
        }
    }
    
    /**
     * 关联源实体侧属性名: SOURCE_PROP_NAME
     */
    public java.lang.String getSourcePropName(){
         onPropGet(PROP_ID_sourcePropName);
         return _sourcePropName;
    }

    /**
     * 关联源实体侧属性名: SOURCE_PROP_NAME
     */
    public void setSourcePropName(java.lang.String value){
        if(onPropSet(PROP_ID_sourcePropName,value)){
            this._sourcePropName = value;
            internalClearRefs(PROP_ID_sourcePropName);
            
        }
    }
    
    /**
     * 关联目标实体侧属性名: TARGET_PROP_NAME
     */
    public java.lang.String getTargetPropName(){
         onPropGet(PROP_ID_targetPropName);
         return _targetPropName;
    }

    /**
     * 关联目标实体侧属性名: TARGET_PROP_NAME
     */
    public void setTargetPropName(java.lang.String value){
        if(onPropSet(PROP_ID_targetPropName,value)){
            this._targetPropName = value;
            internalClearRefs(PROP_ID_targetPropName);
            
        }
    }
    
    /**
     * 关联类型: TYPE
     */
    public java.lang.String getType(){
         onPropGet(PROP_ID_type);
         return _type;
    }

    /**
     * 关联类型: TYPE
     */
    public void setType(java.lang.String value){
        if(onPropSet(PROP_ID_type,value)){
            this._type = value;
            internalClearRefs(PROP_ID_type);
            
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
     * 中间表表名: TABLE_NAME
     */
    public java.lang.String getTableName(){
         onPropGet(PROP_ID_tableName);
         return _tableName;
    }

    /**
     * 中间表表名: TABLE_NAME
     */
    public void setTableName(java.lang.String value){
        if(onPropSet(PROP_ID_tableName,value)){
            this._tableName = value;
            internalClearRefs(PROP_ID_tableName);
            
        }
    }
    
    /**
     * 关联条件列表: JOIN_ON_CONDS
     */
    public java.lang.String getJoinOnCondsJsonText(){
         onPropGet(PROP_ID_joinOnCondsJsonText);
         return _joinOnCondsJsonText;
    }

    /**
     * 关联条件列表: JOIN_ON_CONDS
     */
    public void setJoinOnCondsJsonText(java.lang.String value){
        if(onPropSet(PROP_ID_joinOnCondsJsonText,value)){
            this._joinOnCondsJsonText = value;
            internalClearRefs(PROP_ID_joinOnCondsJsonText);
            
        }
    }
    
    /**
     * 
     */
    public io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity getSource(){
       return (io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity)internalGetRefEntity(PROP_NAME_source);
    }

    public void setSource(io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity refEntity){
   
           if(refEntity == null){
           
                   this.setSourceId(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_source, refEntity,()->{
           
                           this.setSourceId(refEntity.getId());
                       
           });
           }
       
    }
       
    /**
     * 
     */
    public io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity getTarget(){
       return (io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity)internalGetRefEntity(PROP_NAME_target);
    }

    public void setTarget(io.crazydan.duzhou.framework.app_dev.orm.entity.DevAppEntity refEntity){
   
           if(refEntity == null){
           
                   this.setTargetId(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_target, refEntity,()->{
           
                           this.setTargetId(refEntity.getId());
                       
           });
           }
       
    }
       
   public java.lang.Object getJoinOnConds(){
      return (java.lang.Object)internalGetAliasValue("joinOnCondsJsonTextComponent.data");
   }

   public void setJoinOnConds(java.lang.Object value){
      internalSetAliasValue("joinOnCondsJsonTextComponent.data",value);
   }

   private io.nop.orm.component.JsonOrmComponent _joinOnCondsJsonTextComponent;

   private static Map<String,Integer> COMPONENT_PROP_ID_MAP_joinOnCondsJsonTextComponent = new HashMap<>();
   static{
      
         COMPONENT_PROP_ID_MAP_joinOnCondsJsonTextComponent.put(io.nop.orm.component.JsonOrmComponent.PROP_NAME__jsonText,PROP_ID_joinOnCondsJsonText);
      
   }

   public io.nop.orm.component.JsonOrmComponent getJoinOnCondsJsonTextComponent(){
      if(_joinOnCondsJsonTextComponent == null){
          _joinOnCondsJsonTextComponent = new io.nop.orm.component.JsonOrmComponent();
          _joinOnCondsJsonTextComponent.bindToEntity(this, COMPONENT_PROP_ID_MAP_joinOnCondsJsonTextComponent);
      }
      return _joinOnCondsJsonTextComponent;
   }

}
// resume CPD analysis - CPD-ON
