<template>
  <v-app id="theme_dashboard_toolbar">
    <v-toolbar
      id="ThemeDashboardToolbar"
      flat
      class="tasksToolbar toolbarLarge pb-3 ">
      <v-toolbar-title>
        <v-btn
          class="exo_primary_btn"
          @click="createTheme">
          <v-icon>mdi-plus</v-icon>
          {{ $t('addon.elearning.theme.create') }}
        </v-btn>
      </v-toolbar-title>
    </v-toolbar>
  </v-app>
</template>

<script>
export default {
  methods: {
    createTheme(){
      this.$root.$emit('makeTheme');
    }  
  }
};

</script>