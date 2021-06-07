import ElearningManagement from './components/ElearningManagement.vue';
import TutorialDashboard from './components/Tutorials/TutorialDashboard.vue';
import TutorialCard from './components/Tutorials/TutorialCard.vue';
import TutorialCardList from './components/Tutorials/TutorialCardList.vue';
import TutorialDashboardToolbar from './components/Tutorials/TutorialDashboardToolbar.vue';
import TutorialDisplayDrawer from './components/Tutorials/TutorialDisplayDrawer.vue';
import TutorialManagementDrawer from './components/Tutorials/TutorialManagementDrawer.vue';
import ThemeDashboard from './components/Themes/ThemeDashboard.vue';
import ThemeCard from './components/Themes/ThemeCard.vue';
import ThemeCardList from './components/Themes/ThemeCardList.vue';
import ThemeDashboardToolbar from './components/Themes/ThemeDashboardToolbar.vue';
import ThemeManagementDrawer from './components/Themes/ThemeManagementDrawer.vue';

const components = {
  'elearning-management': ElearningManagement,
  'tutorial-dashboard': TutorialDashboard,
  'tutorial-card': TutorialCard,
  'tutorial-card-list': TutorialCardList,
  'tutorial-dashboard-toolbar': TutorialDashboardToolbar,
  'tutorial-display-drawer': TutorialDisplayDrawer,
  'tutorial-management-drawer': TutorialManagementDrawer,
  'theme-dashboard': ThemeDashboard,
  'theme-card': ThemeCard,
  'theme-card-list': ThemeCardList,
  'theme-dashboard-toolbar': ThemeDashboardToolbar,
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