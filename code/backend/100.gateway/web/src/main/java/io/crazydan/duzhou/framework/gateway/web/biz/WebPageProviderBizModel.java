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

package io.crazydan.duzhou.framework.gateway.web.biz;

import java.util.Map;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.api.core.annotations.biz.BizMutation;
import io.nop.api.core.annotations.biz.BizQuery;
import io.nop.api.core.annotations.core.Name;
import io.nop.api.core.annotations.directive.Auth;
import io.nop.core.context.IServiceContext;

/**
 * Web 资源页面处理接口
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-02-19
 */
@BizModel("WebPageProvider")
public class WebPageProviderBizModel {

    @BizQuery
    @Auth(publicAccess = true)
    public Map<String, Object> getPage(@Name("path") String path, IServiceContext context) {
        return null;
    }

    @BizQuery
    public Map<String, Object> getPageSource(@Name("path") String path) {
        return null;
    }

    @BizMutation
    public void savePageSource(@Name("path") String path, @Name("data") Map<String, Object> data) {
    }

    @BizMutation
    public void rollbackPageSource(@Name("path") String path) {
    }
}
