<template>
  <v-app
    id="themes_card_list"
    flat>
    <template>
      <v-container> 
        <v-row class="theme_cards_row border-box-sizing"> 
          <v-col
            class="theme_cards"
            cols="12"
            md="6"
            lg="4"
            xl="3"
            v-for="theme in themeList"
            :key="theme.id"
            :id="'theme-'+theme.id">
            <theme-card :theme="theme" />
          </v-col>
        </v-row>
      </v-container>  
    </template> 
  </v-app>
</template>

<script>
export default {

  data() {
    return {
      themeList: [],
      errors: [],
    };
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