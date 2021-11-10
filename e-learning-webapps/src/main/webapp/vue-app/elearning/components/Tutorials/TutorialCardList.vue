<template>
  <div id="tutos_card_list">
    <template>
      <v-container> 
        <v-row class="tuto_cards_row border-box-sizing"> 
          <v-col
            class="tuto_cards"
            cols="12"
            md="6"
            lg="4"
            xl="3"
            v-for="tutorial in tutorialsList"
            :key="tutorial.id"
            :id="'tutorial-' + tutorial.id">
            <tutorial-card
              :parent-theme="parentTheme"
              :tutorial="tutorial"
              :space="space"
              :can-update="canUpdate" />
          </v-col>
        </v-row>
      </v-container>
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
    parentTheme: {
      type: Object,
      default: null
    },
    space: {
      type: Object,
      default: null
    },
    canUpdate: {
      type: Boolean,
      default: false
    },
    keyword: {
      type: String,
      default: '',
    },
  },
  data() {
    return {
      tutorialsList: [],
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
      errors: [],
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
    
    // this.$root.$on('tutorialUpdated', () => {
    //   this.getTutorialsByTheme(this.themeId);
    // });
    // this.$root.$on('tutoMoved', () => {
    //   this.getTutorialsByTheme(this.themeId);
    // });
    // this.$root.$on('tutoDeleted', () => {
    //   this.getTutorialsByTheme(this.themeId);
    // });
    // this.$root.$on('makeDupTuto', (id) => {
    //   this.getTutoDup(id);
    // });
    // this.$root.$on('makeArchTuto', (id) => {
    //   this.getTutoArch(id);
    // });
    // this.$root.$on('makeUnarchTuto', (id) => {
    //   this.getTutoUnarch(id);
    // });
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
      return this.$tutoService.getTutorialsByTheme(this.parentTheme.id, this.offset, this.limit).then(data => {
        this.tutorialsList = data.tutorialList;
      }).catch((e) => this.errors.push(e));
    },
    getTutorialsByName() {
      return this.$tutoService.getTutorialsByName(this.parentTheme.id, this.keyword).then((data) => {
        this.tutorialsList = data.tutorialList;
      }).catch((e) => this.errors.push(e));
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
      }).catch((e) => this.errors.push(e));
    },
    duplicateTuto() {
      return this.$tutoService.addTutorial(this.tutoD).then(() => {
        this.$root.$emit('tutoDuplicated');
        this.getTutorialsByTheme();
      }).catch((e) => this.errors.push(e));
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
      }).catch((e) => this.errors.push(e));
    },
    archiveTuto() {
      return this.$tutoService.updateTutorial(this.tutoA).then(() => {
        this.$root.$emit('tutoArchived');
      }).then(() => {
        this.getTutorialsByTheme();
      }).catch((e) => this.errors.push(e));
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
      }).catch((e) => this.errors.push(e));
    },
    unarchiveTuto() {
      return this.$tutoService.updateTutorial(this.tutoA).then(() => {
        this.$root.$emit('tutoUnarchived');
      }).then(() => {
        this.getTutorialsByTheme();
      }).catch((e) => this.errors.push(e));
    },
  }
};
</script>    