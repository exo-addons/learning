<template>
  <v-app
    id="tutos_card_list"
    flat>
    <template>
      <v-container> 
        <v-row class="tuto_cards_row border-box-sizing"> 
          <v-col
            class="tuto_cards"
            cols="12"
            md="6"
            lg="4"
            xl="3"
            v-for="tuto in tutoListByTheme"
            :key="tuto.id"
            :id="'tuto-'+tuto.id">
            <tutorial-card :tuto="tuto" />
          </v-col>
        </v-row>
      </v-container>  
    </template> 
  </v-app>
</template>

<script>
export default {
  data () {
    return {
      themeId: null,
      tutoListByTheme: [],
      errors: []
    };
  },

  created () {
    this.$root.$on('showTutos', (id) => {
      this.themeId=id;  
      this.getTutosByTheme(this.themeId);
      this.$root.$emit('getThemeId', id);
    });
    this.$root.$on('tutoCreated', () => {
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('tutoUpdated', () => {
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('tutoMoved', () => {
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('tutoDeleted', () => {
      this.getTutosByTheme(this.themeId);
    });
  },

  methods: {
    getTutosByTheme(id) {
      return this.$tutoService.getAllTutosByTheme(id)
        .then((data) => {(this.tutoListByTheme = data);})
        .catch((e) => this.errors.push(e));
    }
  }
};
</script>    