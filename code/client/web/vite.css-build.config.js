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

import { glob } from 'glob';
import { defineConfig } from 'vite';

import viteCompression from 'vite-plugin-compression';
import postcssImageInliner from 'postcss-image-inliner';
import postcssComments from 'postcss-discard-comments';

// https://www.npmjs.com/package/glob
const cssFiles = await glob(__dirname + '/public/pages/**/index.scss', {});
const cssEntries = {};
cssFiles.forEach((file) => {
  const name = file.replaceAll(
    /^.+\/public\/pages\/(.+)\.scss$/g,
    'pages/$1.css'
  );
  cssEntries[name] = file;
});

// 专用于构建站点的 css
export default defineConfig(({ command, mode }) => {
  return {
    build: {
      // 不清空输出产物目录
      emptyOutDir: false,
      // 不不复制 public 目录
      copyPublicDir: false,
      rollupOptions: {
        treeshake: true,
        input: cssEntries,
        output: {
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
        }
      }
    },
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
    plugins: [
      // https://github.com/vbenjs/vite-plugin-compression
      viteCompression({
        algorithm: 'gzip',
        ext: '.gz',
        deleteOriginFile: false,
        filter: (file) => {
          return file.includes('/assets/css/pages/') && !file.endsWith('.gz');
        }
      })
    ]
  };
});
