<template>
  <v-app id="elearning_app">
    <theme-dashboard 
      v-show="showThemes"
      :space-name="spaceName" />
    <tutorial-dashboard 
      v-show="!showThemes"
      :space-name="spaceName" />
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
      showThemes: true,
      spaceName: eXo.env.portal.spaceName,
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