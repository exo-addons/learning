<template>
  <v-container class="my-5" elevation-10>
    <v-layout>
      <v-flex md12>
        <app-edit-cours-tab />
      </v-flex>
    </v-layout>
    <v-layout>
      <v-flex md12>
        <v-stepper
                v-model="e1"
                flat
                class="text-xs-center ma-3"
                height="650px">
          <v-stepper-header>
            <v-stepper-step
                    :complete="e1 > 1"
                    step="1"
                    color="blue">
              <h5 class="blue--text .headline">Ajouter des Cours</h5>
            </v-stepper-step>

            <v-divider />

            <v-stepper-step step="2" color="blue"><h5 class="blue--text .headline">Ajouter des Leçons</h5></v-stepper-step>
          </v-stepper-header>

          <v-stepper-content step="1">
            <!-- create course-->
            <notification :notifications="notifications" />
              <v-layout>
                  <v-flex>
              <app-create-course :course="courseInForm"
                               @testevent="submitAction"
                               @cancel="resetCourseInForm" />
                  </v-flex>
              </v-layout>
              <v-layout>
                  <v-flex md10>

                  </v-flex>
              <v-flex md2>
                <v-btn flat icon color="indigo">

                <i
                        class="fas fa-forward fa-3x"
                        style="color:#1867c0;"
                        @click.prevent="e1 = 2"></i>
                </v-btn>
              </v-flex>
            </v-layout>
          </v-stepper-content>

          <v-stepper-content step="2">
            <!-- create lesson-->
            <app-create-lesson />
            <v-layout>
              <v-flex md1>
                <v-btn flat icon color="indigo">
                <i
                        class="fas fa-backward fa-3x"
                        style="color:#1867c0;"
                        @click.prevent="e1 = 1"></i>
                </v-btn>
              </v-flex>
            </v-layout>
          </v-stepper-content>
        </v-stepper>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
    import AppCreateCourse from './AppCreateCourse.vue'
    import AppCreateLesson from './AppCreateLesson.vue'
    import AppEditCoursTab from './AppEditCoursTabMain.vue'
    import Notification from '../commun/notifications.vue';



    const initialData = () => {
        return {
            e1: 0,
            courseInForm: {
                idCategory:null,
                nameCourse:'',
                visibilityCourse:false,
                status:'COMPLETED',
                dateStart:null,
                dateEnd:null,
                rewardCourse:'',
                icon:null,
            },
            addSuccess: false,
            addError: false,
            updateMessage: '',
            courses: [],
            categories:[],

        }
    }
    export default {
        components:{AppEditCoursTab,AppCreateCourse,AppCreateLesson,'notification' : Notification},
        data: initialData,
        methods: {
            quitter:function() {
                this.$router.push('/')
            }
        },

    }
</script>
<style>
  .para_border{
    width:600px;
    border: 4px dotted lightslategrey;
  }
  .select_style {
    border: 1px solid;
    border-color: #929b9c;
    border-radius: 5px;
    outline: none;
    width: 100%;
    height: 35px;
  }
  i.uiIconMiniArrowDown.uiIconLightGray {
    color: #ffffff;
    font-size: 23px;
    top: 29px;
    float: right;
    margin-right: 10px;
  }
  input#dateStart {
    background: transparent;
    cursor: pointer;
  }
  input#dateEnd {
    background: transparent;
    cursor: pointer;
  }
</style>
