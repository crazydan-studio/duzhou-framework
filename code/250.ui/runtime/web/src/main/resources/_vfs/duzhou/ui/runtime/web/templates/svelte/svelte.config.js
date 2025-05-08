import adapter from '@sveltejs/adapter-static';
import { vitePreprocess } from '@sveltejs/vite-plugin-svelte';

/** @type {import('@sveltejs/kit').Config} */
const config = {
  // Consult https://svelte.dev/docs/kit/integrations
  // for more information about preprocessors
  preprocess: vitePreprocess(),

  kit: {
    // https://svelte.dev/docs/kit/adapter-static
    adapter: adapter({
      pages: 'dist',    // 输出目录（默认 `build`）
      assets: 'dist',   // 资源目录（默认同 pages）
      fallback: null,    // SPA 回退页面（如设为 '200.html'）
      precompress: false, // 是否预压缩（gzip/brotli）
      strict: true,
    }),
    paths: {
      base: process.argv.includes('dev') ? '' : process.env.BASE_PATH
    },
  }
};

export default config;
