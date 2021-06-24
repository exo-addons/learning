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
      errors: [],
      dupTuto: null,
      tutoA: {
        title: null,
        description: null,
        themeIds: [],
        status: null,
      },
      newTitle: null
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
    this.$root.$on('makeDupTuto', (id) => {
      this.getTutoU(id);
    });
  },

  methods: {
    getTutosByTheme(id) {
      return this.$tutoService.getAllTutosByTheme(id)
        .then((data) => {(this.tutoListByTheme = data);})
        .catch((e) => this.errors.push(e));
    },
    getTutoU(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {this.dupTuto = data;
          this.newTitle= this.$t('addon.elearning.tutorial.duplicate.text') ;
          this.tutoA.title = this.newTitle+this.dupTuto.title;
          this.tutoA.description = this.dupTuto.description;
          this.tutoA.status= 'DRAFT';
          this.tutoA.themeIds=this.dupTuto.themeIds;
          this.duplicateTuto();
        })
        .catch((e) => this.errors.push(e));
    },
    duplicateTuto(){
      return this.$tutoService.tutoPost(this.tutoA)
        .then(() => {this.$root.$emit('tutoDuplicated');
          this.getTutosByTheme(this.themeId);})
        .catch((e) => this.errors.push(e));
    },
  }
};
</script>    