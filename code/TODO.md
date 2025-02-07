待办列表
=========================

这里记录的是实现层面的待办任务，整体的开发进度见
[这里](https://duzhou.crazydan.io/docs/platform/todo/framework)。

## gateway

- [x] 将 Web 站点入口 HTML 页面的生成功能放到 `XWebSite`
  模型中，以在确保站点入口 HTML 可差量化定制的前提下，支持对其编译结果的缓存
  - 以定义 `xdef:value="xpl-node"` 类型节点的方式，无法支持对
    xlib 函数的调用结果做差量，而在基础模型和派生模型的该节点下均直接定义
    html 节点，只能支持部分差量算法，不能做删除合并能够，
    因为，其合并策略是在站点 DSL 上实施的，而不是在 html 节点上
  - 直接在 `XWebSite` 中引入 `html.xdef` 模型的方式，只能在
    `x:post-extends` 的脚本中向各站点附加生成的 html 节点，
    但该方式同样无法支持差量化定制，因为 DSL 已经解析完毕，
    只能针对站点模型做后处理，无法再做差量合并
  - 以新增 `v-path` 类型的 `layout#html` 属性方式，
    可以实现在 `XWebSite` 中编译站点的 html 节点，
    虽然依然无法避免全局变量的使用，但是，却能够以更灵活的方式对站点
    HTML 页面做差量定制
