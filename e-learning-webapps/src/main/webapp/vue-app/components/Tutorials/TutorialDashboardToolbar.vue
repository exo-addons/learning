<template>
  <v-app id="tutorial_dashboard_toolbar">
    <v-toolbar
      id="TutorialDashboardToolbar"
      flat
      class="tasksToolbar toolbarLarge pb-3">
      <v-toolbar-title>
        <v-icon @click="back">mdi-arrow-left</v-icon>
        <span> {{ themeName }} </span>
        <v-btn
          class="exo_primary_btn"
          @click="createTuto">
          <v-icon>mdi-plus</v-icon>
          {{ $t('addon.elearning.tutorial.create') }}
        </v-btn>
      </v-toolbar-title>
    </v-toolbar>
  </v-app>
</template>

<script>
export default {
  props: {
    themeName: {
      type: String,
      default: ''
    }
  },

  methods: {
    createTuto(){
      this.$root.$emit('makeTuto');
    },
    back(){
      this.$root.$emit('backtoThemes');
    },   
  }
};

</script>