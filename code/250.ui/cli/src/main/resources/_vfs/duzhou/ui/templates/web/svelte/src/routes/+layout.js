// https://svelte.dev/docs/kit/page-options#prerender
export const prerender = true;
// 启用客户端渲染，并禁用服务端渲染
// https://svelte.dev/docs/kit/page-options#csr
// https://svelte.dev/docs/kit/page-options#ssr
export const csr = true;
export const ssr = false;
// 按路由生成独立的 /xx/index.html 页面
// https://svelte.dev/docs/kit/page-options#trailingSlash
export const trailingSlash = 'always';
