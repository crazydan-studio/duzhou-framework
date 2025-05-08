<script lang="ts">
  const { type, width, height, align, children } = $props();

  const sizeToClass = (prefix, size) => size && (prefix + size);
  const alignToClass = (align) => align && ('align-' + (align.h || 'auto') + '_' + (align.v || 'auto'));
</script>

<div class={[
    'xui-layout', 'linear', type,
    sizeToClass('width-', width), sizeToClass('height-', height),
    alignToClass(align)
  ]}>
  {@render children?.()}
</div>

<style lang="less">
  /** https://lesscss.org/ */
  .row {
    display: flex;
    flex-direction: row;
  }
  .column {
    display: flex;
    flex-direction: column;
  }
  .item {

    :global {
      & > .xui-block:first-child > * {
        width: 100%;
        height: 100%;
      }
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

  .align-center_center {
    margin: auto;  /* 自动填充剩余空间，实现居中 */
  }
  .align-center_start {
    margin-left: auto;
    margin-right: auto;
  }
</style>
