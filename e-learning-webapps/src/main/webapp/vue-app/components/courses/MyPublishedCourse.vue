<template>
  <div class="team">
    <v-container class="my-5" elevation-10>
      <v-layout>
        <v-flex md12>
          <app-edit-cours-tab />
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex md12 lg12>
          <v-layout>
            <v-flex md12 lg12>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex
                    v-for="(c,index) in courses"
                    :key="c.idCourse"
                    md4
                    lg4>
              <v-card flat class="text-xs-center ma-3 elevation-10">
                <div>
                </div>
                <v-card-text>
                  <center>
                    <v-img
                            height="100px"
                            width="100px"
                            :src="course[0]"
                            aspect-ratio="1"
                            md4
                            lg4
                            class="grey lighten-2" />
                  </center>
                  <table align="center">
                    <tr>
                      <td><div class="title-content">Nom de cours:</div></td>
                      <td><div class="text-content">{{ c.nameCourse }}</div></td>
                    </tr>
                    <tr>
                      <td><div class="title-content">Auteur:</div></td>
                      <td> <div class="text-content">{{ c.userName }}</div></td>
                    </tr>
                    <tr>
                      <td><div class="title-content">Début:</div></td>
                      <td> <div class="text-content">{{ c.dateStart }}</div></td>
                    </tr>
                    <tr>
                      <td><div class="title-content">Fin:</div></td>
                      <td> <div class="text-content">{{ c.dateEnd }}</div></td>
                    </tr>
                  </table>
                </v-card-text>
                <v-card-actions>
                  <v-layout>
                    <v-flex>
                      <v-btn
                              dark v-on="on"
                              fab
                              dark
                              small slot="activator"
                              color="#578dc9">
                        <i class="fas fa-university fa-2x" ></i>
                      </v-btn>
                      <v-btn
                              fab
                              dark
                              small
                              color="#424242"
                              @click.prevent="editExam(courses[index])">
                        <i class="fas fa-edit fa-2x" title="editer les questions examens"></i>
                      </v-btn>
                    </v-flex>
                  </v-layout>
                </v-card-actions>
              </v-card>
            </v-flex>
          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
    import axios from 'axios'
    import AppEditCoursTab from './AppEditCoursTabMain.vue'


    export default {
        name: 'App',
        components: {AppEditCoursTab},
        data() {
            return {
                exercises:[],
                courses: [],
                course: [
                    {
                        libelle: 'The Net Ninja',
                        editeurName: 'Web developer',
                        etat: "disponible",
                        src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'
                    }
                ]
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/allPublishedByUser/PUBLISHED`)
                .then(response => {
                    this.courses = response.data
                })
                .catch(error => {
                    console.log(error)
                })
                .finally(() => this.loading = false)
        },
        methods: {
            editExam(e) {
                axios.get(`/portal/rest/exercise/getExercisesFromCourse/` + e.idCourse)
                    .then(response => {
                        this.exercises = response.data;
                        this.$router.push('/listeQcm?id=' + e.idCourse);

                    })
            },
            editCourse(e){
                axios.get(`/portal/rest/course/getExercisesFromCourse/` + e.idCourse)
                    .then(response => {
                        this.exercises = response.data;
                        this.$router.push('/listeQcm?id=' + e.idCourse);

                    })

            }
        }
    }
</script>
<style>
  .text-content {
    text-align: left;
    margin: 0;
    font-family: roberto sans-serif !important;
    font-size: 14px;
    line-height: 20px;
    color: #333333;
  }

  .title-content {
    margin: 0;
    font-family: roberto sans-serif !important;
    font-size: 14px;
    line-height: 32px;
    color: #333333;
    font-weight: bold !important;
  }
</style>

