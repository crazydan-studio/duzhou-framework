/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */

import {
  alert,
  confirm,
  toast,
  ToastLevel,
  ToastConf,
  dataMapping
} from 'amis';
import type { RenderOptions } from 'amis-core/lib/factory';
import copy from 'copy-to-clipboard';
import {
  isCancel,
  useAdapter,
  ajaxFetch,
  FetcherRequest,
  FetcherResult
} from '@/sdk/nop-core';

/** https://baidu.github.io/amis/zh-CN/docs/start/getting-started#env */
export function createEnv(): RenderOptions {
  const adapter = useAdapter();

  let env: RenderOptions = {
    // 默认为 'global'，决定 store 是否为全局共用的，如果想独占一个 store，需设置不同的值
    session: 'global',
    enableAMISDebug: true,
    dataMapping,

    fetcher(options: FetcherRequest): Promise<FetcherResult> {
      return ajaxFetch(options);
    },
    isCancel: isCancel,

    alert,
    confirm,
    notify(type: ToastLevel, msg: any, conf?: ToastConf): void {
      if (msg.startsWith('_')) return;

      conf = { closeButton: true, ...conf };
      toast[type]
        ? toast[type](msg, conf)
        : console.warn('[notify]', type, msg);

      console.log('[notify]', type, msg);
    },

    copy: (contents, options) => {
      if (options === void 0) {
        options = {};
      }

      const { t } = adapter.useI18n();
      const ret = copy(contents, options);
      ret &&
        (!options || options.shutup !== true) &&
        toast.info(t('Copy To Clipboard'));

      return ret;
    }
  };

  return env;
}
