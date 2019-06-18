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
          <div v-if="alt" class="alert alert-info">
            <i class="uiIconInfo"></i>Aucun Cours Publié
          </div>
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
                            :src="cr[0]"
                            aspect-ratio="1"
                            md4
                            lg4
                            class="grey lighten-2" />
                  </center>
                  <table align="center">
                    <tr>
                      <td class="title-content-mpublished">Nom de cours:</td>
                      <td class="text-content-mpublished">{{ c.nameCourse }}</td>
                    </tr>
                    <tr>
                      <td class="title-content-mpublished">Auteur:</td>
                      <td class="text-content-mpublished">{{ c.userName }}</td>
                    </tr>
                    <tr>
                      <td class="title-content-mpublished">Début:</td>
                      <td class="text-content-mpublished">{{ c.dateStart }}</td>
                    </tr>
                    <tr>
                      <td class="title-content-mpublished">Fin:</td>
                      <td class="text-content-mpublished">{{ c.dateEnd }}</td>
                    </tr>
                  </table>
                </v-card-text>
                <v-card-actions>
                  <div class="btn-center-mpublished">
                    <div class="btn-qcm-edit-mpublished">
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
                alt:false,
                exercises:[],
                courses: [],
                cr: [
                    {
                        src: "https://image.freepik.com/free-psd/course-banner_56173-54.jpg"
                    }
                ]
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/allPublishedByUser/PUBLISHED`)
                .then(response => {
                    this.courses = response.data;
                    if(this.courses.length===0){
                        this.alt=true;
                    }
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
  .text-content-mpublished {
    text-align: left;
    margin: 0;
    font-family: roberto sans-serif !important;
    font-size: 14px;
    line-height: 20px;
    color: #333333;
  }

  .title-content-mpublished {
    margin: 0;
    font-family: roberto sans-serif !important;
    font-size: 14px;
    line-height: 32px;
    color: #333333;
    font-weight: bold !important;
    float: left;

  }
  .flex.md4.lg4 {
    min-width: 25%;
    display: inline-block;

  }
  table {
    width: 100%;
    margin-top: 22px;
  }
  .btn-qcm-edit-mpublished{
    margin-left: -147%;
    margin-bottom: -100.2%;
  }
  .btn-center-mpublished{
    margin-left: 49%!important;
  }

</style>

