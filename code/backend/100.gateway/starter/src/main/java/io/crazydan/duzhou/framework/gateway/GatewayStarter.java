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

package io.crazydan.duzhou.framework.gateway;

import io.crazydan.duzhou.framework.starter.QuarkusStarter;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * Quarkus 应用启动器
 * <p/>
 * 该启动器只能在应用服务中实现，否则，将不会扫描到应用服务的
 * classpath 中的资源。
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-15
 */
@QuarkusMain
public class GatewayStarter implements QuarkusApplication {

    public static void main(String... args) {
        // https://quarkus.io/guides/lifecycle#the-main-method
        Quarkus.run(GatewayStarter.class, QuarkusStarter::stop, args);
    }

    @Override
    public int run(String... args) throws Exception {
        return QuarkusStarter.start(args);
    }
}
