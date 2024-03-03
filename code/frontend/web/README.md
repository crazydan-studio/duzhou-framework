渡舟平台基础框架 - Web 前端
==========================================

## 本地开发

## 开发记录

本项目使用 Vue + Vite 技术框架进行开发。

- 创建 Vite Vue 项目

```bash
yarn create vite \
    duzhou-web \
    -- --template vue
```

> 需在 `package.json` 显式配置 `"type": "module"`
> 以支持 [ESM](https://juejin.cn/post/7169581968336617485)
> 模块导入/导出语法。

- 安装依赖

```bash
yarn install
```

- 启动开发服务

```bash
yarn run dev
```

- 安装第三方依赖和插件

```bash
yarn add \
    amis \
    sass
```

- 构建产物

```bash
yarn run build
```

## 参考

- 开发工具组合：[VS Code](https://code.visualstudio.com/)
  - [Vue Language Features (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur)
  - [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin)
- [Vite 中文文档](https://cn.vitejs.dev/guide/)
- [AMIS 文档](https://baidu.github.io/amis/)
- [Vite 打包优化，通过文件，图片，字体压缩优化大小](https://juejin.cn/post/7336637599895748644)
- [AntV X6](https://x6.antv.antgroup.com/tutorial/getting-started)：
  基于 HTML 和 SVG 的图编辑引擎，提供低成本的定制能力和开箱即用的内置扩展，方便快速搭建 DAG 图、ER 图、流程图、血缘图等应用
