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

package io.crazydan.duzhou.framework.lang;

/**
 * 可转换为代码片段的对象
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-31
 */
public interface CodeSnippet {

    /**
     * 转换为有效的代码片段
     * <p/>
     * 其中，字符串字面量使用指定的引号 <code>strQuote</code> 包裹，
     * 且字面量与表达式之间以 <code>+</code> 连接
     * <p/>
     * <pre>
     * Hello, ${username} ==> 'Hello, ' + (username)
     * </pre>
     * <pre>
     * a + b = ${a + b} ==> 'a + b = ' + (a + b)
     * </pre>
     * <pre>
     * {name: ${props.name}} ==> {name: props.name}
     * </pre>
     */
    String toCodeSnippet(char strQuote);
}
