<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { width, height, align, gap, children } = $props();
</script>

<table class={[
    'xui-layout', 'linear', 'table',
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align),
    gap && 'has-gap',
  ]} style:--linear-table-col-gap={gap && gap.col}>
  {@render children?.()}
</table>

<style lang="less">
  /** https://lesscss.org/ */
  @import "./css/common.less";

  .table-tr {
    border: 0;
  }
  .table-td {
    .table-tr();
    padding: 0;
  }

  .table {
    .table-td();
    /* 合并边框，消除 border-spacing */
    border-collapse: collapse;

    :global {
      tr {
        .table-tr();
      }
      td {
        .table-td();
        overflow: hidden;
        /* 禁止换行 */
        white-space: nowrap;
      }

      /* 通过在布局节点上设置相应宽度的透明边框实现，从而避免影响以 margin 方式实现的对齐机制 */
      &.has-gap > tr:not(:last-child) {
        border-bottom: var(--linear-table-col-gap) solid transparent;
      }
    }
  }
</style>
