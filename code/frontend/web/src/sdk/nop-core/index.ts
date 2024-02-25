/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */

import { registerAdapter } from './adapter';
import { PageApis } from './api';
import { FetcherRequest, fetcherOk } from './core';
import { transformPageJson } from './page';

export * from './lib';

registerAdapter({
  async fetchDict(dictName: string, options: FetcherRequest) {
    return PageApis.DictProvider__getDict(
      dictName,
      options.silent || false
    ).then((res) => fetcherOk(res));
  },

  async fetchPageAndTransform(pagePath: string, options: FetcherRequest) {
    return PageApis.PageProvider__getPage(pagePath).then(async (pageData) => {
      pageData = await transformPageJson(pagePath, pageData);

      return fetcherOk(pageData);
    });
  },

  getPage(pageUrl: string) {
    return PageApis.PageProvider__getPage(pageUrl);
  }
});
