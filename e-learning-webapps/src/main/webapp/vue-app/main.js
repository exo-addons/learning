import './initComponents.js';

Vue.use(Vuetify);
const vuetify = new Vuetify({
  dark: true,
  iconfont: '',
});

// getting language of user
const lang = eXo && eXo.env && eXo.env.portal && eXo.env.portal.language || 'en';

const url = `${eXo.env.portal.context}/${eXo.env.portal.rest}/i18n/bundle/locale.addon.elearning-${lang}.json`;

// getting locale ressources
export function init() {
  exoi18n.loadLanguageAsync(lang, url)
    .then(i18n => {
    // init Vue app when locale ressources are ready
      new Vue({
        template: '<elearning-management></elearning-management>',
        i18n,
        vuetify,
      }).$mount('#elearning_app');
    });
}