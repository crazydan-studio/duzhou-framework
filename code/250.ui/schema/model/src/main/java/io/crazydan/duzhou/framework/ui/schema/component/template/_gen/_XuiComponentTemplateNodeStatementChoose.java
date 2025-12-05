package io.crazydan.duzhou.framework.ui.schema.component.template._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChoose;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/template.xdef <p>
 * >
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentTemplateNodeStatementChoose extends io.nop.core.resource.component.AbstractComponentModel implements io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed{
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     *  缺省条件
     * xml name: otherwise
     * >
     */
    private io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseOtherwise _otherwise ;
    
    /**
     *  特定条件
     * xml name: when
     * > 在 `test` 表达式的结果为 `true` 时，获得其子节点。
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen> _whens = KeyedList.emptyList();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.lang.String get$tag(){
      return _$tag;
    }

    
    public void set$tag(java.lang.String value){
        checkAllowChange();
        
        this._$tag = value;
           
    }

    
    /**
     * 缺省条件
     * xml name: otherwise
     *  >
     */
    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseOtherwise getOtherwise(){
      return _otherwise;
    }

    
    public void setOtherwise(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseOtherwise value){
        checkAllowChange();
        
        this._otherwise = value;
           
    }

    
    /**
     * 特定条件
     * xml name: when
     *  > 在 `test` 表达式的结果为 `true` 时，获得其子节点。
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen> getWhens(){
      return _whens;
    }

    
    public void setWhens(java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen> value){
        checkAllowChange();
        
        this._whens = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen getWhen(String name){
        return this._whens.getByKey(name);
    }

    public boolean hasWhen(String name){
        return this._whens.containsKey(name);
    }

    public void addWhen(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen> list = this.getWhens();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChooseWhen::getXuiName);
            setWhens(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_whens(){
        return this._whens.keySet();
    }

    public boolean hasWhens(){
        return !this._whens.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._otherwise = io.nop.api.core.util.FreezeHelper.deepFreeze(this._otherwise);
            
           this._whens = io.nop.api.core.util.FreezeHelper.deepFreeze(this._whens);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("otherwise",this.getOtherwise());
        out.putNotNull("whens",this.getWhens());
    }

    public XuiComponentTemplateNodeStatementChoose cloneInstance(){
        XuiComponentTemplateNodeStatementChoose instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentTemplateNodeStatementChoose instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setOtherwise(this.getOtherwise());
        instance.setWhens(this.getWhens());
    }

    protected XuiComponentTemplateNodeStatementChoose newInstance(){
        return (XuiComponentTemplateNodeStatementChoose) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
