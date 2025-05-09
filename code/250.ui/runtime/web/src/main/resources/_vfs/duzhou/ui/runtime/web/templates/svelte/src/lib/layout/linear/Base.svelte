<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { type, width, height, align, children } = $props();
</script>

<div class={[
    'xui-layout', 'linear', type,
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align)
  ]}>
  {@render children?.()}
</div>

<style lang="less">
  /** https://lesscss.org/ */
  .row {
    display: flex;
    flex-direction: row;
  }
  .column {
    display: flex;
    flex-direction: column;
  }
  .item {

    :global {
      & > .xui-block:first-child > * {
        width: 100%;
        height: 100%;
      }
    }
  }

  .width-match_parent {
    width: 100%;
  }
  .height-match_parent {
    height: 100%;
  }
  .width-wrap_content {
    width: fit-content;
  }
  .height-wrap_content {
    height: fit-content;
  }
  .width-fill_remains, .height-fill_remains {
    flex: 1;
  }

  /* 通过 margin auto 自动填充剩余空间，实现居中 */
  .align-h-start {
    margin-right: auto;
  }
  .align-h-end {
    margin-left: auto;
  }
  .align-h-center {
    .align-h-start();
    .align-h-end();
  }
  .align-v-start {
    margin-bottom: auto;
  }
  .align-v-end {
    margin-top: auto;
  }
  .align-v-center {
    .align-v-start();
    .align-v-end();
  }
</style>
