import ElearningManagement from './components/ElearningManagement.vue';
import ThemeTutorialMixDashboard from './components/Tutorials/ThemeTutorialMixDashboard.vue';
import TutorialCard from './components/Tutorials/TutorialCard.vue';
import TutorialCardList from './components/Tutorials/TutorialCardList.vue';
import ThemeTutorialMixDashboardToolbar from './components/Tutorials/ThemeTutorialMixDashboardToolbar.vue';
import TutorialDisplayDrawer from './components/Tutorials/drawer/TutorialDisplayDrawer.vue';
import TutorialManagementDrawer from './components/Tutorials/drawer/TutorialManagementDrawer.vue';
import TutorialUpdateDrawer from './components/Tutorials/drawer/TutorialUpdateDrawer.vue';
import TutorialMoveDrawer from './components/Tutorials/drawer/TutorialMoveDrawer.vue';
import ThemeDashboard from './components/Themes/ThemeDashboard.vue';
import ThemeCard from './components/Themes/ThemeCard.vue';
import ThemeCardList from './components/Themes/ThemeCardList.vue';
import ThemeManagementDrawer from './components/Themes/drawer/ThemeManagementDrawer.vue';

const components = {
  'elearning-management': ElearningManagement,
  'theme-tutorial-mix-dashboard': ThemeTutorialMixDashboard,
  'tutorial-card': TutorialCard,
  'tutorial-card-list': TutorialCardList,
  'theme-tutorial-mix-dashboard-toolbar': ThemeTutorialMixDashboardToolbar,
  'tutorial-display-drawer': TutorialDisplayDrawer,
  'tutorial-management-drawer': TutorialManagementDrawer,
  'tutorial-update-drawer': TutorialUpdateDrawer,
  'tutorial-move-drawer': TutorialMoveDrawer,
  'theme-dashboard': ThemeDashboard,
  'theme-card': ThemeCard,
  'theme-card-list': ThemeCardList,
  'theme-management-drawer': ThemeManagementDrawer,
};

for (const key in components) {
  Vue.component(key, components[key]);
}

//get overrided components if exists
if (extensionRegistry) {
  const components = extensionRegistry.loadComponents('Elearning');
  if (components && components.length > 0) {
    components.forEach(cmp => {
      Vue.component(cmp.componentName, cmp.componentOptions);
    });
  }
}