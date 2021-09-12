<template>
  <v-app
    id="themes_card_list"
    flat>
    <v-container>
      <v-row class="theme_cards_row border-box-sizing">
        <v-col
          v-for="theme in themesList"
          :key="theme.id"
          :id="'theme-' + theme.id"
          class="theme_cards"
          cols="12"
          md="6"
          lg="4"
          xl="3">
          <theme-card 
            :space="space"
            :theme="theme"
            :can-update="canUpdate"
            @updateTheme="openThemeDrawerForUpdate"
            @deleteTheme="openDeleteModal" />
        </v-col>
      </v-row>
    </v-container>
    <theme-management-drawer
      ref="themeManagementDrawer"
      :theme-to-update="themeToUpdate"
      :parent-theme="parentTheme"
      :space-name="spaceName"
      @themeAdded="addCreatedTheme"
      @themeUpdated="closeDrawerAfterThemeUpdate" />
    <exo-confirm-dialog
      ref="confirmDialog"
      :message="$t('addon.elearning.theme.deleteConf')"
      :title="$t('addon.elearning.tutorial.confirmD')"
      :ok-label="$t('addon.elearning.tutorial.confirm')"
      :cancel-label="$t('addon.elearning.tutorial.cancel')"
      @ok="deleteTheme" />
  </v-app>
</template>

<script>
export default {
  props: {
    spaceName: {
      type: String,
      default: ''
    },
    space: {
      type: Object,
      default: null
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
      offset: 0,
      limit: 0,
      themeToUpdate: null,
      deleteId: null,
      errors: [],
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
      const isRoot = this.parentTheme ? false : true;
      return this.$themeService.getThemes(this.spaceName, isRoot, this.keyword, this.offset, this.limit).then(data => {
        this.themesCount = data.count;
        this.canUpdate = data.canUpdate;
        this.space = data.space;
        this.themesList = data.themeList;
      }).catch((e) => this.errors.push(e));
    },
    getThemeChildren() {
      return this.$themeService.getChildThemes(this.spaceName, this.parentTheme.id, this.keyword, this.offset, this.limit).then(data => {
        this.themesCount = data.count;
        this.themesList = data.themeList;
      }).catch((e) => this.errors.push(e));
    },
    addCreatedTheme(addedTheme) {
      this.themesList.push(addedTheme);
      this.$refs.themeManagementDrawer.close();
    },
    openThemeDrawerForUpdate(theme) {
      this.themeToUpdate = theme;
      this.$refs.themeManagementDrawer.open();
    },
    closeDrawerAfterThemeUpdate(updatedTheme) {
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
        this.confirmDialog = false;
        const index = this.themesList.findIndex(theme => theme.id === this.deleteId);
        this.themesList.splice(index, 1);
      }).catch((e) => {
        console.error('Error deleting theme', e);
        this.confirmDialog = false;
      });
    }
  }
};
</script>