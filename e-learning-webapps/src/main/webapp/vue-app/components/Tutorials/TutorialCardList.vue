<template>
  <v-container id="tutos_card_list ">
    <v-row v-if="tutoListByTheme">
      <v-col
        cols="12"
        sm="3"
        md="2"
        v-for="tuto in tutoListByTheme"
        :key="tuto.id">
        <tutorial-card :tuto="tuto" />
      </v-col>
    </v-row>
  </v-container>
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