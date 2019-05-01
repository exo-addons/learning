<template>
  <div>
    <v-container elevation-10>
      <v-layout>
        <v-flex md12>
          <app-edit-cours-tab />
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
              <div class="subheading">{{ person.name }}</div>
              <div class="grey--text">{{ person.categorie }}</div>
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
    </v-container>
  </div>
</template>
<script>
    import AppEditCoursTab from './AppEditCoursTabMain.vue'

    export default {
        components:{AppEditCoursTab},
        data() {
            return {
                course: [
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'The Net Ninja', categorie: 'Web developer' },
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'Ryu', categorie: 'Graphic designer' },
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'Gouken', categorie: 'Social media ' },
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'Yoshi', categorie: 'Sales guru'}
                ]
            }
        }
    }
</script>
