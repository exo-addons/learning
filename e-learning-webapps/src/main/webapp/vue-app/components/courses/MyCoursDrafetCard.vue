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
                <i class="uiIconInfo"></i>Aucun Cours Crée
            </div>
          <v-row>
            <v-flex class="md4 lg4"
              v-for="(c,index) in courses"
              :key="c.idCourse">


              <v-card flat class="text-xs-center ma-3 elevation-10">
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
                      <td class="title-content-mdrafet">Nom de cours:</td>
                      <td class="text-content-mdrafet">{{ c.nameCourse }}</td>
                    </tr>
                    <tr>
                      <td class="title-content-mdrafet">Auteur:</td>
                      <td class="text-content-mdrafet"> {{ c.userName }}</td>
                    </tr>
                    <tr>
                      <td class="title-content-mdrafet">Début:</td>
                      <td class="text-content-mdrafet"> {{ c.dateStart }}</td>
                    </tr>
                    <tr>
                      <td class="title-content-mdrafet">Fin:</td>
                      <td class="text-content-mdrafet">{{ c.dateEnd }}</td>
                    </tr>
                  </table>
                </v-card-text>
                <v-card-actions>
                  <div class="btn-center-mdrafet">
                    <div class="btn-qcm-edit-mdrafet">
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
                courses: [],
                cr: [
                    {

                        src: "https://image.freepik.com/free-psd/course-banner_56173-54.jpg"
                    },
                ]
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/allDrafetByUser/DRAFET`)
                .then(response => {
                    this.courses = response.data
                    if(this.courses.length===0){
                        this.alt=true;
                    }

                })
                .catch(error => {
                })
        },
        methods:{
            DontShow() {
                this.alt = false;
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
  .text-content-mdrafet {
    text-align: left;
    margin: 0;
    font-family: roberto sans-serif !important;
    font-size: 14px;
    line-height: 20px;
    color: #333333;
  }

  .title-content-mdrafet {
    margin: 0;
    font-family: roberto sans-serif !important;
    font-size: 14px;
    line-height: 32px;
    color: #333333;
    font-weight: bold !important;
    float: left;

  }
   .btn-qcm-edit-mdrafet{
     margin-left: -147%;
     margin-bottom: -100.2%;
   }
  .flex.md4.lg4 {
    min-width: 25%;
    display: inline-block;

  }
  .title-content-mdrafet {
  }
  table {
    width: 100%;
    margin-top: 22px;
  }
  .btn-center-mdrafet {
    margin-left: 49%!important;
  }

</style>

