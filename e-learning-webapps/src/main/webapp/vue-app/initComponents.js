import App from './components/app.vue';
import ElearningDashboard from './components/ElearningDashboard.vue';
import ElearningDisplayDrawer from './components/ElearningDisplayDrawer.vue';
import ElearningManagementDrawer from './components/ElearningManagementDrawer.vue';

const components = {
  'app': App,
  'elearning-dashboard': ElearningDashboard,
  'elearning-display-drawer': ElearningDisplayDrawer,
  'elearning-management-drawer': ElearningManagementDrawer
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