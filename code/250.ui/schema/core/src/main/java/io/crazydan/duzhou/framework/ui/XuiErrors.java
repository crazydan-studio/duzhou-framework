/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

package io.crazydan.duzhou.framework.ui;

import io.nop.api.core.exceptions.ErrorCode;

import static io.crazydan.duzhou.framework.commons.TextScannerHelper.ARG_LEFT_PAIR;
import static io.crazydan.duzhou.framework.commons.TextScannerHelper.ARG_RIGHT_PAIR;
import static io.crazydan.duzhou.framework.ui.XuiConstants.ATTR_NAME_XUI_SLOT;
import static io.nop.api.core.exceptions.ErrorCode.define;
import static io.nop.xlang.XLangErrors.ARG_ALLOWED_VALUES;
import static io.nop.xlang.XLangErrors.ARG_DEF_LOC;
import static io.nop.xlang.XLangErrors.ARG_NAME;
import static io.nop.xlang.XLangErrors.ARG_NAMES;
import static io.nop.xlang.XLangErrors.ARG_OPTIONS;
import static io.nop.xlang.XLangErrors.ARG_PATH;
import static io.nop.xlang.XLangErrors.ARG_PROP_NAME;
import static io.nop.xlang.XLangErrors.ARG_REF_NAME;
import static io.nop.xlang.XLangErrors.ARG_TAG1;
import static io.nop.xlang.XLangErrors.ARG_TAG2;
import static io.nop.xlang.XLangErrors.ARG_TAG_NAME;
import static io.nop.xlang.XLangErrors.ARG_VALUE;
import static io.nop.xlang.XLangErrors.ARG_VAR_DECL1;
import static io.nop.xlang.XLangErrors.ARG_VAR_DECL2;
import static io.nop.xlang.XLangErrors.ARG_VAR_NAME;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-09
 */
public interface XuiErrors {
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 数据域
    ErrorCode ERR_DOMAIN_TYPE_INVALID_FORMAT = //
            define("duzhou.err.xui.domain-type.invalid-format",
                   "无效的格式 [{" + ARG_VALUE + "}]，其格式必须为 [{" + ARG_ALLOWED_VALUES + "}] 形式",
                   ARG_VALUE,
                   ARG_ALLOWED_VALUES);
    ErrorCode ERR_DOMAIN_TYPE_INVALID_OPTIONS = //
            define("duzhou.err.xui.domain-type.invalid-options",
                   "类型 [{" + ARG_NAME + "}] 的扩展选项 [{" + ARG_VALUE + "}] 不在选项列表 [{" + ARG_OPTIONS + "}] 中",
                   ARG_NAME,
                   ARG_VALUE,
                   ARG_OPTIONS);

    ErrorCode ERR_DOMAIN_TYPE_UNKNOWN_SIZE = //
            define("duzhou.err.xui.domain-type.unknown-size",
                   "未识别的尺寸数据 [{" + ARG_VALUE + "}]，仅 [{" + ARG_NAMES + "}] 才是有效的尺寸单位",
                   ARG_VALUE,
                   ARG_NAMES);
    // >>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<<<<<<<<<<<<<<<<<<<< 布局
    ErrorCode ERR_LAYOUT_LINEAR_UNKNOWN_LINEAR_MODE = //
            define("duzhou.err.xui.layout-linear.unknown-linear-mode",
                   "未知的线性布局模式 [{" + ARG_VALUE + "}]",
                   ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_UNKNOWN_MARK = //
            define("duzhou.err.xui.layout-linear.unknown-mark", //
                   "未知的标记符 '{" + ARG_VALUE + "}'", ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_NOT_ALLOW_SPACES_AFTER_ALIGN_MARK = //
            define("duzhou.err.xui.layout-linear.not-allow-spaces-after-align-mark",
                   "在对齐方向标记符（<>^v）之后不能有空白字符");
    ErrorCode ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK = //
            define("duzhou.err.xui.layout-linear.duplicated-align-mark",
                   "存在重复的对齐标记符 '{" + ARG_VALUE + "}'",
                   ARG_VALUE);
    ErrorCode ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK = //
            define("duzhou.err.xui.layout-linear.no-right-mark-for-left-mark",
                   "没有为配对的左标记符 '{" + ARG_LEFT_PAIR + "}' 指定对应的右标记符 '{" + ARG_RIGHT_PAIR + "}'",
                   ARG_LEFT_PAIR,
                   ARG_RIGHT_PAIR);
    ErrorCode ERR_LAYOUT_LINEAR_NO_END_MARK_FOR_TABLE_CELL = //
            define("duzhou.err.xui.layout-linear.no-end-mark-for-table-cell",
                   "没有为表格单元格指定相应的结束标记符 '|'");
    ErrorCode ERR_LAYOUT_LINEAR_NO_PROP_VALUE_SPECIFIED = //
            define("duzhou.err.xui.layout-linear.no-prop-value-specified",
                   "没有为配置参数 '{" + ARG_VALUE + "}' 指定值",
                   ARG_VALUE);
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<< 组件
    ErrorCode ERR_COMPONENT_INVALID_TAG_NAME = //
            define("duzhou.err.ui.component.invalid-tag-name",
                   ("组件标签名 [{" + ARG_TAG_NAME + "}] 不符合规范。")
                   + "其须为字母、数字、下划线组成的驼峰形式，且首字母必须大写，"
                   + "如 Button、Button_Ext",
                   ARG_TAG_NAME);
    ErrorCode ERR_COMPONENT_MULTIPLE_SAME_NAME_SLOT_NOT_ALLOWED = //
            define("duzhou.err.ui.component.multiple-same-name-slot-not-allowed",
                   "不允许在 <{" + ARG_TAG_NAME + "}/> 标签中定义多个名字相同的 <slot name=\"{" + ARG_NAME + "}\"/>",
                   ARG_TAG_NAME,
                   ARG_NAME);
    ErrorCode ERR_COMPONENT_MULTIPLE_SAME_XUI_SLOT_NOT_ALLOWED = //
            define("duzhou.err.ui.component.multiple-same-xui-slot-not-allowed",
                   "不允许在 <{"
                   + ARG_TAG_NAME
                   + "}/> 标签中定义多个 ["
                   + ATTR_NAME_XUI_SLOT
                   + "] 属性值（=\""
                   + ARG_VALUE
                   + "\"）相同的节点",
                   ARG_TAG_NAME,
                   ARG_VALUE);
    ErrorCode ERR_COMPONENT_SLOT_IN_DEPTH_NOT_ALLOWED = //
            define("duzhou.err.ui.component.slot-in-depth-not-allowed", //
                   "不允许在 <slot/> 标签内嵌套使用 <slot/>");
    ErrorCode ERR_COMPONENT_DSL_NODE_NOT_BOUND = //
            define("duzhou.err.ui.component.dsl-node-not-bound", //
                   "组件未与其 XNode 节点绑定，建议在 xdef 元模型中的 <xdef:post-parse/> 脚本中做全局自动绑定，如：_dsl_model.setDslNode(_dsl_root)");
    ErrorCode ERR_COMPONENT_TAG_COMPONENT_NOT_IMPORTED = //
            define("duzhou.err.ui.component.tag-component-not-imported", //
                   "标签 <{" + ARG_TAG_NAME + "}/> 对应的组件未通过 <import/> 显式导入", ARG_TAG_NAME);
    ErrorCode ERR_COMPONENT_TAG_COMPONENT_LOADING_FAILED = //
            define("duzhou.err.ui.component.tag-component-loading-failed", //
                   "标签 <{" + ARG_TAG_NAME + "}/> 对应的组件 [{" + ARG_PATH + "}] 加载失败", ARG_TAG_NAME, ARG_PATH);
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<< 样式
    ErrorCode ERR_STYLES_INVALID_TAG_NAME = //
            define("duzhou.err.ui.styles.invalid-tag-name",
                   ("样式标签名 [{" + ARG_TAG_NAME + "}] 不符合规范。")
                   + "其须由小写字母、数字、下划线、短横线组成，开头必须为字母，且短横线不能连续出现"
                   + "如 button-blue、layout__root",
                   ARG_TAG_NAME);
    ErrorCode ERR_STYLES_PATCH_NODE_NOT_ALLOWED = //
            define("duzhou.err.ui.styles.patch-node-not-allowed",
                   "复合样式 <{"
                   + ARG_TAG1
                   + "}/> 只允许包含最多一层结构，因此，在 <{"
                   + ARG_TAG2
                   + "}/> 中不能包含子结构",
                   ARG_TAG1,
                   ARG_TAG2);
    ErrorCode ERR_STYLES_UNDEFINED_STYLE = //
            define("duzhou.err.ui.styles.undefined-style",
                   "未在样式库 {" + ARG_DEF_LOC + "} 或其基础库中定义样式 <{" + ARG_TAG_NAME + "}/>",
                   ARG_DEF_LOC,
                   ARG_TAG_NAME);
    ErrorCode ERR_STYLES_UNDEFINED_STYLE_PROP = //
            define("duzhou.err.ui.styles.undefined-style-prop",
                   "在样式 {" + ARG_DEF_LOC + "} 上未定义属性 {" + ARG_PROP_NAME + "}",
                   ARG_DEF_LOC,
                   ARG_PROP_NAME);
    ErrorCode ERR_STYLES_UNDEFINED_REF_VAR = //
            define("duzhou.err.ui.styles.undefined-ref-var",
                   "在样式 {"
                   + ARG_DEF_LOC
                   + "} 上未定义属性 {"
                   + ARG_PROP_NAME
                   + "}，"
                   + "不能以 ${{"
                   + ARG_REF_NAME
                   + "}} 形式引用",
                   ARG_DEF_LOC,
                   ARG_PROP_NAME,
                   ARG_REF_NAME);
    ErrorCode ERR_STYLES_REF_VAR_NOT_MATCH_DEF_PROP = //
            define("duzhou.err.ui.styles.ref-var-not-match-def-prop",
                   "属性 {"
                   + ARG_PROP_NAME
                   + "} 的定义类型（={"
                   + ARG_VAR_DECL1
                   + "}）与其引用的变量 {"
                   + ARG_VAR_NAME
                   + "} 的类型（={"
                   + ARG_VAR_DECL2
                   + "}）不一致",
                   ARG_PROP_NAME,
                   ARG_VAR_DECL1,
                   ARG_VAR_NAME,
                   ARG_VAR_DECL2);
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
