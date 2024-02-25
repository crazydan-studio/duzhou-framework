window.__APP_SITE_CONFIG__ = {
  el: document.querySelector('#app'),
  layout: () => {
    return {
      data: {},
      // schemaApi: '@query:PageProvider__getPage?path=/duzhou/web/pages/auth/signin.page.xml'
      schemaApi: '/pages/signin/page.json'
    };
  }
};
