<template>
  <div class="team">
    <v-container class="my-5" elevation-20>
      <v-layout>
        <v-flex md12>
          <app-edit-cours-tab></app-edit-cours-tab>
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
          v-for="person in course"
          :key="person.libelle"
          md4
          lg4>
          <v-card flat class="text-xs-center ma-3 elevation-10">
            <div>
              <v-img
                :src="person.src"
                aspect-ratio="1"
                class="grey lighten-2" />
            </div>
            <v-card-text>
              <div class="subheading">{{ person.libelle }}</div>
              <div class="grey--text">{{ person.editeurName }}</div>
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
    import AppSearchCardMain from './AppSearchCardMain.vue'
    import AppEditCoursTab from './AppEditCoursTabMain.vue'


    export default {
        name: 'App',
        components:{AppSearchCardMain,AppEditCoursTab},
        data() {
            return {
                course: [
                    { libelle: 'The Net Ninja', editeurName: 'Web developer',etat:"disponible",src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'},
                    { libelle: 'Ryu', editeurName: 'Graphic designer',etat:"disponible",src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'},
                    { libelle: 'Chun Li', editeurName: 'Web developer' ,etat:"disponible",src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'},
                    { libelle: 'Gouken', editeurName: 'Social media maverick' ,etat:"disponible",src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'},
                    { libelle: 'Yoshi', editeurName: 'Sales guru',etat:"disponible",src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'}
                ]
            }
        }
    }
</script>

