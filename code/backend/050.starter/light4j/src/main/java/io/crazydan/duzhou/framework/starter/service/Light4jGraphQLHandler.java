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

package io.crazydan.duzhou.framework.starter.service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.networknt.handler.LightHttpHandler;
import com.networknt.http.HttpStatus;
import com.networknt.http.MediaType;
import com.networknt.utility.StringUtils;
import io.crazydan.duzhou.framework.starter.Light4jContext;
import io.nop.api.core.beans.graphql.GraphQLResponseBean;
import io.nop.api.core.util.FutureHelper;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.json.JsonTool;
import io.nop.graphql.core.IGraphQLExecutionContext;
import io.nop.graphql.core.web.GraphQLWebService;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-05-05
 */
public class Light4jGraphQLHandler extends GraphQLWebService implements LightHttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Light4jContext.withExchange(exchange, () -> {
            InputStream inputStream = exchange.getInputStream();
            String body = StringUtils.inputStreamToString(inputStream, StandardCharsets.UTF_8);

            String result = FutureHelper.syncGet(runGraphQL(body, this::transformGraphQLResponse));

            exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            exchange.setStatusCode(HttpStatus.OK.value());
            exchange.getResponseSender().send(result);
        });
    }

    protected String transformGraphQLResponse(GraphQLResponseBean response, IGraphQLExecutionContext gqlContext) {
        Light4jWebHelper.setResponseHeader(Light4jContext.getExchange(), gqlContext.getResponseHeaders());

        return JsonTool.serialize(response, false);
    }

    @Override
    protected Map<String, String> getParams() {
        HttpServerExchange exchange = Light4jContext.getExchange();

        Map<String, String> ret = new LinkedHashMap<>();
        exchange.getQueryParameters().forEach((name, value) -> {
            name = StringHelper.replace(name, "%40", "@");

            ret.put(name, value.getFirst());
        });
        return ret;
    }

    @Override
    protected Map<String, Object> getHeaders() {
        HttpServerExchange exchange = Light4jContext.getExchange();

        Map<String, Object> headers = new HashMap<>();
        exchange.getRequestHeaders().forEach((header) -> {
            headers.put(header.getHeaderName().toString(), header.getFirst());
        });
        return headers;
    }
}
