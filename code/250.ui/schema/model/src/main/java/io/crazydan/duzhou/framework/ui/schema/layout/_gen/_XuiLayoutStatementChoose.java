package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChoose;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutStatementChoose extends io.nop.core.resource.component.AbstractComponentModel implements io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutRoot{
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     *  缺省条件
     * xml name: otherwise
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseOtherwise _otherwise ;
    
    /**
     *  特定条件
     * xml name: when
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen> _whens = KeyedList.emptyList();
    
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
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseOtherwise getOtherwise(){
      return _otherwise;
    }

    
    public void setOtherwise(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseOtherwise value){
        checkAllowChange();
        
        this._otherwise = value;
           
    }

    
    /**
     * 特定条件
     * xml name: when
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen> getWhens(){
      return _whens;
    }

    
    public void setWhens(java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen> value){
        checkAllowChange();
        
        this._whens = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen getWhen(String name){
        return this._whens.getByKey(name);
    }

    public boolean hasWhen(String name){
        return this._whens.containsKey(name);
    }

    public void addWhen(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen> list = this.getWhens();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutStatementChooseWhen::getXuiName);
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

    public XuiLayoutStatementChoose cloneInstance(){
        XuiLayoutStatementChoose instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutStatementChoose instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setOtherwise(this.getOtherwise());
        instance.setWhens(this.getWhens());
    }

    protected XuiLayoutStatementChoose newInstance(){
        return (XuiLayoutStatementChoose) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
