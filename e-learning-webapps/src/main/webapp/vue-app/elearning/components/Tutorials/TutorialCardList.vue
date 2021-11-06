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
            v-for="tutorial in tutorialsList"
            :key="tutorial.id"
            :id="'tutorial-' + tutorial.id">
            <tutorial-card :tutorial="tutorial" />
          </v-col>
        </v-row>
      </v-container>
      <tutorial-management-drawer
        ref="tutorialManagementDrawer"
        :tutorial="tutorialToUpdate"
        :parent-theme="parentTheme"
        :space-name="space && space.prettyName"
        :space="space" />
    </template> 
  </v-app>
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
        this.getTutosByName();
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
    
    
    this.$root.$on('tutoCreated', () => {
      this.getTutorialsByTheme(this.themeId);
    });
    this.$root.$on('tutoUpdated', () => {
      this.getTutorialsByTheme(this.themeId);
    });
    this.$root.$on('tutoMoved', () => {
      this.getTutorialsByTheme(this.themeId);
    });
    this.$root.$on('tutoDeleted', () => {
      this.getTutorialsByTheme(this.themeId);
    });
    this.$root.$on('makeDupTuto', (id) => {
      this.getTutoDup(id);
    });
    this.$root.$on('makeArchTuto', (id) => {
      this.getTutoArch(id);
    });
    this.$root.$on('makeUnarchTuto', (id) => {
      this.getTutoUnarch(id);
    });
  },
  methods: {
    getTutorialsByTheme() {
      return this.$tutoService.getTutorialsByTheme(this.parentTheme.id, this.offset, this.limit).then(data => {
        this.tutorialsList = data.tutorialList;
      }).catch((e) => this.errors.push(e));
    },
    getTutosByName() {
      return this.$tutoService.getTutosByName(this.parentTheme.id, this.keyword).then((data) => {
        this.tutorialsList = data.tutorialList;
      }).catch((e) => this.errors.push(e));
    },
    getTutoDup(id) {
      return this.$tutoService.getTutoById(id).then((data) => {
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
      return this.$tutoService.tutoPost(this.tutoD).then(() => {
        this.$root.$emit('tutoDuplicated');
        this.getTutorialsByTheme();
      }).catch((e) => this.errors.push(e));
    },
    getTutoArch(id) {
      return this.$tutoService.getTutoById(id).then((data) => {
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
      return this.$tutoService.tutoUpdate(this.tutoA).then(() => {
        this.$root.$emit('tutoArchived');
      }).then(() => {
        this.getTutorialsByTheme();
      }).catch((e) => this.errors.push(e));
    },
    getTutoUnarch(id) {
      return this.$tutoService.getTutoById(id).then((data) => {
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
      return this.$tutoService.tutoUpdate(this.tutoA).then(() => {
        this.$root.$emit('tutoUnarchived');
      }).then(() => {
        this.getTutorialsByTheme();
      }).catch((e) => this.errors.push(e));
    },
  }
};
</script>    