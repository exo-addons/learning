import './initComponents';

import * as tutoService from '../js/tutoService';
import * as themeService from '../js/themeService';

Vue.use(Vuetify);
const vuetify = new Vuetify({
  dark: true,
  iconfont: '',
});

const appId = 'elearningPreview_app';

// getting language of user
const lang = eXo && eXo.env && eXo.env.portal && eXo.env.portal.language || 'en';

const url = `${eXo.env.portal.context}/${eXo.env.portal.rest}/i18n/bundle/locale.addon.elearning-${lang}.json`;

window.Object.defineProperty(Vue.prototype, '$tutoService', {
  value: tutoService,
});

window.Object.defineProperty(Vue.prototype, '$themeService', {
  value: themeService,
});

// getting locale ressources
export function init() {
  exoi18n.loadLanguageAsync(lang, url)
    .then(i18n => {
      // init Vue app when locale ressources are ready
      new Vue({
        template: `<elearning-preview id="${appId}" />`,
        i18n,
        vuetify,
      }).$mount('#elearningPreview_app');
    });
}