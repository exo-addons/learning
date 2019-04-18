import Vue from 'vue'
import Router from 'vue-router'
import AppHome from '../components/AppCardMain.vue'
import AppCreateCours from '../components/AppCreateCours.vue'
import AppEditCards from '../components/AppCardEditCours.vue'
import AppEditCoursTab from '../components/AppEditCoursTabMain.vue'
import AppCoursTabform from '../components/AppEditCours.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'AppHome',
      component: AppHome
    },
    {
      path: '/createCours',
      name: 'AppCreateCours',
      component: AppCreateCours
    },
      {
          path: '/EditCoursCard',
          name: 'AppEditCards',
          component: AppEditCards
      },
      {
          path: '/EditCoursTab',
          name: 'AppEditCoursTab',
          component: AppEditCoursTab
      },
      {
          path: '/CoursTabform',
          name: 'CoursTabform',
          component: AppCoursTabform
      }
  ]
})
