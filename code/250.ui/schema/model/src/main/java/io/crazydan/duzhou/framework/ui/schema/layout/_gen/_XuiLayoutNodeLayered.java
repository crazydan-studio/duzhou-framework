package io.crazydan.duzhou.framework.ui.schema.layout._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeLayered;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/layout.xdef <p>
 * > 通过 `layer` 指定布局节点在 z 轴上的位置，从而实现不同布局层之间的堆叠效果。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiLayoutNodeLayered extends io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNodeStyled {
    
    /**
     *  层序号
     * xml name: layer
     * > 布局节点在 z 轴上的序号，该值越大，则其越靠近顶层
     */
    private int _layer  = 0;
    
    /**
     * 层序号
     * xml name: layer
     *  > 布局节点在 z 轴上的序号，该值越大，则其越靠近顶层
     */
    
    public int getLayer(){
      return _layer;
    }

    
    public void setLayer(int value){
        checkAllowChange();
        
        this._layer = value;
           
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
        
        out.putNotNull("layer",this.getLayer());
    }

    public XuiLayoutNodeLayered cloneInstance(){
        XuiLayoutNodeLayered instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiLayoutNodeLayered instance){
        super.copyTo(instance);
        
        instance.setLayer(this.getLayer());
    }

    protected XuiLayoutNodeLayered newInstance(){
        return (XuiLayoutNodeLayered) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
