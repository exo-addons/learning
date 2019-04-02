import Vue from 'vue'
import Router from 'vue-router'
import AppHome from '../components/AppCardMain.vue'
import AppCreateCours from '../components/AppCreateCours.vue'
import AppCreateGroup from '../components/AppCreateGroup.vue'
import AppEditCours from '../components/AppEditCours.vue'



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
    }
      ,
      {
          path: '/createGroup',
          name: 'AppCreateGroup',
          component: AppCreateGroup
      },
      {
          path: '/EditCours',
          name: 'AppEditCours',
          component: AppEditCours
      }
  ]
})
