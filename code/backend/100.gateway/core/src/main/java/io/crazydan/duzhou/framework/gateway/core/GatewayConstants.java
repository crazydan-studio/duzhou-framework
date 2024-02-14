/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.crazydan.duzhou.framework.gateway.core;

import io.nop.http.api.server.IHttpServerFilter;

public interface GatewayConstants {
    int PRIORITY_APP_FILTER = 5;
    int PRIORITY_CONTENT_ENCODING_FILTER = 10;

    int PRIORITY_WEB_SITE_FILTER = IHttpServerFilter.LOW_PRIORITY - 100;
}
