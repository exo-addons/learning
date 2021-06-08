<template>
  <v-app id="theme_dashboard_toolbar">
    <v-toolbar
      id="ThemeDashboardToolbar"
      flat
      class="toolbarLarge pb-3 ">
      <v-toolbar-title />
      <v-spacer />

      <v-scale-transition>
        <v-icon id="theme_add_btn" @click="displayActionMenu = true">mdi-plus</v-icon>
      </v-scale-transition>
      <v-menu
        v-model="displayActionMenu"
        attach="#ThemeDashboardToolbar"
        transition="slide-x-reverse-transition"
        offset-y>
        <v-list class="pa-0" dense>
          <v-list-item class="menu-list" @click="createTheme">
            <v-list-item-title class="subtitle-2">
              <v-icon id="theme_add_list_btn">mdi-folder</v-icon>
              <span>{{ $t('addon.elearning.theme.create') }}</span>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <v-scale-transition>
        <div id="theme_filter_input">
          <v-text-field        
            placeholder="Filter By Name"
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
      displayActionMenu: false
    };
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