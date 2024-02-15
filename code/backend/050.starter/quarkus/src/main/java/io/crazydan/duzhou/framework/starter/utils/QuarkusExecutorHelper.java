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
