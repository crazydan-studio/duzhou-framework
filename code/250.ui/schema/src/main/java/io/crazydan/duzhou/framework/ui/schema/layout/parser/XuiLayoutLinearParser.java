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

package io.crazydan.duzhou.framework.ui.schema.layout.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crazydan.duzhou.framework.commons.StringHelper;
import io.crazydan.duzhou.framework.commons.TextScannerHelper;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponentLayoutLinear;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutProps;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSize;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.text.tokenizer.TextScanner;

import static io.crazydan.duzhou.framework.commons.TextScannerHelper.consumeBetweenPairChars;
import static io.crazydan.duzhou.framework.commons.TextScannerHelper.consumeUntilPosNotChanged;
import static io.crazydan.duzhou.framework.commons.TextScannerHelper.extractBetweenPairChars;
import static io.crazydan.duzhou.framework.commons.TextScannerHelper.moveToValidCharInLine;
import static io.crazydan.duzhou.framework.commons.TextScannerHelper.skipBlankAndConsumeInLine;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_NOT_ALLOW_SPACES_AFTER_ALIGN_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_NO_END_MARK_FOR_TABLE_CELL;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_NO_PROP_VALUE_SPECIFIED;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_UNKNOWN_LINEAR_MODE;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_UNKNOWN_MARK;
import static io.nop.commons.util.StringHelper.isSpaceInLine;
import static io.nop.xlang.XLangErrors.ARG_VALUE;

/**
 * 线性布局解析器
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-11
 */
public class XuiLayoutLinearParser {
    private final XuiComponentLayoutLinear.Mode mode;

    public XuiLayoutLinearParser(XuiComponentLayoutLinear.Mode mode) {
        this.mode = mode;
    }

    public XuiLayoutNode parseFromText(SourceLocation loc, String text) {
        // Note: 若是布局的根节点未指定子节点，则子组件均会被视为独立的布局节点，统一按行/列模式进行布局
        XuiLayoutNode root = null;
        switch (this.mode) {
            case column: {
                root = XuiLayoutNode.column();
                break;
            }
            case row: {
                root = XuiLayoutNode.row();
                break;
            }
        }
        if (root == null) {
            throw new NopException(ERR_LAYOUT_LINEAR_UNKNOWN_LINEAR_MODE).param(ARG_VALUE, this.mode);
        }

        // Note: 根节点尺寸始终与上层容器的尺寸相同
        root.setWidth(XuiLayoutSize.match_parent());
        root.setHeight(XuiLayoutSize.match_parent());

        TextScanner sc = TextScanner.fromString(loc, text);
        List<XuiLayoutNode> children = parseNodes(sc);
        if (!sc.isEnd()) {
            throw sc.newError(ERR_LAYOUT_LINEAR_UNKNOWN_MARK).param(ARG_VALUE, (char) sc.cur);
        }

        if (children.size() == 1) {
            XuiLayoutNode child = children.get(0);

            switch (child.getType()) {
                case row:
                case column:
                    // 去掉多余的嵌套
                    if (child.getChildren().size() == 1) {
                        children = child.getChildren();
                    }
                    break;
            }
        }

        root.addChildren(children);

        adjustNodeChildren(root);

        return root;
    }

    /** 解析布局节点 */
    private List<XuiLayoutNode> parseNodes(TextScanner sc) {
        List<XuiLayoutNode> nodes = new ArrayList<>();

        sc.skipBlank();
        consumeUntilPosNotChanged(sc, null, () -> {
            if (sc.cur == '|') {
                return parseTable(sc);
            } else {
                return parseRow(sc);
            }
        }, (node) -> {
            if (node != null) {
                nodes.add(node);
            }

            // Note: 对行的解析结束可能发生在嵌套结束符上，
            // 因此，不能直接跳过行
            sc.skipBlank();
        });

        return nodes;
    }

    /**
     * 解析行，其中，嵌套节点和配置参数列表可为多行文本
     * <pre>
     * [a1] [b1] [c1]
     * </pre>
     * <pre>
     * // 在同一行内的嵌套布局
     * {
     *     [a1]
     *     [a2]
     * } [b1] [c1]
     * </pre>
     * <pre>
     * // 在同一行内的嵌套布局
     * {
     *     [a1]
     *     [a2]
     * } {
     *     [b1]
     *     [b2]
     * }
     * </pre>
     */
    private XuiLayoutNode parseRow(TextScanner sc) {
        XuiLayoutNode row = XuiLayoutNode.row();

        // Note: 对行内节点的解析，一定在明确的行尾结束（嵌套布局将作为一个整体进行解析）
        consumeUntilPosNotChanged(sc, TextScannerHelper::isLineBreak, () -> parseNode(sc), (node) -> {
            if (node == null) {
                return;
            }

            // 行内节点默认左上角对齐
            if (node.getAlign().horizontal == null) {
                node.setAlign(node.getAlign().withHorizontal(XuiLayoutAlign.Direction.start));
            }
            if (node.getAlign().vertical == null) {
                node.setAlign(node.getAlign().withVertical(XuiLayoutAlign.Direction.start));
            }

            row.addChild(node);
        });

        return row.hasChild() ? row : null;
    }

    /**
     * 解析表格
     * <pre>
     * | [a1] [a2] | [b1] |
     * |           | [c1] |
     * </pre>
     * <pre>
     * | {
     *     [a1]
     *     [a2]
     * } | [b1] |
     * </pre>
     * <pre>
     * | {
     *     | [a1] | [b1] |
     *     | [c1] | [d1] |
     * } | [b1] |
     * </pre>
     * <pre>
     * // 设置单元格尺寸
     * | (width:100px) | (width:300px) |
     * | [a1]> | <[b1]> |
     * </pre>
     * <p/>
     */
    private XuiLayoutNode parseTable(TextScanner sc) {
        XuiLayoutNode table = XuiLayoutNode.table();

        consumeUntilPosNotChanged(sc, (s) -> s.cur != '|', () -> parseTableRow(sc), (row) -> {
            if (row != null) {
                // 表格行宽度自适应 table 宽度
                row.setWidth(XuiLayoutSize.match_parent());

                table.addChild(row);
            }

            // Note: 遇到非 | 字符时，对表格的解析便会结束，因此，不能直接跳过行
            sc.skipBlank();
        });

        return table.hasChild() ? table : null;
    }

    /**
     * 解析表格行
     * <p/>
     * 注：需确保 {@link TextScanner} 的当前位置在标记符 <code>|</code> 上
     */
    private XuiLayoutNode parseTableRow(TextScanner sc) {
        List<XuiLayoutNode> cells = new ArrayList<>();

        consumeUntilPosNotChanged(sc, (s) -> s.cur != '|', () -> parseNodesInTableCell(sc), (children) -> {
            XuiLayoutNode cell = null;
            if (children.size() == 1) {
                // Note: 其依然为 row 或 table 类型节点
                cell = children.get(0);
            } else if (children.size() > 1) {
                cell = XuiLayoutNode.row(children);
            }

            if (cell == null) {
                // 空白单元格：用于占位
                cell = XuiLayoutNode.space();
            }

            // 表格单元格节点始终占满整个单元格
            cell.setWidth(XuiLayoutSize.match_parent());
            cell.setHeight(XuiLayoutSize.match_parent());

            cells.add(cell);
        });

        // Note: 解析单元格时，必然会在尾部添加一个多余的空白单元格
        if (!cells.isEmpty()) {
            cells.remove(cells.size() - 1);
        }

        return cells.isEmpty() ? null : XuiLayoutNode.row(cells);
    }

    /**
     * 解析表格单元格中的节点（两个相邻 | 之间的部分）
     * <pre>
     * | [a1] [a2] |
     * </pre>
     * <pre>
     * | {
     *     | [a1] | [a2] |
     *     | [b1] | [b2] |
     * } |
     * </pre>
     * <p/>
     * 注：被嵌套的表格只能在嵌套布局 <code>{}</code> 内定义
     */
    private List<XuiLayoutNode> parseNodesInTableCell(TextScanner sc) {
        List<XuiLayoutNode> nodes = new ArrayList<>();

        sc.consume('|');

        consumeUntilPosNotChanged(sc,
                                  // 到达单元格结束符号，则停止提取，但不跳过结束符号，以便于识别下一个单元格
                                  (s) -> s.cur == '|', //
                                  () -> parseRow(sc), //
                                  (node) -> {
                                      if (node != null) {
                                          nodes.add(node);
                                      }
                                  });

        if (sc.cur != '|' && !nodes.isEmpty()) {
            throw sc.newError(ERR_LAYOUT_LINEAR_NO_END_MARK_FOR_TABLE_CELL);
        }

        return nodes;
    }

    /**
     * 解析布局节点（其将推动 TextScanner 游标后移）
     * <pre>
     * ^[abc]v
     * </pre>
     * <pre>
     * ^<[abc]>v
     * </pre>
     * <pre>
     * ^<[]>v
     * </pre>
     * <pre>
     * ^<>v
     * </pre>
     * <pre>
     * v>(width:100px,height:200px)<^
     * </pre>
     * <pre>
     * ^{<[abc] [def]>}
     * </pre>
     */
    private XuiLayoutNode parseNode(TextScanner sc) {
        moveToValidCharInLine(sc);
        if (sc.isEnd()) {
            return null;
        }

        // 解析起始方向的对齐位置
        XuiLayoutAlign.Direction[] startAlign = parseAlign(sc);

        if (isSpaceInLine(sc.cur)) {
            throw sc.newError(ERR_LAYOUT_LINEAR_NOT_ALLOW_SPACES_AFTER_ALIGN_MARK);
        }

        XuiLayoutNode node = null;
        // 解析组件匹配模式
        if (sc.cur == '[') {
            String text = extractBetweenPairChars(sc, '[', ']', ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK);

            if (text != null) {
                node = XuiLayoutNode.item(text);
            }
        } else if (sc.cur == '{') {
            List<XuiLayoutNode> children = new ArrayList<>();
            consumeBetweenPairChars(sc, '{', '}', ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK, () -> {
                List<XuiLayoutNode> nodes = parseNodes(sc);
                children.addAll(nodes);
            });

            if (children.size() == 1) {
                node = children.get(0);
            } else if (children.size() > 1) {
                node = XuiLayoutNode.column(children);
            }
        }

        // 解析布局配置参数列表
        if (sc.cur == '(') {
            XuiLayoutProps props = parseProps(sc);

            if (props != null) {
                if (node == null) {
                    node = XuiLayoutNode.space();
                }
                node.setProps(props);
            }
        }

        // 解析终止方向的对齐位置：终止方向的对齐符号必须紧挨节点，不能有空白
        XuiLayoutAlign.Direction[] endAlign = parseAlign(sc);

        // 确定尺寸设置和对齐方向
        XuiLayoutSize width = XuiLayoutSize.wrap_content();
        XuiLayoutSize height = XuiLayoutSize.wrap_content();
        XuiLayoutAlign.Direction[] align = new XuiLayoutAlign.Direction[] {
                null, null
        };

        if (startAlign[0] == XuiLayoutAlign.Direction.start && endAlign[0] == XuiLayoutAlign.Direction.end) {
            width = XuiLayoutSize.fill_remains();
        } else if (startAlign[0] == XuiLayoutAlign.Direction.end && endAlign[0] == XuiLayoutAlign.Direction.start) {
            align[0] = XuiLayoutAlign.Direction.center;
        } else if (endAlign[0] == XuiLayoutAlign.Direction.end) {
            align[0] = XuiLayoutAlign.Direction.end;
        }

        if (startAlign[1] == XuiLayoutAlign.Direction.start && endAlign[1] == XuiLayoutAlign.Direction.end) {
            height = XuiLayoutSize.fill_remains();
        } else if (startAlign[1] == XuiLayoutAlign.Direction.end && endAlign[1] == XuiLayoutAlign.Direction.start) {
            align[1] = XuiLayoutAlign.Direction.center;
        } else if (endAlign[1] == XuiLayoutAlign.Direction.end) {
            align[1] = XuiLayoutAlign.Direction.end;
        }

        // Note: 仅占满剩余空间的空白占位才是有效的
        if (node == null) {
            if (width.type == XuiLayoutSize.Type.fill_remains //
                || height.type == XuiLayoutSize.Type.fill_remains //
            ) {
                node = XuiLayoutNode.space();
            }
        }

        if (node == null) {
            return null;
        }

        node.setWidth(width);
        node.setHeight(height);
        node.setAlign(XuiLayoutAlign.create(align[0], align[1]));

        return node;
    }

    /** @return <code>[horizontal direction, vertical direction]</code> */
    private XuiLayoutAlign.Direction[] parseAlign(TextScanner sc) {
        XuiLayoutAlign.Direction[] align = new XuiLayoutAlign.Direction[] { null, null };

        do {
            switch (sc.cur) {
                case '<': {
                    if (align[0] == XuiLayoutAlign.Direction.start) {
                        throw sc.newError(ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK).param(ARG_VALUE, (char) sc.cur);
                    }
                    if (align[0] != null) {
                        return align;
                    }

                    align[0] = XuiLayoutAlign.Direction.start;
                    sc.next();
                    break;
                }
                case '>': {
                    if (align[0] == XuiLayoutAlign.Direction.end) {
                        throw sc.newError(ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK).param(ARG_VALUE, (char) sc.cur);
                    }
                    if (align[0] != null) {
                        return align;
                    }

                    align[0] = XuiLayoutAlign.Direction.end;
                    sc.next();
                    break;
                }
                case '^': {
                    if (align[1] == XuiLayoutAlign.Direction.start) {
                        throw sc.newError(ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK).param(ARG_VALUE, (char) sc.cur);
                    }
                    if (align[1] != null) {
                        return align;
                    }

                    align[1] = XuiLayoutAlign.Direction.start;
                    sc.next();
                    break;
                }
                case 'v': {
                    if (align[1] == XuiLayoutAlign.Direction.end) {
                        throw sc.newError(ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK).param(ARG_VALUE, (char) sc.cur);
                    }
                    if (align[1] != null) {
                        return align;
                    }

                    align[1] = XuiLayoutAlign.Direction.end;
                    sc.next();
                    break;
                }
                default: {
                    return align;
                }
            }
        } while (!sc.isEnd());

        return align;
    }

    /**
     * 解析配置参数列表（类 JSON 形式）
     * <pre>
     * (gap: 1em, padding: { left: .5em, })
     * </pre>
     * <p/>
     * 注：需确保 {@link TextScanner} 的当前位置在标记符 <code>(</code> 上
     */
    private XuiLayoutProps parseProps(TextScanner sc) {
        Map<String, Object> props = parseProps(sc, '(', ')');

        return props.isEmpty() ? null : new XuiLayoutProps(props);
    }

    /**
     * 解析配置参数列表（类 JSON 形式）
     * <pre>
     * (gap: 1em)
     * </pre>
     * <pre>
     * { left: 1em, right: 1em }
     * </pre>
     * <p/>
     * 注：需确保 {@link TextScanner} 的当前位置在标记符 <code>leftMark</code> 上
     */
    private Map<String, Object> parseProps(TextScanner sc, char leftMark, char rightMark) {
        Map<String, Object> props = new HashMap<>();

        consumeBetweenPairChars(sc, leftMark, rightMark, ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK, () -> {
            // 读取参数名
            String name = sc.nextConfigVar();
            skipBlankAndConsumeInLine(sc, ':');

            moveToValidCharInLine(sc);
            if (sc.cur == '{') {
                // 解析嵌套结构的配置参数
                Map<String, Object> value = parseProps(sc, '{', '}');
                props.put(name, value);
            } else {
                String value;
                if (sc.startsWith("${")) {
                    // 解析 ${xxx} 表达式
                    sc.next(2);
                    sc.skipBlankInLine();

                    value = sc.nextConfigVar();
                    skipBlankAndConsumeInLine(sc, '}');

                    value = "${" + value + "}";
                } else {
                    value = sc.nextUntil(s -> s.cur == ',' || s.cur == ')', true, ",").trim().toString();
                    if (sc.cur == ',') {
                        sc.next();
                    }
                }

                if (StringHelper.isBlank(value)) {
                    throw sc.newError(ERR_LAYOUT_LINEAR_NO_PROP_VALUE_SPECIFIED).param(ARG_VALUE, name);
                }
                props.put(name, value);
            }
        });

        return props;
    }

    private void adjustNodeChildren(XuiLayoutNode node) {
        node.getChildren().forEach((child) -> {
            if (child.getChildren().size() != 1) {
                return;
            }

            switch (node.getType()) {
                case row:
                case column: {
                    break;
                }
                default:
                    return;
            }

            XuiLayoutNode newChild = child.getChildren().get(0);
            switch (newChild.getType()) {
                case row:
                case column:
                case table: {
                    break;
                }
                default:
                    return;
            }

            // 提升嵌套的唯一子节点
            // Note: 嵌套节点在只有一个子节点时，在其上配置将作用在该唯一子节点上，
            // 故而，不需要迁移配置
            node.replaceChild(child, newChild);
        });

        // 调整节点自适应尺寸设置
        node.getChildren().forEach((child) -> {
            switch (child.getType()) {
                case row:
                case column:
                case table:
                    break;
                default:
                    return;
            }

            switch (node.getType()) {
                case row: {
                    if (child.getHeight().type == XuiLayoutSize.Type.wrap_content //
                        && (child.getAlign() == null //
                            || child.getAlign().vertical == XuiLayoutAlign.Direction.start) //
                    ) {
                        child.setHeight(XuiLayoutSize.match_parent());
                    }
                    break;
                }
                case column: {
                    if (child.getWidth().type == XuiLayoutSize.Type.wrap_content //
                        && (child.getAlign() == null //
                            || child.getAlign().horizontal == XuiLayoutAlign.Direction.start) //
                    ) {
                        child.setWidth(XuiLayoutSize.match_parent());
                    }
                    break;
                }
            }

            adjustNodeChildren(child);
        });
    }
}
