<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { type, width, height, align, gap, children } = $props();
</script>

<div class={[
    'xui-layout', 'linear', type,
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align),
    gap && 'has-gap',
  ]} style:--gap={gap}>
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
    display: inline-flex;
    align-items: stretch;

    :global {
      & > .xui-block > * {
        flex-grow: 1;
      }
    }
  }

  /* 通过在布局节点上设置相应宽度的透明边框实现，从而避免影响以 margin 方式实现的对齐机制 */
  .has-gap {
    :global {
      .item:not(:last-child) {
        border-right: var(--gap) solid transparent;
      }
    }
  }
</style>
