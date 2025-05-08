<script lang="ts">
  const { width, height, align, children } = $props();

  const sizeToClass = (prefix, size) => size && (prefix + size);
  const alignToClass = (align) => align && ('align-' + (align.h || 'auto') + '_' + (align.v || 'auto'));
</script>

<table class={[
    'xui-layout', 'linear', 'table',
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align)
  ]}>
  {@render children?.()}
</table>

<style lang="less">
  .table-tr {
    border: 0;
  }
  .table-td {
    .table-tr();
    padding: 0;
    overflow: hidden;
    /* 禁止换行 */
    white-space: nowrap;
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
    }
  }

  /** https://lesscss.org/ */
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

  .align-center_center {
    margin: auto;  /* 自动填充剩余空间，实现居中 */
  }
  .align-center_start {
    margin-left: auto;
    margin-right: auto;
  }
</style>
