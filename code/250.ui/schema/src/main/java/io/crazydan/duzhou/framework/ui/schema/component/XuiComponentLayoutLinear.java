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

        List<XuiLayoutNode> children = parseText(loc, text);
        root.addChildren(children);

        return root;
    }

    /** 解析文本 */
    private static List<XuiLayoutNode> parseText(SourceLocation loc, String text) {
        List<XuiLayoutNode> nodes = new ArrayList<>();

        TextScanner sc = TextScanner.fromString(loc, text);

        while (!sc.isEnd()) {
            sc.skipEmptyLines();
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

    /** 解析行：嵌套节点和配置参数列表可为多行文本 */
    private static XuiLayoutNode parseRow(TextScanner sc) {
        XuiLayoutNode row = XuiLayoutNode.row();

        int pos = sc.pos;
        while (true) {
            sc.skipBlankInLine();
            if (sc.isEnd() || sc.cur == '\r' || sc.cur == '\n') {
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

    /** 解析表格 */
    private static XuiLayoutNode parseTable(TextScanner sc) {
        XuiLayoutNode table = XuiLayoutNode.table();

        XuiLayoutNode row = XuiLayoutNode.row();
        while (true) {
            sc.skipBlankInLine();
            if (sc.isEnd()) {
                break;
            }
            if (sc.cur == '\r' || sc.cur == '\n') {
                row = XuiLayoutNode.row();
                continue;
            }

            sc.consumeInline('|');
            // TODO 支持表格嵌套：被嵌套的表格只能在嵌套布局 {} 内定义
            String text = StringHelper.emptyAsNull(sc.nextUntil('|', false).trim().toString());
        }

        return table;
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
            sc.next();
            String pattern = StringHelper.emptyAsNull(sc.nextUntil(']', false).trim().toString());
            sc.consumeInline(']');

            if (pattern != null) {
                node = XuiLayoutNode.item(pattern);
            }
        } else if (sc.cur == '{') {
            sc.next();
            // TODO 支持布局嵌套：递归查找最外层的配对符号
            String text = StringHelper.emptyAsNull(sc.nextUntil('}', false).trim().toString());
            sc.consumeInline('}');

            if (text != null) {
                List<XuiLayoutNode> children = parseText(sc.location(), text);

                if (!children.isEmpty()) {
                    node = XuiLayoutNode.column();

                    node.addChildren(children);
                }
            }
        }

        // 解析布局配置参数列表
        if (sc.cur == '(') {
            sc.next();
            String text = StringHelper.emptyAsNull(sc.nextUntil(')', false).trim().toString());
            sc.consumeInline(')');

            // TODO 解析配置参数列表（类 JSON 形式）
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
}
