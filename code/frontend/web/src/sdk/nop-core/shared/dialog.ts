/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */

import { Component, createApp, nextTick } from 'vue';

export type DialogOptions = {
  component: Component;
  props?: Record<string, unknown>;
  onOk?: (data?: any) => boolean | void;
  onCancel?: () => void;
};

export function showDialog({
  component,
  props,
  onOk,
  onCancel
}: DialogOptions) {
  const div = document.createElement('div');
  const el = document.createElement('div');
  div.appendChild(el);
  document.body.appendChild(div);

  let dialogInstance: any = createApp(component, {
    ...props,
    handleOk,
    handleCancel
  });

  function update(newConfig) {
    if (dialogInstance) Object.assign(dialogInstance, newConfig || {});
  }

  function handleOk(...args) {
    if (onOk) {
      if (onOk.apply(null, args) === false) return false;
    }

    destroy();
    return true;
  }

  function handleCancel() {
    if (onCancel) {
      onCancel();
    }
    destroy();
  }

  function destroy() {
    nextTick(() => {
      if (dialogInstance && div.parentNode) {
        dialogInstance.unmount();
        dialogInstance = null;
        div.parentNode.removeChild(div);
      }
    });
  }

  dialogInstance.mount(el);

  return {
    cancel: handleCancel,
    destroy,
    update
  };
}
