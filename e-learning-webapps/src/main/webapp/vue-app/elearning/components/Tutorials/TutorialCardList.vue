<template>
  <div id="tutos_card_list">
    <template>
      <v-row class="tuto_cards_row border-box-sizing" dense>
        <v-col
          v-for="tutorial in tutorialsList"
          class="tuto_cards pa-4"
          cols="12"
          md="6"
          lg="4"
          xl="3"
          :key="tutorial.id">
          <tutorial-card
            :parent-theme="parentTheme"
            :tutorial="tutorial"
            :space="space"
            :can-update="canUpdate" />
        </v-col>
      </v-row>
      <tutorial-management-drawer
        ref="tutorialManagementDrawer"
        :tutorial="tutorialToUpdate"
        :parent-theme="parentTheme"
        :space-name="space && space.prettyName"
        :space="space"
        @tutorialAdded="addCreatedTutorial"
        @tutorialUpdated="updateTutorial" />
    </template> 
  </div>
</template>

<script>
export default {
  props: {
    spaceName: {
      type: String,
      default: ''
    },
    parentTheme: {
      type: Object,
      default: null
    },
    keyword: {
      type: String,
      default: '',
    },
  },
  data() {
    return {
      tutorialsList: [],
      tutorialsCount: 0,
      space: null,
      canUpdate: false,
      tutorialToUpdate: {
        id: null,
        title: null,
        description: null,
        themeIds: [],        
      },
      dupTuto: null,
      archTurto: null,
      offset: 0,
      limit: 0,
      tutoD: {
        title: null,
        description: null,
        themeIds: [],
        status: null,
        author: null
      },
      tutoA: {
        id: 0,
        title: null,
        description: null,
        themeIds: [],
        status: null,
        author: null
      },
      newTitle: null,
    };
  },
  watch: {
    parentTheme: {
      immediate: true,
      handler() {
        if (this.parentTheme) {
          this.getTutorialsByTheme();          
        }
      }
    },
    keyword() {
      if (!this.keyword) {
        this.getTutorialsByTheme();
      }
      if (this.keyword) {
        this.getTutorialsByName();
      }

    }
  },
  created() {
    this.getTutorialsByTheme(this.themeId);
    this.$root.$on('openTutorialDrawer', () => {
      if (this.$refs.tutorialManagementDrawer) {
        this.$refs.tutorialManagementDrawer.open();
      }
    });
    this.$root.$on('tutorial-deleted', () => {
      this.getTutorialsByTheme(this.themeId);
    });
  },
  methods: {
    addCreatedTutorial(addedTutorial) {
      this.tutorialsList.unshift(addedTutorial);
      this.$refs.tutorialManagementDrawer.close();
    },
    updateTutorial(updatedTutorial) {
      const index = this.tutorialsList.findIndex(tutorial => tutorial.id === updatedTutorial.id);
      this.tutorialsList.splice(index, 1, updatedTutorial);
      this.tutorialToUpdate = null;
      this.$refs.tutorialManagementDrawer.close();

    },
    getTutorialsByTheme() {
      return this.$tutoService.getTutorialsByTheme(this.parentTheme.id, this.spaceName, this.keyword, this.offset, this.limit).then(data => {
        this.tutorialsList = data.tutorialList;
        this.tutorialsCount = data.count;
        this.space = data.space;
        this.canUpdate = data.canUpdate;
      }).catch((e) => console.error('Error when retrieving tutorials by theme', e));
    },
    getTutorialsByName() {
      return this.$tutoService.getTutorialsByName(this.parentTheme.id, this.keyword).then((data) => {
        this.tutorialsList = data.tutorialList;
        this.tutorialsCount = data.count;
        this.space = data.space;
        this.canUpdate = data.canUpdate;
      }).catch((e) => console.error('Error when retrieving tutorials by name', e));
    },
    getTutoDup(id) {
      return this.$tutoService.getTutorialById(id).then((data) => {
        this.dupTuto = data;
        this.newTitle = this.$t('addon.elearning.tutorial.duplicate.text');
        this.tutoD.title = this.newTitle + this.dupTuto.title;
        this.tutoD.description = this.dupTuto.description;
        this.tutoD.status = 'DRAFT';
        this.tutoD.themeIds = this.dupTuto.themeIds;
        this.tutoD.author = this.dupTuto.author;
        this.duplicateTuto();
      }).catch((e) => console.error('Error when retrieving tutorial', e));
    },
    duplicateTuto() {
      return this.$tutoService.addTutorial(this.tutoD).then(() => {
        this.$root.$emit('tutoDuplicated');
        this.getTutorialsByTheme();
      }).catch((e) => console.error('Error when duplicating tutorial', e));
    },
    getTutoArch(id) {
      return this.$tutoService.getTutorialById(id).then((data) => {
        this.archTurto = data;
        this.tutoA.id = this.archTurto.id;
        this.tutoA.title = this.archTurto.title;
        this.tutoA.description = this.archTurto.description;
        this.tutoA.status = 'ARCHIVED';
        this.tutoA.themeIds = this.archTurto.themeIds;
        this.tutoA.author = this.archTurto.author;
        this.archiveTuto();
      }).catch((e) => console.error('Error when retrieving tutorial', e));
    },
    archiveTuto() {
      return this.$tutoService.updateTutorial(this.tutoA).then(() => {
        this.$root.$emit('tutoArchived');
      }).then(() => {
        this.getTutorialsByTheme();
      }).catch((e) => console.error('Error when archiving tutorial', e));
    },
    getTutoUnarch(id) {
      return this.$tutoService.getTutorialById(id).then((data) => {
        this.archTurto = data;
        this.tutoA.id = this.archTurto.id;
        this.tutoA.title = this.archTurto.title;
        this.tutoA.description = this.archTurto.description;
        this.tutoA.status = 'PUBLISHED';
        this.tutoA.themeIds = this.archTurto.themeIds;
        this.tutoA.author = this.archTurto.author;
        this.unarchiveTuto();
      }).catch((e) => console.error('Error when retrieving tutorial', e));
    },
    unarchiveTuto() {
      return this.$tutoService.updateTutorial(this.tutoA).then(() => {
        this.$root.$emit('tutoUnarchived');
      }).then(() => {
        this.getTutorialsByTheme();
      }).catch((e) => console.error('Error when withdrawing tutorial', e));
    },
  }
};
</script>    