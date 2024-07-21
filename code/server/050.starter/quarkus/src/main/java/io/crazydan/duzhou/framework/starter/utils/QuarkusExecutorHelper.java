/**
 * Copyright (c) 2017-2024 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-entropy
 * Github: https://github.com/entropy-cloud/nop-entropy
 */
package io.crazydan.duzhou.framework.starter.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import io.nop.api.core.util.FutureHelper;
import io.quarkus.runtime.BlockingOperationControl;
import io.quarkus.runtime.ExecutorRecorder;

public class QuarkusExecutorHelper {

    /** 异步执行任务 */
    public static CompletionStage<Object> executeBlocking(Callable<?> task) {
        // 如果已经在工作线程上
        if (BlockingOperationControl.isBlockingAllowed()) {
            return FutureHelper.futureCall(task);
        }

        // 如果当前在 IO 线程上，则调度到工作线程池上再执行
        CompletableFuture<Object> future = new CompletableFuture<>();
        ExecutorRecorder.getCurrent().execute(() -> {
            CompletionStage<Object> promise = FutureHelper.futureCall(task);
            FutureHelper.bindResult(promise, future);
        });
        return future;
    }
}
