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

package io.crazydan.duzhou.framework.starter;

import io.nop.boot.NopApplication;
import io.nop.core.initialize.CoreInitialization;
import io.quarkus.runtime.Quarkus;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-15
 */
public class QuarkusStarter {

    /** 传入运行参数，再启动并等待，直到应用服务退出 */
    public static int start(String... args) throws Exception {
        QuarkusIntegration.start();

        // Banner 在线设计: https://patorjk.com/software/taag
        NopApplication app = new NopApplication();

        return app.run(args, () -> {
            Quarkus.waitForExit();
            return 0;
        });
    }

    /** 在应用服务退出后，做清理工作 */
    public static void stop(int exitCode, Throwable e) {
        CoreInitialization.destroy();
    }
}
