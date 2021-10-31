<template>
  <div id="theme-tutorial-mix-dashboard">
    <theme-tutorial-mix-dashboard-toolbar 
      :theme-name="themeName"
      :space="space"
      :keyword="keyword"
      @keyword-changed="keyword = $event" />
    <theme-card-list
      :space-name="space && space.prettyName"
      :space="space"
      :parent-theme="parentTheme"
      :keyword="keyword" />
    <v-divider />
    <tutorial-card-list
      :keyword="keyword"
      :parent-theme="parentTheme"
      :space="space"
      :can-update="canUpdate" />
    <exo-confirm-dialog
      ref="confirmDialog"
      :message="$t('addon.elearning.tutorial.deleteConf')"
      :title="$t('addon.elearning.tutorial.confirmD')"
      :ok-label="$t('addon.elearning.tutorial.confirm')"
      :cancel-label="$t('addon.elearning.tutorial.cancel')"
      @ok="deleteTuto()" />
    <v-alert
      v-model="alert"
      :type="type"
      dismissible>
      {{ message }}
    </v-alert>
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
  },
  
  data() {
    return {
      alert: false,
      type: '',
      message: '',
      deleteId: null,
      themeId: null,
      themeName: null,
      errors: [],
      keyword: '',
    };
  },

  created() {
    this.$root.$on('show-alert', message => {
      this.displayMessage(message);
    });
    this.$root.$on('tutoCreated', () => {
      this.timeout = 3000;
      this.successBar = false;
      this.successBar = true;
      this.color = 'success';
      this.text = this.$t('addon.elearning.tutorial.created');
    });
    this.$root.$on('tutoUpdated', () => {
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
    this.$root.$on('deleteTuto', (id) => {
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
    deleteTuto() {
      return this.$tutoService.deleteTuto(this.deleteId)
        .then(() => {
          this.confirmDialog = false;
          this.timeout = 3000;
          this.successBar = true;
          this.text = this.$t('addon.elearning.tutorial.deleted');
          this.$root.$emit('tutoDeleted');
        })
        .catch((e) => this.errors.push(e));
    },
    getThemeName() {
      return this.$themeService.getThemeById(this.themeId)
        .then((data) => {
          this.themeName = data.name;
        })
        .catch((e) => this.errors.push(e));
    },
    displayMessage(message) {
      this.message = message.message;
      this.type = message.type;
      this.alert = true;
      window.setTimeout(() => this.alert = false, 5000);
    },
  }
};
</script>