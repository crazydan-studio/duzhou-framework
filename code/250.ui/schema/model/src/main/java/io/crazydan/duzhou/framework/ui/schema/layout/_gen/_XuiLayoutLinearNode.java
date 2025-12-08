package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutLinearNode;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutLinearNode extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.lang.String _$tag ;
    
    /**
     *  子节点
     * xml name: body
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> _body = KeyedList.emptyList();
    
    /**
     *  节点配置
     * xml name: props
     * > 对子节点整体进行布局控制。
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNestedProps _props ;
    
    /**
     *  布局类型
     * xml name: type
     * > 指定其内部子节点的布局方式
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutLinearNode.Type _type ;
    
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
     * 子节点
     * xml name: body
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> getBody(){
      return _body;
    }

    
    public void setBody(java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> value){
        checkAllowChange();
        
        this._body = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed getNode(String name){
        return this._body.getByKey(name);
    }

    public boolean hasNode(String name){
        return this._body.containsKey(name);
    }

    public void addNode(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> list = this.getBody();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed::getXuiName);
            setBody(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_body(){
        return this._body.keySet();
    }

    public boolean hasBody(){
        return !this._body.isEmpty();
    }
    
    /**
     * 节点配置
     * xml name: props
     *  > 对子节点整体进行布局控制。
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNestedProps getProps(){
      return _props;
    }

    
    public void setProps(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNestedProps value){
        checkAllowChange();
        
        this._props = value;
           
    }

    
    /**
     * 布局类型
     * xml name: type
     *  > 指定其内部子节点的布局方式
     */
    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutLinearNode.Type getType(){
      return _type;
    }

    
    public void setType(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutLinearNode.Type value){
        checkAllowChange();
        
        this._type = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._body = io.nop.api.core.util.FreezeHelper.deepFreeze(this._body);
            
           this._props = io.nop.api.core.util.FreezeHelper.deepFreeze(this._props);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("body",this.getBody());
        out.putNotNull("props",this.getProps());
        out.putNotNull("type",this.getType());
    }

    public XuiLayoutLinearNode cloneInstance(){
        XuiLayoutLinearNode instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutLinearNode instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setBody(this.getBody());
        instance.setProps(this.getProps());
        instance.setType(this.getType());
    }

    protected XuiLayoutLinearNode newInstance(){
        return (XuiLayoutLinearNode) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
