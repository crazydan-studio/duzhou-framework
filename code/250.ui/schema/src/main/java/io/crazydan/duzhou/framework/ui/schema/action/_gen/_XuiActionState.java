package io.crazydan.duzhou.framework.ui.schema.action._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.action.XuiActionState;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/action/node.xdef <p>
 * 更新组件状态：默认在同一层级仅出现一次
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiActionState extends io.crazydan.duzhou.framework.ui.schema.action.XuiActionNamed {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.action.XuiActionStateData> _dataList = java.util.Collections.emptyMap();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.action.XuiActionStateData> getDataList(){
      return _dataList;
    }

    
    public void setDataList(java.util.Map<java.lang.String,io.crazydan.duzhou.framework.ui.schema.action.XuiActionStateData> value){
        checkAllowChange();
        
        this._dataList = value;
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.action.XuiActionStateData getData(String name){
        return this._dataList.get(name);
    }

    public boolean hasData(String name){
        return this._dataList.containsKey(name);
    }
    
    public boolean hasDataList(){
        return this._dataList != null && !this._dataList.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._dataList = io.nop.api.core.util.FreezeHelper.deepFreeze(this._dataList);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("dataList",this.getDataList());
    }

    public XuiActionState cloneInstance(){
        XuiActionState instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiActionState instance){
        super.copyTo(instance);
        
        instance.setDataList(this.getDataList());
    }

    protected XuiActionState newInstance(){
        return (XuiActionState) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
