<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { type, width, height, align, children } = $props();
</script>

<div class={[
    'xui-layout', 'linear', type,
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align),
  ]}>
  {@render children?.()}
</div>

<style lang="less">
  /** https://lesscss.org/ */
  @import "./css/layout.less";

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
        .width-match_parent();
        .height-match_parent();
      }
    }
  }
</style>
