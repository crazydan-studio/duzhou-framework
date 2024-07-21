/**
 * Copyright (c) 2017-2024 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-entropy
 * Github: https://github.com/entropy-cloud/nop-entropy
 */
package io.crazydan.duzhou.framework.starter.filter;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import io.crazydan.duzhou.framework.starter.utils.QuarkusExecutorHelper;
import io.nop.api.core.context.IContext;
import io.nop.commons.util.StringHelper;
import io.nop.http.api.server.IAsyncBody;
import io.nop.http.api.server.IHttpServerContext;
import io.vertx.core.MultiMap;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.CookieSameSite;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.impl.CookieImpl;
import io.vertx.ext.web.RoutingContext;

public class VertxHttpServerContext implements IHttpServerContext {
    private final RoutingContext routingContext;

    private boolean responseSent;
    private IContext context;

    public VertxHttpServerContext(RoutingContext routingContext) {
        this.routingContext = routingContext;
    }

    @Override
    public String getHost() {
        return this.routingContext.request().host();
    }

    @Override
    public String getRequestPath() {
        return this.routingContext.normalizedPath();
    }

    @Override
    public String getRequestUrl() {
        return this.routingContext.request().absoluteURI();
    }

    @Override
    public String getQueryParam(String name) {
        List<String> list = this.routingContext.queryParam(name);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Map<String, Object> getRequestHeaders() {
        MultiMap map = this.routingContext.request().headers();

        Map<String, Object> ret = new HashMap<>();
        for (Map.Entry<String, String> entry : map) {
            ret.putIfAbsent(entry.getKey().toLowerCase(), entry.getValue());
        }

        return ret;
    }

    @Override
    public Object getRequestHeader(String headerName) {
        return this.routingContext.request().getHeader(headerName);
    }

    @Override
    public String getCookie(String name) {
        Cookie cookie = this.routingContext.request().getCookie(name);
        if (cookie == null) {
            return null;
        }

        return cookie.getValue();
    }

    @Override
    public void resumeRequest() {
        this.routingContext.request().resume();
    }

    @Override
    public void addCookie(String sameSite, HttpCookie httpCookie) {
        CookieImpl cookie = new CookieImpl(httpCookie.getName(), httpCookie.getValue());
        cookie.setDomain(httpCookie.getDomain());
        cookie.setHttpOnly(httpCookie.isHttpOnly());
        cookie.setPath(httpCookie.getPath());
        cookie.setSecure(httpCookie.getSecure());

        if (CookieSameSite.LAX.toString().equals(sameSite)) {
            cookie.setSameSite(CookieSameSite.LAX);
        } else if (CookieSameSite.NONE.toString().equals(sameSite)) {
            cookie.setSameSite(CookieSameSite.NONE);
        } else if (CookieSameSite.STRICT.toString().equals(sameSite)) {
            cookie.setSameSite(CookieSameSite.STRICT);
        }

        this.routingContext.response().addCookie(cookie);
    }

    @Override
    public void removeCookie(String name) {
        this.routingContext.response().removeCookie(name);
    }

    @Override
    public void removeCookie(String name, String domain, String path) {
        this.routingContext.response().removeCookie(name, domain, path);
    }

    @Override
    public void setResponseHeader(String headerName, Object value) {
        if (value == null) {
            this.routingContext.response().headers().remove(headerName);
        } else {
            this.routingContext.response().headers().set(headerName, String.valueOf(value));
        }
    }

    @Override
    public void sendRedirect(String url) {
        this.responseSent = true;
        this.routingContext.redirect(url);
    }

    @Override
    public void sendResponse(int httpStatus, String body) {
        this.responseSent = true;
        this.routingContext.response().setStatusCode(httpStatus);
        this.routingContext.response().send(body);
    }

    public boolean isResponseSent() {
        return this.responseSent;
    }

    @Override
    public String getAcceptableContentType() {
        return this.routingContext.getAcceptableContentType();
    }

    @Override
    public String getResponseContentType() {
        return this.routingContext.response().headers().get(HttpHeaders.CONTENT_TYPE);
    }

    @Override
    public void setResponseContentType(String contentType) {
        this.routingContext.response().headers().set(HttpHeaders.CONTENT_TYPE, contentType);
    }

    public CompletionStage<Void> proceedAsync() {
        CompletableFuture<Void> future = new CompletableFuture<>();

        this.routingContext.addEndHandler(ret -> {
            if (ret.failed()) {
                future.completeExceptionally(ret.cause());
            } else {
                future.complete(null);
            }
        });
        this.routingContext.next();

        return future;
    }

    @Override
    public IAsyncBody getRequestBody() {
        return () -> {
            CompletableFuture<String> future = new CompletableFuture<>();

            this.routingContext.request().body().onSuccess(v -> {
                future.complete(v.toString(StringHelper.CHARSET_UTF8));
            }).onFailure(future::completeExceptionally);

            return future;
        };
    }

    @Override
    public CompletionStage<Object> executeBlocking(Callable<?> task) {
        return QuarkusExecutorHelper.executeBlocking(task);
    }

    @Override
    public IContext getContext() {
        return this.context;
    }

    @Override
    public void setContext(IContext context) {
        this.context = context;
    }
}
