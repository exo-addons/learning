import ElearningManagement from './components/ElearningManagement.vue';
import TutorialDashboard from './components/Tutorials/TutorialDashboard.vue';
import TutorialDisplayDrawer from './components/Tutorials/TutorialDisplayDrawer.vue';
import TutorialManagementDrawer from './components/Tutorials/TutorialManagementDrawer.vue';
import ThemeDashboard from './components/Themes/ThemeDashboard.vue';
import ThemeManagementDrawer from './components/Themes/ThemeManagementDrawer.vue';

const components = {
  'elearning-management': ElearningManagement,
  'tutorial-dashboard': TutorialDashboard,
  'tutorial-display-drawer': TutorialDisplayDrawer,
  'tutorial-management-drawer': TutorialManagementDrawer,
  'theme-dashboard': ThemeDashboard,
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