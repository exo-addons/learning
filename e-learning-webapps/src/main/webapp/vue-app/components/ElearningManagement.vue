<template>
  <v-app id="elearning_app">
    <theme-dashboard v-show="showThemes" />
    <tutorial-dashboard v-show="!showThemes" />
    <theme-management-drawer />
    <tutorial-management-drawer />
    <tutorial-update-drawer />
    <tutorial-move-drawer />
    <tutorial-display-drawer />
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      showThemes: true
    };
  },

  created() {
    this.$root.$on('showTutos', (id) => {
      this.$root.$emit('setId', id);
      this.showThemes = false;
    });
    this.$root.$on('backThemes', () => {
      this.showThemes = true;
    });
  }
};
</script>