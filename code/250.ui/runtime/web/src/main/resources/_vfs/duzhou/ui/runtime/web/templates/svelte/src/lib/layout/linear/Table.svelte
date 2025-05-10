<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { width, height, align, children } = $props();
</script>

<table class={[
    'xui-layout', 'linear', 'table',
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align),
  ]}>
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
      & tr {
        .table-tr();
      }
      & td {
        .table-td();
        overflow: hidden;
        /* 禁止换行 */
        white-space: nowrap;
      }
    }
  }
</style>
