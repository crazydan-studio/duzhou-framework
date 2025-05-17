<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { type, width, height, align, gap, children } = $props();
</script>

<div class={[
    'xui-layout', 'linear', type,
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align),
    gap && 'has-gap',
  ]}
  style:--linear-col-gap={gap && gap.col}
  style:--linear-row-gap={gap && gap.row}
>
  {@render children?.()}
</div>

<style lang="less">
  /** https://lesscss.org/ */
  @import "./css/layout.less";

  .row {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
  }
  .column {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
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
  .row.has-gap {
    :global {
      & > :is(.row, .column, .item):not(:last-child) {
        border-right: var(--linear-row-gap) solid transparent;
      }
    }
  }
  .column.has-gap {
    :global {
      & > :is(.row, .column, .item):not(:last-child) {
        border-bottom: var(--linear-col-gap) solid transparent;
      }
    }
  }
</style>
