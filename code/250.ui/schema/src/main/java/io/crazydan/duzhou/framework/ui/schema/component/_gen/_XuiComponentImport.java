package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentImport;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/import.xdef <p>
 * > 单独定义，以支持在指定包下创建 `class`
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentImport extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  导入的组件名称
     * xml name: as
     * > (必填) 其名称需为首字母大写的驼峰形式（可包含下划线）
     */
    private java.lang.String _as ;
    
    /**
     *  导入组件的 v-path 路径
     * xml name: from
     * > (必填) 如果为 `native:{code}`
     * > 形式，则表示在解析模型时，需由运行环境提供标识为 `{code}` 的原生组件，
     * > 并由运行时构建器决定如何生成运行时产物；
     */
    private java.lang.String _from ;
    
    /**
     * 导入的组件名称
     * xml name: as
     *  > (必填) 其名称需为首字母大写的驼峰形式（可包含下划线）
     */
    
    public java.lang.String getAs(){
      return _as;
    }

    
    public void setAs(java.lang.String value){
        checkAllowChange();
        
        this._as = value;
           
    }

    
    /**
     * 导入组件的 v-path 路径
     * xml name: from
     *  > (必填) 如果为 `native:{code}`
     * > 形式，则表示在解析模型时，需由运行环境提供标识为 `{code}` 的原生组件，
     * > 并由运行时构建器决定如何生成运行时产物；
     */
    
    public java.lang.String getFrom(){
      return _from;
    }

    
    public void setFrom(java.lang.String value){
        checkAllowChange();
        
        this._from = value;
           
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
        
        out.putNotNull("as",this.getAs());
        out.putNotNull("from",this.getFrom());
    }

    public XuiComponentImport cloneInstance(){
        XuiComponentImport instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentImport instance){
        super.copyTo(instance);
        
        instance.setAs(this.getAs());
        instance.setFrom(this.getFrom());
    }

    protected XuiComponentImport newInstance(){
        return (XuiComponentImport) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
