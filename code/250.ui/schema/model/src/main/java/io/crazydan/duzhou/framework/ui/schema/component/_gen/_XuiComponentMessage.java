package io.crazydan.duzhou.framework.ui.schema.component._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentMessage;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/component/message.xdef <p>
 * > 单独定义，以支持在指定包下创建 `class`。
 * >
 * > - 每个消息均由唯一 `type` 表示，并以 `data` 作为负载数据的引用名字；
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiComponentMessage extends io.crazydan.duzhou.framework.ui.schema.action.XuiActionNode {
    
    /**
     *  消息类型
     * xml name: type
     * > (必填) 其值需为首字母大写的驼峰形式（可包含下划线）；
     */
    private java.lang.String _type ;
    
    /**
     * 消息类型
     * xml name: type
     *  > (必填) 其值需为首字母大写的驼峰形式（可包含下划线）；
     */
    
    public java.lang.String getType(){
      return _type;
    }

    
    public void setType(java.lang.String value){
        checkAllowChange();
        
        this._type = value;
           
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
        
        out.putNotNull("type",this.getType());
    }

    public XuiComponentMessage cloneInstance(){
        XuiComponentMessage instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiComponentMessage instance){
        super.copyTo(instance);
        
        instance.setType(this.getType());
    }

    protected XuiComponentMessage newInstance(){
        return (XuiComponentMessage) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
