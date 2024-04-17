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

package io.crazydan.duzhou.framework.app_dev.test.biz;

import java.util.HashMap;
import java.util.Map;

import io.nop.api.core.annotations.autotest.EnableSnapshot;
import io.nop.api.core.annotations.autotest.NopTestConfig;
import io.nop.api.core.beans.ApiRequest;
import io.nop.api.core.beans.ApiResponse;
import io.nop.api.core.util.FutureHelper;
import io.nop.autotest.junit.JunitAutoTestCase;
import io.nop.core.lang.json.JsonTool;
import io.nop.graphql.core.IGraphQLExecutionContext;
import io.nop.graphql.core.ast.GraphQLOperationType;
import io.nop.graphql.core.engine.IGraphQLEngine;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-04-17
 */
@NopTestConfig(enableActionAuth = "false", initDatabaseSchema = true,
               testConfigFile = "classpath:/application.properties")
public class DevAppServiceBizModelTest extends JunitAutoTestCase {
    private static final Logger log = LoggerFactory.getLogger(DevAppServiceBizModelTest.class);

    @Inject
    IGraphQLEngine graphQLEngine;

    @Test
    @EnableSnapshot
    public void test_release() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("id", "559f97fde5ef49d8a9fc5aec7972e7ab");
        requestData.put("releaseDir", getTargetFile("modeler").getAbsolutePath());

        ApiRequest<?> request = ApiRequest.build(requestData);

        IGraphQLExecutionContext context = this.graphQLEngine.newRpcContext(GraphQLOperationType.mutation,
                                                                            "DevAppService__release",
                                                                            request);
        ApiResponse<?> response = FutureHelper.syncGet(this.graphQLEngine.executeRpcAsync(context));
        log.info("devapp.service.release.result={}", JsonTool.stringify(response));
    }
}
