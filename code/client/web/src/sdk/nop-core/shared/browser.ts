/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */

let timer: any;

export function changeBrowserTitle(title: string, delay?: boolean) {
  if (timer) {
    clearTimeout(timer);
    timer = null;
  }

  if (delay) {
    timer = setTimeout(() => {
      document.title = title || '';
    }, 100);
  } else {
    document.title = title || '';
  }
}
