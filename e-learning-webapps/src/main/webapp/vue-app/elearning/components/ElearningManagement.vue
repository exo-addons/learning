<template>
  <v-app
    class="transparent"
    role="main"
    flat>
    <div>
      <theme-dashboard 
        v-if="displayThemesBoard"
        ref="themeDashboard"
        :space-name="spaceName" />
      <theme-tutorial-mix-dashboard 
        v-else
        ref="themeTutorialMixDashboard"
        :parent-theme="parentTheme"
        :space-name="spaceName" />
    </div>
    <v-row dense>
      <v-col cols="4">
        <v-alert
          v-model="alert"
          border="left"
          :type="alertType"
          colored-border
          dismissible>
          {{ message }}
        </v-alert>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      displayThemesBoard: true,
      spaceName: eXo.env.portal.spaceName,
      parentTheme: null,
      alert: false,
      alertType: '',
      message: '',
    };
  },
  watch: {
    parentTheme: {
      immediate: true,
      handler() {
        this.displayThemesBoard = !this.parentTheme;
      }
    }
  },
  created() {
    const currentUrlSearchParams = window.location.search;
    const queryParams = new URLSearchParams(currentUrlSearchParams);
    if (queryParams.has('parentThemeId')) {
      const parentThemeId = queryParams.get('parentThemeId');
      this.$themeService.getThemeById(parentThemeId).then(parentTheme => {
        this.displayThemesBoard = false;
        this.parentTheme = parentTheme;
      }).catch((e) => console.error('Error when retrieving parent theme', e));
      
    }
    
    this.$root.$on('parent-theme-updated', updatedParent => {
      this.changeParentTheme(updatedParent);
    });
    
    this.$root.$on('displayThemeContent', theme => {
      this.changeParentTheme(theme);
    });
    
    this.$root.$on('displayThemesBoard', () => {
      this.displayThemesBoard = true;
      this.parentTheme = null;
      window.history.pushState('', '', `${eXo.env.server.portalBaseURL}`);
    });

    this.$root.$on('show-alert', messageObject => {
      this.displayMessage(messageObject);
    });
  },
  methods: {
    changeParentTheme(newParent) {
      this.displayThemesBoard = false;
      this.parentTheme = newParent;
      window.history.pushState('', '', `${eXo.env.server.portalBaseURL}?parentThemeId=${this.parentTheme.id}`);
    },
    displayMessage(messageObject) {
      this.message = messageObject.message;
      this.alertType = messageObject.type;
      this.alert = true;
      window.setTimeout(() => this.alert = false, 5000);
    },
  }
};
</script>