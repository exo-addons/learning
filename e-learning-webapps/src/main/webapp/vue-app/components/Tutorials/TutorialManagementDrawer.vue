<template>
  <v-app
    id="tutorials_management"
    flat>
    <exo-drawer 
      id="tutorialManagementDrawer"
      ref="tutorialManagementDrawer"
      right>
      <template slot="title">
        {{ $t('addon.elearning.tutorial.creating') }}     
      </template>
      <template slot="content">
        <template>
          <v-stepper
            v-model="add_steps"
            vertical
            class="tutorial_steps_wrapper">
            <v-stepper-step
              :complete="add_steps > 1"
              step="1">
              {{ $t('addon.elearning.tutorial.details') }}
              <small v-show="add_steps === 2 && tutoA.title != null && tutoA.description != null" class="stepper_label">{{ tutoA.title }} : {{ tutoA.description }}</small>
            </v-stepper-step>

            <v-stepper-content step="1">
              <label class="tuto_title_label" :for="tutoA.title">
                {{ $t('addon.elearning.theme.label.title') }}  
              </label>
              <v-text-field
                class="primary_tutorial_input"
                clearable
                :placeholder="$t('addon.elearning.tutorial.label.title')"
                name="title"
                v-model="tutoA.title" />
              <label class="tuto_description_label" :for="tutoA.description">
                {{ $t('addon.elearning.tutorial.label.description') }}  
              </label>
              <extended-textarea
                class="primary_text_area_input"
                :placeholder="$t('addon.elearning.tutorial.label.description')"
                :max-length="255"
                name="description"
                v-model="tutoA.description" />
              <v-btn
                class="tutorial_stepper_next_btn"
                @click="add_steps = 2"
                :disabled="!isFirstStepComplete">
                {{ $t('addon.elearning.tutorial.stepper.continue') }}
                <v-icon class="tutorial_stepper_next_btn_icon">mdi-arrow-right</v-icon>
              </v-btn>
            </v-stepper-content>

            <v-stepper-step
              :complete="add_steps > 2"
              step="2">
              {{ $t('addon.elearning.tutorial.stepper.themes') }}
            </v-stepper-step>

            <v-stepper-content step="2">
              <template>
                <v-container fluid id="themes_checkbox_container">
                  <v-checkbox
                    v-for="theme in themes"
                    v-model="tutoA.themeIds"
                    :key="theme.id"
                    :id="'theme-'+theme.id"
                    :label="theme.name"
                    :value="theme.id" />
                </v-container>
              </template>
              <v-btn
                class="tutorial_stepper_back_btn"
                @click="add_steps = 1">
                <v-icon class="tutorial_stepper_back_btn_icon">mdi-arrow-left</v-icon>
                {{ $t('addon.elearning.tutorial.stepper.back') }}
              </v-btn>
            </v-stepper-content>
          </v-stepper>
        </template>
      </template>
      <template slot="footer">
        <v-btn
          class="tuto_drawer_btn_add"
          @click="tutoPost"
          :disabled="!isStepsComplete">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="clear">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </v-app>
</template>
<script>
export default {
  data() {
    return {
      add_steps: 1,
      tutoId: null,
      tutoU: null,
      errors: [],
      status: ['DRAFT', 'PUBLISHED', 'ARCHIVED'],
      themes: [],
      tutoA: {
        title: null,
        description: null,
        status: 'PUBLISHED',
        themeIds: []
      }
    };
  },

  computed: {
    isStepsComplete() {
      return this.tutoA.title && this.tutoA.themeIds.length > 0;
    },
    isFirstStepComplete() {
      return this.tutoA.title;
    }
  },

  created() {
    this.$root.$on('addTuto', () => {
      this.$refs.tutorialManagementDrawer.open();
    });
  },

  methods: {
    tutoPost() {
      return this.$tutoService.tutoPost(this.tutoA)
        .then(() => {
          this.$root.$emit('tutoCreated');
        })
        .then(() => {
          this.clear();
          this.$refs.tutorialManagementDrawer.close();
        })
        .catch((e) => this.errors.push(e));

    },

    getThemes() {
      return this.$themeService.getThemes()
        .then((data) => {
          (this.themes = data);
        })
        .catch((e) => this.errors.push(e));
    },
    clear() {
      this.tutoA.title = null;
      this.tutoA.description = null;
      this.tutoA.themeIds = [];
      this.add_steps = 1;
    }
  }
};

</script>