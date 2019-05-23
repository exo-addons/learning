import Vue from 'vue'
import Router from 'vue-router'
import AppHome from '../components/courses/CoursPublishedCard.vue'
import AppStepper from '../components/courses/AppStepper.vue'
import AppCoursPublished from '../components/courses/CoursPublishedCard.vue'
import MyDrafetCours from '../components/courses/MyCoursDrafetCard.vue'
import AppEditCards from '../components/courses/MyPublishedCourse.vue'
import AppEditCoursTab from '../components/courses/AppEditCoursTabMain.vue'
import AppCoursTabform from '../components/courses/MyCompletedCourse.vue'
import AppStepperExam from '../components/exam/AppStepperExam.vue'
import AppListQcm from '../components/exam/AppListQcm.vue'
import AppEditQcm from '../components/exam/AppEditQcm.vue'
import AppPassExam from '../components/exam/PassExam.vue'
import AppQcmCreate from '../components/exam/AppCreateQcm.vue'
import AppContentCourse from '../components/courses/ContentCourse.vue'
import AppSearchQcm from '../components/exam/AppSearchQcm.vue'
import GestionQcm from '../components/exam/GestionQcm.vue'
import ChooseExam from '../components/exam/ChooseExam.vue'
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
          path: '/EditMyDrafetCours',
          name: 'MyDrafetCours',
          component: MyDrafetCours
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
      },
      {
          path: '/createOcm',
          name: 'AppQcmCreate',
          component: AppQcmCreate
      },
      {
          path: '/listeQcm',
          name: 'AppListQcm',
          component: AppListQcm
      },
      {
          path: '/editQcm',
          name: 'AppEditQcm',
          component: AppEditQcm
      },
      {
          path: '/passExam',
          name: 'AppPassExam',
          component: AppPassExam
      },
      {
          path: '/contentCourse',
          name: 'AppContentCourse',
          component: AppContentCourse
      },
      {
          path: '/configQcm',
          name: 'AppSearchQcm',
          component: AppSearchQcm
      },
      {
          path: '/GestionQcm',
          name: 'AppGestionQcm',
          component: GestionQcm
      },

      {
          path: '/ChooseExam',
          name: 'ChooseExam',
          component: ChooseExam
      }

  ]
})
