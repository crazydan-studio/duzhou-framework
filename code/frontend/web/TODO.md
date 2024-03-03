待办列表
======================

这里记录的是实现层面的待办任务，整体的开发进度见
[这里](https://duzhou.crazydan.io/docs/platform/todo/framework)。

- [x] 支持集成和构建 TypeScript 代码，以便于引入第三方的功能源码
- [x] 拆分构建 AMIS 代码，以充分利用前端的按需加载和并行加载机制
- [x] 重载或重写 AMIS 的 `App` 组件，以修改导航菜单项的 `href`，
  并在 `logo` 上添加站点链接，并且页面 `title` 需附加应用名称
- [x] 配置 AMIS 主题，以使其对所有主题都添加固定前缀，
  以便于按通用的 class 名称重载样式
  - `import { theme as registerTheme } from 'amis-core'`
    通过主题注册函数 `registerTheme` 设置当前主题的 `classPrefix` 为
    `amis-`：`registerTheme(theme, { classPrefix: 'amis-' })`
  - 从 `amis-ui` 中导入对应主题的 `${theme}.scss` 文件，并在
    `@import 'amis-ui/scss/themes/common.scss'`
    之前覆盖定义变量为 `$ns: 'amis-'`
