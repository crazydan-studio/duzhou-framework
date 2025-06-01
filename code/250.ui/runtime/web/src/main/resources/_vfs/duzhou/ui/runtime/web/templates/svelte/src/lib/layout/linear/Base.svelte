<script lang="ts">
  import { sizeToNative } from '$lib/common/utils.ts';
  import { layoutSizeToClass, layoutAlignToClass } from '$lib/common/component/layout.ts';

  const { type, width, height, align, gap, padding, children } = $props();
  const [widthClass, widthSpecified] = layoutSizeToClass('width-', width);
  const [heightClass, heightSpecified] = layoutSizeToClass('height-', height);
</script>

<!--
- 对 wrapper 应用布局节点的 align、width、height 和 gap 配置
  - align 采用 `margin: auto` 进行控制
  - gap 则采用 `padding: Npx` 进行控制
- 对 wrapper 的直接子节点应用布局节点的 border、padding、shadow 等配置
  - 在子节点上应用有确定值的 width/height
-->
<div class={[
    'xui-layout', 'linear', type, 'wrapper',
    widthClass, heightClass,
    layoutAlignToClass(align),
  ]}
>
  <div class={[
      'xui-layout', 'linear', type,
      padding && 'has-padding',
      gap && 'has-gap',
    ]}
    style:--linear-col-gap={gap && sizeToNative(gap.col)}
    style:--linear-row-gap={gap && sizeToNative(gap.row)}
    style:width={widthSpecified}
    style:height={heightSpecified}
    style:padding-left={padding && sizeToNative(padding.left)}
    style:padding-right={padding && sizeToNative(padding.right)}
    style:padding-top={padding && sizeToNative(padding.top)}
    style:padding-bottom={padding && sizeToNative(padding.bottom)}
  >
    {@render children?.()}
  </div>
</div>

<style lang="less">
  /** https://lesscss.org/ */
  @import "./css/layout.less";

  .flex {
    display: flex;
    /* 在多行时，无法控制最后一行的底部间隔，
    故而，只允许始终为单行，通过滚动条显隐配置处理溢出 */
    flex-wrap: nowrap;
  }

  .linear.wrapper {
    :global {
      & > :is(.linear, .row, .column, .item) {
        width: 100%;
        height: 100%;
      }
    }
  }

  .linear.row:not(.wrapper) {
    .flex();
    flex-direction: row;

    :global {
      &.has-gap > .wrapper:not(:last-child) {
        padding-right: var(--linear-row-gap);
      }
    }
  }
  .linear.column:not(.wrapper) {
    .flex();
    flex-direction: column;

    :global {
      &.has-gap > .wrapper:not(:last-child) {
        padding-bottom: var(--linear-col-gap);
      }
    }
  }

  .linear.item:not(.wrapper) {
    display: inline-flex;
    align-items: stretch;

    :global {
      & > .xui-block > * {
        flex-grow: 1;
      }
    }
  }
</style>
