<template>
  <div id="themes_card_list" :class="!themesList.length ? 'empty' : ''">
    <v-row class="theme_cards_row border-box-sizing" dense>
      <v-col
        v-for="theme in themesList"
        class="theme_cards pa-4"
        cols="12"
        md="6"
        lg="4"
        xl="3"
        :key="theme.id">
        <theme-card 
          :space="space"
          :theme="theme"
          :can-update="canUpdate"
          @updateTheme="openThemeDrawerForUpdate"
          @deleteTheme="openDeleteModal" />
      </v-col>
    </v-row>
    <theme-management-drawer
      ref="themeManagementDrawer"
      :theme-to-update="themeToUpdate"
      :parent-theme="parentTheme"
      :space-name="spaceName"
      @themeAdded="addCreatedTheme"
      @themeUpdated="closeDrawerAfterThemeUpdate" />
    <exo-confirm-dialog
      ref="confirmDialog"
      :message="$t('addon.elearning.theme.form.delete.confirm')"
      :title="$t('addon.elearning.theme.form.delete.title')"
      :ok-label="$t('addon.elearning.theme.form.confirm')"
      :cancel-label="$t('addon.elearning.theme.form.cancel')"
      @ok="deleteTheme" />
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
      themesList: [],
      themesCount: 0,
      canUpdate: false,
      space: null,
      offset: 0,
      limit: 0,
      themeToUpdate: null,
      deleteId: null,
    };
  },
  watch: {
    keyword() {
      if (!this.keyword) {
        this.getThemes();
      }
      if (this.keyword) {
        this.findThemes();
      }

    },
    parentTheme: {
      immediate: true,
      handler() {
        this.fillThemesList();
      }
    },
  },
  created() {
    this.fillThemesList();
    this.$root.$on('openThemeDrawer', () => {
      if (this.$refs.themeManagementDrawer) {
        this.$refs.themeManagementDrawer.open();
      }
    });
  },
  methods: {
    fillThemesList() {
      if (!this.parentTheme) {
        this.findThemes();
      } else {
        this.getThemeChildren();
      }      
    },
    findThemes() {
      const isRoot = !this.parentTheme;
      return this.$themeService.getThemes(this.spaceName, isRoot, this.keyword, this.offset, this.limit).then(data => {
        this.themesCount = data.count;
        this.canUpdate = data.canUpdate;
        this.space = data.space;
        this.themesList = data.themeList;
      }).catch((e) => console.error('Error when retrieving themes', e));
    },
    getThemeChildren() {
      return this.$themeService.getChildThemes(this.spaceName, this.parentTheme.id, this.keyword, this.offset, this.limit).then(data => {
        this.themesCount = data.count;
        this.canUpdate = data.canUpdate;
        this.space = data.space;
        this.themesList = data.themeList;
      }).catch((e) => console.error('Error when retrieving children themes', e));
    },
    addCreatedTheme(addedTheme) {
      this.$root.$emit('show-alert', {
        message: this.$t('addon.elearning.theme.message.created'),
        type: 'success',
      });
      this.themesList.unshift(addedTheme);
      this.$refs.themeManagementDrawer.close();
    },
    openThemeDrawerForUpdate(theme) {
      this.themeToUpdate = theme;
      this.$refs.themeManagementDrawer.open();
    },
    closeDrawerAfterThemeUpdate(updatedTheme) {
      this.$root.$emit('show-alert', {
        message: this.$t('addon.elearning.theme.message.updated'),
        type: 'success',
      });
      const index = this.themesList.findIndex(theme => theme.id === updatedTheme.id);
      this.themesList.splice(index, 1, updatedTheme);
      this.themeToUpdate = null;
      this.$refs.themeManagementDrawer.close();
    },
    openDeleteModal(id) {
      this.deleteId = id;
      this.$refs.confirmDialog.open();
    },
    deleteTheme() {
      return this.$themeService.deleteTheme(this.deleteId).then(() => {
        this.$root.$emit('show-alert', {
          message: this.$t('addon.elearning.theme.message.deleted'),
          type: 'success',
        });
        this.confirmDialog = false;
        const index = this.themesList.findIndex(theme => theme.id === this.deleteId);
        this.themesList.splice(index, 1);
      }).catch((e) => {
        console.error('Error when deleting theme', e);
        this.confirmDialog = false;
      });
    }
  }
};
</script>