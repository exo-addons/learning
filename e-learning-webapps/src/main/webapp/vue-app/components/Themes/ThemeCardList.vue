<template>
  <v-app
    id="themes_card_list"
    flat>
    <template>
      <v-container class="theme_items_grid">
        <v-row>
          <v-col
            cols="12"
            sm="3"
            md="2"
            v-for="theme in themeList"
            :key="theme.id">
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