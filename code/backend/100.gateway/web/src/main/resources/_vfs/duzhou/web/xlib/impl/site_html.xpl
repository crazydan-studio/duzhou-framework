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
Note：在 xml 内必须有且仅有一个根节点，也可以通过 c:unit 来作为根节点
-->
<html xmlns:c="c" xmlns:thisLib="thisLib"
      lang="${site.locale}"
>
    <c:script><![CDATA[
        // 已导入的 class 可以在 ${} 中直接引用
        import io.crazydan.duzhou.framework.gateway.core.utils.WebStaticResourcesHelper;

        const siteElementId = 'app-site';
        // Note：在 <title/> 内调用 xpl 函数将得不到其返回值，
        // 只能在该代码段内动态调用，再通过变量引用得到其返回值
        const title = xpl `<thisLib:GenSiteTitle site="${site}" />`;

        const logoMimeType = WebStaticResourcesHelper.getImageMimeType(site.logo);
        const spinnerImageDataUrl = xpl `
            <thisLib:GenImageDataUrl path="${site.layout.spinner}" />
        `;
        const siteLayoutConfig = {
            ...site.getLayoutConfig(),
            container: '#' + siteElementId
        };
    ]]></c:script>

    <head>
        <meta name="charset" charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link name="icon:logo" rel="icon" type="${logoMimeType}" href="${site.logo}" />
        <title>${title}</title>

        <style name="style:global"><![CDATA[
            * { min-width: 0; min-height: 0; box-sizing: border-box; }

            html, body {
                margin: 0; padding: 0;
                width: 100%; height: 100%;
                --body-bg: ${site.layout.bgColor};
                /* 修改默认的 AMIS 加载动画和背景色: https://baidu.github.io/amis/zh-CN/style/css-vars#%E5%9B%BE%E7%89%87 */
                --Spinner-bg: url('${spinnerImageDataUrl}');
                /* 加载动画的实际长宽比为 1:2，故需按比例重置图片高度以使其长宽比一致 */
                --Spinner-height: calc(var(--Spinner-width) / 2);
                --Spinner--lg-height: calc(var(--Spinner--lg-width) / 2);
                --Spinner--sm-height: calc(var(--Spinner--sm-width) / 2);
            }
        ]]></style>
        <style name="style:loading"><![CDATA[
            .loading::after {
                z-index: 10000; opacity: 1;
                transition: opacity 0.5s ease-out;
                transition-delay: 0.2s;
                pointer-events: none;

                overflow: hidden; position: absolute;
                top: 0; bottom: 0; left: 0; right: 0;

                content: '';
                background-image: var(--Spinner-bg);
                background-color: var(--body-bg);
                background-position: center;
                background-size: 10rem;
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

        <!-- 填充站点的布局配置数据 -->
        <script name="js:site-config"><![CDATA[
            window.__APP_SITE_CONFIG__ = ${$JSON.stringify(siteLayoutConfig)};
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
