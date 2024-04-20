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

package io.crazydan.duzhou.framework.codegen.imp;

import java.util.Map;

import io.nop.orm.model.OrmModel;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-18
 */
public class AppModel extends OrmModel {
    private Map<String, Object> app;
    private Map<String, Object> appModule;

    public Map<String, Object> getApp() {
        return this.app;
    }

    public void setApp(Map<String, Object> app) {
        this.app = app;
    }

    public Map<String, Object> getAppModule() {
        return this.appModule;
    }

    public void setAppModule(Map<String, Object> appModule) {
        this.appModule = appModule;
    }
}