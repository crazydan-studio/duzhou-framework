window.__APP_SITE_CONFIG__ = {
  el: '#app',
  layout: async ([form]) => {
    return {
      data: {},
      schemaApi: form.url
    };
  },
  resources: [
    {
      id: 'signin-form',
      url: '/pages/signin/page.json'
    }
  ]
};
