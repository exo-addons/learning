import ElearningEditor from './components/ElearningEditor.vue';
import StepDashboard from './components/Steps/StepDashboard.vue';

const components = {
  'elearning-editor': ElearningEditor,
  'step-dashboard': StepDashboard,
};

for (const key in components) {
  Vue.component(key, components[key]);
}

//get overrided components if exists
if (extensionRegistry) {
  const components = extensionRegistry.loadComponents('ElearningEditor');
  if (components && components.length > 0) {
    components.forEach(cmp => {
      Vue.component(cmp.componentName, cmp.componentOptions);
    });
  }
}