渡舟平台基础框架 - Web 客户端
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
- [React Flow](https://reactflow.dev/learn)：
  采用 HTML 绘制节点、SVG 绘制连线，整体集成和定制较容易，完成度也很高（AntV X6 对细节的处理则没那么理想）
