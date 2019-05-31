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
            <v-flex class="md4 lg4"
                    v-for="(c,index) in courses"
                    :key="c.idCourse">


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
                              fab
                              dark
                              small slot="activator"
                              @click.prevent="passCourse(courses[index])"
                              color="#578dc9">
                        <i class="fas fa-university fa-2x"></i>
                      </v-btn>
                      <v-btn
                              fab
                              dark
                              small
                              color="#424242"
                              @click.prevent="passExam(courses[index])">
                        <i class="fas fa-graduation-cap fa-2x"></i>
                      </v-btn>
                    </v-flex>
                  </v-layout>
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
    import {bus} from '../../main.js'


    export default {
        props:{
            exams:{
            }
        },
        components: {AppEditCoursTab},
        data() {
            return {
                count:null,
                counterWorker:0,
                allworkers:[],
                allregistration:[
                    {
                        idCourse: null,
                        idWorker: null,
                        dateRegistration: null
                    }
                ],
                dialog:false,
                courses: [],
                users:[],
                datajsonfor:{
                    idCourse:null,
                    idWorker: null
                },
                dataExam:{
                    idExam: null,
                    idWorker:null

                },
                dataExercise:{
                    idExam:null

                },
                datajson:{

                },
                cr: [
                    {
                        libelle: 'The Net Ninja',
                        editeurName: 'Web developer',
                        etat: "disponible",
                        src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'
                    },
                    {
                        libelle: 'Ryu',
                        editeurName: 'Graphic designer',
                        etat: "disponible",
                        src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'
                    },
                    {
                        libelle: 'Chun Li',
                        editeurName: 'Web developer',
                        etat: "disponible",
                        src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'
                    },
                    {
                        libelle: 'Gouken',
                        editeurName: 'Social media maverick',
                        etat: "disponible",
                        src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'
                    },
                    {
                        libelle: 'Yoshi',
                        editeurName: 'Sales guru',
                        etat: "disponible",
                        src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'
                    }
                ]
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/allOtherPublishedCourse/PUBLISHED`)
                .then(response => {
                    this.courses = response.data
                })
                .catch(error => {
                })
                .finally(() => this.loading = false)
        },
        methods: {
            passExam(el) {
               // this.$router.push('/passExam?id=' + el.idCourse);
                this.$router.push('/ChooseExam')
                axios.get(`/portal/rest/exam/allExamByUserName/`+ el.userName)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.exams= response.data
                        bus.$emit('transferData',this.exams)

                    })
            },
            passCourse(el) {
                console.log("course id", el.idCourse)
                        axios.post(`/portal/rest/worker/addNewUser`, this.datajson)
                            .then(response => {
                                this.datajsonfor.idCourse = el.idCourse;
                                this.datajsonfor.idWorker = response.data.id;
                                axios.get(`/portal/rest/cregistration/countNumberWorker/` + this.datajsonfor.idWorker)
                                    .then(response => {
                                        this.count = response.data
                                        if (this.count.counter_worker<1) {
                                            axios.get(`/portal/rest/cregistration/countNumberWorker/` + this.datajsonfor.idWorker)
                                                .then(response => {
                                                    this.counterWorker = this.data
                                                    axios.post(`/portal/rest/cregistration/add`, this.datajsonfor)
                                                        .then(
                                                    this.$router.push('/contentCourse'))
                                                })
                                        }else{
                                            this.$router.push('/contentCourse')

                                        }
                                    })
                    });
            },
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
  .btn-qcm-edit{
    margin-left: 37%;
    margin-bottom: -11.2%;
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

</style>
