<script lang="ts">
  import { sizeToClass, alignToClass } from '$lib/common/component/layout.ts';

  const { type, width, height, align, gap, padding, children } = $props();
</script>

<!--
- 对 wrapper 应用布局节点的 align、width、height 和 gap 配置
  - align 采用 `margin: auto` 进行控制
  - gap 则采用 `border: Npx solid transparent` 进行控制
- 对 wrapper 的直接子节点应用布局节点的 border 和 padding 配置；
-->
<div class={[
    'xui-layout', 'linear', type, 'wrapper',
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align),
  ]}
>
  <div class={[
      'xui-layout', 'linear', type,
      padding && 'has-padding',
      gap && 'has-gap',
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
        border-right: var(--linear-row-gap) solid transparent;
      }
    }
  }
  .linear.column:not(.wrapper) {
    .flex();
    flex-direction: column;

    :global {
      &.has-gap > .wrapper:not(:last-child) {
        border-bottom: var(--linear-col-gap) solid transparent;
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
