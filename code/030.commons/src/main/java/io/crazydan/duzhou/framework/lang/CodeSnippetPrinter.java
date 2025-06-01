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

import java.util.Collection;
import java.util.Map;

import io.nop.xlang.ast.Expression;
import io.nop.xlang.ast.Literal;
import io.nop.xlang.ast.TemplateExpression;
import io.nop.xlang.ast.XLangASTNode;
import io.nop.xlang.ast.print.XLangExpressionPrinter;

import static io.nop.api.core.util.ApiStringHelper.escapeJava;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-31
 */
public class CodeSnippetPrinter {
    private final char strQuote;

    CodeSnippetPrinter(char strQuote) {
        this.strQuote = strQuote;
    }

    public static CodeSnippetPrinter create(char strQuote) {
        return new CodeSnippetPrinter(strQuote);
    }

    /**
     * 遍历 {@link Expression} 并打印输出其对应的代码片段
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
    public String print(Expression expr) {
        ExpressionPrinter out = new ExpressionPrinter();

        return out.toExprString(expr);
    }

    class ExpressionPrinter extends XLangExpressionPrinter {

        @Override
        public void visitTemplateExpression(TemplateExpression node) {
            for (int i = 0; i < node.getExpressions().size(); i++) {
                if (i > 0) {
                    print(" + ");
                }

                Expression expr = node.getExpressions().get(i);
                if (expr instanceof Literal) {
                    visitLiteral((Literal) expr);
                } else {
                    print('(');
                    visit(expr);
                    print(')');
                }
            }
        }

        @Override
        public void visitLiteral(Literal node) {
            char strQuote = CodeSnippetPrinter.this.strQuote;
            Object val = node.getValue();

            if (val == null || val instanceof Number || val instanceof Boolean) {
                print(val);
            }
            //
            else if (val instanceof String) {
                print(strQuote);
                print(escapeJava(val.toString()));
                print(strQuote);
            }
            //
            else if (val instanceof CodeSnippet) {
                print(((CodeSnippet) val).toCodeSnippet(strQuote));
            }
            //
            else {
                if (val instanceof Mappable) {
                    val = ((Mappable) val).toMap();
                } else if (val instanceof Collection) {
                    val = ((Collection<?>) val).toArray();
                }

                if (val instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) val;
                    int i = 0;

                    print('{');
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        Object prop = entry.getKey();
                        Object obj = entry.getValue();
                        if (obj == null) {
                            continue;
                        }

                        if (i > 0) {
                            print(',');
                        }
                        print(prop);
                        print(':');
                        visitObject(obj);

                        i++;
                    }
                    print('}');
                } else if (val.getClass().isArray()) {
                    Object[] array = (Object[]) val;

                    print('[');
                    for (int i = 0; i < array.length; i++) {
                        Object obj = array[i];

                        if (i > 0) {
                            print(',');
                        }
                        visitObject(obj);
                    }
                    print(']');
                }
                //
                else {
                    // TODO 不支持转换为代码片段的数据类型，需实现 CodeSnippet 接口
                    throw new IllegalStateException("Unsupported literal "
                                                    + val.getClass().getName()
                                                    + "["
                                                    + val
                                                    + "]");
                }
            }
        }

        private void visitObject(Object obj) {
            if (obj instanceof XLangASTNode) {
                visit((XLangASTNode) obj);
            } else {
                visitLiteral(Literal.valueOf(null, obj));
            }
        }
    }
}
