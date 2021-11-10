<template>
  <div id="theme_tutorial_dashboard_toolbar">
    <v-toolbar
      id="themeTutorialDashboardToolbar"
      flat
      class="toolbarLarge pb-3">
      <v-icon @click="backToThemeDashboard" id="tuto_toolbar_prom">account_tree</v-icon>
      <v-breadcrumbs :items="items">
        <template v-slot:item="{ item }">
          <v-breadcrumbs-item
            class="breadcrumb"
            :disabled="item.disabled"
            @click="openTheme(item.themeId)">
            {{ item.name }}
          </v-breadcrumbs-item>
        </template>
        <template v-slot:divider>
          <v-icon>mdi-chevron-right</v-icon>
        </template>
      </v-breadcrumbs>
      <v-spacer />
      <v-scale-transition>
        <div class="add_them_tutorial_wrapper">
          <v-icon id="theme_tutorial_add_btn" @click="displayActionMenu = true">mdi-plus</v-icon>
          <v-menu
            v-model="displayActionMenu"
            attach="#themeTutorialDashboardToolbar"
            content-class="tutorial_Dashboard_menu"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
            <v-list class="toolbar_menu_list" dense>
              <v-list-item v-if="parentTheme" @click="addNewTutorial">
                <v-list-item-title class="toolbar_menu_list_items">
                  <v-icon class="toolbar_menu_icon">mdi-school</v-icon>
                  <span class="toolbar_menu_text">{{ $t('addon.elearning.tutorial.create') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="addNewSubTheme">
                <v-list-item-title class="toolbar_menu_list_items">
                  <v-icon class="toolbar_menu_icon">mdi-folder</v-icon>
                  <span class="toolbar_menu_text">{{ $t('addon.elearning.theme.create') }}</span>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </v-scale-transition>
      <v-scale-transition>
        <div id="tuto_filter_input">
          <v-text-field
            v-model="keyword"        
            :placeholder="$t('addon.elearning.filter.placeholder')"
            prepend-inner-icon="fa-filter"
            class="pa-0 ms-3 me-3 my-auto"
            clearable />
        </div>
      </v-scale-transition>
    </v-toolbar>
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
    keyword: {
      type: String,
      default: '',
    }
  },
  data() {
    return {
      items: [],
      displayActionMenu: false,
      errors: [],
    };
  },
  watch: {
    keyword() {
      this.$root.$emit('key-changed', this.keyword);
    },
    parentTheme: {
      immediate: true,
      handler() {
        this.updateBreadcrumbs();
      }
    },
  },  
  created() {
    this.updateBreadcrumbs();
    $(document).on('mousedown', () => {
      if (this.displayActionMenu) {
        window.setTimeout(() => {
          this.closeToolbarMenu();
        }, 200);
      }
    });
  },  
  methods: {
    openTheme(themeId) {
      if (themeId && this.parentTheme.id !== themeId) {
        this.$themeService.getThemeById(themeId).then(parentTheme => {
          this.$root.$emit('displayThemeContent', parentTheme);
        }).catch((e) => this.errors.push(e));
      } else if (!themeId) {
        this.$root.$emit('displayThemesBoard');
      }
    },
    updateBreadcrumbs() {
      this.items = this.parentTheme ? this.parentTheme.breadcrumbs : [{
        themeId: null,
        name: 'Home',
        disabled: false
      }];
    },
    addNewTutorial() {
      this.$root.$emit('openTutorialDrawer');
    },
    addNewSubTheme() {
      this.$root.$emit('openThemeDrawer');
    },
    backToThemeDashboard() {
      this.$root.$emit('displayThemesBoard');
    },
    closeToolbarMenu() {
      this.displayActionMenu = false;
    },
  }
};

</script>