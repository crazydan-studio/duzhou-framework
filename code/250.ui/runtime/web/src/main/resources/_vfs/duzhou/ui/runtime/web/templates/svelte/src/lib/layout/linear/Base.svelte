<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { type, width, height, align, gap, padding, children } = $props();
</script>

<div class={[
    'xui-layout', 'linear', type,
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align),
    gap && 'has-gap',
    padding && 'has-padding',
  ]}
  style:--linear-col-gap={gap && gap.col}
  style:--linear-row-gap={gap && gap.row}
  style:padding-left={padding && padding.left}
  style:padding-right={padding && padding.right}
  style:padding-top={padding && padding.top}
  style:padding-bottom={padding && padding.bottom}
>
  {@render children?.()}
</div>

<style lang="less">
  /** https://lesscss.org/ */
  @import "./css/layout.less";

  .linear {
    display: flex;
    /* 在多行时，无法控制最后一行的底部间隔，
    故而，只允许始终为单行，通过滚动条显隐配置处理溢出 */
    flex-wrap: nowrap;
  }

  .row {
    .linear();
    flex-direction: row;
  }
  .column {
    .linear();
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
