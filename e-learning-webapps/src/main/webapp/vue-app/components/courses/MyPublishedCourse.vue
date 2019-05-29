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
            <v-row>
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
                  <div class="btn-center">
                    <div class="btn-qcm-edit">
                      <Editcourse :course="courses[index]"></Editcourse>
                    </div>
                    <v-btn
                            dark v-on="on"
                            fab
                            dark
                            small slot="activator"
                            color="#BDBDBD"
                            @click.prevent="deleteCourse(c.idCourse)">
                      <i class="far fa-trash-alt fa-2x" ></i>
                    </v-btn>
                  </div>
                </v-card-actions>
              </v-card>
            </v-flex>
            </v-row>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
    import axios from 'axios'
    import AppEditCoursTab from './AppEditCoursTabMain.vue'
    import Editcourse from './editCourse.vue'



    export default {
        name: 'App',
        components: {AppEditCoursTab,Editcourse},
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
                })
                .finally(() => this.loading = false)
        },
        methods: {
            deleteCourse: function(event)
            {
                axios.delete('http://127.0.0.1:8080/portal/rest/cours/delete/'+event, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then(
                    this.courses=this.courses.filter(d=>d.idCourse !==event)
                )
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
  .flex.md4.lg4 {
    min-width: 25%;
    display: inline-block;

  }
  .title-content {
    float: left;
  }
  table {
    width: 100%;
    margin-top: 22px;
  }
  .btn-qcm-edit{
    margin-left: -147%;
    margin-bottom: -100.2%;
  }
  .btn-center {
    margin-left: 49%!important;
  }

</style>

