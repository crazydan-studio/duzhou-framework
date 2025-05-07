<script lang="ts">
  let { type, width, height, align, children } = $props();
</script>

<div class={['xui-layout', 'linear', type, width && ('width-' + width), height && ('height-' + height)]}>
  {@render children?.()}
</div>

<style>
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
  .width-fill_remains {
  }
  .height-fill_remains {
  }
</style>
