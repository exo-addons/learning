<template>
  <div id="tutorial_move">
    <exo-drawer 
      id="tutorialMoveDrawer"
      ref="tutorialMoveDrawer"
      right>
      <template slot="title">
        {{ $t('addon.elearning.tutorial.move.form.title') }}     
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
          @click="updateTutorial"
          :disabled="!isThemeSelected">
          {{ $t('addon.elearning.tutorial.form.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="clear">{{ $t('addon.elearning.tutorial.form.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tutorialId: null,
      tutoU: null,
      themes: [],
      tutoUp: {
        id: 0,
        title: null,
        description: null,
        status: null,
        themeIds: [],
        author: null
      }
    };
  },
  computed: {
    isThemeSelected() {
      return this.tutoUp.themeIds.length > 0;
    }
  },
  created() {
    this.$root.$on('makeMoveTuto', (id) => {
      this.$refs.tutorialMoveDrawer.open();
      this.getTutoU(id);
    });
  },
  methods: {
    updateTutorial() {
      return this.$tutoService.updateTutorial(this.tutoUp)
        .then(() => {
          this.$root.$emit('tutoMoved');
        })
        .then(() => {
          this.$refs.tutorialMoveDrawer.close();
        })
        .catch((e) => console.error('Error when updating tutorial', e));
    },

    getTutoU(id) {
      return this.$tutoService.getTutorialById(id)
        .then((data) => {
          this.tutoU = data;
          this.tutoUp.id = this.tutoU.id;
          this.tutoUp.title = this.tutoU.title;
          this.tutoUp.description = this.tutoU.description;
          this.tutoUp.status = this.tutoU.status;
          this.tutoUp.themeIds = this.tutoU.themeIds;
          this.tutoUp.author = this.tutoU.author;
        })
        .catch((e) => console.error('Error when retrieving tutorial', e));
    },

    getThemes() {
      return this.$themeService.getThemes()
        .then((data) => {
          (this.themes = data);
        })
        .catch((e) => console.error('Error when retrieving themes', e));
    },
    clear() {
      this.tutoUp.themeIds = [];
    }
  }
};

</script>