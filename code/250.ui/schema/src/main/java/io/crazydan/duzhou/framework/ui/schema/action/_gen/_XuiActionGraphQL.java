package io.crazydan.duzhou.framework.ui.schema.action._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.action.XuiActionGraphQL;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/action/node.xdef <p>
 * 发送 GraphQL 请求
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiActionGraphQL extends io.crazydan.duzhou.framework.ui.schema.action.XuiActionHttpBase {
    
    /**
     *  必填
     * xml name: field
     * GraphQL 字段；
     */
    private java.lang.String _field ;
    
    /**
     *  可选
     * xml name: selection
     * GraphQL 选择字段集；
     */
    private java.lang.String _selection ;
    
    /**
     * 必填
     * xml name: field
     *  GraphQL 字段；
     */
    
    public java.lang.String getField(){
      return _field;
    }

    
    public void setField(java.lang.String value){
        checkAllowChange();
        
        this._field = value;
           
    }

    
    /**
     * 可选
     * xml name: selection
     *  GraphQL 选择字段集；
     */
    
    public java.lang.String getSelection(){
      return _selection;
    }

    
    public void setSelection(java.lang.String value){
        checkAllowChange();
        
        this._selection = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("field",this.getField());
        out.putNotNull("selection",this.getSelection());
    }

    public XuiActionGraphQL cloneInstance(){
        XuiActionGraphQL instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiActionGraphQL instance){
        super.copyTo(instance);
        
        instance.setField(this.getField());
        instance.setSelection(this.getSelection());
    }

    protected XuiActionGraphQL newInstance(){
        return (XuiActionGraphQL) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
