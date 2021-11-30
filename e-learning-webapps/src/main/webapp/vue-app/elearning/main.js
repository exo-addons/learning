import './initComponents.js';

import * as tutoService from '../js/tutoService';
import * as themeService from '../js/themeService';

Vue.use(Vuetify);
const vuetify = new Vuetify({
  dark: true,
  iconfont: '',
});

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
        template: '<elearning-management id="elearning_app"></elearning-management>',
        i18n,
        vuetify,
      }).$mount('#elearning_app');
    });
}