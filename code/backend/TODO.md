待办列表
=========================

这里记录的是实现层面的待办任务，整体的开发进度见
[这里](https://duzhou.crazydan.io/docs/platform/todo/framework)。

## gateway

- [ ] 将 `QuarkusStaticResources` 迁移到 `gateway-web`
  模块中，提升模块的内聚性
- [ ] Web 站点入口 HTML 页面的生成放到 `site` DSL
  中，以避免使用全局变量，以缓存其编译结果
