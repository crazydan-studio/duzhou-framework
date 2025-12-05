package io.crazydan.duzhou.framework.ui.schema.component;

import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeAny;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNamed;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeNested;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeStatementChoose;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplateNodeText;
import io.crazydan.duzhou.framework.ui.util.XuiHelper;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.INeedInit;
import io.nop.api.core.util.ISourceLocationGetter;
import io.nop.commons.util.StringHelper;
import io.nop.commons.util.objects.ValueWithLocation;
import io.nop.core.lang.eval.IEvalAction;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.xml.XNode;
import io.nop.core.reflect.ReflectionManager;
import io.nop.core.reflect.bean.IBeanModel;
import io.nop.xlang.api.IXLangCompileScope;
import io.nop.xlang.api.XLang;
import io.nop.xlang.api.XLangCompileTool;
import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.XLangOutputMode;
import io.nop.xlang.xdsl.DslModelHelper;
import io.nop.xlang.xpl.IXplCompiler;
import io.nop.xlang.xpl.IXplTagCompiler;
import io.nop.xlang.xpl.tags.ChooseTagCompiler;
import io.nop.xlang.xpl.tags.ForTagCompiler;
import io.nop.xlang.xpl.tags.IfTagCompiler;
import io.nop.xlang.xpl.utils.XplParseHelper;

import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_NAME;
import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_NAME_RAW;
import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_CHOOSE;
import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_FOR;
import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_IF;
import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_OTHERWISE;
import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_TEMPLATE;
import static io.crazydan.duzhou.framework.ui.XuiConstants.TAG_NAME_WHEN;
import static io.crazydan.duzhou.framework.ui.XuiConstants.XDSL_SCHEMA_COMPONENT_TEMPLATE;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_DSL_NODE_NOT_BOUND;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_TAG_COMPONENT_LOADING_FAILED;
import static io.crazydan.duzhou.framework.ui.XuiErrors.ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED;
import static io.nop.xlang.XLangErrors.ARG_PATH;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;
import static io.nop.xlang.xpl.XplConstants.INDEX_NAME;

/**
 * 支持调用 {@link #evalTemplate} 以动态解析组件树
 * <br/><br/>
 * 对组件树的动态解析是通过将 &lt;if/>、&lt;for/> 等标签映射为
 * &lt;c:if/>、&lt;c:for/> 等 Xpl 标签，再将 {@link #getTemplate()}
 * 对应的 XNode 节点解析为 Xpl 脚本的方式实现。
 * 虽然该方式存在重复解析 XNode 节点的问题，但考虑到对组件完整性和有效性的检查等业务问题，
 * 其性能损耗是必要且可接受的（对 Xpl 脚本的解析结果将被缓存起来，避免重复解析）
 */
public class XuiComponent extends _XuiComponent implements INeedInit {
    /** 组件的 {@link XNode} 节点 */
    private XNode _dslNode;
    /** 用于支持 Xpl 动态解析组件树 */
    private IEvalAction templateEvalAction;

    public XuiComponent() {

    }

    @Override
    public void init() {
        if (getTemplate() != null) {
            getTemplate().init();
            checkImported(getTemplate());
        }
    }

    /** 动态生成组件模版树 */
    public XuiComponentTemplate evalTemplate(IEvalScope scope) {
        return doEvalTemplate(scope);
    }

    /** 加载标签对应的{@link XuiComponent 组件} */
    public XuiComponent loadTagComponent(XuiComponentTemplateNodeNamed node) {
        String tagName = getTagName(node);
        if (tagName == null) {
            return null;
        }

        String dslPath = getImport(tagName).getFrom();
        try {
            return XuiHelper.loadComponent(dslPath);
        } catch (Exception e) {
            throw new NopException(ERR_COMPONENT_TAG_COMPONENT_LOADING_FAILED, e).source((ISourceLocationGetter) node)
                                                                                 .param(ARG_TAG_NAME, tagName)
                                                                                 .param(ARG_PATH, dslPath);
        }
    }

    /** 检查组件节点是否已显式通过 {@code <import/>} 导入 */
    protected void checkImported(XuiComponentTemplateNodeNamed node) {
        if (node == null) {
            return;
        }
        if (node instanceof XuiComponentTemplateNodeStatementChoose) {
            XuiComponentTemplateNodeStatementChoose choose = (XuiComponentTemplateNodeStatementChoose) node;

            choose.getWhens().forEach(this::checkImported);
            checkImported(choose.getOtherwise());
            return;
        }

        String tagName = getTagName(node);
        if (tagName != null && !hasImport(tagName)) {
            throw new NopException(ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED).source((ISourceLocationGetter) node)
                                                                            .param(ARG_TAG_NAME, tagName);
        }

        if (node instanceof XuiComponentTemplateNodeNested) {
            ((XuiComponentTemplateNodeNested) node).getChildren().forEach(this::checkImported);
        }
    }

    protected XuiComponentTemplate doEvalTemplate(IEvalScope scope) {
        IEvalAction action = getTemplateEvalAction();
        if (action == null) {
            return null;
        }

        XNode node = (XNode) action.invoke(scope);

        // Note: 在解析得到模型后，将自动调用 INeedInit#init 进行初始化
        return (XuiComponentTemplate) DslModelHelper.parseDslNode(XDSL_SCHEMA_COMPONENT_TEMPLATE, node);
    }

    protected IEvalAction getTemplateEvalAction() {
        if (this._dslNode == null) {
            throw new NopException(ERR_COMPONENT_DSL_NODE_NOT_BOUND).source(this);
        }

        if (this.templateEvalAction == null) {
            XNode node = this._dslNode.childByTag(TAG_NAME_TEMPLATE);

            if (node != null) {
                // 涉及对 Xpl 节点的修改，故而需复制一份以避免影响原始节点的结构
                node = cloneXNode(node, getTemplate());

                // Note: 必须将 <template/> 单独挂载到仅有唯一子节点的根节点上，
                // 因为 Xpl 脚本的执行结果返回的是脚本构造节点的最后一个子节点
                XNode dummy = XNode.makeDummyNode();
                node.insertParent(dummy);

                this.templateEvalAction = newCompileTool().compileTagBody(dummy, XLangOutputMode.node);
            }
        }
        return this.templateEvalAction;
    }

    public static XLangCompileTool newCompileTool() {
        XLangCompileTool compileTool = XLang.newCompileTool();
        compileTool.allowUnregisteredScopeVar(true);

        IXLangCompileScope scope = compileTool.getScope();
        scope.addTagCompiler(TAG_NAME_IF, new XplTagCompiler(IfTagCompiler.INSTANCE));
        scope.addTagCompiler(TAG_NAME_CHOOSE, new XplTagCompiler(ChooseTagCompiler.INSTANCE));
        scope.addTagCompiler(TAG_NAME_FOR, new XplTagCompiler(ForTagCompiler.INSTANCE));

        // TODO 定义 slot 标签函数

        return compileTool;
    }

    private String getTagName(XuiComponentTemplateNodeNamed node) {
        if (node instanceof XuiComponentTemplateNodeText) {
            return ((XuiComponentTemplateNodeText) node).get$tag();
        } //
        else if (node instanceof XuiComponentTemplateNodeAny) {
            return ((XuiComponentTemplateNodeAny) node).get$tag();
        }
        return null;
    }

    protected static XNode cloneXNode(XNode node, XuiComponentTemplateNodeNamed bean) {
        XNode copiedNode = node.cloneWithoutChildren();

        if (bean != null && copiedNode.getAttrCount() > 0) {
            IBeanModel beanModel = ReflectionManager.instance().getBeanModelForClass(bean.getClass());

            String[] attrNames = copiedNode.getAttrNames().toArray(new String[0]);
            for (String attrName : attrNames) {
                ValueWithLocation attr = copiedNode.attrValueLoc(attrName);

                // 将宏表达式 #{xxx} 替换为实际解析后的值
                // Note: 宏表达式仅在构造 XuiComponent 实例时解析并赋值在该实例中
                if (XplParseHelper.isCpExpr(attr.asString())) {
                    String propName = StringHelper.xmlNameToPropName(attrName);
                    Object attrValue = beanModel.getProperty(bean, propName);

                    copiedNode.setAttr(attr.getLocation(), attrName, attrValue);
                }
            }
        }

        for (XNode child : node.getChildren()) {
            Object xuiName = child.getAttr(ATTR_NAME_XUI_NAME);
            XuiComponentTemplateNodeNamed beanChild = null;
            if (bean instanceof XuiComponentTemplateNodeNested) {
                beanChild = ((XuiComponentTemplateNodeNested) bean).getChild((String) xuiName);
            }

            XNode copiedChild = cloneXNode(child, beanChild);
            copiedNode.appendChild(copiedChild);
        }

        return copiedNode;
    }

    // <<<<<<<<<<<<<<< getter/setter

    public XNode getDslNode() {
        return this._dslNode;
    }

    public void setDslNode(XNode dslNode) {
        this._dslNode = dslNode;
    }

    // >>>>>>>>>>>>>>>

    private static class XplTagCompiler implements IXplTagCompiler {
        private static final List<String> TAGS = List.of(TAG_NAME_IF,
                                                         TAG_NAME_CHOOSE,
                                                         TAG_NAME_FOR,
                                                         TAG_NAME_WHEN,
                                                         TAG_NAME_OTHERWISE);

        private final IXplTagCompiler compiler;

        private XplTagCompiler(IXplTagCompiler compiler) {
            this.compiler = compiler;
        }

        @Override
        public Expression parseTag(XNode node, IXplCompiler cp, IXLangCompileScope scope) {
            cleanNode(node);
            if (this.compiler instanceof ChooseTagCompiler) {
                node.getChildren().forEach(this::cleanNode);
            } //
            else if (this.compiler instanceof ForTagCompiler) {
                String indexName = node.attrText(INDEX_NAME, "_forIndex_");
                if (!node.hasAttr(INDEX_NAME)) {
                    node.setAttr(INDEX_NAME, indexName);
                }

                patchXuiNameInFor(node, indexName, false);
            }

            return this.compiler.parseTag(node, cp, scope);
        }

        private void cleanNode(XNode node) {
            node.removeAttr(ATTR_NAME_XUI_NAME);
        }

        /** 为确保 for 循环中的节点唯一标识的唯一性，需要在原始的标识中添加循环序号变量 */
        private void patchXuiNameInFor(XNode node, String indexName, boolean ignoreFor) {
            if (ignoreFor && TAG_NAME_FOR.equals(node.getTagName())) {
                return;
            }

            for (XNode child : node.getChildren()) {
                String childTagName = child.getTagName();

                if (TAGS.contains(childTagName)) {
                    patchXuiNameInFor(child, indexName, true);
                } else {
                    String xuiName = child.attrText(ATTR_NAME_XUI_NAME);
                    child.setAttr(ATTR_NAME_XUI_NAME_RAW, xuiName);
                    child.setAttr(ATTR_NAME_XUI_NAME, xuiName + "_${" + indexName + "}");
                }
            }
        }
    }
}
