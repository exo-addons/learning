<template>
  <v-app
    class="transparent"
    role="main"
    flat>
    <div id="eLearningPreview" class="py-10">
      <div class="pa-1 white">
        <v-row class="previewHeader ma-0">
          <v-col class="backBtn pl-2" cols="6">
            <div>
              <v-btn
                icon
                @click="back">
                <v-icon>mdi-arrow-left-circle</v-icon>
              </v-btn>
              <span class="text-h5 text-color">{{ tutorial && tutorial.title }}</span>
            </div>
          </v-col>
          <v-spacer />
          <v-col class="editBtn pr-2" cols="1">
            <v-btn
              icon
              @click="edit">
              <i class="uiIconEdit v-icon"></i>
            </v-btn>
          </v-col>
        </v-row>
        <v-row class="stepImageContainer ma-0">
          <v-col>
            <div class="slideContainer py-5 px-14">
              <v-carousel 
                @change="goToStep"
                hide-delimiters>
                <v-carousel-item
                  v-for="(item,i) in allSteps"
                  :key="i"
                  :src="item.src" />
              </v-carousel>
            </div>
          </v-col>
        </v-row>
        <v-row class="mt-3 mb-1 mx-0">
          <v-spacer />
          <v-col cols="4">
            <v-progress-linear 
              v-model="stepLevel"
              height="8"
              rounded />
            <div class="mt-1 text-center primary--text text-decoration-underline">
              <span>{{ currentStepIndex + 1 + '/' + allSteps.length }}</span>
            </div>
          </v-col>
          <v-spacer />
        </v-row>
        <div class="px-8">
          <v-divider />
        </div>
        <v-row class="ma-0">
          <v-col class="px-8 pb-0">
            <div>
              <span class="text-h6 text-color">{{ currentStep && currentStep.title }}</span>
            </div>
          </v-col>
        </v-row>
        <v-row class="ma-0">
          <v-col class="pt-0 pb-8 px-8">
            <div
              class="notes-application-content text-color"
              v-html="currentStep && currentStep.content">
            </div>
          </v-col>   
        </v-row>
      </div>
    </div>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      spaceId: '',
      theme: null,
      tutorial: null,
      currentStep: null,
      allSteps: [],
      currentStepIndex: 0,
    };
  },
  computed: {
    stepLevel() {
      return (this.currentStepIndex + 1) * 100 / this.allSteps.length;
    },
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
        this.getAllStep();
      }).catch(e => {
        console.error('Error when retrieving tutorial', e);
      });
    },
    getAllStep() {
      this.$tutoService.getTutorialSteps(this.tutorial.id).then(steps => {
        this.allSteps = steps;
      });
    },
    goToStep(index) {
      this.currentStep = this.allSteps[index];
      this.currentStepIndex = index;
    },
    edit() {
      window.open(`${eXo.env.portal.context}/${eXo.env.portal.portalName}/elearning-editor?spaceId=${eXo.env.portal.spaceId}&themeId=${this.theme.id}&tutorialId=${this.tutorial.id}`, '_blank');
    },
    back() {
      console.log('Back !');
    },
  },
};
</script>