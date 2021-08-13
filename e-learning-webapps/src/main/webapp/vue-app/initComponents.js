import App from './components/app.vue';
import ElearningDashboard from './components/ElearningDashboard.vue';
import ElearningManagement from './components/ElearningManagement.vue';
import ElearningDisplay from './components/ElearningDisplay.vue';

const components = {
  'app': App,
  'elearning-dashboard': ElearningDashboard,
  'elearning-management': ElearningManagement,
  'elearning-display': ElearningDisplay
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