import ElearningEditor from './components/ElearningEditor.vue';
import StepCardList from './components/Steps/StepCardList.vue';
import StepCard from './components/Steps/StepCard.vue';
import TutorialManagementDrawer from '../elearning/components/Tutorials/drawer/TutorialManagementDrawer.vue';

const components = {
  'elearning-editor': ElearningEditor,
  'step-card-list': StepCardList,
  'step-card': StepCard,
  'tutorial-management-drawer': TutorialManagementDrawer,
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