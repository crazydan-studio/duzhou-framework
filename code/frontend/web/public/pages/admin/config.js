window.__APP_SITE_CONFIG__ = {
  container: '#app',
  data: {
    API_HOST: 'https://3xsw4ap8wah59.cfc-execute.bj.baidubce.com'
  },
  schema: {
    type: 'app',
    logo: '/logo.svg',
    brandName: '后台管理',
    showBreadcrumb: false,
    header: {
      type: 'tpl',
      className: 'w-full',
      tpl: '<div class="flex justify-between"><div>顶部区域左侧</div><div>顶部区域右侧</div></div>'
    },
    pages: [
      {
        url: '/',
        schema: {
          type: 'page',
          title: '首页',
          body: '欢迎'
        }
      },
      {
        label: '应用建模',
        children: [
          {
            label: '应用',
            url: 'app-dev/app',
            icon: 'fa-solid fa-globe',
            schemaApi: 'get:/pages/admin/demo/app-dev.json'
          },
          {
            label: '域定义',
            url: 'app-dev/domain',
            icon: 'fa-solid fa-globe',
            schemaApi: 'get:/pages/admin/demo/app-dev-domain.json'
          },
          {
            label: '字典定义',
            url: 'app-dev/dict',
            icon: 'fa-solid fa-globe',
            schemaApi: 'get:/pages/admin/demo/app-dev-dict.json'
          },
          {
            label: '应用模块',
            url: 'app-dev/module',
            icon: 'fa-solid fa-globe',
            schemaApi: 'get:/pages/admin/demo/app-dev-module.json'
          },
          {
            label: '应用模型',
            url: 'app-dev/entity',
            icon: 'fa-solid fa-globe',
            schemaApi: 'get:/pages/admin/demo/app-dev-entity.json'
          }
        ]
      },
      {
        label: '应用设计',
        children: [
          {
            label: '应用管理',
            url: 'app',
            icon: 'fa-solid fa-globe',
            schemaApi: 'get:/pages/admin/demo/app.json',
            children: [
              {
                label: '应用设计器',
                url: 'dsl-editor',
                icon: 'fa fa-cubes',
                // visible: false,
                schemaApi: 'get:/pages/admin/demo/dsl-editor.json'
              }
            ]
          }
        ]
      },
      {
        label: '示例 1',
        children: [
          {
            label: '基础表单',
            url: 'form-basic',
            icon: 'fa fa-gears',
            schemaApi: 'get:/pages/admin/demo/form-basic.json'
          },
          {
            label: '复杂表单',
            url: 'form-advance',
            icon: 'fa fa-gears',
            schemaApi: 'get:/pages/admin/demo/form-advance.json'
          },
          {
            label: '表单向导',
            url: 'wizard',
            icon: 'fa fa-gears',
            schemaApi: 'get:/pages/admin/demo/wizard.json'
          }
        ]
      },
      {
        label: '示例 2',
        children: [
          {
            label: '外部链接',
            link: 'http://baidu.gitee.io/amis'
          },
          {
            label: '远端模板页面',
            url: 'remote',
            icon: 'fa fa-gears',
            // Note：解析 schemaApi 的数据域为当前组件的配置对象，未传递全局的 data 或 context
            API_HOST: 'https://3xsw4ap8wah59.cfc-execute.bj.baidubce.com',
            schemaApi: '${API_HOST}/api/amis-mock/mock2/service/form?tpl=tpl3'
          },
          {
            label: 'JSONP 示例',
            url: 'jsonp',
            icon: 'fa fa-gears',
            schemaApi: 'jsonp:/pages/admin/demo/jsonp.js?callback=jsonpCallback'
          }
        ]
      }
    ]
  }
};
