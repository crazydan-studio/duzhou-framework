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
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> _children = KeyedList.emptyList();
    
    /**
     *  布局类型
     * xml name: type
     * > 指定其内部子节点的布局方式
     */
    private io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutLinearNode.Type _type ;
    
    /**
     *  是否可换行
     * xml name: wrap
     * > 若为 `false`，则溢出节点将被隐藏，否则，溢出节点将自动换行显式。缺省为 `false`
     */
    private java.lang.Boolean _wrap ;
    
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
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> getChildren(){
      return _children;
    }

    
    public void setChildren(java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> value){
        checkAllowChange();
        
        this._children = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed::getXuiName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed getChild(String name){
        return this._children.getByKey(name);
    }

    public boolean hasChild(String name){
        return this._children.containsKey(name);
    }

    public void addChild(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed> list = this.getChildren();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeNamed::getXuiName);
            setChildren(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_children(){
        return this._children.keySet();
    }

    public boolean hasChildren(){
        return !this._children.isEmpty();
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

    
    /**
     * 是否可换行
     * xml name: wrap
     *  > 若为 `false`，则溢出节点将被隐藏，否则，溢出节点将自动换行显式。缺省为 `false`
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
        
           this._children = io.nop.api.core.util.FreezeHelper.deepFreeze(this._children);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("$tag",this.get$tag());
        out.putNotNull("children",this.getChildren());
        out.putNotNull("type",this.getType());
        out.putNotNull("wrap",this.getWrap());
    }

    public XuiLayoutLinearNode cloneInstance(){
        XuiLayoutLinearNode instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutLinearNode instance){
        super.copyTo(instance);
        
        instance.set$tag(this.get$tag());
        instance.setChildren(this.getChildren());
        instance.setType(this.getType());
        instance.setWrap(this.getWrap());
    }

    protected XuiLayoutLinearNode newInstance(){
        return (XuiLayoutLinearNode) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
