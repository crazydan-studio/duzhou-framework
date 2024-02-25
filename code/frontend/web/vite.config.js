/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

import path from 'path';
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

import viteVirtualHtml from 'vite-plugin-virtual-html';
import viteCompression from 'vite-plugin-compression';
import viteImagemin from 'vite-plugin-imagemin';
import viteDts from 'vite-plugin-dts';
import rollupCleanup from 'rollup-plugin-cleanup';
import { visualizer as rollupVisualizer } from 'rollup-plugin-visualizer';
import postcssImageInliner from 'postcss-image-inliner';
import postcssComments from 'postcss-discard-comments';

import pkg from './package.json';
import amisPkg from './node_modules/amis/package.json';

function absPath(...paths) {
  return path.join(__dirname, ...paths);
}

// https://vitejs.dev/config/#conditional-config
export default defineConfig(({ command, mode }) => {
  return {
    server: {
      // https://cn.vitejs.dev/config/server-options#server-proxy
      proxy: {
        '/graphql': 'http://localhost:8080'
      }
    },
    resolve: {
      // https://stackoverflow.com/questions/66043612/vue3-vite-project-alias-src-to-not-working#answer-70251354
      alias: {
        '@/': absPath('src/')
      }
    },
    plugins: [
      react(),
      viteDts({ rollupTypes: true, logDiagnostics: true }),
      ...getMinifyPlugins(),
      ...(mode === 'development' ? getDevPlugins() : [])
    ],
    css: {
      // https://cn.vitejs.dev/config/shared-options#css-postcss
      postcss: {
        plugins: [
          // https://www.npmjs.com/package/postcss-discard-comments
          postcssComments({
            removeAll: true
          }),
          // https://github.com/bezoerb/postcss-image-inliner
          postcssImageInliner({
            maxFileSize: 10240,
            b64Svg: true,
            filter: /^(background(?:-image)?)|(content)|(cursor)|(--.+-bg)/
          })
        ]
      }
    },
    build: {
      minify: true,
      target: 'ES2017',
      rollupOptions: {
        treeshake: true,
        // 指定入口脚本名称
        // https://rollupjs.org/configuration-options/#input
        // https://vitejs.dev/guide/build.html#multi-page-app
        input: {
          // 指定渲染引擎的构建产物名称
          // [`renderer-other-${pkg.version}`]: absPath('src/other/index.js'),
          [`renderer-amis-${pkg.version}`]: absPath('src/amis/index.js')
        },
        output: {
          // 入口脚本的位置
          entryFileNames: 'js/[name].js',
          // 各个依赖模块独立打包，并放在 js 目录下
          chunkFileNames: 'js/lib/[name].js',
          manualChunks: getLibChunks,
          // css 等资源文件名称
          assetFileNames: (asset) => {
            let ext = asset.name.replaceAll(/.+\.([^.]+)$/g, '$1');

            if (['ttf', 'eot', 'woff', 'woff2'].includes(ext)) {
              ext = 'fonts';
            } else if (ext !== 'css') {
              ext = 'images';
            }
            return `assets/${ext}/[name].[ext]`;
          }
        },
        plugins: [
          // Note：对 css 的处理耗时太长，暂时不用
          // // https://www.npmjs.com/package/rollup-plugin-cleanup#predefined-comment-filters
          // rollupCleanup({
          //   comments: 'license'
          // })
        ]
      }
    }
  };
});

function getDevPlugins() {
  return [
    // https://github.com/windsonR/vite-plugin-virtual-html?tab=readme-ov-file#usage
    // https://github.com/windsonR/vite-plugin-virtual-html/blob/64a3f8f/vite.config.ts
    viteVirtualHtml({
      indexPage: 'signin/',
      data: {
        site_title: '渡舟平台',
        site_logoImage: '/logo.svg',
        site_loadingImage: './public/loading.svg'
      },
      pages: {
        // signin/ -> /signin/
        // signin/index -> /signin/
        'signin/': {
          template: '/public/template.html',
          data: {
            site_subTitle: '用户登录',
            // 指定 lang="scss" 以支持通过 sass-loader 载入编译后的 scss
            styles:
              '<style lang="scss">@import "/public/pages/signin/index.scss";</style>',
            scripts: '<script src="/pages/signin/config.js"></script>'
          }
        },
        'admin/': {
          template: '/public/template.html',
          data: {
            site_subTitle: '后台管理',
            styles:
              '<style lang="scss">@import "/public/pages/admin/index.scss";</style>',
            scripts: '<script src="/pages/admin/config.js"></script>'
          }
        }
      }
    })
  ];
}

function getLibChunks(id) {
  function include_any(libs) {
    for (let lib of libs) {
      if (id.includes('/node_modules/' + lib + '/')) {
        return true;
      }
    }
    return false;
  }

  const libs = {
    [`amis-${amisPkg.version}`]: ['amis', 'amis-core'],
    [`amis-ui-${amisPkg.version}`]: ['amis-ui', 'amis-formula'],
    [`amis-editor-${amisPkg.version}`]: ['amis-editor'],
    react: ['react', 'react-dom', 'react-cropper'],
    echarts: ['echarts', 'zrender', 'echarts-wordcloud'],
    medias: ['video-react', 'hls.js', 'mpegts.js'],
    editors: ['monaco-editor', 'tinymce', 'codemirror', 'froala-editor'],
    offices: ['exceljs', 'xlsx', 'office-viewer', 'papaparse']
  };

  for (const [chunkName, libNames] of Object.entries(libs)) {
    if (include_any(libNames)) {
      return chunkName;
    }
  }
}

function getMinifyPlugins() {
  return [
    // https://www.npmjs.com/package/rollup-plugin-visualizer?activeTab=readme
    rollupVisualizer({
      open: false,
      filename: absPath('dist/stats.html')
    }),
    // https://github.com/vbenjs/vite-plugin-imagemin
    viteImagemin({
      svgo: {
        plugins: [
          {
            name: 'removeViewBox'
          },
          {
            name: 'removeEmptyAttrs',
            active: false
          }
        ]
      }
    }),
    // Note：不能对 viteImagemin 的产物压缩，只能压缩源文件？
    // https://github.com/vbenjs/vite-plugin-compression
    viteCompression({
      algorithm: 'gzip',
      ext: '.gz',
      deleteOriginFile: false,
      filter: (file) => {
        if (file.endsWith('/stats.html') || file.endsWith('.svg')) {
          return false;
        }
        return true;
      }
    })
  ];
}
