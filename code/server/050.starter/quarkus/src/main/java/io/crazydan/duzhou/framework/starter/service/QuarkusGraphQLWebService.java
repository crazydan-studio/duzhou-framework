/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.crazydan.duzhou.framework.starter.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import io.nop.graphql.core.web.GraphQLWebService;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

@Path("")
@ApplicationScoped
public class QuarkusGraphQLWebService extends GraphQLWebService {
    @Context
    private HttpServerRequest req;

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> ret = new HashMap<>();

        this.req.params().forEach(ret::put);

        return ret;
    }

    @Override
    protected Map<String, Object> getHeaders() {
        Map<String, Object> ret = new TreeMap<>();

        this.req.headers().forEach((name, value) -> {
            name = name.toLowerCase(Locale.ENGLISH);
            if (shouldIgnoreHeader(name)) {
                return;
            }
            ret.put(name, value);
        });
        return ret;
    }
}
