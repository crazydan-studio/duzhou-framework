/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.crazydan.duzhou.framework.gateway.starter.ioc;

import java.lang.annotation.Annotation;

public class MarkerInterfaceAnnotation implements Annotation {
    private final Class<? extends Annotation> annType;

    public MarkerInterfaceAnnotation(Class<? extends Annotation> annType) {
        this.annType = annType;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.annType;
    }
}
