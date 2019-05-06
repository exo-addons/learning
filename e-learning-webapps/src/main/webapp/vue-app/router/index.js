import Vue from 'vue'
import Router from 'vue-router'
import AppHome from '../components/courses/AppCardMain.vue'
import AppStepper from '../components/courses/AppStepper.vue'
import AppCoursPublished from '../components/courses/AppCardMain.vue'
import AppEditCards from '../components/courses/AppCardEditCours.vue'
import AppEditCoursTab from '../components/courses/AppEditCoursTabMain.vue'
import AppCoursTabform from '../components/courses/AppEditCours.vue'
import AppStepperExam from '../components/exam/AppStepperExam.vue'
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
      name: 'AppStepper',
      component: AppStepper
    },
      {
          path: '/EditCoursCard',
          name: 'AppEditCards',
          component: AppEditCards
      },
      {
          path: '/EditCoursPublished',
          name: 'EditCoursPublished',
          component: AppCoursPublished
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
      },
      {
          path: '/createExam',
          name: 'AppStepperExam',
          component: AppStepperExam
      }
  ]
})
