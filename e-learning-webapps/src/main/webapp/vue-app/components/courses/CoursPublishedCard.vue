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
            <v-flex
                    v-for="(c,index) in courses"
                    :key="c.idCourse"
                    md4
                    lg4>
              <v-card flat class="text-xs-center ma-3 elevation-5">
                <div>
                </div>
                <v-card-text>
                  <center>
                    <v-img
                            height="100px"
                            width="100px"
                            :src="course[1]"
                            aspect-ratio="1"
                            md4
                            lg4
                            class="grey lighten-2" />
                  </center>
                  <div class="subheading">{{ c.nameCourse }}</div>
                  <div class="grey--text">{{ c.status }}</div>
                  <div class="grey--text">{{ c.dateStart }}</div>
                  <div class="grey--text">{{ c.dateStart }}</div>
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
                datajson:{

                },
                course: [
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
                    console.log(error)
                })
                .finally(() => this.loading = false)
        },
        methods:{
            passExam(el){
                console.log(el.idCourse)
                this.$router.push('/passExam?id='+el.idCourse);
            },
            passCourse(el){
                console.log("course id",el.idCourse)
                axios.get(`http://127.0.0.1:8080/portal/rest/worker/all`)
                    .then(response=>{
                        this.allworkers=response.data
                console.log("all registration",this.allworkers);
                           axios.post(`/portal/rest/worker/addNewUser`, this.datajson)
                                .then(response => {
                                    this.datajsonfor.idCourse = el.idCourse;
                                    this.datajsonfor.idWorker = response.data.id;
                                    axios.post(`/portal/rest/cregistration/add`, this.datajsonfor)
                                        .then(
                                            console.log("****************", this.datajsonfor))
                                    this.$router.push('/contentCourse')


                                })


                });
            },

        }
    }
</script>

