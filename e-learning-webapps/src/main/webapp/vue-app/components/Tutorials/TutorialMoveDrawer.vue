<template>
  <v-app
    id="tutorial_move"
    flat>
    <exo-drawer ref="tutorialMoveDrawer" right>
      <template slot="title">
        {{ $t('addon.elearning.tutorial.moveF') }}     
      </template>
      <template slot="content">
        <template>
          <v-container fluid id="themes_checkbox_container">
            <v-checkbox
              v-for="theme in themes"
              v-model="tutoUp.themeIds"
              :key="theme.id"
              :id="'theme-'+theme.id"
              :label="theme.name"
              :value="theme.id" />
          </v-container>
        </template>
      </template>
      <template slot="footer">
        <v-btn
          class="tuto_drawer_btn_move"
          @click="tutoUpdate"
          :disabled="!isThemeSelected">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="clear">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </v-app>
</template>
<script>
export default {
  
  data () {
    return {
      tutoId: null,
      tutoU: null,
      errors: [],
      themes: [],
      tutoUp: {
        id: 0,
        title: null,
        description: null,
        themeIds: []
      }
    };
  },

  computed: {
    isThemeSelected () {
      return this.tutoUp.themeIds.length > 0;
    }
  },
  
  created() {
    this.getThemes();
    this.$root.$on('makeMoveTuto', (id) => {
      this.$refs.tutorialMoveDrawer.open();
      this.getTutoU(id);
    });
  },

  methods: {

    tutoUpdate() {
      return this.$tutoService.tutoUpdate(this.tutoUp)
        .then(() => {this.$root.$emit('tutoMoved');})
        .then(() => {
          this.$refs.tutorialMoveDrawer.close();})
        .catch((e) => this.errors.push(e));      
    },

    getTutoU(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {this.tutoU = data;
          this.tutoUp.id = this.tutoU.id;
          this.tutoUp.title = this.tutoU.title;
          this.tutoUp.description = this.tutoU.description;
          this.tutoUp.themeIds=this.tutoU.themeIds;})
        .catch((e) => this.errors.push(e));
    },
    
    getThemes() {
      return this.$themeService.getThemes()
        .then((data) => {(this.themes = data);})
        .catch((e) => this.errors.push(e));
    },
    clear(){
      this.tutoUp.themeIds=[];
    }
  }
};

</script>