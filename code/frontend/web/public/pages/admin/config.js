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
        children: [
          {
            label: '设计器',
            url: 'designer',
            icon: 'fa fa-cubes',
            schemaApi: 'get:/pages/admin/demo/designer.json'
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
