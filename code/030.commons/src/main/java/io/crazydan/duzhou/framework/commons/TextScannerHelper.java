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

package io.crazydan.duzhou.framework.commons;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import io.nop.api.core.exceptions.ErrorCode;
import io.nop.commons.text.MutableString;
import io.nop.commons.text.tokenizer.TextScanner;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-11
 */
public class TextScannerHelper {
    public static final String ARG_LEFT_PAIR = "leftPair";
    public static final String ARG_RIGHT_PAIR = "rightPair";

    /** 跳到有效的字符处：行首空白，注释行等无用字符均将被直接跳过 */
    public static void moveToValidCharInLine(TextScanner sc) {
        sc.skipBlankInLine();
        // Note: 连续注释将被自动跳过，并且自动跳过非注释行开头的空白（含换行符）
        sc.skipJavaComment(false);
    }

    /** 当前字符是否为换行符 */
    public static boolean isLineBreak(TextScanner sc) {
        return sc.cur == '\r' || sc.cur == '\n';
    }

    /** 跳过行空白并{@link TextScanner#consume 消费}指定的字符 */
    public static void skipBlankAndConsumeInLine(TextScanner sc, char c) {
        sc.skipBlankInLine();
        sc.consume(c);
    }

    /**
     * 提取配对符号之间的文本
     * <p/>
     * 提取完成后，<code>sc</code> 将跳到右侧符号 <code>right</code> 的下一个位置
     * <p/>
     * 注：需确保 {@link TextScanner} 的当前位置在左侧符号 <code>left</code> 上
     */
    public static String extractBetweenPairChars(TextScanner sc, char left, char right, ErrorCode code) {
        sc.consume(left);

        int pairs = 1;
        MutableString buf = sc.getReusableBuffer();
        while (!sc.isEnd()) {
            if (sc.cur == left) {
                pairs += 1;
            } else if (sc.cur == right) {
                pairs -= 1;

                if (pairs == 0) {
                    // 跳过匹配的最右侧的符号
                    sc.next();
                }
            }

            if (pairs > 0) {
                sc.appendToBuf(sc.cur);
            } else {
                break;
            }

            sc.next();
        }

        if (pairs != 0) {
            throw sc.newError(code).param(ARG_LEFT_PAIR, left).param(ARG_RIGHT_PAIR, right);
        }

        return StringHelper.emptyAsNull(buf.trim().toString());
    }

    /**
     * 在配对符号之间消费 {@link TextScanner}
     * <p/>
     * 在 <code>consumer</code> 中推动对 {@link TextScanner} 的消费，
     * 并自行处理相同配对符号的嵌套
     * <p/>
     * 处理完成后，<code>sc</code> 将跳到右侧符号 <code>right</code> 的下一个位置
     * <p/>
     * 注：需确保 {@link TextScanner} 的当前位置在左侧符号 <code>left</code> 上
     */
    public static void consumeBetweenPairChars(
            TextScanner sc, char left, char right, ErrorCode code, Runnable consumer) {
        sc.consume(left);

        int pos = sc.pos;
        while (true) {
            moveToValidCharInLine(sc);

            sc.skipBlank();
            if (sc.isEnd()) {
                throw sc.newError(code).param(ARG_LEFT_PAIR, left).param(ARG_RIGHT_PAIR, right);
            }

            if (sc.cur == right) {
                sc.next();
                break;
            }

            consumer.run();

            if (pos == sc.pos) {
                throw sc.newError(code).param(ARG_LEFT_PAIR, left).param(ARG_RIGHT_PAIR, right);
            } else {
                pos = sc.pos;
            }
        }
    }

    /** 持续消费 {@link TextScanner} 直到游标不再变化 */
    public static <T> void consumeUntilPosNotChanged(
            TextScanner sc, Predicate<TextScanner> whenBreak, Supplier<T> supplier, Consumer<T> consumer) {
        int pos = sc.pos;
        while (true) {
            moveToValidCharInLine(sc);
            if (sc.isEnd() || (whenBreak != null && whenBreak.test(sc))) {
                break;
            }

            T t = supplier.get();

            if (pos == sc.pos) {
                break;
            } else {
                pos = sc.pos;
            }

            consumer.accept(t);
        }
    }
}
