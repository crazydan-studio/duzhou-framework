待办列表
=========================

这里记录的是实现层面的待办任务，整体的开发进度见
[这里](https://duzhou.crazydan.io/docs/platform/todo/framework)。

## gateway

- [x] 支持路由静态资源的 gzip 压缩文件：在请求资源含 `.gz` 压缩文件时，
  直接返回该压缩文件，避免在服务端重复压缩静态资源
  - 实现见 `QuarkusStaticResourceRouter.GzipStaticHandler`
- [x] 将 `QuarkusStaticResources` 迁移到 `gateway-web`
  模块中，以提升模块的内聚性
  - 同时更名为 `QuarkusStaticResourceRouter`
  - 所在模块需启用 Maven 插件 `org.jboss.jandex:jandex-maven-plugin`
    以生成 class 的索引文件 `META-INF/jandex.idx`，Quarkus
    依赖该索引来查找和创建 @ApplicationScoped 等标注的 Beans
- [ ] Web 站点入口 HTML 页面的生成放到 `site` DSL
  中，以避免使用全局变量，并可缓存其编译结果
  - 其 XPath 路径为 `site/layout/html`
