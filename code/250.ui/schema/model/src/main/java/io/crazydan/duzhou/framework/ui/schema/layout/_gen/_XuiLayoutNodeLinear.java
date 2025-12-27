package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeLinear;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 在行/列方向上进行布局控制，与
 * > [css flexbox](https://css-tricks.com/snippets/css/a-guide-to-flexbox/)
 * > 类似。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeLinear extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeLayered {
    
    /**
     *  布局命名节点
     * xml name: body
     * 
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeBody _body ;
    
    /**
     *  布局类型
     * xml name: type
     * > 指定其内部子节点的布局方式
     */
    private java.lang.String _type ;
    
    /**
     *  是否可换行
     * xml name: wrap
     * > 若为 `false`，则溢出节点将被隐藏，否则，溢出节点将自动换行显示。缺省为 `false`
     */
    private java.lang.Boolean _wrap  = false;
    
    /**
     * 布局命名节点
     * xml name: body
     *  
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeBody getBody(){
      return _body;
    }

    
    public void setBody(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeBody value){
        checkAllowChange();
        
        this._body = value;
           
    }

    
    /**
     * 布局类型
     * xml name: type
     *  > 指定其内部子节点的布局方式
     */
    
    public java.lang.String getType(){
      return _type;
    }

    
    public void setType(java.lang.String value){
        checkAllowChange();
        
        this._type = value;
           
    }

    
    /**
     * 是否可换行
     * xml name: wrap
     *  > 若为 `false`，则溢出节点将被隐藏，否则，溢出节点将自动换行显示。缺省为 `false`
     */
    
    public java.lang.Boolean getWrap(){
      return _wrap;
    }

    
    public void setWrap(java.lang.Boolean value){
        checkAllowChange();
        
        this._wrap = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._body = io.nop.api.core.util.FreezeHelper.deepFreeze(this._body);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("body",this.getBody());
        out.putNotNull("type",this.getType());
        out.putNotNull("wrap",this.getWrap());
    }

    public XuiLayoutNodeLinear cloneInstance(){
        XuiLayoutNodeLinear instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeLinear instance){
        super.copyTo(instance);
        
        instance.setBody(this.getBody());
        instance.setType(this.getType());
        instance.setWrap(this.getWrap());
    }

    protected XuiLayoutNodeLinear newInstance(){
        return (XuiLayoutNodeLinear) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
