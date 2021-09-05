<template>
  <v-app id="tutorial_dashboard_toolbar">
    <v-toolbar
      id="TutorialDashboardToolbar"
      flat
      class="toolbarLarge pb-3">
      <v-icon @click="back" id="tuto_toolbar_prom">mdi-chart-tree</v-icon>
      <v-icon>mdi-chevron-right</v-icon>
      <span> {{ themeName }} </span>
      <v-toolbar-title />
      <v-spacer />

      <v-scale-transition>
        <div class="add_tuto_wrapper">
          <v-icon id="tuto_add_btn" @click="displayActionMenu = true">mdi-plus</v-icon>
          <v-menu
            v-model="displayActionMenu"
            attach="#TutorialDashboardToolbar"
            content-class="tuto_Dashboard_menu"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
            <v-list class="toolbar_menu_list" dense>
              <v-list-item @click="createTuto">
                <v-list-item-title class="toolbar_menu_list_items">
                  <v-icon class="toolbar_menu_icon">mdi-school</v-icon>
                  <span class="toolbar_menu_text">{{ $t('addon.elearning.tutorial.create') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="clickable">
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
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      displayActionMenu: false
    };
  },
  props: {
    themeName: {
      type: String,
      default: ''
    },
    keyword: {
      type: String,
      default: null,
    }
  },
  watch: {
    keyword() {
      this.$root.$emit('key-changed', this.keyword);
    }
  },
  created() {
    $(document).on('mousedown', () => {
      if (this.displayActionMenu) {
        window.setTimeout(() => {
          this.displayActionMenu = false;
        }, 200);
      }
    });
  },
  methods: {
    createTuto() {
      this.$root.$emit('makeTuto');
    },
    back() {
      this.$root.$emit('backtoThemes');
    },
  }
};

</script>