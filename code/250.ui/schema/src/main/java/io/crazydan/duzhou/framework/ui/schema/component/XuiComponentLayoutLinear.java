package io.crazydan.duzhou.framework.ui.schema.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crazydan.duzhou.framework.ui.schema.component._gen._XuiComponentLayoutLinear;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutAlign;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutProps;
import io.crazydan.duzhou.framework.ui.schema.layout.XuiLayoutSize;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.util.SourceLocation;
import io.nop.commons.text.tokenizer.TextScanner;
import io.nop.commons.util.StringHelper;

import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ARG_LEFT_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ARG_RIGHT_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_DUPLICATED_ALIGN_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_NOT_ALLOW_SPACES_AFTER_ALIGN_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_NO_END_MARK_FOR_TABLE_CELL;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_UNKNOWN_LINEAR_MODE;
import static io.crazydan.duzhou.framework.ui.schema.XuiErrors.ERR_LAYOUT_LINEAR_UNKNOWN_MARK;
import static io.nop.xlang.XLangErrors.ARG_VALUE;

public class XuiComponentLayoutLinear extends _XuiComponentLayoutLinear {
    private XuiLayoutNode root;

    public enum Mode {
        column,
        row,
    }

    public XuiComponentLayoutLinear() {
    }

    @Override
    public String getType() {
        return get$type();
    }

    @Override
    public XuiLayoutNode getRoot() {
        if (this.root == null) {
            SourceLocation loc = getLocation();

            this.root = parse(loc, Mode.valueOf(getMode()), getValue());
        }
        return this.root;
    }

    /**
     * @param mode
     *         布局模式，枚举值见 <code>_vfs/dict/duzhou/ui/layout-linear-mode.dict.yaml</code>
     */
    public static XuiLayoutNode parse(SourceLocation loc, Mode mode, String text) {
        // Note: 若是布局的根节点未指定子节点，则子组件均会被视为独立的布局节点，统一按行/列模式进行布局
        XuiLayoutNode root = null;
        switch (mode) {
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
            throw new NopException(ERR_LAYOUT_LINEAR_UNKNOWN_LINEAR_MODE).param(ARG_VALUE, mode);
        }

        // Note: 根节点尺寸始终与上层容器的尺寸相同
        root.setWidth(XuiLayoutSize.match_parent());
        root.setHeight(XuiLayoutSize.match_parent());

        List<XuiLayoutNode> children = parseNodes(loc, text);
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

    private static void adjustNodeChildren(XuiLayoutNode node) {
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

    /** 解析布局节点 */
    private static List<XuiLayoutNode> parseNodes(SourceLocation loc, String text) {
        List<XuiLayoutNode> nodes = new ArrayList<>();

        TextScanner sc = TextScanner.fromString(loc, text);

        while (!sc.isEnd()) {
            moveToValidCharInLine(sc);

            if (sc.cur == '|') {
                XuiLayoutNode table = parseTable(sc);
                if (table != null) {
                    nodes.add(table);
                }

                // Note: 表格解析后会自动跳转到下一行
                continue;
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
            moveToValidCharInLine(sc);
            if (sc.isEnd() || isLineBreak(sc)) {
                break;
            }

            XuiLayoutNode node = parseNode(sc);
            if (node != null) {
                // 行内节点默认左上角对齐
                if (node.getAlign().horizontal == null) {
                    node.setAlign(node.getAlign().withHorizontal(XuiLayoutAlign.Direction.start));
                }
                if (node.getAlign().vertical == null) {
                    node.setAlign(node.getAlign().withVertical(XuiLayoutAlign.Direction.start));
                }

                row.addChild(node);
            }

            // 防止游标不动
            if (pos == sc.pos) {
                throw sc.newError(ERR_LAYOUT_LINEAR_UNKNOWN_MARK).param(ARG_VALUE, (char) sc.cur);
            }
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
     * <p/>
     * 注意：表格解析完后，将自动跳到下一行
     */
    private static XuiLayoutNode parseTable(TextScanner sc) {
        XuiLayoutNode table = XuiLayoutNode.table();

        while (true) {
            moveToValidCharInLine(sc);
            if (sc.isEnd() || sc.cur != '|') {
                break;
            }

            XuiLayoutNode row = parseTableRow(sc);
            if (row != null) {
                // 表格行宽度自适应 table 宽度
                row.setWidth(XuiLayoutSize.match_parent());

                table.addChild(row);
            }

            // 跳至下一行
            sc.skipLine();
        }

        return table.hasChild() ? table : null;
    }

    /** 解析表格行 */
    private static XuiLayoutNode parseTableRow(TextScanner sc) {
        List<XuiLayoutNode> cells = new ArrayList<>();

        while (!sc.isEnd()) {
            moveToValidCharInLine(sc);
            if (isLineBreak(sc)) {
                break;
            }

            SourceLocation loc = sc.location();
            String text = extractTableCell(sc);

            XuiLayoutNode cell = null;
            if (text != null) {
                List<XuiLayoutNode> children = parseNodes(loc, text);
                if (children.size() == 1) {
                    // Note: 其依然为 row 或 table 类型节点
                    cell = children.get(0);
                } else if (children.size() > 1) {
                    cell = XuiLayoutNode.row(children);
                }
            }

            if (cell == null) {
                // 空白单元格：用于占位
                cell = XuiLayoutNode.space();
            }

            // 表格单元格节点始终占满整个单元格
            cell.setWidth(XuiLayoutSize.match_parent());
            cell.setHeight(XuiLayoutSize.match_parent());

            cells.add(cell);
        }

        // Note: 解析单元格时，必然会在尾部添加一个多余的空白单元格
        if (!cells.isEmpty()) {
            cells.remove(cells.size() - 1);
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
        moveToValidCharInLine(sc);
        if (sc.isEnd()) {
            return null;
        }

        // 解析起始方向的对齐位置
        XuiLayoutAlign.Direction[] startAlign = parseAlign(sc);

        if (StringHelper.isSpaceInLine(sc.cur)) {
            throw sc.newError(ERR_LAYOUT_LINEAR_NOT_ALLOW_SPACES_AFTER_ALIGN_MARK);
        }

        XuiLayoutNode node = null;
        // 解析组件匹配模式
        if (sc.cur == '[') {
            String text = extractBetweenMark(sc, '[', ']');

            if (text != null) {
                node = XuiLayoutNode.item(text);
            }
        } else if (sc.cur == '{') {
            SourceLocation loc = sc.location();
            String text = extractBetweenMark(sc, '{', '}');

            if (text != null) {
                // TODO loc 的准确位置应该在源文本基础上后移一位
                List<XuiLayoutNode> children = parseNodes(loc, text);

                if (children.size() == 1) {
                    node = children.get(0);
                } else if (children.size() > 1) {
                    node = XuiLayoutNode.column(children);
                }
            }
        }

        // 解析布局配置参数列表
        if (sc.cur == '(') {
            SourceLocation loc = sc.location();
            String text = extractBetweenMark(sc, '(', ')');

            if (text != null) {
                XuiLayoutProps props = parseProps(loc, text);

                if (props != null) {
                    if (node == null) {
                        node = XuiLayoutNode.space();
                    }
                    node.updateProps(props);
                }
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
    private static XuiLayoutAlign.Direction[] parseAlign(TextScanner sc) {
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

    /** 解析配置参数列表（类 JSON 形式） */
    private static XuiLayoutProps parseProps(SourceLocation loc, String text) {
        Map<String, Object> props = new HashMap<>();

        TextScanner sc = TextScanner.fromString(loc, text);
        while (true) {
            moveToValidCharInLine(sc);
            if (sc.isEnd()) {
                break;
            }

            String name = sc.nextUntil(':', false).trim().toString();
            sc.consume(':');

            moveToValidCharInLine(sc);
            if (sc.isEnd()) {
                // TODO 未配置参数值
                break;
            }

            if (sc.cur == '{') {
                // TODO 解析结构化的参数值
            } else if (sc.cur == '$') {
                // TODO 解析动态表达式
            }

            String value = sc.nextUntil(',', true).trim().toString();
            if (!sc.isEnd()) {
                sc.consume(',');
            }

            props.put(name, value);
        }

        return props.isEmpty() ? null : new XuiLayoutProps(props);
    }

    /**
     * 提取标记符号之间的文本
     * <p/>
     * 提取完成后，<code>sc</code> 将跳到右侧标记符号的下一个位置
     * <p/>
     * 注：仅在遇到 <code>leftMark</code> 时才能调用本方法
     */
    private static String extractBetweenMark(TextScanner sc, char leftMark, char rightMark) {
        StringBuilder sb = new StringBuilder();

        int pairs = 1;
        while (!sc.isEnd()) {
            sc.next();

            if (sc.cur == leftMark) {
                pairs += 1;
            } else if (sc.cur == rightMark) {
                pairs -= 1;

                if (pairs == 0) {
                    // 跳过匹配的最右侧的标记符号
                    sc.next();
                }
            }

            if (pairs > 0 && !sc.isEnd()) {
                sb.append((char) sc.cur);
            } else {
                break;
            }
        }

        if (pairs != 0) {
            throw sc.newError(ERR_LAYOUT_LINEAR_NO_RIGHT_MARK_FOR_LEFT_MARK)
                    .param(ARG_RIGHT_MARK, rightMark)
                    .param(ARG_LEFT_MARK, leftMark);
        }

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
                    sb.append((char) sc.cur);
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

        if (!cellExtracted && text != null) {
            throw sc.newError(ERR_LAYOUT_LINEAR_NO_END_MARK_FOR_TABLE_CELL);
        }

        return text;
    }

    /** 跳到有效的标记字符处：行首空白，注释行等无用字符均将被直接跳过 */
    private static void moveToValidCharInLine(TextScanner sc) {
        sc.skipBlankInLine();
        // Note: 连续注释将被自动跳过，并且自动跳过非注释行开头的空白（含换行符）
        sc.skipJavaComment(false);
    }

    private static String toString(StringBuilder sb) {
        return sb.length() == 0 ? null : StringHelper.emptyAsNull(sb.toString().trim());
    }

    private static boolean isLineBreak(TextScanner sc) {
        return sc.cur == '\r' || sc.cur == '\n';
    }
}
