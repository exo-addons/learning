<template>
  <div>
    <v-container class="my-5" elevation-20>
      <v-layout>
        <v-flex md12>
          <app-edit-cours-tab />
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex md12>
          <v-expansion-panel>
            <v-expansion-panel-content v-for="c in courses" :key="c.idCourse">
              <div slot="header" class="subheading font-weight-bold py-1">{{ c.nameCourse}}</div>
              <v-card>
                <v-card-text class="px-4 grey--text">
                  <div class="font-weight-bold">Date Début:&nbsp;{{ c.dateStart }}</div>
                  <div class="font-weight-bold">Date Fin:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ c.dateEnd }}</div>
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
  created: function(){
            this.getAllCourses();
        },
  computed: {
    myProjects() {
      return this.projects.filter(project => {
        return project.person 
      })
    }
  },
  methods:{
       getAllCourses: function()
            {
                axios.get('http://127.0.0.1:8080/portal/rest/cours/all').then((response) => {
                    this.courses = response.data;
                }, (response) => {
                });
            },
  }
}
</script>
