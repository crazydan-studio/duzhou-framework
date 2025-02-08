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

package io.crazydan.duzhou.framework.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 继承该类的 Spring boot 启动类可支持如下特性：<ul>
 * <li>
 * 在本地开发时，可以将本地相关的配置放在 `src/main/resources/application-local.properties`
 * 或 `src/main/resources/application-local.yaml` 中，
 * 以避免因向仓库提交修改的 `application.yaml` 而影响到其他开发人员的正常开发。
 * Note：在该子类的 `main(String[] args)` 中需通过 {@link #run(Class, String...)} 或 {@link #build(Class)}
 * 启动 Spring 应用；
 * </li>
 * </ul>
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-20
 */
public abstract class SpringStarter {

    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return build(primarySource).run(args);
    }

    public static SpringApplication build(Class<?> primarySource) {
        // 采用指定 spring.config.additional-location 的方式引入本地开发配置文件（可以没有该文件）
        // - https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config
        // Note: spring.config.additional-location 可在命令行中重新设值，以覆盖该 local 配置
        String prop = "spring.config.additional-location="
                      + "optional:classpath:/application-local.properties"
                      + ",optional:classpath:/application-local.yaml";
        return new SpringApplicationBuilder(primarySource).properties(prop).build();
    }
}
