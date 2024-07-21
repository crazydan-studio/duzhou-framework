/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */

import { PageObject } from './page';

let s_page: PageObject | undefined;

export function usePage() {
  return s_page;
}

export function providePage(page: PageObject) {
  s_page = page;
}

let s_scoped: any;

export function useScoped() {
  return s_scoped;
}

export function provideScoped(scoped: any) {
  s_scoped = scoped;
}

let s_scopedStore: any;

export function useScopedStore() {
  return s_scopedStore;
}

export function provideScopedStore(store: any) {
  s_scopedStore = store;
}

export function clearScoped() {
  s_page = undefined;
  s_scoped = undefined;
  s_scopedStore = undefined;
}
