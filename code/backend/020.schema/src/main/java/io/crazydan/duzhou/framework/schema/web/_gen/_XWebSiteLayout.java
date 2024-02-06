package io.crazydan.duzhou.framework.schema.web._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.schema.web.XWebSiteLayout;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [41:6:0:0]/duzhou/web/site.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XWebSiteLayout extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: render
     * 
     */
    private io.nop.core.lang.eval.IEvalAction _render ;
    
    /**
     *  
     * xml name: scripts
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> _scripts = KeyedList.emptyList();
    
    /**
     *  
     * xml name: styles
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> _styles = KeyedList.emptyList();
    
    /**
     * 
     * xml name: render
     *  
     */
    
    public io.nop.core.lang.eval.IEvalAction getRender(){
      return _render;
    }

    
    public void setRender(io.nop.core.lang.eval.IEvalAction value){
        checkAllowChange();
        
        this._render = value;
           
    }

    
    /**
     * 
     * xml name: scripts
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> getScripts(){
      return _scripts;
    }

    
    public void setScripts(java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> value){
        checkAllowChange();
        
        this._scripts = KeyedList.fromList(value, io.crazydan.duzhou.framework.schema.web.XWebSiteScript::getName);
           
    }

    
    public io.crazydan.duzhou.framework.schema.web.XWebSiteScript getScript(String name){
        return this._scripts.getByKey(name);
    }

    public boolean hasScript(String name){
        return this._scripts.containsKey(name);
    }

    public void addScript(io.crazydan.duzhou.framework.schema.web.XWebSiteScript item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteScript> list = this.getScripts();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.schema.web.XWebSiteScript::getName);
            setScripts(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_scripts(){
        return this._scripts.keySet();
    }

    public boolean hasScripts(){
        return !this._scripts.isEmpty();
    }
    
    /**
     * 
     * xml name: styles
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> getStyles(){
      return _styles;
    }

    
    public void setStyles(java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> value){
        checkAllowChange();
        
        this._styles = KeyedList.fromList(value, io.crazydan.duzhou.framework.schema.web.XWebSiteStyle::getName);
           
    }

    
    public io.crazydan.duzhou.framework.schema.web.XWebSiteStyle getStyle(String name){
        return this._styles.getByKey(name);
    }

    public boolean hasStyle(String name){
        return this._styles.containsKey(name);
    }

    public void addStyle(io.crazydan.duzhou.framework.schema.web.XWebSiteStyle item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.schema.web.XWebSiteStyle> list = this.getStyles();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.schema.web.XWebSiteStyle::getName);
            setStyles(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_styles(){
        return this._styles.keySet();
    }

    public boolean hasStyles(){
        return !this._styles.isEmpty();
    }
    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._scripts = io.nop.api.core.util.FreezeHelper.deepFreeze(this._scripts);
            
           this._styles = io.nop.api.core.util.FreezeHelper.deepFreeze(this._styles);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("render",this.getRender());
        out.putNotNull("scripts",this.getScripts());
        out.putNotNull("styles",this.getStyles());
    }

    public XWebSiteLayout cloneInstance(){
        XWebSiteLayout instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XWebSiteLayout instance){
        super.copyTo(instance);
        
        instance.setRender(this.getRender());
        instance.setScripts(this.getScripts());
        instance.setStyles(this.getStyles());
    }

    protected XWebSiteLayout newInstance(){
        return (XWebSiteLayout) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
