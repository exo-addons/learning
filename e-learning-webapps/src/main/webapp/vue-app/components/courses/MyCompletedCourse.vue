<template>
  <div>
    <v-container class="my-5" elevation-10>
      <v-layout>
        <v-flex md12>
          <app-edit-cours-tab />
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex md12>
          <v-expansion-panel>
            <v-expansion-panel-content v-for="(c,index) in courses" :key="c.idCourse">
              <div slot="header" class="subheading font-weight-bold py-1">{{ c.nameCourse }}</div>
              <v-card>
                <v-card-text class="px-4 grey--text">
                  <v-layout>
                    <v-flex md10>
                      <div class="font-weight-bold">Date Début:&nbsp;{{ c.dateStart }}</div>
                      <div class="font-weight-bold">Date Fin:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ c.dateEnd }}</div>
                    </v-flex>
                    <v-flex>
                      <div class="btn-complet-edit">
                      <v-btn
                        fab
                        dark
                        small
                        color="#BDBDBD"
                      @click.prevent="deleteCourse(c.idCourse)">
                        <i class="far fa-trash-alt"></i>
                      </v-btn>
                      </div>
                    </v-flex>
                  </v-layout>
                </v-card-text>
              </v-card>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
    import AppEditCoursTab from './AppEditCoursTabMain.vue'
    import axios from 'axios'
export default {
  components:{AppEditCoursTab},
  data() {
    return {
      courses:[],
    }
  },
  computed: {
    myProjects() {
      return this.projects.filter(project => {
        return project.person 
      })
    }
  },
  mounted: function(){
            this.getAllCourses();
        },

  methods:{
       getAllCourses: function()
            {
                axios.get('/portal/rest/cours/allPublishedByUser/COMPLETED').then((response) => {
                    this.courses = response.data;
                }).catch(error => {
                    this.errored = true
                })
            },
      deleteCourse: function(event)
      {
          axios.delete('/portal/rest/cours/delete/'+event, {
              headers : {
                  'Content-Type' : 'application/json'
              }

          }).then(
             this.courses=this.courses.filter(d=>d.idCourse !==event)
          )
      }
  },
}
</script>
<style>
  .btn-complet-edit{
    margin-left: 59%;
  }
</style>
