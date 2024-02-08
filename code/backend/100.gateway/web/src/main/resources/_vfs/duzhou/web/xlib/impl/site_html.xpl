<?xml version="1.0" encoding="UTF-8" ?>

<!--
  - 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
  - Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
  -
  - This program is free software: you can redistribute it and/or modify
  - it under the terms of the GNU Lesser General Public License as published by
  - the Free Software Foundation, either version 3 of the License, or
  - (at your option) any later version.
  -
  - This program is distributed in the hope that it will be useful,
  - but WITHOUT ANY WARRANTY; without even the implied warranty of
  - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  - GNU Lesser General Public License for more details.
  -
  - You should have received a copy of the GNU Lesser General Public License
  - along with this program.
  - If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
  -->

<c:unit xmlns:c="c" xmlns:thisLib="thisLib">
    <c:script><![CDATA[
        //import xx;

        const siteElementId = 'app';
        const spinnerBgBase64 = site.layout.spinner;
    ]]></c:script>

    <c:unit><![CDATA[
        <!DOCTYPE html>
    ]]></c:unit>
    <html lang="en">
        <head>
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <link rel="icon" href="${site.logo}" />
            <title>
            </title>

            <!-- 定义加载动画和背景色 -->
            <style><![CDATA[
                * { box-sizing: border-box; }

                html, body {
                    margin: 0; padding: 0;
                    min-width: 0; min-height: 0;
                    /* 修改默认的 AMIS 加载动画和背景色: https://baidu.github.io/amis/zh-CN/style/css-vars#%E5%9B%BE%E7%89%87 */
                    --Spinner-bg: url('${spinnerBgBase64}');
                    --body-bg: ${site.layout.bgColor};
                }
                html, body, #${siteElementId} {
                    width: 100%; height: 100%;
                }

                .loading::after {
                    z-index: 10000; opacity: 1;
                    transition: opacity 0.5s ease-out;
                    transition-delay: 0.2s;
                    pointer-events: none;

                    overflow: hidden;
                    position: absolute; top: 0;
                    width: 100%; height: 100%;

                    content: '';
                    background-image: var(--Spinner-bg);
                    background-color: var(--body-bg);
                    background-position: center;
                    background-size: 12rem;
                    background-repeat: no-repeat;
                }
                .loading.done::after {
                    opacity: 0;
                }

                .loading > * {
                    opacity: 0;
                    transition: opacity 0.5s ease-in;
                }
                .loading.done > * {
                    opacity: 1;
                }
            ]]></style>
        </head>
        <body class="loading">
            <div id="${siteElementId}" />

            <c:for items="${site.layout.styles}" var="style">
                <link rel="stylesheet" href="${style.url}" />
            </c:for>

            <!-- 站点配置数据，填充布局函数和站点资源 -->
            <script><![CDATA[
                window.__APP_SITE_CONFIG__ = {
                    el: '#${siteElementId}',
                    layout: async () => {
                        return ${site.renderLayout()};
                    }
                };
            ]]></script>
            <c:for items="${site.layout.scripts}" var="script" index="index">
                <c:choose>
                    <when test="${index == 0}">
                        <script type="module" src="${script.url}" />
                    </when>
                    <otherwise>
                        <link rel="modulepreload" href="${script.url}" />
                    </otherwise>
                </c:choose>
            </c:for>
        </body>
    </html>
</c:unit>
