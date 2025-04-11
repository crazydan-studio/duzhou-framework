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

package io.crazydan.duzhou.framework.ui.domain;

import io.nop.commons.util.StringHelper;
import io.nop.xlang.xdef.domain.CheckStdDomainHandler;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-04-11
 */
public class GenericStdDomainHandlers {

    /** 是否为有效的组件名，由字母、数字、下划线组成的驼峰形式，且必须首字母需大写，如：`Button`、`Button_Ext` */
    public static boolean isValidComponentName(String text) {
        return StringHelper.isValidJavaVarName(text) //
               && !text.contains("$") //
               && text.charAt(0) >= 'A' && text.charAt(0) <= 'Z';
    }

    /** 组件名类型：{@link #isValidComponentName} */
    public static class ComponentNameType extends CheckStdDomainHandler {

        @Override
        public String getName() {
            return "component-name";
        }

        @Override
        protected boolean isValid(String text) {
            return isValidComponentName(text);
        }
    }
}
