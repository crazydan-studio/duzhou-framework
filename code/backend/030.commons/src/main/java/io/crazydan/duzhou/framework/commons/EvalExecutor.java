/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import io.nop.core.lang.eval.IEvalAction;
import io.nop.core.lang.eval.IEvalScope;
import io.nop.core.lang.eval.global.EvalGlobalRegistry;
import io.nop.core.lang.eval.global.IGlobalVariableDefinition;
import io.nop.core.lang.xml.IXNodeGenerator;
import io.nop.core.lang.xml.XNode;
import io.nop.core.reflect.ReflectionManager;
import io.nop.core.type.IGenericType;
import io.nop.xlang.api.XLang;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-29
 */
public class EvalExecutor {

    /**
     * 执行 {@link IEvalAction} 并返回执行结果
     * {@link IEvalAction} 对应 `xdef:value="xpl"`
     * 的 XDSL 节点，在该节点内可直接执行代码或 xpl/xlib 函数，
     * 其执行结果通过 `return` 返回
     *
     * @param vars
     *         需传入的变量键值对
     */
    public static <T> T exec(IEvalAction action, Object[]... vars) {
        IEvalScope scope = evalScope(vars);

        return (T) action.invoke(scope);
    }

    /**
     * 执行 {@link IXNodeGenerator} 并返回生成的 {@link XNode}
     * <p/>
     * {@link IXNodeGenerator} 对应 `xdef:value="xpl-node"`
     * 的 XDSL 节点，在该节点内可直接执行代码或 xpl/xlib 函数，
     * 若是以节点树方式定义，则可以 `${}` 方式引用变量
     *
     * @param vars
     *         需传入的变量键值对
     */
    public static XNode exec(IXNodeGenerator nodeGen, Object[]... vars) {
        IEvalScope scope = evalScope(vars);

        return nodeGen.generateNode(scope);
    }

    /** 根据变量键值对构造 {@link IEvalScope} */
    public static IEvalScope evalScope(Object[]... vars) {
        IEvalScope scope = XLang.newEvalScope();

        if (vars != null) {
            for (Object[] var : vars) {
                if (var.length > 1 && var[0] != null) {
                    scope.setLocalValue(var[0].toString(), var[1]);
                }
            }
        }
        return scope;
    }

    /**
     * 注册全局变量，并执行消费函数，并在返回结果前，移除全局变量
     */
    public static <T> T withGlobalVars(Supplier<T> cb, Object[]... vars) {
        if (vars == null) {
            return cb.get();
        }

        List<String> varNames = new ArrayList<>();
        for (Object[] var : vars) {
            String name = var.length > 1 ? var[0].toString() : null;
            if (name == null) {
                continue;
            }

            EvalGlobalRegistry.instance().registerVariable(name, new EvalVarDef(var[1]));
            varNames.add(name);
        }

        try {
            return cb.get();
        } finally {
            varNames.forEach(EvalGlobalRegistry.instance()::unregisterVariable);
        }
    }

    private static class EvalVarDef implements IGlobalVariableDefinition {
        private final Object var;

        private EvalVarDef(Object var) {
            this.var = var;
        }

        @Override
        public IGenericType getResolvedType() {
            return ReflectionManager.instance().buildRawType(this.var.getClass());
        }

        @Override
        public Object getValue(IEvalScope scope) {
            return this.var;
        }
    }
}
