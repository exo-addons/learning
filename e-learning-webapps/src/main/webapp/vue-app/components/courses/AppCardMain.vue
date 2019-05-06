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
              <app-search-card-main />
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex
              v-for="c in courses"
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
                  <v-btn flat color="grey">
                    <v-icon small left>message</v-icon>
                    <span class="">Message</span>
                  </v-btn>
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
    import AppSearchCardMain from './AppSearchCardMain.vue'
    import AppEditCoursTab from './AppEditCoursTabMain.vue'


    export default {
        name: 'App',
        components: {AppSearchCardMain, AppEditCoursTab},
        data() {
            return {
                courses: [],
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
                    this.errored = true
                })
                .finally(() => this.loading = false)
        },
    }
</script>

