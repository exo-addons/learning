<template>
  <v-app
    id="themes_card_list"
    flat>
    <template>
      <!--
      <v-container id="theme_items_grid"> 
        <v-row>
          <v-col
            cols="12"
            md="2"
            v-for="theme in themeList"
            :key="theme.id">
            <theme-card :theme="theme" />
          </v-col>
        </v-row>
      </v-container>
      -->
      <div
        v-for="themes in groupedThemes"
        class="row theme_cards_container"
        :key="themes.id">
        <div
          class="col theme_cards"
          v-for="theme in themes"
          :key="theme.id">
          <theme-card :theme="theme" />
        </div>
      </div>
    </template> 
  </v-app>
</template>

<script>
const _ = require('lodash');
export default {

  data() {
    return {
      themeList: [],
      errors: [],
    };
  },

  computed: {
    groupedThemes(){
      return _.chunk(this.themeList, 4);
    }
  },

  created() {
    this.getThemes();
    this.$root.$on('themeCreated', () => {
      this.getThemes();
    });
    this.$root.$on('themeUpdated', () => {
      this.getThemes();
    });
    this.$root.$on('themeDeleted', () => {
      this.getThemes();
    });
  },

  methods: {
    getThemes() {
      return this.$themeService.getThemes()
        .then((data) => {(this.themeList = data);})
        .catch((e) => this.errors.push(e));
    }
  }  
};
</script>