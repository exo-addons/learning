<template>
  <v-app id="theme_dashboard_toolbar">
    <v-toolbar
      id="ThemeDashboardToolbar"
      flat
      class="toolbarLarge pb-3 ">
      <v-toolbar-title />
      <v-spacer />

      <v-scale-transition>
        <div class="add_theme_wrapper">
          <v-icon id="theme_add_btn" @click="displayActionMenu = true">mdi-plus</v-icon>
          <v-menu
            v-model="displayActionMenu"
            attach="#ThemeDashboardToolbar"
            content-class="theme_Dashboard_menu"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
            <v-list class="toolbar_menu_list" dense>
              <v-list-item @click="createTheme">
                <v-list-item-title class="toolbar_menu_list_items">
                  <v-icon class="toolbar_menu_icon" id="theme_add_list_btn">mdi-folder</v-icon>
                  <span class="toolbar_menu_text">{{ $t('addon.elearning.theme.create') }}</span>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </v-scale-transition>

      <v-scale-transition>
        <div id="theme_filter_input">
          <v-text-field
            v-model="keyword"        
            :placeholder="$t('addon.elearning.filter.placeholder')"
            prepend-inner-icon="fa-filter"
            class="pa-0 ms-3 me-3 my-auto"
            clearable />
        </div>
      </v-scale-transition>
    </v-toolbar>
  </v-app>
</template>

<script>
export default {
  data (){
    return {
      displayActionMenu: false,
    };
  },
  props: {
    keyword: {
      type: String,
      default: null,
    }
  },
  watch: {
    keyword() {
      this.$emit('keyword-changed', this.keyword);
    }
  },
  created () {
    $(document).on('mousedown', () => {
      if (this.displayActionMenu) {
        window.setTimeout(() => {
          this.displayActionMenu = false;
        }, 200);
      }
    });
  },
  methods: {
    createTheme(){
      this.$root.$emit('makeTheme');
    } 
  }
};

</script>