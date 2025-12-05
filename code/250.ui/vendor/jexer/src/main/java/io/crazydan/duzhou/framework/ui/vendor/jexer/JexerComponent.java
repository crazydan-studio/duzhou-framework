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

package io.crazydan.duzhou.framework.ui.vendor.jexer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.crazydan.duzhou.framework.ui.XuiExpression;
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;
import io.crazydan.duzhou.framework.ui.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.layout.XuiLayoutGap;
import io.crazydan.duzhou.framework.ui.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.layout.XuiLayoutProps;
import io.crazydan.duzhou.framework.ui.layout.XuiLayoutSize;
import io.crazydan.duzhou.framework.ui.layout.XuiLayoutSpacing;
import io.crazydan.duzhou.framework.ui.vendor.core.XuiComponentTreeNode;
import io.crazydan.duzhou.framework.ui.vendor.jexer.component.JexerBox;
import io.nop.api.core.exceptions.NopException;
import io.nop.xlang.ast.Literal;
import jexer.TWidget;

import static io.crazydan.duzhou.framework.ui.layout.XuiLayoutSize.Type.fill_remains;
import static io.crazydan.duzhou.framework.ui.layout.XuiLayoutSize.Type.wrap_content;
import static io.crazydan.duzhou.framework.ui.vendor.core.XuiVendorErrors.ERR_COMPONENT_NATIVE_NOT_REGISTERED;
import static io.nop.xlang.XLangErrors.ARG_NAME;

/**
 * 连接 {@link XuiComponentTreeNode} 与 {@link TWidget} 的中间模型
 * <p/>
 * Note:<ul>
 * <li>组件树 {@link XuiComponentTreeNode} 中的节点均为确定的可显示组件，
 * 其与布局节点 {@link XuiLayoutNode} 不是按层级对应的；
 * </li>
 * <li>在布局树 {@link XuiComponentTreeNode#layout}
 * 中的布局节点（{@link XuiLayoutNode#getType() 类型}不是
 * {@link XuiLayoutNode.Type#item} 的节点）对应容器组件 {@link JexerBox} 以控制内部组件的位置和尺寸；
 * </li>
 * </ul>
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-28
 */
public abstract class JexerComponent {
    public interface Creator {
        JexerComponent create(JexerComponent parent, XuiComponentTreeNode node);
    }

    private static final XuiLayoutNode LAYOUT_ANY_ITEM = XuiLayoutNode.item(null, ".+");

    private final TWidget widget;

    public final XuiLayoutNode layout;
    public final List<JexerComponent> children;

    protected JexerComponent(JexerComponent parent, XuiComponentTreeNode node) {
        this(parent.widget, node);
    }

    protected JexerComponent(JexerComponent parent, XuiComponentTreeNode node, XuiLayoutNode layout) {
        this(parent.widget, node, layout);
    }

    protected JexerComponent(TWidget parent, XuiComponentTreeNode node) {
        this( //
              parent, node, //
              node != null && node.layout != null //
              ? node.layout.getRoot() //
              : XuiLayoutNode.column(null, List.of(LAYOUT_ANY_ITEM)) //
        );
    }

    protected JexerComponent(TWidget parent, XuiComponentTreeNode node, XuiLayoutNode layout) {
        this.widget = createWidget(parent, node);

        this.layout = layout;
        this.children = layoutChildren(node, layout);
    }

    public int getWidth() {
        if (this.layout.getProps().getWidth().type == wrap_content) {
            if (this.children.size() == 1) {
                return this.children.get(0).getWidth();
            }
        }
        return this.widget.getWidth();
    }

    public void setWidth(int width) {
        this.widget.setWidth(width);
    }

    public int getHeight() {
        if (this.layout.getProps().getHeight().type == wrap_content) {
            if (this.children.size() == 1) {
                return this.children.get(0).getHeight();
            }
        }
        return this.widget.getHeight();
    }

    public void setHeight(int height) {
        this.widget.setHeight(height);
    }

    public void setX(int x) {
        this.widget.setX(x);
    }

    public void setY(int y) {
        this.widget.setY(y);
    }

    /**
     * 响应视口（可见区域）尺寸变化，重新布局子节点的位置和尺寸
     *
     * @param viewportWidth
     *         视口宽度
     * @param viewportHeight
     *         视口高度
     */
    public void onResize(int viewportWidth, int viewportHeight) {
        int rowGapCount = -1;
        int colGapCount = -1;
        int rowFillRemainsCount = 0;
        int colFillRemainsCount = 0;
        int contentWidth = 0;
        int contentHeight = 0;

        Map<XuiLayoutAlign.Direction, List<JexerComponent>> alignChildren = new HashMap<>();
        // 计算内容尺寸
        for (JexerComponent child : this.children) {
            XuiLayoutProps childLayoutProps = child.layout.getProps();

            int childWidth = 0;
            int childHeight = 0;
            // Note: 仅 wrap_content 类型才有确定尺寸
            if (childLayoutProps.getWidth().type == wrap_content) {
                childWidth = child.getWidth();
            } else if (childLayoutProps.getWidth().type == fill_remains) {
                rowFillRemainsCount += 1;
            }
            if (childLayoutProps.getHeight().type == wrap_content) {
                childHeight = child.getHeight();
            } else if (childLayoutProps.getHeight().type == fill_remains) {
                colFillRemainsCount += 1;
            }

            // Note: 在行列布局内，任意对齐模式均需按顺序排列，不允许组件发生重叠
            switch (this.layout.getType()) {
                case row: {
                    rowGapCount += 1;
                    contentWidth += childWidth;
                    contentHeight = Math.max(contentHeight, childHeight);
                    break;
                }
                case column: {
                    colGapCount += 1;
                    contentWidth = Math.max(contentWidth, childWidth);
                    contentHeight += childHeight;
                    break;
                }
            }

            // Note: start/end 对齐的组件必须是相邻的，且 center 只针对仅含唯一子节点的情况。
            // 该约束由布局解析器处理，这里直接使用
            XuiLayoutAlign.Direction childRowDir = getLayoutRowAlign(childLayoutProps);
            XuiLayoutAlign.Direction childColDir = getLayoutColAlign(childLayoutProps);
            switch (this.layout.getType()) {
                case row: {
                    alignChildren.computeIfAbsent(childRowDir, (k) -> new ArrayList<>(this.children.size())).add(child);
                    break;
                }
                case column: {
                    alignChildren.computeIfAbsent(childColDir, (k) -> new ArrayList<>(this.children.size())).add(child);
                    break;
                }
            }
        }
        rowGapCount = Math.max(0, rowGapCount);
        colGapCount = Math.max(0, colGapCount);

        XuiLayoutProps layoutProps = this.layout.getProps();
        int layoutWidth = calculateLayoutSize(layoutProps.getWidth(), viewportWidth, contentWidth);
        int layoutHeight = calculateLayoutSize(layoutProps.getHeight(), viewportHeight, contentHeight);

        // 计算空白（间隔、边距）尺寸
        int rowGapSize = 0;
        int colGapSize = 0;
        XuiLayoutGap layoutGap = layoutProps.getGap();
        if (layoutGap != null) {
            rowGapSize = calculateSize(layoutGap.row, layoutWidth);
            colGapSize = calculateSize(layoutGap.col, layoutHeight);
        }
        int leftPaddingSize = 0;
        int rightPaddingSize = 0;
        int topPaddingSize = 0;
        int bottomPaddingSize = 0;
        XuiLayoutSpacing layoutPadding = layoutProps.getPadding();
        if (layoutPadding != null) {
            leftPaddingSize = calculateSize(layoutPadding.left, layoutWidth);
            rightPaddingSize = calculateSize(layoutPadding.right, layoutWidth);
            topPaddingSize = calculateSize(layoutPadding.top, layoutHeight);
            bottomPaddingSize = calculateSize(layoutPadding.bottom, layoutHeight);
        }

        int maxContentWidth = contentWidth;
        int maxContentHeight = contentHeight;
        if (layoutProps.getWidth().type == wrap_content) {
            layoutWidth = leftPaddingSize + contentWidth + rowGapSize * rowGapCount + rightPaddingSize;
        } else {
            maxContentWidth = //
                    Math.max(0, layoutWidth - rowGapSize * rowGapCount - (leftPaddingSize + rightPaddingSize));
        }
        if (layoutProps.getHeight().type == wrap_content) {
            layoutHeight = topPaddingSize + contentHeight + colGapSize * colGapCount + bottomPaddingSize;
        } else {
            maxContentHeight = //
                    Math.max(0, layoutHeight - colGapSize * colGapCount - (topPaddingSize + bottomPaddingSize));
        }

        setWidth(layoutWidth);
        setHeight(layoutHeight);

        // 计算留白填充组件的尺寸：多个组件均分留白空间
        int rowFillRemainsSize = rowFillRemainsCount > 0 //
                                 ? Math.max(0, (maxContentWidth - contentWidth) / rowFillRemainsCount) : 0;
        int colFillRemainsSize = colFillRemainsCount > 0 //
                                 ? Math.max(0, (maxContentHeight - contentHeight) / colFillRemainsCount) : 0;

        // 调整组件尺寸
        for (JexerComponent child : this.children) {
            XuiLayoutProps childLayoutProps = child.layout.getProps();

            int childViewportWidth = //
                    childLayoutProps.getWidth().type == fill_remains ? rowFillRemainsSize : maxContentWidth;
            int childViewportHeight = //
                    childLayoutProps.getHeight().type == fill_remains ? colFillRemainsSize : maxContentHeight;
            int childLayoutWidth = //
                    calculateLayoutSize(childLayoutProps.getWidth(), childViewportWidth, child.getWidth());
            int childLayoutHeight = //
                    calculateLayoutSize(childLayoutProps.getHeight(), childViewportHeight, child.getHeight());

            child.onResize(childLayoutWidth, childLayoutHeight);

            // 设置与布局方向垂直的方向上的组件偏移
            XuiLayoutAlign.Direction childRowDir = getLayoutRowAlign(childLayoutProps);
            XuiLayoutAlign.Direction childColDir = getLayoutColAlign(childLayoutProps);
            switch (this.layout.getType()) {
                case row: {
                    if (childRowDir == XuiLayoutAlign.Direction.center) {
                        int offset = //
                                calculateLayoutOffset(childRowDir, maxContentWidth, childLayoutWidth) + leftPaddingSize;
                        child.setX(offset);
                    }

                    int offset = //
                            calculateLayoutOffset(childColDir, maxContentHeight, childLayoutHeight) + topPaddingSize;
                    child.setY(offset);
                    break;
                }
                case column: {
                    if (childRowDir == XuiLayoutAlign.Direction.center) {
                        int offset = //
                                calculateLayoutOffset(childColDir, maxContentHeight, childLayoutHeight)
                                + topPaddingSize;
                        child.setY(offset);
                    }

                    int offset = //
                            calculateLayoutOffset(childRowDir, maxContentWidth, childLayoutWidth) + leftPaddingSize;
                    child.setX(offset);
                    break;
                }
            }
        }

        // 调整布局方向上的组件偏移
        switch (this.layout.getType()) {
            case row: {
                int startOffset = leftPaddingSize;
                List<JexerComponent> starts = alignChildren.getOrDefault(XuiLayoutAlign.Direction.start, List.of());
                for (JexerComponent start : starts) {
                    start.setX(startOffset);
                    startOffset += start.getWidth() + rowGapSize;
                }

                int endOffset = leftPaddingSize + maxContentWidth;
                List<JexerComponent> ends = alignChildren.getOrDefault(XuiLayoutAlign.Direction.end, List.of());
                Collections.reverse(ends);
                for (JexerComponent end : ends) {
                    endOffset -= end.getWidth();
                    end.setX(endOffset);
                    endOffset -= rowGapSize;
                }
                break;
            }
            case column: {
                int startOffset = topPaddingSize;
                List<JexerComponent> starts = alignChildren.getOrDefault(XuiLayoutAlign.Direction.start, List.of());
                for (JexerComponent start : starts) {
                    start.setY(startOffset);
                    startOffset += start.getHeight() + colGapSize;
                }

                int endOffset = topPaddingSize + maxContentHeight;
                List<JexerComponent> ends = alignChildren.getOrDefault(XuiLayoutAlign.Direction.end, List.of());
                Collections.reverse(ends);
                for (JexerComponent end : ends) {
                    endOffset -= end.getHeight();
                    end.setY(endOffset);
                    endOffset -= colGapSize;
                }
                break;
            }
        }
    }

    protected List<JexerComponent> layoutChildren(XuiComponentTreeNode node, XuiLayoutNode layout) {
        List<JexerComponent> children = new ArrayList<>();
        // Note: 布局子节点仅包含多层嵌套的布局节点，以及当前组件树的直接子节点
        for (XuiLayoutNode layoutChild : layout.getChildren()) {
            List<JexerComponent> list = layoutChild(this, node, layoutChild);
            children.addAll(list);
        }

        return Collections.unmodifiableList(children);
    }

    protected List<JexerComponent> layoutChild(JexerComponent parent, XuiComponentTreeNode node, XuiLayoutNode layout) {
        switch (layout.getType()) {
            case item: {
                // 匹配组件树直接子节点
                return node.children.stream() //
                                    .filter((n) -> layout.matched(n.key)) //
                                    .map((n) -> createComponent(parent, n, layout)) //
                                    .collect(Collectors.toList());
            }
            default: {
                JexerBox box = new JexerBox(parent, node, layout);
                return List.of(box);
            }
        }
    }

    protected abstract TWidget createWidget(TWidget parent, XuiComponentTreeNode node);

    protected static JexerComponent createComponent(
            JexerComponent parent, XuiComponentTreeNode node, XuiLayoutNode layout) {
        if (node.nativeName == null) {
            XuiLayoutNode newLayout = layout.cloneInstance();
            if (node.layout != null) {
                newLayout.addChildren(node.layout.getRoot().getChildren());
            } else {
                newLayout.addChild(LAYOUT_ANY_ITEM);
            }
            return new JexerBox(parent, node, newLayout);
        } //
        else {
            Creator creator = JexerComponentRegistry.get(node.nativeName);
            if (creator != null) {
                return creator.create(parent, node);
            } else {
                throw new NopException(ERR_COMPONENT_NATIVE_NOT_REGISTERED).param(ARG_NAME, node.nativeName);
            }
        }
    }

    /**
     * 对于 {@link XuiLayoutSize.Type#fill_remains fill_remains}，
     * 直接返回视口尺寸，而其具体值则由父节点决定
     */
    protected static int calculateLayoutSize(XuiLayoutSize size, int viewportSize, int contentSize) {
        switch (size.type) {
            case fill_remains:
            case match_parent: {
                return viewportSize;
            }
            case wrap_content: {
                return contentSize;
            }
            case with_specified: {
                return calculateSize(size.value, viewportSize);
            }
        }
        return 0;
    }

    protected static int calculateSize(XuiExpression<XuiSize> size, int refSize) {
        if (size != null && size.expr instanceof Literal) {
            XuiSize s = (XuiSize) ((Literal) size.expr).getValue();

            if (s != null) {
                switch (s.unit) {
                    case base: {
                        return (int) s.value;
                    }
                    case percent: {
                        return (int) (s.value * 0.01 * refSize);
                    }
                }
            }
        }
        return 0;
    }

    protected static int calculateLayoutOffset(XuiLayoutAlign.Direction dir, int viewportSize, int selfSize) {
        switch (dir) {
            case center: {
                return (viewportSize - selfSize) / 2;
            }
            case end: {
                return viewportSize - selfSize;
            }
        }
        return 0;
    }

    protected static XuiLayoutAlign.Direction getLayoutRowAlign(XuiLayoutProps props) {
        return props.getAlign() == null || props.getAlign().row == null
               ? XuiLayoutAlign.Direction.start
               : props.getAlign().row;
    }

    protected static XuiLayoutAlign.Direction getLayoutColAlign(XuiLayoutProps props) {
        return props.getAlign() == null || props.getAlign().col == null
               ? XuiLayoutAlign.Direction.start
               : props.getAlign().col;
    }
}
