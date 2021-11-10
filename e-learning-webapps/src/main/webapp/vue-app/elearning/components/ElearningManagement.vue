<template>
  <v-app id="elearning_app">
    <theme-dashboard 
      v-if="displayThemesBoard"
      :space-name="spaceName" />
    <theme-tutorial-mix-dashboard 
      v-else
      :parent-theme="parentTheme"
      :space="space"
      :can-update="canUpdate" />
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      displayThemesBoard: true,
      spaceName: eXo.env.portal.spaceName,
      parentTheme: null,
      space: null,
      canUpdate: false,
    };
  },

  created() {
    this.$root.$on('parent-theme-updated', updatedParent => {
      this.parentTheme = updatedParent;
    });
    this.$root.$on('displayThemeContent', theme => {
      this.parentTheme = theme;
    });
    this.$root.$on('displayRootThemeContent', (parentTheme, space, canUpdate) => {
      this.displayThemesBoard = false;
      this.parentTheme = parentTheme;
      this.space = space;
      this.canUpdate = canUpdate;
    });
    this.$root.$on('displayThemesBoard', () => {
      this.displayThemesBoard = true;
    });
  }
};
</script>