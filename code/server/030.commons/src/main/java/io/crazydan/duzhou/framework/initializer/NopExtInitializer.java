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

package io.crazydan.duzhou.framework.initializer;

import io.nop.commons.lang.impl.Cancellable;
import io.nop.core.initialize.ICoreInitializer;
import io.nop.core.lang.eval.global.EvalGlobalRegistry;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-25
 */
public class NopExtInitializer implements ICoreInitializer {
    private final Cancellable cleanup = new Cancellable();

    @Override
    public void initialize() {
        this.cleanup.append(EvalGlobalRegistry.instance().registerStaticFunctions(NopExtFunctions.class));
    }

    @Override
    public void destroy() {
        this.cleanup.cancel();
    }
}
