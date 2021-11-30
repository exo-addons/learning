<template>
  <div id="theme-tutorial-mix-dashboard">
    <theme-tutorial-mix-dashboard-toolbar
      :parent-theme="parentTheme"
      :keyword="keyword"
      @keyword-changed="keyword = $event" />
    <theme-card-list
      :space-name="spaceName"
      :parent-theme="parentTheme"
      :keyword="keyword" />
    <v-divider v-show="displayDivider" />
    <tutorial-card-list
      :space-name="spaceName"
      :parent-theme="parentTheme"
      :keyword="keyword" />
    <exo-confirm-dialog
      ref="confirmDialog"
      :message="$t('addon.elearning.tutorial.deleteConf')"
      :title="$t('addon.elearning.tutorial.form.delete.title')"
      :ok-label="$t('addon.elearning.tutorial.form.confirm')"
      :cancel-label="$t('addon.elearning.tutorial.form.cancel')"
      @ok="deleteTutorial()" />
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
  },  
  data() {
    return {
      deleteId: null,
      themeId: null,
      themeName: null,
      displayDivider: false,
      keyword: '',
    };
  },
  watch: {
    parentTheme: {
      immediate: true,
      handler() {
        this.displayDivider = this.parentTheme && this.parentTheme.childrenIds.length > 0 && this.parentTheme.tutorialIds.length > 0;
      }
    },
  },
  created() {
    this.$root.$on('tutorialUpdated', () => {
      this.timeout = 3000;
      this.successBar = false;
      this.successBar = true;
      this.color = 'success';
      this.text = this.$t('addon.elearning.tutorial.updated');
    });
    this.$root.$on('tutoMoved', () => {
      this.timeout = 3000;
      this.successBar = false;
      this.successBar = true;
      this.color = 'success';
      this.text = this.$t('addon.elearning.tutorial.moved');
    });
    this.$root.$on('tutoDuplicated', () => {
      this.timeout = 3000;
      this.successBar = false;
      this.successBar = true;
      this.color = 'success';
      this.text = this.$t('addon.elearning.tutorial.duplicated');
    });
    this.$root.$on('tutoArchived', () => {
      this.timeout = 3000;
      this.successBar = false;
      this.successBar = true;
      this.color = 'success';
      this.text = this.$t('addon.elearning.tutorial.archived');
    });
    this.$root.$on('tutoUnarchived', () => {
      this.timeout = 3000;
      this.successBar = false;
      this.successBar = true;
      this.color = 'success';
      this.text = this.$t('addon.elearning.tutorial.unarchived');
    });
    this.$root.$on('setId', (id) => {
      this.themeId = id;
      this.getThemeName();
    });
    this.$root.$on('deleteTutorial', (id) => {
      this.prepareDelete(id);
    });

    $(document).on('mousedown', () => {
      if (this.displayActionMenu) {
        window.setTimeout(() => {
          this.displayActionMenu = false;
        }, 200);
      }
    });

    this.$root.$on('key-changed', (keyword) => {
      this.keyword = keyword;
    });

  },
  methods: {
    prepareDelete(id) {
      this.deleteId = id;
      this.$refs.confirmDialog.open();
    },
    deleteTutorial() {
      return this.$tutoService.deleteTutorial(this.deleteId)
        .then(() => {
          this.confirmDialog = false;
          this.timeout = 3000;
          this.successBar = true;
          this.text = this.$t('addon.elearning.tutorial.deleted');
          this.$root.$emit('tutoDeleted');
        })
        .catch((e) => console.error('Error when deleting tutorial', e));
    },
    getThemeName() {
      return this.$themeService.getThemeById(this.themeId)
        .then((data) => {
          this.themeName = data.name;
        })
        .catch((e) => console.error('Error when retrieving theme name', e));
    },
  }
};
</script>