package io.crazydan.duzhou.framework.ui.schema._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.crazydan.duzhou.framework.ui.schema.XuiApp;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from /duzhou/ui/schema/app.xdef <p>
 * > 用于组织应用的页面。
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XuiApp extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  应用唯一标识
     * xml name: code
     * > (必填)
     */
    private java.lang.String _code ;
    
    /**
     *  应用的版权声明内容
     * xml name: copyright
     * 
     */
    private java.lang.String _copyright ;
    
    /**
     *  应用的描述内容
     * xml name: description
     * 
     */
    private java.lang.String _description ;
    
    /**
     *  应用许可协议
     * xml name: license
     * > (可选) 可填写协议名称或编码
     */
    private java.lang.String _license ;
    
    /**
     *  应用 logo 地址
     * xml name: logo
     * > (可选)
     */
    private java.lang.String _logo ;
    
    /**
     *  
     * xml name: pages
     * 
     */
    private KeyedList<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> _pageRefs = KeyedList.emptyList();
    
    /**
     *  应用加载动画资源路径
     * xml name: spinner
     * > (可选) svg 或 gif 类型的资源 classpath 路径
     */
    private java.lang.String _spinner ;
    
    /**
     *  应用标题
     * xml name: title
     * > (必填)
     */
    private java.lang.String _title ;
    
    /**
     *  应用版本号
     * xml name: version
     * > (必填)
     */
    private java.lang.String _version ;
    
    /**
     * 应用唯一标识
     * xml name: code
     *  > (必填)
     */
    
    public java.lang.String getCode(){
      return _code;
    }

    
    public void setCode(java.lang.String value){
        checkAllowChange();
        
        this._code = value;
           
    }

    
    /**
     * 应用的版权声明内容
     * xml name: copyright
     *  
     */
    
    public java.lang.String getCopyright(){
      return _copyright;
    }

    
    public void setCopyright(java.lang.String value){
        checkAllowChange();
        
        this._copyright = value;
           
    }

    
    /**
     * 应用的描述内容
     * xml name: description
     *  
     */
    
    public java.lang.String getDescription(){
      return _description;
    }

    
    public void setDescription(java.lang.String value){
        checkAllowChange();
        
        this._description = value;
           
    }

    
    /**
     * 应用许可协议
     * xml name: license
     *  > (可选) 可填写协议名称或编码
     */
    
    public java.lang.String getLicense(){
      return _license;
    }

    
    public void setLicense(java.lang.String value){
        checkAllowChange();
        
        this._license = value;
           
    }

    
    /**
     * 应用 logo 地址
     * xml name: logo
     *  > (可选)
     */
    
    public java.lang.String getLogo(){
      return _logo;
    }

    
    public void setLogo(java.lang.String value){
        checkAllowChange();
        
        this._logo = value;
           
    }

    
    /**
     * 
     * xml name: pages
     *  
     */
    
    public java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> getPageRefs(){
      return _pageRefs;
    }

    
    public void setPageRefs(java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> value){
        checkAllowChange();
        
        this._pageRefs = KeyedList.fromList(value, io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef::getName);
           
    }

    
    public io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef getPageRef(String name){
        return this._pageRefs.getByKey(name);
    }

    public boolean hasPageRef(String name){
        return this._pageRefs.containsKey(name);
    }

    public void addPageRef(io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef item) {
        checkAllowChange();
        java.util.List<io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef> list = this.getPageRefs();
        if (list == null || list.isEmpty()) {
            list = new KeyedList<>(io.crazydan.duzhou.framework.ui.schema.app.XuiAppPageRef::getName);
            setPageRefs(list);
        }
        list.add(item);
    }
    
    public java.util.Set<String> keySet_pageRefs(){
        return this._pageRefs.keySet();
    }

    public boolean hasPageRefs(){
        return !this._pageRefs.isEmpty();
    }
    
    /**
     * 应用加载动画资源路径
     * xml name: spinner
     *  > (可选) svg 或 gif 类型的资源 classpath 路径
     */
    
    public java.lang.String getSpinner(){
      return _spinner;
    }

    
    public void setSpinner(java.lang.String value){
        checkAllowChange();
        
        this._spinner = value;
           
    }

    
    /**
     * 应用标题
     * xml name: title
     *  > (必填)
     */
    
    public java.lang.String getTitle(){
      return _title;
    }

    
    public void setTitle(java.lang.String value){
        checkAllowChange();
        
        this._title = value;
           
    }

    
    /**
     * 应用版本号
     * xml name: version
     *  > (必填)
     */
    
    public java.lang.String getVersion(){
      return _version;
    }

    
    public void setVersion(java.lang.String value){
        checkAllowChange();
        
        this._version = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._pageRefs = io.nop.api.core.util.FreezeHelper.deepFreeze(this._pageRefs);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("code",this.getCode());
        out.putNotNull("copyright",this.getCopyright());
        out.putNotNull("description",this.getDescription());
        out.putNotNull("license",this.getLicense());
        out.putNotNull("logo",this.getLogo());
        out.putNotNull("pageRefs",this.getPageRefs());
        out.putNotNull("spinner",this.getSpinner());
        out.putNotNull("title",this.getTitle());
        out.putNotNull("version",this.getVersion());
    }

    public XuiApp cloneInstance(){
        XuiApp instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(XuiApp instance){
        super.copyTo(instance);
        
        instance.setCode(this.getCode());
        instance.setCopyright(this.getCopyright());
        instance.setDescription(this.getDescription());
        instance.setLicense(this.getLicense());
        instance.setLogo(this.getLogo());
        instance.setPageRefs(this.getPageRefs());
        instance.setSpinner(this.getSpinner());
        instance.setTitle(this.getTitle());
        instance.setVersion(this.getVersion());
    }

    protected XuiApp newInstance(){
        return (XuiApp) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
