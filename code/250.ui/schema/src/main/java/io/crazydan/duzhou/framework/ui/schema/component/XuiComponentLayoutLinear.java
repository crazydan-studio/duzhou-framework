package io.crazydan.duzhou.framework.ui.schema.component;

import java.util.ArrayList;
import java.util.List;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentLayoutLinear;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSize;
import io.nop.api.core.util.Guard;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.text.tokenizer.TextScanner;
import io.nop.commons.util.StringHelper;

public class XuiComponentLayoutLinear extends _XuiComponentLayoutLinear {

    public XuiComponentLayoutLinear() {
    }

    public XuiLayoutNode create() {
        SourceLocation loc = getLocation();

        return parse(loc, getMode(), getValue());
    }

    public static XuiLayoutNode parse(SourceLocation loc, String mode, String text) {
        // Note: 若是布局的根节点未指定子节点，则子组件均会被视为独立的布局节点，统一按行/列模式进行布局
        XuiLayoutNode root;
        switch (mode) {
            case "column": {
                root = XuiLayoutNode.column();
                break;
            }
            case "row": {
                root = XuiLayoutNode.row();
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown linear layout mode '" + mode + "'");
            }
        }

        // Note: 根节点尺寸始终与上层容器的尺寸相同
        root.setHeight(XuiLayoutSize.match_parent());
        root.setWidth(XuiLayoutSize.match_parent());

        List<XuiLayoutNode> children = parseNodes(loc, text);
        root.addChildren(children);

        return root;
    }

    /** 解析布局节点 */
    private static List<XuiLayoutNode> parseNodes(SourceLocation loc, String text) {
        List<XuiLayoutNode> nodes = new ArrayList<>();

        TextScanner sc = TextScanner.fromString(loc, text);

        while (!sc.isEnd()) {
            sc.skipBlankInLine();

            if (sc.cur == '|') {
                XuiLayoutNode table = parseTable(sc);
                if (table != null) {
                    nodes.add(table);
                }
            } else {
                XuiLayoutNode row = parseRow(sc);
                if (row != null) {
                    nodes.add(row);
                }
            }

            // 跳至下一行
            sc.skipLine();
        }

        return nodes;
    }

    /**
     * 解析行：嵌套节点和配置参数列表可为多行文本
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
    private static XuiLayoutNode parseRow(TextScanner sc) {
        XuiLayoutNode row = XuiLayoutNode.row();

        int pos = sc.pos;
        while (true) {
            sc.skipBlankInLine();
            if (sc.isEnd() || isLineBreak(sc)) {
                break;
            }

            XuiLayoutNode node = parseNode(sc);
            if (node != null) {
                row.addChild(node);
            }

            // 防止游标不动
            Guard.checkState(pos != sc.pos, "unknown mark '" + sc.cur + "'");
            pos = sc.pos;
        }

        return row.hasChild() ? row : null;
    }

    /**
     * 解析表格：
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
     */
    private static XuiLayoutNode parseTable(TextScanner sc) {
        XuiLayoutNode table = XuiLayoutNode.table();

        while (true) {
            sc.skipBlankInLine();
            if (sc.isEnd() || sc.cur != '|') {
                break;
            }

            XuiLayoutNode row = parseTableRow(sc);
            if (row != null) {
                table.addChild(row);
            }
        }

        return table.hasChild() ? table : null;
    }

    /**
     * 解析表格行
     * <p/>
     * 表格行解析完后，自动跳到下一行
     */
    private static XuiLayoutNode parseTableRow(TextScanner sc) {
        List<XuiLayoutNode> cells = new ArrayList<>();

        while (!sc.isEnd()) {
            sc.skipBlankInLine();

            if (isLineBreak(sc)) {
                // Note: 解析单元格时，必然会在尾部添加一个多余的空白单元格
                if (!cells.isEmpty()) {
                    cells.remove(cells.size() - 1);
                }

                // 跳至下一行
                sc.skipLine();
                break;
            }

            SourceLocation loc = sc.location();
            String text = extractTableCell(sc);

            XuiLayoutNode cell = null;
            if (text != null) {
                List<XuiLayoutNode> children = parseNodes(loc, text);
                if (children.size() == 1) {
                    cell = children.get(0);
                } else if (children.size() > 1) {
                    cell = XuiLayoutNode.row(children);
                }
            }

            if (cell != null) {
                // 空白单元格：用于占位
                cell = XuiLayoutNode.space();
            }
            cells.add(cell);
        }

        return cells.isEmpty() ? null : XuiLayoutNode.row(cells);
    }

    /**
     * 解析布局节点：
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
    private static XuiLayoutNode parseNode(TextScanner sc) {
        if (sc.isEnd()) {
            return null;
        }

        // 解析起始方向的对齐位置
        XuiLayoutAlign.Direction[] startAlign = parseAlign(sc);
        sc.skipBlankInLine();

        XuiLayoutNode node = null;
        // 解析组件匹配模式
        if (sc.cur == '[') {
            String text = extractBetweenMark(sc, '[', ']');

            if (text != null) {
                node = XuiLayoutNode.item(text);
            }
        } else if (sc.cur == '{') {
            String text = extractBetweenMark(sc, '{', '}');

            if (text != null) {
                List<XuiLayoutNode> children = parseNodes(sc.location(), text);

                if (!children.isEmpty()) {
                    node = XuiLayoutNode.column();

                    node.addChildren(children);
                }
            }
        }

        // 解析布局配置参数列表
        if (sc.cur == '(') {
            String text = extractBetweenMark(sc, '(', ')');

            // TODO 解析配置参数列表（类 JSON 形式）
            // TODO 参数列表不为空且 node 为 null，则创建空白节点
        }

        // 解析终止方向的对齐位置
        XuiLayoutAlign.Direction[] endAlign = parseAlign(sc);

        // 确定尺寸设置和对齐方向
        XuiLayoutSize width = XuiLayoutSize.wrap_content();
        XuiLayoutSize height = XuiLayoutSize.wrap_content();
        XuiLayoutAlign.Direction[] align = new XuiLayoutAlign.Direction[] { null, null };

        if (startAlign[0] == XuiLayoutAlign.Direction.start && endAlign[0] == XuiLayoutAlign.Direction.end) {
            width = XuiLayoutSize.fill_remains();
        } else if (startAlign[0] == XuiLayoutAlign.Direction.end && endAlign[0] == XuiLayoutAlign.Direction.start) {
            align[0] = XuiLayoutAlign.Direction.center;
        } else if (startAlign[0] == XuiLayoutAlign.Direction.start) {
            align[0] = XuiLayoutAlign.Direction.start;
        } else if (endAlign[0] == XuiLayoutAlign.Direction.end) {
            align[0] = XuiLayoutAlign.Direction.end;
        }

        if (startAlign[1] == XuiLayoutAlign.Direction.start && endAlign[1] == XuiLayoutAlign.Direction.end) {
            height = XuiLayoutSize.fill_remains();
        } else if (startAlign[1] == XuiLayoutAlign.Direction.end && endAlign[1] == XuiLayoutAlign.Direction.start) {
            align[1] = XuiLayoutAlign.Direction.center;
        } else if (startAlign[1] == XuiLayoutAlign.Direction.start) {
            align[1] = XuiLayoutAlign.Direction.start;
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
    private static XuiLayoutAlign.Direction[] parseAlign(TextScanner sc) {
        XuiLayoutAlign.Direction[] align = new XuiLayoutAlign.Direction[] { null, null };

        do {
            switch (sc.cur) {
                case '<': {
                    Guard.checkState(align[0] != XuiLayoutAlign.Direction.start,
                                     "Duplicated align direction '" + sc.cur + "'");
                    if (align[0] != null) {
                        return align;
                    }

                    align[0] = XuiLayoutAlign.Direction.start;
                    sc.next();
                    break;
                }
                case '>': {
                    Guard.checkState(align[0] != XuiLayoutAlign.Direction.end,
                                     "Duplicated align direction '" + sc.cur + "'");
                    if (align[0] != null) {
                        return align;
                    }

                    align[0] = XuiLayoutAlign.Direction.end;
                    sc.next();
                    break;
                }
                case '^': {
                    Guard.checkState(align[1] != XuiLayoutAlign.Direction.start,
                                     "Duplicated align direction '" + sc.cur + "'");
                    if (align[1] != null) {
                        return align;
                    }

                    align[1] = XuiLayoutAlign.Direction.start;
                    sc.next();
                    break;
                }
                case 'v': {
                    Guard.checkState(align[1] != XuiLayoutAlign.Direction.end,
                                     "Duplicated align direction '" + sc.cur + "'");
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
     * 提取标记符号之间的文本
     * <p/>
     * 提取完成后，<code>sc</code> 将跳到右侧标记符号的下一个位置
     */
    private static String extractBetweenMark(TextScanner sc, char leftMark, char rightMark) {
        StringBuilder sb = new StringBuilder();

        int pairs = 0;
        while (!sc.isEnd()) {
            if (sc.cur == leftMark) {
                pairs += 1;
            } else if (sc.cur == rightMark) {
                pairs -= 1;
            }

            // Note: 需要跳过开始和结尾的标记符号
            sc.next();

            if (pairs > 0 && !sc.isEnd()) {
                sb.append((char) sc.cur);
            } else {
                break;
            }
        }

        Guard.checkEquals(pairs, 0, "No right mark '" + rightMark + "' found for the left mark '" + leftMark + "'");

        return toString(sb);
    }

    /**
     * 提取表格单元格标记文本：
     * 从标记符号 <code>|</code> 开始，并以 <code>|</code> 结束，
     * 返回二者中间的文本，且游标位置保持在结束的 <code>|</code> 处
     * <p/>
     * 注意，被嵌套的表格只能在嵌套布局 <code>{}</code> 内定义
     */
    private static String extractTableCell(TextScanner sc) {
        StringBuilder sb = new StringBuilder();

        boolean cellExtracting = false;
        boolean cellExtracted = false;
        while (!sc.isEnd()) {
            if (cellExtracting) {
                // 到达单元格结束符号，则停止提取，且不跳过结束符号，以便于识别下一个单元格
                if (sc.cur == '|') {
                    cellExtracted = true;
                    break;
                }
                // 处理 | 到换行符之间的文本：只允许出现空白字符
                else if (isLineBreak(sc)) {
                    break;
                }
                // 单独提取嵌套节点，以避免嵌套表格影响外层表格的识别
                else if (sc.cur == '{') {
                    String text = extractBetweenMark(sc, '{', '}');
                    if (text != null) {
                        sb.append('{').append(text).append('}');
                    }

                    // Note: 上述提取过程已跳过右侧标记，不能再多跳
                    continue;
                } else {
                    sb.append(sc.cur);
                }
            }
            // 开始提取单元格
            else if (sc.cur == '|') {
                cellExtracting = true;
            }

            // 移动到下一个位置
            sc.next();
        }

        String text = toString(sb);

        Guard.checkState(cellExtracted || text == null, "No cell end mark '|' specified");

        return text;
    }

    private static String toString(StringBuilder sb) {
        return sb.length() == 0 ? null : StringHelper.emptyAsNull(sb.toString().trim());
    }

    private static boolean isLineBreak(TextScanner sc) {
        return sc.cur == '\r' || sc.cur == '\n';
    }
}
