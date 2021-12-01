<template>
  <div class="steps-list">
    <step-card
      v-for="step in stepsList"
      :key="step.id"
      :tutorial="tutorial"
      :step="step"
      @step-deleted="getSteps" />
  </div>
</template>

<script>
export default {
  props: {
    tutorial: {
      type: Object,
      default: null
    },
  },
  data() {
    return {
      stepsList: [],
    };
  },
  watch: {
    tutorial() {
      if (this.tutorial) {
        this.getSteps();
      }
    },
  },
  created() {
    this.$root.$on('step-added', addedStep => {
      const addedStepIndex = this.stepsList.findIndex(step => step.id === addedStep.id);
      if (addedStepIndex < 1) {
        this.stepsList.push(addedStep);
      } else {
        this.$set(this.stepsList, addedStepIndex, addedStep);
      }
    });
  },
  methods: {
    getSteps() {
      this.$tutoService.getTutorialSteps(this.tutorial.id).then(steps => {
        this.stepsList = steps;
      });
    },
  },
};
</script>