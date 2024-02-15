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
package io.crazydan.duzhou.framework.starter.ioc;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import io.crazydan.duzhou.framework.starter.QuarkusConstants;
import io.nop.api.core.ApiConstants;
import io.nop.api.core.ApiErrors;
import io.nop.api.core.exceptions.NopException;
import io.nop.api.core.ioc.IBeanContainer;
import io.nop.commons.util.StringHelper;
import io.quarkus.arc.Arc;
import io.quarkus.arc.ArcContainer;
import io.quarkus.arc.InjectableBean;
import io.quarkus.arc.InjectableInstance;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.arc.impl.ArcContainerImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;

public class NopQuarkusBeanContainer implements IBeanContainer {

    @Override
    public String getId() {
        return "quarkus";
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void restart() {
    }

    private ArcContainer container() {
        return ArcContainerImpl.instance();
    }

    @Override
    public boolean containsBeanType(Class<?> clazz) {
        return container().instance(clazz).isAvailable();
    }

    @Override
    public String findAutowireCandidate(Class<?> beanType) {
        InstanceHandle<?> bean = container().instance(beanType);
        return getQuarkusBeanId(bean);
    }

    private String getQuarkusBeanId(InstanceHandle<?> bean) {
        if (bean.isAvailable()) {
            return QuarkusConstants.QUARKUS_ID_PREFIX + bean.getBean().getIdentifier();
        }
        return null;
    }

    @Override
    public boolean isRunning() {
        return container().isRunning();
    }

    @Override
    public boolean containsBean(String name) {
        InjectableBean<?> quarkusBean = getQuarkusBean(name);
        return quarkusBean != null;
    }

    @Override
    public Object getBean(String name) {
        InjectableBean<?> quarkusBean = getQuarkusBean(name);
        Object bean = quarkusBean == null ? null : Arc.container().instance(quarkusBean).get();
        if (bean == null) {
            throw new NopException(ApiErrors.ERR_IOC_UNKNOWN_BEAN_FOR_NAME).param(ApiErrors.ARG_BEAN_NAME, name);
        }
        return bean;
    }

    @Override
    public <T> T getBeanByType(Class<T> clazz) {
        T bean = container().instance(clazz).get();
        if (bean == null) {
            throw new NopException(ApiErrors.ERR_IOC_UNKNOWN_BEAN_FOR_TYPE).param(ApiErrors.ARG_BEAN_TYPE, clazz);
        }
        return bean;
    }

    @Override
    public <T> T tryGetBeanByType(Class<T> clazz) {
        return container().instance(clazz).get();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        Map<String, T> ret = new HashMap<>();

        for (InstanceHandle<T> handle : Arc.container().select(clazz).handles()) {
            String beanId = getQuarkusBeanId(handle);
            ret.put(beanId, handle.get());
        }
        return ret;
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annClass) {
        InjectableInstance<Object> handles = container().select(Object.class, new MarkerInterfaceAnnotation(annClass));

        Map<String, Object> ret = new HashMap<>();
        for (InstanceHandle<Object> handle : handles.handles()) {
            String beanId = getQuarkusBeanId(handle);
            ret.put(beanId, handle.get());
        }

        return ret;
    }

    @Override
    public String getBeanScope(String name) {
        InjectableBean<?> bean = getQuarkusBean(name);
        if (bean == null) {
            throw new NopException(ApiErrors.ERR_IOC_UNKNOWN_BEAN_FOR_NAME).param(ApiErrors.ARG_BEAN_NAME, name);
        }

        Class<? extends Annotation> scope = bean.getScope();
        if (scope == Dependent.class || scope == ApplicationScoped.class) {
            return ApiConstants.BEAN_SCOPE_SINGLETON;
        } else if (scope == RequestScoped.class) {
            return ApiConstants.BEAN_SCOPE_REQUEST;
        }

        return StringHelper.camelCaseToHyphen(scope.getSimpleName());
    }

    private InjectableBean<?> getQuarkusBean(String name) {
        if (name.startsWith(QuarkusConstants.QUARKUS_ID_PREFIX)) {
            return Arc.container().bean(name.substring(QuarkusConstants.QUARKUS_ID_PREFIX.length()));
        }
        return Arc.container().namedBean(name);
    }
}
