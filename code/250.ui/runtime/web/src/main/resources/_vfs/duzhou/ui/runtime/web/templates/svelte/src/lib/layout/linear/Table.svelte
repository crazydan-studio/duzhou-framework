<script lang="ts">
  import { sizeToClass } from '$lib/common/component/layout.ts';

  const { width, height, align, children } = $props();
</script>

<table class={[
    'xui-layout', 'linear', 'table',
    sizeToClass('width-', width), sizeToClass('height-', height)
  ]}>
  {@render children?.()}
</table>

<style lang="less">
  /** https://lesscss.org/ */
  .table-tr {
    border: 0;
  }
  .table-td {
    .table-tr();
    padding: 0;
  }

  .table {
    .table-td();
    margin: 0;
    /* 合并边框，消除 border-spacing */
    border-collapse: collapse;

    & :global tr {
      .table-tr();
    }
    & :global td {
      .table-td();
      overflow: hidden;
      /* 禁止换行 */
      white-space: nowrap;
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
</style>
