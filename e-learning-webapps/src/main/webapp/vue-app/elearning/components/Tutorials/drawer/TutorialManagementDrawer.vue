<template>
  <div id="tutorials_management">
    <exo-drawer 
      id="tutorialManagementDrawer"
      ref="tutorialManagementDrawer"
      right>
      <template slot="title">
        {{ !isUpdateMode ? $t('addon.elearning.tutorial.create.form.title') : $t('addon.elearning.tutorial.update.form.title') }}
      </template>
      <template slot="content">
        <template>
          <v-stepper
            v-model="add_steps"
            v-if="!isUpdateMode"
            vertical
            class="tutorial_steps_wrapper">
            <v-stepper-step
              :complete="add_steps > 1"
              step="1">
              {{ $t('addon.elearning.tutorial.form.details.label') }}
              <small v-show="add_steps === 2 && tutorial.title != null && tutorial.description != null" class="stepper_label">{{ tutorial.title }} : {{ tutorial.description }}</small>
            </v-stepper-step>

            <v-stepper-content step="1">
              <label class="tuto_title_label" :for="tutorial.title">
                {{ `${$t('addon.elearning.theme.form.title.label')}*` }}
              </label>
              <v-text-field
                class="primary_tutorial_input"
                clearable
                :placeholder="$t('addon.elearning.tutorial.form.title.placeholder')"
                name="title"
                v-model="tutorial.title" />
              <label class="tuto_description_label" :for="tutorial.description">
                {{ $t('addon.elearning.tutorial.for.description.label') }}
              </label>
              <extended-textarea
                class="primary_text_area_input"
                :placeholder="$t('addon.elearning.tutorial.for.description.placeholder')"
                :max-length="255"
                name="description"
                v-model="tutorial.description" />
              <v-btn
                class="tutorial_stepper_next_btn"
                @click="add_steps = 2"
                :disabled="!isFirstStepComplete">
                {{ $t('addon.elearning.tutorial.form.stepper.continue') }}
                <v-icon class="tutorial_stepper_next_btn_icon">mdi-arrow-right</v-icon>
              </v-btn>
            </v-stepper-content>

            <v-stepper-step
              :complete="add_steps > 2"
              step="2">
              {{ $t('addon.elearning.tutorial.form.tutorial.stepper.theme.label') }}
            </v-stepper-step>

            <v-stepper-content step="2">
              <template>
                <v-container fluid id="themes_checkbox_container">
                  <v-checkbox
                    v-for="theme in themes"
                    v-model="tutorial.themeIds"
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
                {{ $t('addon.elearning.tutorial.form.stepper.back') }}
              </v-btn>
            </v-stepper-content>
          </v-stepper>
          <div v-else class="mt-4 pa-3">
            <label class="tuto_title_label" :for="tutorial.title">
              {{ `${$t('addon.elearning.theme.form.title.label')}*` }}
            </label>
            <v-text-field
              class="primary_tutorial_input"
              clearable
              :placeholder="$t('addon.elearning.tutorial.form.title.placeholder')"
              name="title"
              v-model="tutorial.title" />
            <label class="tuto_description_label" :for="tutorial.description">
              {{ $t('addon.elearning.tutorial.for.description.label') }}
            </label>
            <extended-textarea
              class="primary_text_area_input"
              :placeholder="$t('addon.elearning.tutorial.for.description.placeholder')"
              :max-length="255"
              name="description"
              v-model="tutorial.description" />
          </div>
        </template>
      </template>
      <template slot="footer">
        <v-btn
          class="tuto_drawer_btn_add"
          @click="createOrUpdateTutorial"
          :disabled="!isStepsComplete">
          {{ $t('addon.elearning.tutorial.form.confirm') }}
        </v-btn>
        <v-btn v-if="!isUpdateMode" class="exo_cancel_btn" @click="clear">{{ $t('addon.elearning.tutorial.form.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </div>
</template>
<script>
export default {
  props: {
    tutorial: {
      type: Object,
      default: () => ({
        id: null,
        title: null,
        description: null,
        themeIds: [], 
      })
    },
    parentTheme: {
      type: Object,
      default: null
    },
    spaceName: {
      type: String,
      default: ''
    },
  },  
  data() {
    return {
      isUpdateMode: false,
      add_steps: 1,
      status: ['DRAFT', 'PUBLISHED', 'ARCHIVED'],
      themes: [],
    };
  },
  computed: {
    isStepsComplete() {
      return this.tutorial && this.tutorial.title && this.tutorial.themeIds && this.tutorial.themeIds.length > 0;
    },
    isFirstStepComplete() {
      return this.tutorial && this.tutorial.title;
    },
  },
  methods: {
    createOrUpdateTutorial() {
      if (!this.isUpdateMode) {
        let tutorialId;
        return this.$tutoService.addTutorial(this.tutorial).then(addedTutorial => {
          tutorialId = addedTutorial.id;
          this.$emit('tutorialAdded', addedTutorial);
          const tutorialIds = this.parentTheme.tutorialIds;
          tutorialIds.push(addedTutorial.id);
          const updatedParent = {
            tutorialIds: tutorialIds,
          };
          Object.assign(this.parentTheme, updatedParent);
          this.$root.$emit('parent-theme-updated', this.parentTheme);
        }).catch((e) => console.error('Error when saving tutorial', e))
          .finally(() => window.open(`${eXo.env.portal.context}/${eXo.env.portal.portalName}/elearning-editor?spaceId=${eXo.env.portal.spaceId}&themeId=${this.parentTheme.id}&tutorialId=${tutorialId}`, '_blank'));
      } else {
        return this.$tutoService.updateTutorial(this.tutorial).then(updatedTutorial => {
          this.$emit('tutorialUpdated', updatedTutorial);
        }).catch((e) => console.error('Error when updating tutorial', e));
      }
    },
    getThemes() {
      const isRoot = !this.parentTheme;
      return this.$themeService.getThemes(this.spaceName, isRoot, '', 0, 0).then((data) => {
        this.themes = data.themeList;
        this.tutorial.themeIds.push(this.parentTheme.id);
      }).catch((e) => console.error('Error when retrieving themes', e));
    },
    open() {
      this.isUpdateMode = !!(this.tutorial && this.tutorial.id);
      if (!this.isUpdateMode) {
        this.getThemes();
      }
      this.$refs.tutorialManagementDrawer.open();
    },
    close() {
      this.clear();
      this.$refs.tutorialManagementDrawer.close();
    },
    clear() {
      this.tutorial.title = null;
      this.tutorial.description = null;
      this.tutorial.themes = [];
      this.add_steps = 1;
    },
  }
};

</script>