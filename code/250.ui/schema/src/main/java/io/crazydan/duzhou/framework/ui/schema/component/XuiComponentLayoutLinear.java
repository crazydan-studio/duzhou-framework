package io.crazydan.duzhou.framework.ui.schema.component;

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

        TextScanner sc = TextScanner.fromString(loc, text);

        do {
            sc.skipEmptyLines();
            sc.skipBlankInLine();

        } while (!sc.isEnd());

        return root;
    }

    /**
     * 解析如下标记文本：
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
        XuiLayoutNode node = null;

        // 解析起始方向的对齐位置
        XuiLayoutAlign.Direction[] startAlign = parseAlign(sc);

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
            String text = StringHelper.emptyAsNull(sc.nextUntil('}', false).trim().toString());
            sc.consumeInline('}');

            node = XuiLayoutNode.column();
            // TODO 解析嵌套布局的子节点
        }

        // 解析布局配置参数列表
        if (sc.cur == '(') {
            sc.consumeInline('(');
            sc.skipBlankInLine();
            if (sc.cur != ')') {
                sc.consumeInline(',');
                sc.skipBlankInLine();
            }
            sc.consumeInline(')');
        }

        // 解析终止方向的对齐位置
        XuiLayoutAlign.Direction[] endAlign = parseAlign(sc);

        // 确定尺寸设置和对齐方向
        XuiLayoutSize width = XuiLayoutSize.wrap_content();
        XuiLayoutSize height = XuiLayoutSize.wrap_content();
        XuiLayoutAlign.Direction[] align = new XuiLayoutAlign.Direction[] { null, null };

        if (startAlign[0] == XuiLayoutAlign.Direction.start && endAlign[0] == XuiLayoutAlign.Direction.end) {
            width = XuiLayoutSize.match_parent();
        } else if (startAlign[1] == XuiLayoutAlign.Direction.start && endAlign[1] == XuiLayoutAlign.Direction.end) {
            height = XuiLayoutSize.match_parent();
        } else if (startAlign[0] == XuiLayoutAlign.Direction.end && endAlign[0] == XuiLayoutAlign.Direction.start) {
            align[0] = XuiLayoutAlign.Direction.center;
        } else if (startAlign[1] == XuiLayoutAlign.Direction.end && endAlign[1] == XuiLayoutAlign.Direction.start) {
            align[1] = XuiLayoutAlign.Direction.center;
        } else {
        }

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
