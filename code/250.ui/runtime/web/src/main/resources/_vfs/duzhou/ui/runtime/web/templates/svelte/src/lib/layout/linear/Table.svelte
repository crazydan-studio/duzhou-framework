<script lang="ts">
  import { sizeToNative } from '$lib/common/utils.ts';
  import { layoutSizeToClass, layoutAlignToClass } from '$lib/common/component/layout.ts';

  const { width, height, align, gap, padding, children } = $props();
  const [widthClass, widthSpecified] = layoutSizeToClass('width-', width);
  const [heightClass, heightSpecified] = layoutSizeToClass('height-', height);
</script>

<!--
在 `border-collapse: collapse` 模式下：
- padding 只能采用透明 border 方式实现，直接设置 padding 是无效的；
-->
<div class={[
    'xui-layout', 'linear', 'table',
    widthClass, heightClass,
    layoutAlignToClass(align),
    gap && 'has-gap',
    padding && 'has-padding',
  ]}
  style:--linear-table-row-gap={gap && sizeToNative(gap.row || 0)}
  style:--linear-table-col-gap={gap && sizeToNative(gap.col || 0)}
  style:width={widthSpecified}
  style:height={heightSpecified}
  style:padding-left={padding && sizeToNative(padding.left)}
  style:padding-right={padding && sizeToNative(padding.right)}
  style:padding-top={padding && sizeToNative(padding.top)}
  style:padding-bottom={padding && sizeToNative(padding.bottom)}
>
  <table>
    {@render children?.()}
  </table>
</div>

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

  .table > table {
    .table-td();
    width: 100%;
    height: 100%;
    /* 默认行间无间隔 */
    border-spacing: 0;

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
    }
  }

  .table.has-gap > table {
    /* Note: 与 row/colum 布局保持一致，仅设置列方向上的间隔，水平方向的间隔为 0 */
    border-spacing: 0 var(--linear-table-col-gap);
  }
</style>
