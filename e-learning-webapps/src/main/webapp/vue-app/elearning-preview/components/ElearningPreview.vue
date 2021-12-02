<template>
  <v-app
    class="transparent"
    role="main"
    flat>
    <v-container id="eLearningPreview">
      <v-row>
        <v-col>
          <div>
            <h1 class="text-center text-color" :style="{color: theme && theme.color ? `${theme.color}!important` : ''}">{{ tutorial && tutorial.title }}</h1>
          </div>
        </v-col>       
      </v-row>
      <v-row>
        <v-col class="pa-8">
          <div>
            <h2 class="text-color">{{ step && step.title }}</h2>
          </div>
        </v-col>       
      </v-row>
      <v-row>
        <v-col>
          <div class="pa-12 align-center">
            MEDIA
          </div>          
        </v-col>
      </v-row>
      <v-row>
        <v-col class="pa-8">
          <div
            class="notes-application-content text-color"
            v-html="step && step.content">
          </div>
        </v-col>   
      </v-row>
      <template>
        <div class="steps-paginator text-center">
          <v-pagination
            v-model="stepOrder"
            :length="tutorial && tutorial.stepsIds.length"
            @input="goToStep" />
        </div>
      </template>
    </v-container>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      stepOrder: 1,
      spaceId: '',
      theme: null,
      tutorial: null,
      step: null,
    };
  },
  created() {
    const queryPath = window.location.search;
    const urlParams = new URLSearchParams(queryPath);
    if (urlParams.has('spaceId')) {
      this.spaceId = urlParams.get('spaceId');
    }
    if (urlParams.has('themeId')) {
      const themeId = urlParams.get('themeId');
      this.getTheme(themeId);
    }
    if (urlParams.has('tutorialId')) {
      const tutorialId = urlParams.get('tutorialId');
      this.getTutorial(tutorialId);
    }
  },
  methods: {
    getTheme(id) {
      this.$themeService.getThemeById(id).then(theme => {
        this.theme = theme;
      }).catch((e) => console.error('Error when retrieving theme', e));
    },
    getTutorial(id) {
      this.$tutoService.getTutorialById(id).then(tutorial => {
        this.tutorial = tutorial;
        this.getFirstStep(this.tutorial.id);
      }).catch(e => {
        console.error('Error when retrieving tutorial', e);
      });
    },
    getFirstStep(tutorialId) {
      this.$tutoService.getTutorialStepByOrder(tutorialId, 1).then(step => {
        this.step = step;
      });
    },
    goToStep() {
      this.$tutoService.getTutorialStepByOrder(this.tutorial.id, this.stepOrder).then(step => {
        this.step = step;
      });
    },
  },
};
</script>