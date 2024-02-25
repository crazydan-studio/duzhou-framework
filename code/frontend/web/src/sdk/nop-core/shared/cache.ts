/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */

import LRUCache from 'lru-cache';
import type { Options } from 'lru-cache';
import { cloneDeep } from 'lodash-es';

export type AsyncCacheLoader<T> = (key: string) => Promise<T>;

export type AsyncCache<T> = {
  get(key: string, loader: AsyncCacheLoader<T>): Promise<T>;
  delete(key: string): void;
  clear(): void;
};

export type AsyncCacheOptions<T> = Options<string, T>;

export function createAsyncCache<T>(
  options: AsyncCacheOptions<any>
): AsyncCache<T> {
  const cache = new LRUCache<string, Promise<T>>(options);
  return {
    get(key, loader) {
      let promise = cache.get(key);
      if (promise) {
        return promise.then((v) => v && cloneDeep(v));
      }
      promise = loader(key);
      cache.set(key, promise);
      return promise
        .then((v) => v && cloneDeep(v))
        .catch((e) => {
          cache.delete(key);
          throw e;
        });
    },

    delete(key) {
      cache.delete(key);
    },

    clear() {
      cache.clear();
    }
  };
}
