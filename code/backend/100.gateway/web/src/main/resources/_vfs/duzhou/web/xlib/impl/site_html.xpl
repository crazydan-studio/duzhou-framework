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

<!--
Note：在 xml 内必须有一个根节点，但在构造 dsl 时该节点需要被忽略，
仅挂载其子节点，所以，在 xpl 中通过 c:unit 来承担该处理该需求
-->
<c:unit xmlns:c="c" xmlns:thisLib="thisLib">
    <c:script><![CDATA[
        // 已导入的 class 可以在 ${} 中直接引用
        import io.nop.core.lang.json.JsonTool;

        const siteElementId = 'app';
        // Note：在 <title/> 内调用 xpl 函数将得不到其返回值，
        // 只能在该代码段内动态调用，再通过变量引用得到其返回值
        const title = xpl `<thisLib:GenSiteTitle site="${site}" />`;

        const spinnerImageDataUrl = xpl `
            <thisLib:GenImageDataUrl path="${site.layout.spinner}" />
        `;
    ]]></c:script>

    <html lang="${site.locale}">
        <head>
            <meta name="charset" charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <link name="icon:logo" rel="icon" href="${site.logo}" />
            <title>${title}</title>

            <style name="style:global"><![CDATA[
                * { box-sizing: border-box; }

                html, body {
                    margin: 0; padding: 0;
                    min-width: 0; min-height: 0;
                    /* 修改默认的 AMIS 加载动画和背景色: https://baidu.github.io/amis/zh-CN/style/css-vars#%E5%9B%BE%E7%89%87 */
                    --Spinner-bg: url('${spinnerImageDataUrl}');
                    --body-bg: ${site.layout.bgColor};
                }
                html, body, #${siteElementId} {
                    width: 100%; height: 100%;
                }
            ]]></style>
            <style name="style:loading"><![CDATA[
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
                <link name="css:${style.name}" rel="stylesheet" href="${style.url}" />
            </c:for>

            <!-- 站点配置数据，填充布局函数和站点资源 -->
            <script name="js:site-config"><![CDATA[
                window.__APP_SITE_CONFIG__ = {
                    el: '#${siteElementId}',
                    layout: async () => {
                        return ${JsonTool.stringify(site.renderLayout())};
                    }
                };
            ]]></script>
            <c:for items="${site.layout.scripts}" var="script" index="index">
                <c:choose>
                    <when test="${index == 0}">
                        <script name="js:${script.name}" type="module" src="${script.url}" />
                    </when>
                    <otherwise>
                        <link name="js:${script.name}" rel="modulepreload" href="${script.url}" />
                    </otherwise>
                </c:choose>
            </c:for>
        </body>
    </html>
</c:unit>
